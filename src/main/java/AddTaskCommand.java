import java.util.ArrayList;

public class AddTaskCommand extends InputCommand {
    private ArrayList<String> args;

    public AddTaskCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 1;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        if (args.size() == 1) {
            String text = args.get(0);
            controller.addTask(args.get(0));
        } else if (args.size() == 2) {
            String text = args.get(0);
            int listId = Integer.parseUnsignedInt(args.get(1));
            controller.addTask(text, listId);
        } else {
            assert false;
        }
    }

    @Override
    public String getName() {
        return "addtask";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADDTASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.args = args;
    }
}
