import java.util.ArrayList;

public class TaskList {
    private ArrayList<Task> taskList;
    private int id;
    private int taskIdToInsert;

    public TaskList(int id) {
        taskList = new ArrayList<>();
        this.id = id;
        taskIdToInsert = 1;
    }

    public void addTask(final String text) {
        taskList.add(new Task(taskIdToInsert, text));
        ++taskIdToInsert;
    }

    public void replaceTask(final String text, int taskId) {
        taskList.add(new Task(taskId, text));
    }

    public boolean deleteTask(int taskId) {
        int foundTaskToDeleteIndex = -1;
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getId() == taskId) {
                foundTaskToDeleteIndex = i;
                break;
            }
        }
        if (foundTaskToDeleteIndex != -1) {
            taskList.remove(foundTaskToDeleteIndex);
            return true;
        }
        return false;
    }

    public boolean removeTask(int taskId) {
        int taskIndexToDelete = -1;
        for (int i = 0; i < taskList.size(); ++i) {
            Task task = taskList.get(i);
            if (task.getId() == taskId) {
                taskIndexToDelete = i;
            }
        }
        if (taskIndexToDelete != -1) {
            taskList.remove(taskIndexToDelete);
            return true;
        }
        return false;
    }

    public int getId() {
        return id;
    }

    public void print() {
        if (taskList.isEmpty()) {
            System.out.println("<empty>");
        }
        for (Task task : taskList) {
            System.out.println("Task " + task.getId() + ". Text: \"" + task.getText() +
                    "\". Status: " + task.getStatus().toString() + ".");
        }
        System.out.println();
    }
}
