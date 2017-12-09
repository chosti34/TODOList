import java.util.HashMap;
import java.util.Scanner;

public class Program {
    private static final String EXIT_COMMAND = "exit";
    private static boolean running = true;
    private static HashMap<String, Runnable> actionMap = new HashMap<String, Runnable>() {{
        put("show", () -> System.out.println("showing something"));
        put("exit", () -> running = false);
    }};

    private static void dispatchInput(final String input) {
        Runnable command = actionMap.get(input);
        if (command != null) {
            command.run();
        } else {
            System.out.println("Unknown command!");
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        while (running) {
            System.out.print("> ");
            if (scanner.hasNextLine()) {
                dispatchInput(scanner.nextLine());
            } else {
                running = false;
            }
        }
    }
}
