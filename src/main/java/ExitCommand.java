import java.util.ArrayList;

public class ExitCommand extends InputCommand {
    public ExitCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        controller.exit();
    }

    @Override
    public String getName() {
        return "exit";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EXIT;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        assert args.size() == getRequiredArgsCount();
    }
}
