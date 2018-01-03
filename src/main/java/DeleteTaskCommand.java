import java.util.ArrayList;

public class DeleteTaskCommand extends InputCommand {
    private ArrayList<String> args;

    public DeleteTaskCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        int taskId = Integer.parseUnsignedInt(args.get(0));
        int listId = Integer.parseUnsignedInt(args.get(1));
        controller.deleteTask(taskId, listId);
    }

    @Override
    public String getName() {
        return "deletetask";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.DELETETASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.args = args;
    }
}
