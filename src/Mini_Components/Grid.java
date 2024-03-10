package Mini_Components;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Point;

public class Grid extends Rectangle {
    private int indexX; 
    private int indexY; 
    
    // Constructor with default values
    public Grid(double size, double x, double y) {
        super(size, size, x, y);
        this.indexX = 0;
        this.indexY = 0;
    }

    // Constructor 
    public Grid(double size, double x, double y, int indexX, int indexY) {
        super(size, size, x, y);
        this.indexX = indexX;
        this.indexY = indexY;
    }

    

    public double getSquareSize(){
        return getHeight();
    }

    public Point getIndices() {
        return new Point(this.indexX, this.indexY);
    }
}
