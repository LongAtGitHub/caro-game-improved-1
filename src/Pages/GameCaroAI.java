package Pages;

import java.util.List;

import algorithms.RandomBot;
import edu.macalester.graphics.CanvasWindow;
import edu.macalester.graphics.events.MouseButtonEvent;

public class GameCaroAI extends GameCaro{
    protected RandomBot randoBot;
    private Boolean humanTurn = true;

    public GameCaroAI(int numGridM, int numGridN) {
        super(numGridM, numGridN);
        this.randoBot = new RandomBot(numGridM, numGridN);
    }

    public GameCaroAI() {
        super();
        this.randoBot = new RandomBot(numGridM, numGridN);
    }

    @Override
    protected void gameProgress(CanvasWindow canva) {
        canva.onClick((event -> {
            // end game
            if (gameState == 1 || gameState == -1 || fillUpNum >= numGridM * numGridN) {
                return;
            }
        
            if (humanTurn) {
                Boolean hasHumanCompletedTurn = humanPlay(event);
                if (!hasHumanCompletedTurn) {
                    // Human player didn't complete the turn, exit early
                    return;
                }
                humanTurn = !humanTurn;
        
                // Wait 1 second before the bot's move
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
        
                if (!humanTurn) {
                    botPlay();
                    humanTurn = !humanTurn;
                }
            }
        
            changeGameStatusUIVal();
        }));
    }

    protected Boolean humanPlay(MouseButtonEvent event) {
        List<Integer> indices = translatePointToGrid(event.getPosition());
        if (indices == null) { return false;}
        
        Integer i = indices.get(0); Integer j = indices.get(1);
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        { 
            charArray[i][j] = currentTurn;
            setXOImagePath();
            fillUpNum+=1;
            gameState = winChecker.output(charArray, currentTurn, i, j);
            setNextTurnChar();
            return true;
        }
        return false;
    }

    protected Boolean botPlay() {
        int[] botOuput = randoBot.output(currentTurn, charArray);
        int i = botOuput[0]; int j = botOuput[1];
        Boolean markCharacterSuccess = gridArray[i][j].setCharValue(currentTurn);
        if (markCharacterSuccess) 
        { 
            charArray[i][j] = currentTurn;
            setXOImagePath();
            fillUpNum+=1;
            gameState = winChecker.output(charArray, currentTurn, i, j);
            setNextTurnChar();
            return true;
        }
        return true;
    }

    public static void main(String[] args) {
        GameCaroAI game = new GameCaroAI();
        game.gameComplete();
    }
}

