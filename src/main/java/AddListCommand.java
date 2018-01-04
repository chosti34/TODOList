import java.util.ArrayList;

public class AddListCommand extends InputCommand {
    private ArrayList<String> args;

    public AddListCommand(final ArrayList<String> args) {
        super(args);
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
        controller.addTaskList(args.get(0));
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADD_LIST;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.args = args;
    }
}
