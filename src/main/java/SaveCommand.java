import java.util.ArrayList;

public class SaveCommand extends InputCommand {
    private String filePath;

    public SaveCommand(final ArrayList<String> args) {
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
        controller.save(filePath);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.SAVE;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        filePath = args.get(0);
    }
}
