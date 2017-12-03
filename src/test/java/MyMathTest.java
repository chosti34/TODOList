import static org.junit.Assert.*;

public class MyMathTest {
    @org.junit.Test
    public void add() throws Exception {
        int expected = 5;
        int actual = MyMath.add(2, 3);
        assertEquals(expected, actual);
    }

}