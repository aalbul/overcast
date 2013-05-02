package com.xebialabs.overcast.command;

import org.junit.Test;

import static com.xebialabs.overcast.command.Command.aCommand;
import static com.xebialabs.overcast.command.CommandProcessor.atLocation;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;
import static org.junit.Assume.assumeThat;

public class CommandProcessorTest {


    @Test(expected = NonZeroCodeException.class)
    public void shouldThrowExceptionWhenCommandFailed() throws Exception {
        //Test only for UNIX
        assumeThat(System.getenv().containsKey("PATH"), is(true));
        atLocation("/tmp").run(aCommand("ls").withArguments("-wrong-argument"));
    }

    @Test
    public void shouldStoreOutput() {
        //Test only for UNIX
        assumeThat(System.getenv().containsKey("PATH"), is(true));
        CommandResponse ls = atLocation("/tmp").run(aCommand("ls"));
        assertThat(ls.getReturnCode(), is(0));
        assertThat(ls.getOutput().length() > 0, is(true));
    }


}