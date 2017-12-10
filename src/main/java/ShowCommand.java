import java.util.ArrayList;

public class ShowCommand extends InputCommand {
    public ShowCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getRequiredArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.show();
    }

    @Override
    public String getName() {
        return "show";
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.SHOW;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
        assert args.size() == getRequiredArgsCount();
    }
}
