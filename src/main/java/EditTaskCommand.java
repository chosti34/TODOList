import java.util.ArrayList;

public class EditTaskCommand extends InputCommand {
    private ArrayList<String> args;

    public EditTaskCommand (final ArrayList<String> args) {
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
        String newTask = args.get(2);
        controller.editTask(listId, taskId, newTask);
    }

    @Override
    public String getName() {
        return "edittask";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.EDITTASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.args = args;
    }
}
