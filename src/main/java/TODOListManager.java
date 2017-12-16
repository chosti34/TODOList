import java.util.Scanner;

public class TODOListManager {
    public class Controller {
        public void exit() {
            System.out.println("bye");
            running = false;
        }

        public void show() {
            System.out.println("show is not implemented");
        }

        public void addTask() {
            System.out.println("addtask is not implemented");
        }

        public void addTaskList() {
            System.out.println("addlist is not implemented");
        }

        public void deleteTask() {
            System.out.println("deletetask is not implemented");
        }

        public void deleteTaskList() {
            System.out.println("deletelist is not implemented");
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
        } catch (Exception ex) {
            System.err.println(ex.getMessage());
            ex.printStackTrace();
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
