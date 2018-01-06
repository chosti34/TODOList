package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class LoadCommand extends InputCommand {
    private String filePath;

    public LoadCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 1;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.load(filePath);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.LOAD;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        filePath = args.get(0);
    }
}
