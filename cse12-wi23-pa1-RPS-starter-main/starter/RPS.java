/* 
  Name: Jiarui Song
  Email: jis052@ucsd.edu
  PID: A17460928
  Sources Used: Java Documentation, PA1 Write-up
   
  This file is for CSE 12 PA1 in Winter 2023,
  it is the main class of RPS of some basic functionalities.
*/
import java.util.Scanner;

public class RPS extends RPSAbstract {
    // Messages for the game.
    protected static final String GAME_NOT_IMPLEMENTED =
            "Game not yet implemented.";

    /**
     * Construct a new instance of RPS with the given possible moves.
     * @param moves all possible moves in the game.
     */
    public RPS(String[] moves) {
        this.possibleMoves = moves;
        this.playerMoves = new String[MAX_GAMES];
        this.cpuMoves = new String[MAX_GAMES];
    }


    public int determineWinner(String playerMove, String cpuMove) {
        // TODO
    	int position_player = -1;
        int position_cpu = -1;
        for(int i=0; i<possibleMoves.length; i++){
            if(playerMove.equals(possibleMoves[i])){
                position_player = i;
            }
            
            if(cpuMove.equals(possibleMoves[i])){
                position_cpu = i;
            }
        }
        if(position_player == -1 || position_cpu == -1) {
        	return -1;
        }
        else if (position_player == 0 
                && position_cpu == possibleMoves.length -1) {
        	return 2;
        }
        else if (position_cpu == 0 
                && position_player == possibleMoves.length -1) {
        	return 1;
        }
        else if(position_player == position_cpu-1){
            return 1; 
        }else if (position_player-1 == position_cpu){
            return 2;
        }else{
            return 0;
        }
    }

    public static void main(String[] args) {
        // If command line args are provided use those as the possible moves
        String[] moves = new String[args.length];
        if (args.length >= MIN_POSSIBLE_MOVES) {
            for (int i = 0; i < args.length; i++) {
                moves[i] = args[i];
            }
        } else {
            moves = RPS.DEFAULT_MOVES;
        }
        
        // Create new game and scanner
        RPS game = new RPS(moves);
        Scanner in = new Scanner(System.in);
        String input ;
        
        System.out.println(PROMPT_MOVE);
        
        while(true){
        	input = in.next();
        	String cpuMove = game.genCPUMove();
	        if (game.isValidMove(input) && game.isValidMove(cpuMove)) {
	        	game.playRPS(input, cpuMove);
	        }
	        else if (input.equals("q")) {
	        	game.displayStats();
	        	break;
	        }
	        else {
	        	System.out.println(INVALID_INPUT);
	        }
	        System.out.println(PROMPT_MOVE);
        }

        // While user does not input "q", play game
        //System.out.println(GAME_NOT_IMPLEMENTED);

        // TODO: Insert the code to play the game. 
        // See the writeup for an example of the game play.
        // Hint: call the methods we/you have already written 
        // to do most of the work! And don't forget Javadoc.

        in.close();
    }
}
