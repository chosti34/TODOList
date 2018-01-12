import java.util.ArrayList;
import java.util.Scanner;
import java.io.*;


public class TODOListManager {
    public class Controller {
        public void exit() {
            System.out.println("bye");
            running = false;
        }

        public void show() {
            for (TaskList list : taskLists) {
                System.out.println("TaskList #" + list.getId() + ":");
                list.print();
            }
        }

        public void addTask(final String text) {
            if (taskLists.isEmpty()) {
                System.out.println("Error: no any TaskList to insert at");
                return;
            }
            TaskList lastInsertedTaskList = taskLists.get(taskLists.size() - 1);
            lastInsertedTaskList.addTask(text);
            System.out.println("Task \"" + text + "\" added to TaskList #" + lastInsertedTaskList.getId());
        }

        public void addTask(final String text, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("Error: TaskList #" + listId + " doesn't exists");
                return;
            }
            list.addTask(text);
            System.out.println("Task \"" + text + "\" added to TaskList #" + listId);

        }

        public void editTask(int listId, int taskId, final String newTask) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("TaskList #" + listId + " not found");
                return;
            }
            list.removeTask(taskId);
            list.replaceTask(newTask, taskId);
        }



        public void addTaskList() {
            taskLists.add(new TaskList(listIdToInsert));
            System.out.println("TaskList #" + listIdToInsert + " added");
            ++listIdToInsert;
        }

        public void deleteTask(int taskId, int listId) {
            TaskList list = getListById(listId);
            if (list == null) {
                System.out.println("TaskList #" + listId + " not found");
                return;
            }
            if (list.removeTask(taskId)) {
                System.out.println("Task " + taskId + " removed from TaskList #" + listId);
            } else {
                System.out.println("Task " + taskId + " not found at TaskList #" + listId);
            }
        }

        public void deleteTaskList(int listId) {
            TaskList taskList = getListById(listId);
            if (taskList == null) {
                System.out.println("Error: can't find TaskList #" + listId);
                return;
            }
            taskLists.remove(taskList);
            System.out.println("TaskList #" + taskList.getId() + " deleted successfully");
        }
    }

    private InputCommandParser parser;
    private boolean running;
    private Controller controller;
    private int listIdToInsert;
    private ArrayList<TaskList> taskLists;

    public TODOListManager() {
        parser = new InputCommandParser();
        running = true;
        controller = new Controller();
        listIdToInsert = 1;
        taskLists = new ArrayList<>();
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
