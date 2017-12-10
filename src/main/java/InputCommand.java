import java.util.ArrayList;

public abstract class InputCommand {
    public InputCommand(final ArrayList<String> args) {
        if (args.size() != getRequiredArgsCount()) {
            throw new IllegalArgumentException("illegal arguments count");
        }
        setArguments(args);
    }

    public abstract int getRequiredArgsCount();
    public abstract void execute(TODOListManager.Controller controller);
    public abstract String getName();
    public abstract InputCommandType getType();
    protected abstract void setArguments(final ArrayList<String> args);
}
