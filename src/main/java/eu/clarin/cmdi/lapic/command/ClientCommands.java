package eu.clarin.cmdi.lapic.command;

import eu.clarin.cmdi.lapic.conf.LapicConf;
import org.apache.commons.io.IOUtils;
import org.springframework.shell.command.annotation.Command;
import org.springframework.shell.command.annotation.Option;

import java.io.IOException;
import java.net.Authenticator;
import java.net.PasswordAuthentication;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

@Command
public class ClientCommands {

    private final LapicConf lapicConf;

    private final HttpClient  httpClient;


    public ClientCommands(LapicConf conf) {

        this.lapicConf = conf;

        Authenticator authenticator = new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {

                return new PasswordAuthentication(conf.getUser().getUsername(), conf.getUser().getPassword().toCharArray());
            }
        };

        httpClient = HttpClient
                .newBuilder()
                .authenticator(authenticator)
                .version(HttpClient.Version.HTTP_1_1)
                .build();
    }

    @Command(command = "status", description = "get latest status results for a URL")
    public String getStatus(@Option(required = true, longNames = "url", shortNames = 'u') String urlString) throws URISyntaxException, IOException, InterruptedException {

        return this.httpClient.send(
                getHttpRequest("/status", "[{\"url\":\"" + urlString + "\"}]"),
                HttpResponse.BodyHandlers.ofString()
            ).body();
    }

    @Command(command = "history", description = "get historic status results for URL")
    public String getHistory(@Option(required = true, longNames = "url", shortNames = 'u') String urlString) throws URISyntaxException, IOException, InterruptedException {

        return this.httpClient.send(
                getHttpRequest("/history", "[{\"url\":\"" + urlString + "\"}]"),
                HttpResponse.BodyHandlers.ofString()
        ).body();
    }

    @Command(command = "check", description = "upload a JSON file with URLs to check")
    public String doCheck(@Option(required = true, longNames = "file", shortNames = 'f') String file) throws URISyntaxException, IOException, InterruptedException {

        Path path = Paths.get(file);

        if (Files.notExists(path)) {

            return "File does not exist";
        }

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(this.lapicConf.getBaseUrl() + "/checkrequest"))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofFile(path))
                .build();

        return this.httpClient.send(request, HttpResponse.BodyHandlers.ofString()).body();
    }

    @Command(command = "result", description = "get either all checking results or for a specific batch ID")
    public void getResult(@Option(longNames = "id", shortNames = 'i') String batchId) throws URISyntaxException, IOException, InterruptedException {

        HttpRequest request = HttpRequest
                .newBuilder()
                .uri(new URI(this.lapicConf.getBaseUrl() + "/checkrequest" + batchId!=null?"/" + batchId:""))
                .header("Content-Type", "application/json")
                .build();

        IOUtils.copy(this.httpClient.send(request, HttpResponse.BodyHandlers.ofInputStream()).body(), System.out);
    }

    private HttpRequest getHttpRequest(String path, String body) throws URISyntaxException {

        return HttpRequest
                .newBuilder()
                .uri(new URI(this.lapicConf.getBaseUrl() + path))
                .header("Content-Type", "application/json")
                .POST(HttpRequest.BodyPublishers.ofString(body))
                .build();
    }
}
