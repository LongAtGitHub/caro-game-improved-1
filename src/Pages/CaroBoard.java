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
    private int numGrid=3;

    public CaroBoard(int numGrid) {
        this.numGrid = numGrid;
    }

    public GraphicsGroup UI() {
        System.out.println(this.numGrid);
        double elemHeight = HEIGHT/ 20;
        GraphicsText text = new GraphicsText("Caro Game", WIDTH*1/5, elemHeight+10);
        text.setFontSize(24);  // Set font size to 24 points
        text.setFontStyle(FontStyle.BOLD);  // Set font style to bold

        Button playAgain = new Button("Play Again");
        playAgain.setPosition(WIDTH*3/4.5, elemHeight);
        Button menu = new Button("Menu");
        menu.setPosition(WIDTH*3/4, elemHeight);
        GraphicsGroup ui = new GraphicsGroup(0, 0);
        ui.add(text);
        ui.add(playAgain);
        ui.add(menu);
        return ui;
    }

    public GraphicsGroup mainBoard() {
        double boardHeight = HEIGHT/ 15;
        double gridSize = Math.min(WIDTH, boardHeight) / numGrid;
        GraphicsGroup mainBoard = new GraphicsGroup(0, boardHeight);
        double locationX = 0;
        double locationY= 0;
        for (int i=0; i< numGrid; i++) {
            for (int j=0; j< numGrid; j++) {
                Grid grid= new Grid(gridSize, locationX, locationY, i, j);
                mainBoard.add(grid);
                locationX+= gridSize;
            }
            locationY+=gridSize;
        }
        return mainBoard;
    }

    public void displayComplete() {  
        CanvasWindow canva = new CanvasWindow("Caro Game", WIDTH, HEIGHT);
        // GraphicsGroup ui = this.UI();  
        canva.add(this.UI());
        canva.add(this.mainBoard());
        canva.draw();
    }

    public static void main(String[] args) {
        CaroBoard game = new CaroBoard(12);
        game.displayComplete();
    }
}
