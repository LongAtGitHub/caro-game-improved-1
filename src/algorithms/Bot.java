package algorithms;

// Define the Bot interface
public interface Bot {

    /**
     * 
     * @param markToPlay 'X' or 'O'
     * @return an array of {character, indexI, indexJ}
     */
    Object[] output(Character markToPlay);
}
