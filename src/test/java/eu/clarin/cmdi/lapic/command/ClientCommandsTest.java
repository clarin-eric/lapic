package eu.clarin.cmdi.lapic.command;

import eu.clarin.cmdi.lapic.LapicApplication;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.shell.test.ShellAssertions;
import org.springframework.shell.test.ShellScreen;
import org.springframework.shell.test.ShellTestClient;
import org.springframework.shell.test.autoconfigure.ShellTest;
import org.springframework.test.context.ContextConfiguration;

@ShellTest
@ContextConfiguration(classes = LapicApplication.class)
class ClientCommandsTest {


    @Test
    void help(@Autowired ShellTestClient client) throws Exception {
        ShellScreen shellScreen = client.sendCommand("help");

        ShellAssertions.assertThat(shellScreen).containsText("status");
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