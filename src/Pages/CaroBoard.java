package Pages;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.ui.*;
import Mini_Components.Grid;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.server.UID;

public class CaroBoard {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH = (int) screenSize.getWidth();
    private int HEIGHT = (int) screenSize.getHeight();
    private int numGridN = 3;
    private int numGridM = 3;
    private double marginLeft = WIDTH * 1/20;

    public CaroBoard(int numGridM, int numGridN) {
        this.numGridM = numGridM;
        this.numGridN = numGridN;
    }

    public GraphicsGroup UI() {
        double uiYLocation = HEIGHT/ 20;
        GraphicsGroup ui = new GraphicsGroup(marginLeft, uiYLocation);
        
        GraphicsText text = new GraphicsText("Caro Game", 0, 20);
        text.setFontSize(24);  // Set font size to 24 points
        text.setFontStyle(FontStyle.BOLD);  // Set font style to bold

        Button playAgain = new Button("Play Again");
        playAgain.setPosition(WIDTH*1/4 , 0);
        Button menu = new Button("Menu");
        menu.setPosition(WIDTH*1/4+ 150, 0);
        ui.add(text);
        ui.add(playAgain);
        ui.add(menu);
        return ui;
    }

    public GraphicsGroup mainBoard() {
        double boardHeight = HEIGHT * 15/20;
        
        double gridSize = Math.min(WIDTH /numGridN, boardHeight / numGridM);
        GraphicsGroup mainBoard = new GraphicsGroup(marginLeft, HEIGHT* 2/20);

        System.out.println(Math.min(WIDTH, boardHeight));
        System.out.println(gridSize);
        
        double locationX = 0;
        double locationY= 0;
        for (int i=0; i< numGridM; i++) {
            for (int j=0; j< numGridN; j++) {
                Grid grid= new Grid(gridSize, locationX, locationY, i, j);
                mainBoard.add(grid);
                locationX+= gridSize;
            }
            locationY+=gridSize;
            locationX =0;
        }
        // debug
        // Grid grid = new Grid(gridSize, locationX, locationY, 0, 0);
        // mainBoard.add(grid);
        // Grid grid2 = new Grid(gridSize, locationX+gridSize, locationY, 0, 1);
        // System.out.println(grid2.getX() == locationX+gridSize);
        // mainBoard.add(grid2);



        return mainBoard;

        
    }

    public void displayComplete() {  
        CanvasWindow canva = new CanvasWindow("Caro Game", WIDTH, HEIGHT); 
        canva.add(this.UI());
        canva.add(this.mainBoard());
        canva.draw();
    }

    public static void main(String[] args) {
        CaroBoard game = new CaroBoard(12, 15);
        game.displayComplete();
    }
}
