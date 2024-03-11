import org.junit.Test;
import static org.junit.Assert.*;

public class GridTest {
   
    @Test
    public void testConstructor() {
        Grid grid = new Grid(10, 20, 5, 5);
        assertEquals(10, grid.getWidth(), 0.001); // Check width
        assertEquals(20, grid.getHeight(), 0.001); // Check height
        assertEquals(5, grid.getX(), 0.001); // Check x coordinate
        assertEquals(5, grid.getY(), 0.001); // Check y coordinate
    }
   
    @Test
    public void testGetMyX() {
        Grid grid = new Grid(10, 20, 5, 5);
        assertEquals(5, grid.getMyX(), 0.001); // Check getMyX method
    }

    @Test
    public void testGetMyY() {
        Grid grid = new Grid(10, 20, 5, 5);
        assertEquals(5, grid.getMyY(), 0.001); // Check getMyY method
    }

    // Add more test methods for other functionalities as needed
}
