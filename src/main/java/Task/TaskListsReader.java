package Task;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import org.w3c.dom.Document;
import org.w3c.dom.NodeList;
import org.w3c.dom.Node;
import org.w3c.dom.Element;
import java.io.File;
import java.util.ArrayList;

public class TaskListsReader {
    public static ArrayList<TaskList> read(final String filePath) throws Exception {
        File file = new File(filePath);

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document document = builder.parse(file);

        document.getDocumentElement().normalize();

        ArrayList<TaskList> taskLists = new ArrayList<>();
        NodeList nList = document.getElementsByTagName("TaskList");

        for (int i = 0; i < nList.getLength(); ++i) {
            Element taskListElement = (Element)nList.item(i);

            TaskList taskList = new TaskList(taskListElement.getAttribute("name"), Integer.parseUnsignedInt(taskListElement.getAttribute("id")));
            taskLists.add(taskList);

            NodeList tasks = taskListElement.getChildNodes();
            for (int j = 0; j < tasks.getLength(); ++j) {
                Node taskNode = tasks.item(j);
                if (taskNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element taskNodeElement = (Element)taskNode;
                    Task task = new Task(Integer.parseUnsignedInt(taskNodeElement.getAttribute("id")), taskNodeElement.getTextContent());
                    task.setStatus(toStatus(taskNodeElement.getAttribute("status")));
                    taskList.toArrayList().add(task);
                }
            }
        }
        return taskLists;
    }

    private static TaskStatus toStatus(final String str) throws Exception {
        switch (str) {
            case "in progress":
                return TaskStatus.IN_PROGRESS;
            case "done":
                return TaskStatus.DONE;
            case "cancelled":
                return TaskStatus.CANCELLED;
            default:
                throw new Exception("error reading task status");
        }
    }
}
