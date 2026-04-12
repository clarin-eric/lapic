package eu.clarin.cmdi.lapic.command;

import eu.clarin.cmdi.lapic.LapicApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.annotation.DirtiesContext;

import java.util.concurrent.TimeUnit;

import static org.awaitility.Awaitility.await;

@ShellTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class ClientCommandsTest {

    private final ShellTestClient client;

    @Autowired
    public ClientCommandsTest(ShellTestClient client) {

        this.client = client;
    }

    @Test
    void test() {
        ShellTestClient.NonInteractiveShellSession session = client
                .nonInterative("help")
                .run();



    //    await().atMost(5, TimeUnit.SECONDS).untilAsserted(() -> {session.screen().lines().forEach(System.out::println);});


        await().atMost(2, TimeUnit.SECONDS).untilAsserted(() -> {
            ShellAssertions.assertThat(session.screen())
                    .containsText("status");
        });
    }

    @Test
    void getStatus() {
    }

    @Test
    void getHistory() {
    }

    @Test
    void doCheck() {
    }

    @Test
    void getResult() {
    }
}