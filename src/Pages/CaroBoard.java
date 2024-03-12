package Pages;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsObject;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.*;
import Mini_Components.Grid;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.rmi.server.UID;
import java.util.ArrayList;
import java.util.List;


public class CaroBoard {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH = (int) screenSize.getWidth();
    private int HEIGHT = (int) screenSize.getHeight();
    private int numGridN = 3;
    private int numGridM = 3;
    private double boardStartX = WIDTH * 1/20;
    private double boardStartY = HEIGHT * 2/20;
    private Grid[][] gridArray;

    private double boardHeight;
        
    private double gridSize;



    public CaroBoard(int numGridM, int numGridN) {
        this.numGridM = numGridM;
        this.numGridN = numGridN;
        this.boardHeight = HEIGHT * 15/20;
        this.gridSize = Math.min(WIDTH /numGridN, boardHeight / numGridM);
        gridArray = new Grid[this.numGridM][this.numGridN];
    }

    public GraphicsGroup UI() {
        double uiYLocation = HEIGHT/ 20;
        GraphicsGroup ui = new GraphicsGroup(boardStartX, uiYLocation);
        
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
        
        GraphicsGroup mainBoard = new GraphicsGroup(boardStartX, boardStartY);

        // System.out.println(Math.min(WIDTH, boardHeight));
        // System.out.println(gridSize);
        
        double locationX = 0;
        double locationY= 0;
        for (int i=0; i< numGridM; i++) {
            for (int j=0; j< numGridN; j++) {
                Grid grid= new Grid(gridSize, locationX, locationY, i, j);
                gridArray[i][j] = grid;
                mainBoard.add(gridArray[i][j]);
                locationX+= gridSize;
            }
            locationY+=gridSize;
            locationX =0;
        }

       
      
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
        
        canva.onClick((event -> {
            List<Integer> indices = translatePointToGrid(event.getPosition());
            if (indices == null) { return; }
            
            Integer i = indices.get(0);
            Integer j = indices.get(1);
            System.out.println(i + " " + j);
            Boolean done = gridArray[i][j].setCharValue('X');
            System.out.println(done);
            
        }));
        
        
    }

    /**
     * 
     * @param pos position of canvas onclick mouse event
     * @return  an 2D array of [indexI, indexJ] to specify the grid
     */
    public ArrayList<Integer> translatePointToGrid(Point pos) {
        Point upperRef = new Point(this.boardStartX + numGridN * gridSize, boardStartY+ numGridM* gridSize);
        Boolean checkBoundX = boardStartX<= pos.getX() && pos.getX() <= upperRef.getX();
        Boolean checkBoundY = boardStartY <= pos.getY() && pos.getY() <= upperRef.getY();
        if (!checkBoundX || !checkBoundY) {
            // System.out.println("Out of bound clicking");
            return null;
        }

        double givenX = pos.getX(); double givenY = pos.getY();
        double lowerX = boardStartX; double lowerY = boardStartY;
        
        int i = (int) ((givenY - lowerY) / gridSize);
        int j = (int) ((givenX - lowerX) / gridSize);
        // System.out.println(gridSize);
        // System.out.println(lowerX);
        // System.out.println(lowerY);
        return new ArrayList<Integer>() {{ add(i); add(j); }};
    }

    public static void main(String[] args) {
        CaroBoard game = new CaroBoard(12, 15);
        game.displayComplete();
    }
}
