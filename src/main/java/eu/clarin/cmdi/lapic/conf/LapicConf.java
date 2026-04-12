package eu.clarin.cmdi.lapic.conf;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

@ConfigurationProperties(prefix = "lapic")
@Data
public class LapicConf {

    private String baseUrl;

    private User user = new User();

    @Data
    public class User {

        private String username;
        private String password;
    }
}

