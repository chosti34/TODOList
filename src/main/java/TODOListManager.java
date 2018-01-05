import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.Scanner;

public class TODOListManager {
    public class Controller {
        /**
         * Команда для выхода из программы
         */
        public void onExit() {
            System.out.println("bye");
            running = false;
        }

        /**
         * Команда для вывода списков задач с их содержимым в System.out
         */
        public void onShow() {
            if (taskLists.isEmpty()) {
                System.out.println("nothing to show...");
                return;
            }
            for (TaskList taskList : taskLists) {
                System.out.println("TaskList #" + taskList.getId() + " \"" + taskList.getName() + "\":");
                ArrayList<Task> tasks = taskList.toArrayList();
                if (tasks.isEmpty()) {
                    System.out.println("\tempty...");
                    continue;
                }
                for (Task task : tasks) {
                    System.out.println("\tTask " + task.getId() + ". Text: \"" + task.getText() + "\". Status: " + task.getStatus().toString() + ".");
                }
            }
        }

        /**
         * Обработчик для команды `help`
         */
        public void onHelp() {
            InputCommandType[] commandTypes = InputCommandType.values();
            if (commandTypes.length == 0) {
                System.out.println("There is no available commands. Developers are too lazy!");
                return;
            }
            System.out.println("List of available commands:");
            int index = 1;
            for (InputCommandType commandType : commandTypes) {
                System.out.println(index + ". " + commandType.toString() + ". " + commandType.getHelpMessage() + ".");
                ++index;
            }
        }

        /**
         * Добавляет задачу в последний добавленный список, если таковой имеется.
         * Выводит в System.out информацию о выполненной операции
         */
        public void addTaskToLastInsertedList(final String text) {
            if (taskLists.isEmpty()) {
                System.out.println("Error: no any TaskList to insert at");
                return;
            }
            TaskList lastInsertedTaskList = taskLists.get(taskLists.size() - 1);
            if (lastInsertedTaskList.addTask(text)) {
                System.out.println("Task \"" + text + "\" added to TaskList #" + lastInsertedTaskList.getId());
            } else {
                System.out.println("Error: TaskList #" + lastInsertedTaskList.getId() + " already have task with text \"" + text + "\"");
            }
        }

        /**
         * Добавляет задачу в список с заданным идентификатором listId, если таковой имеется.
         * Выводит в System.out информацию о выполненной операции
         */
        public void addTask(final String text, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("Error: TaskList #" + listId + " doesn't exists");
                return;
            }
            if (list.addTask(text)) {
                System.out.println("Task \"" + text + "\" added to TaskList #" + list.getId());
            } else {
                System.out.println("Error: TaskList #" + list.getId() + " already have task with text \"" + text + "\"");
            }
        }

        /**
         * Команда для добавления нового списка задач
         */
        public void addTaskList(final String name) {
            taskLists.add(new TaskList(name, listIdToInsert));
            System.out.println("TaskList #" + listIdToInsert + " \"" + taskLists.get(taskLists.size() - 1).getName() + "\" added");
            ++listIdToInsert;
        }

        /**
         * Удаление задачи с заданным taskId из списка с идентификатором listId
         */
        public void deleteTask(int taskId, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("TaskList #" + listId + " not found");
                return;
            }
            if (list.deleteTask(taskId)) {
                System.out.println("Task " + taskId + " removed from TaskList #" + listId);
            } else {
                System.out.println("Task " + taskId + " not found at TaskList #" + listId);
            }
        }

        /**
         * Удаление списка задач с заданным идентификатором
         */
        public void deleteTaskList(int listId) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return;
            }
            taskLists.remove(taskList);
            System.out.println("TaskList \"" + taskList.getName() + "\" #" + taskList.getId() + " deleted successfully");
        }

        public void save(final String path) {
            try {
                TaskListsSerializer.serialize(taskLists, path);
                System.out.println("All task lists has been saved to " + path);
            } catch (Exception ex) {
                System.out.println("Error: failed to save tasks - " + ex.getMessage());
            }
        }

        public void load(final String path) {
            try {
                taskLists = TaskListsReader.read(path);
                System.out.println("All task lists loaded from " + path);
            } catch (Exception ex) {
                System.out.println("Error: failed to load tasks - " + ex.getMessage());
            }
        }
    }

    private InputCommandParser parser = new InputCommandParser();
    private boolean running = true;
    private Controller controller = new Controller();
    private int listIdToInsert = 1;
    private ArrayList<TaskList> taskLists = new ArrayList<>();

    public Controller getController() {
        return controller;
    }

    public void doMainLoop(InputStream stream) throws Exception {
        System.out.println("Welcome to TODO-List! Please, write some commands...\nOr type `help` for help.");
        Scanner scanner = new Scanner(stream);
        while (running) {
            System.out.print("> ");
            if (scanner.hasNextLine()) {
                dispatchInput(scanner.nextLine());
            } else {
                running = false;
            }
        }
    }

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

    private TaskList getListById(int id) {
        for (TaskList list : taskLists) {
            if (list.getId() == id) {
                return list;
            }
        }
        return null;
    }
}
