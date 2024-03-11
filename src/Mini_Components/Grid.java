package Mini_Components;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.Rectangle;
import edu.macalester.graphics.Image;
import edu.macalester.graphics.Point;

public class Grid extends GraphicsGroup {
    private Rectangle rectangle;
    private Image image;
    private double imageMargin;
    private int indexX; 
    private int indexY; 
    private Character value;

    // public Grid(double size, double x, double y) {
    //     this(size, x, y, 0, 0);
    // }

    public Grid(double size, double x, double y, int indexX, int indexY) {
        super(x, y);
        this.indexX = indexX;
        this.indexY = indexY;

        this.rectangle = new Rectangle(0, 0, size, size);
        this.rectangle.setStrokeWidth(5);
        this.add(rectangle);

        this.imageMargin  = size/10;
        this.setCharValue('X');
        System.out.println("wrasfas");
    }

    public double getSquareSize() {
        return rectangle.getHeight();
    }

    public Point getIndices() {
        return new Point(this.indexX, this.indexY);
    }

    public Boolean setCharValue(Character c) {
        if (this.value != null) {return false;}
        if (c!='X' || c!='O') {return false;}
        if (c=='X') {
            this.value = 'X';
            this.image = new Image(imageMargin, imageMargin, "img/X.png");  
        }
        else if (c=='O') {
            this.value = 'O';
            this.image = new Image(imageMargin, imageMargin, "img/O.png");  
        }
        this.add(image);
        System.out.println("Set char value and add picture");
        return true;
    }

    public static void main(String[] args) {
        Grid grid = new Grid(0, 0, 0, 0, 0);
        grid.setCharValue('X');
    }
}
