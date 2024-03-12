package Pages;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.FontStyle;
import edu.macalester.graphics.GraphicsGroup;
import edu.macalester.graphics.GraphicsText;
import edu.macalester.graphics.Point;
import edu.macalester.graphics.ui.*;
import Mini_Components.Grid;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import algorithms.*;
public class CaroBoard {
    Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
    private int WIDTH = (int) screenSize.getWidth();
    private int HEIGHT = (int) screenSize.getHeight();
    private int numGridN = 3;
    private int numGridM = 3;
    private double boardStartX = WIDTH * 1/20;
    private double boardStartY = HEIGHT * 2/20;
    private Grid[][] gridArray;
    private Character[][] charArray; // support array

    private double boardHeight;
        
    private double gridSize;

    private Character currentTurn=  'X';
    private CheckWin winChecker; 
    private int gameState = 0; private int fillUpNum=0;



    public CaroBoard(int numGridM, int numGridN) {
        if (numGridM<3 || numGridN<3) { 
            System.err.println("num grid too small");
            return;
        }

        this.numGridM = numGridM;
        this.numGridN = numGridN;
        this.boardHeight = HEIGHT * 15/20;
        // this.gridSize = Math.min(WIDTH /numGridN, boardHeight / numGridM);
        this.gridSize = WIDTH/ numGridN *0.8;
        gridArray = new Grid[this.numGridM][this.numGridN];
        

        // init checkwin object and support array to checkwin
        charArray = new Character[numGridM][numGridN];
        Integer targetCount = 3;
        if (numGridM>3 && numGridN>3)  targetCount = 5;
        System.out.println(targetCount);
        winChecker = new CheckWin(numGridM,numGridN,targetCount);
        
    }

    public GraphicsGroup UI() {
        double uiYLocation = HEIGHT/ 20;
        GraphicsGroup ui = new GraphicsGroup(boardStartX, uiYLocation);
        
        GraphicsText text = new GraphicsText("Caro Game", 0, 20);
        text.setFontSize(24);  // Set font size to 24 points
        text.setFontStyle(FontStyle.BOLD);  // Set font style to bold

        Button playAgain = new Button("Play Again");
        playAgain.setPosition(WIDTH*1/2.5 , 0);
        Button menu = new Button("Menu");
        menu.setPosition(WIDTH*1/2.5+ 100, 0);

        GraphicsText turn=  new GraphicsText("Current Turn",WIDTH*1/5,20);
        ui.add(text);
        ui.add(turn);
        ui.add(playAgain);
        ui.add(menu);

        return ui;
    }

    public GraphicsGroup getInitGameBoard() {
        GraphicsGroup getInitGameBoard = new GraphicsGroup(boardStartX, boardStartY);
        double locationX = 0;
        double locationY= 0;
        for (int i=0; i< numGridM; i++) {
            for (int j=0; j< numGridN; j++) {
                Grid grid= new Grid(gridSize, locationX, locationY, i, j);
                charArray[i][j] = null;
                gridArray[i][j] = grid;
                getInitGameBoard.add(gridArray[i][j]);
                locationX+= gridSize;
            }
            locationY+=gridSize;
            locationX =0;
        }
        return getInitGameBoard;
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

    public void setNextTurnChar() {
        if (this.currentTurn=='X') { currentTurn = 'O';}
        else if (this.currentTurn=='O') { currentTurn = 'X';}
        return;
    }

    public void gameProgress(CanvasWindow canva) {
        canva.onClick((event -> {
            if (gameState == 1 || gameState== -1 || fillUpNum >= numGridM*numGridN) {
                System.out.println("The game is already won");
                return;
            }
            List<Integer> indices = translatePointToGrid(event.getPosition());
            if (indices == null) { return; }
            
            Integer i = indices.get(0); Integer j = indices.get(1);
            Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
            if (markCharacterSuccess) 
            { 
                charArray[i][j] = currentTurn;
                fillUpNum+=1;
                gameState = winChecker.output(charArray, currentTurn, i, j);
                setNextTurnChar();
            }
        }));   
    }

    public void printCharArray() {
        for (int i = 0; i < numGridM; i++) {
            for (int j = 0; j < numGridN; j++) {
                System.out.print(charArray[i][j] + " ");
            }
            System.out.println(); // Move to the next line after printing each row
        }
    }

    public void gameComplete() {  
        // add components
        CanvasWindow canva = new CanvasWindow("Caro Game", WIDTH, HEIGHT); 
        canva.add(this.UI());
        canva.add(this.getInitGameBoard());
        canva.draw();
        gameProgress(canva); 
    }

    public static void main(String[] args) {
        CaroBoard game = new CaroBoard(12, 20);
        game.gameComplete();
    }
}
