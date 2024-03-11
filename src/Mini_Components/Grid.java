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
    /**
     * Grid constructor
     * @param size how big is the grid?
     * @param x where is it in x location?
     * @param y where is it in y location?
     * @param indexX Give me a name id for x-index
     * @param indexY Give me a name id for y-index
     */
    public Grid(double size, double x, double y, int indexX, int indexY) {
        super(x, y, size, size);
        this.indexX = indexX;
        this.indexY = indexY;
        this.setStrokeWidth(5);
    }

    

    public double getSquareSize(){
        return getHeight();
    }

    public Point getIndices() {
        return new Point(this.indexX, this.indexY);
    }
}
