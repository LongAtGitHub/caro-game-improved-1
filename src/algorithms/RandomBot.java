package algorithms;

import java.util.ArrayList;
import java.util.Random;

public class RandomBot  {
    // Define the DumbBot class implementing the Bot interface
    private Character[][] charArr;
    private int nrow ;
    private int ncol ;
    private ArrayList<int[]> possibleMove;

    private int outputI, outputJ;
    private char outputChar;

    public RandomBot(int nrow, int ncol) {
        this.nrow = nrow;
        this.ncol = ncol;
    }


    public int[] output(Character markToPlay, Character[][] charArr) {
        setPosssibleMove(charArr);
        Random random = new Random();
        int randomIndex = random.nextInt(possibleMove.size());
        int[] randomElement = possibleMove.get(randomIndex);
        // int indexI = randomElement[0];
        // int indexJ = randomElement[1];
        // return new int[]{indexI, indexJ};
        return randomElement;
    }

    private void setPosssibleMove(Character[][] charArr) {
        possibleMove = new ArrayList<int[]>();
        for (int i = 0; i < nrow; i++) {
            for (int j = 0; j < ncol; j++) {
                if (charArr[i][j] == null ){
                    int[] indices = new int[] {i,j};
                    possibleMove.add(indices);
                } 
            }
        } 
    }


    
}
