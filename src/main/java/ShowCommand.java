import java.util.ArrayList;

public class ShowCommand extends InputCommand {
    public ShowCommand(final ArrayList<String> args) {
        super(args);
    }

    @Override
    public int getMinRequiredArgsCount() {
        return 0;
    }

    @Override
    public int getMaxOptionalArgsCount() {
        return 0;
    }

    @Override
    public void execute(TODOListManager.Controller controller) {
        controller.onShow();
    }

    @Override
    public InputCommandType getType() {
        return InputCommandType.SHOW;
    }

    @Override
    protected void setArguments(final ArrayList<String> args) {
    }
}
