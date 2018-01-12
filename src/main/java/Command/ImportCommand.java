package Command;

import Task.TODOListManager;

import java.util.ArrayList;

public class ImportCommand extends InputCommand {
    private String filePath;

    public ImportCommand(final ArrayList<String> args) {
        super(args);
        setArguments(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 0;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        if (filePath == null) {
            controller.load("lists");
            return;
        }
        controller.load(filePath);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.IMPORT;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        if (args.isEmpty()) {
            return;
        }
        filePath = args.get(0);
    }
}
