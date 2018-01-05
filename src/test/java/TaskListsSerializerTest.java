import org.junit.Test;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;

import static org.junit.Assert.*;

public class TaskListsSerializerTest {
    @Test
    public void serialize() throws Exception {
        ArrayList<TaskList> taskLists = new ArrayList<>();
        TaskList list1 = new TaskList("list1_name", 1);
        TaskList list2 = new TaskList("list2_name", 2);
        TaskList emptyList = new TaskList("empty_name", 3);
        list1.addTask("task1");
        list2.addTask("task2");
        taskLists.add(list1);
        taskLists.add(list2);
        taskLists.add(emptyList);
        TaskListsSerializer.serialize(taskLists, "temp.xml");
        String content = getFileContent("temp.xml");
        assertEquals("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\r\n" +
                "<Root>\r\n" +
                "    <TaskList id=\"1\" name=\"list1_name\">\r\n" +
                "        <Task id=\"1\" status=\"in progress\">task1</Task>\r\n" +
                "    </TaskList>\r\n" +
                "    <TaskList id=\"2\" name=\"list2_name\">\r\n" +
                "        <Task id=\"1\" status=\"in progress\">task2</Task>\r\n" +
                "    </TaskList>\r\n" +
                "    <TaskList id=\"3\" name=\"empty_name\"/>\r\n" +
                "</Root>\r\n", content);
    }

    private String getFileContent(final String filePath) throws Exception {
        byte[] encoded = Files.readAllBytes(Paths.get(filePath));
        return new String(encoded, Charset.defaultCharset());
    }
}
