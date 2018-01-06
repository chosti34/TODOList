package Command;

import Command.InputCommandType;
import Command.LoadCommand;
import Task.TODOListManager;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.*;

public class LoadCommandTest {
    private LoadCommand command = new LoadCommand(new ArrayList<String>() {{ add("target/generated-sources/path"); }});

    @Test
    public void getMinRequiredArgsCount() throws Exception {
        assertEquals(1, command.getMinRequiredArgsCount());
    }

    @Test
    public void getMaxOptionalArgsCount() throws Exception {
        assertEquals(1, command.getMaxOptionalArgsCount());
    }

    @Test
    public void execute() throws Exception {
        command.execute(new TODOListManager().getController());
    }

    @Test
    public void getType() throws Exception {
        assertEquals(InputCommandType.LOAD, command.getType());
    }

    @Test
    public void setArguments() throws Exception {
    }
}
