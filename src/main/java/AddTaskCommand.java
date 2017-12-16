import java.util.ArrayList;

public class AddTaskCommand extends InputCommand {
    public AddTaskCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.addTask();
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
    }
}
