import java.util.ArrayList;

public class DeleteListCommand extends InputCommand {
    private ArrayList<String> args;

    public DeleteListCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        int listId = Integer.parseUnsignedInt(args.get(0));
        controller.deleteTaskList(listId);
    }

    @Override
    public String getName() {
        return "deletelist";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.DELETELIST;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.args = args;
    }
}
