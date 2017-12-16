import java.util.ArrayList;

public class DeleteTaskCommand extends InputCommand {
    public DeleteTaskCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.deleteTask();
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
    }
}
