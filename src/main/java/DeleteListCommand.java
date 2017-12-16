import java.util.ArrayList;

public class DeleteListCommand extends InputCommand {
    public DeleteListCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.deleteTaskList();
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
    }
}
