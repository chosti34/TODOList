import java.util.ArrayList;
import java.util.HashMap;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class InputCommandParser {
    private static final int MATCH_DATA_GROUP_ID = 1;
    private static final int FIRST_ELEMENT_ID = 0;

    private Pattern pattern;
    private HashMap<String, InputCommandType> commandTypeMap;

    public InputCommandParser() {
        pattern = Pattern.compile("([^\"]\\S*|\".*\"\\S*)\\s*");
        commandTypeMap = new HashMap<String, InputCommandType>() {{
            put("show", InputCommandType.SHOW);
            put("exit", InputCommandType.EXIT);
            put("addlist", InputCommandType.ADDLIST);
            put("addtask", InputCommandType.ADDTASK);
            put("deletelist", InputCommandType.DELETELIST);
            put("deletetask", InputCommandType.DELETETASK);
            put("edittask", InputCommandType.EDITTASK);
        }};
    }

    public InputCommand parse(final String input) throws Exception {
        final ArrayList<String> tokens = tokenize(input);
        if (tokens.isEmpty()) {
            throw new IllegalArgumentException("empty input");
        }

        final InputCommandType type = commandTypeMap.get(tokens.get(FIRST_ELEMENT_ID));
        if (type == null) {
            throw new IllegalArgumentException("unknown command");
        }

        return getCommand(type, spliceToOnlyArguments(tokens));
    }

    private InputCommand getCommand(final InputCommandType type, final ArrayList<String> args) throws Exception {
        switch (type) {
            case ADDLIST:
                return new AddListCommand(args);
            case DELETELIST:
                return new DeleteListCommand(args);
            case ADDTASK:
                return new AddTaskCommand(args);
            case DELETETASK:
                return new DeleteTaskCommand(args);
            case SHOW:
                return new ShowCommand(args);
            case EXIT:
                return new ExitCommand(args);
            case EDITTASK:
                return new EditTaskCommand(args);
            default:
                throw new Exception("default branch should be unreachable");
        }
    }

    private ArrayList<String> spliceToOnlyArguments(final ArrayList<String> fullCommandInput) {
        return new ArrayList<String>(fullCommandInput.subList(1, fullCommandInput.size()));
    }

    private ArrayList<String> tokenize(final String input) {
        ArrayList<String> matches = new ArrayList<>();
        Matcher matcher = pattern.matcher(input);
        while (matcher.find()) {
            matches.add(matcher.group(MATCH_DATA_GROUP_ID).replaceAll("\"", ""));
        }
        return matches;
    }
}
