import java.util.ArrayList;
import java.util.Optional;

public class AddTaskCommand extends InputCommand {
    private ArrayList<String> args;
    private Optional<Integer> listId;
    private String text;

    public AddTaskCommand(final ArrayList<String> args) {
        super(args);
        listId = Optional.empty();
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 1;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 2;
    }

    @Override
    public void execute(final TODOListManager.Controller controller) {
        if (listId.isPresent()) {
            controller.addTask(text, listId.get());
            return;
        }
        controller.addTaskToLastInsertedList(text);
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.ADD_TASK;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        this.text = args.get(0);
        if (args.size() > 1) {
            this.listId = Optional.of(Integer.parseUnsignedInt(args.get(1)));
        }
    }
}
