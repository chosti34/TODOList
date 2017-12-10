import java.util.Scanner;

public class TODOListManager {
    public class Controller {
        public void exit() {
            System.out.println("bye");
            running = false;
        }

        public void show() {
            System.out.println("show not implemented");
        }

        public void addTask() {

        }

        public void addTaskList() {

        }
    }

    private InputCommandParser parser = new InputCommandParser();
    private boolean running = true;
    private Controller controller = new Controller();

    private void dispatchInput(final String input) {
        try {
            InputCommand command = parser.parse(input);
            command.execute(controller);
        } catch (IllegalArgumentException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public void doMainLoop() {
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
