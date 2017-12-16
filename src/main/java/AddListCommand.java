import java.util.ArrayList;

public class AddListCommand extends InputCommand {
    public AddListCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.addTaskList();
    }

    @Override
    public String getName() {
        return "addlist";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADDLIST;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
    }
}
