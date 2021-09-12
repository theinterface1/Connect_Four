import java.util.LinkedList;
import java.util.List;
import java.util.Random;

public class ComputerPlayer {

    public int makeMove(BoardState state){
        int bestScore = -1000;
        int bestMove = 1;
        for(int m=1; m<=state.getBoardWidth(); m++){
            if(state.moveIsValid(m)){
                int score = miniMax(new BoardState(state, m),0, true);
                if( score > bestScore){
                    bestScore = score;
                    bestMove = m;
                }
            }
        }
        return -bestMove;
    }
    public int miniMax(BoardState state, int depth, boolean myTurn){
        //evaluation of end state
        if(state.isFinished()){
            if(myTurn)
                return 1000;
            else
                return -1000;
        }
        if(state.isDraw())
            return 0;
        if( depth > 7){
            Random random = new Random();
            return random.nextInt() % 22;
        }
        //my turn
        if(myTurn){
            int maxEval = -1000;
            for (int m=1; m<=state.getBoardWidth(); m++) {
                int eval = miniMax( new BoardState(state, m), depth+1, false);
                maxEval = Math.max(maxEval, eval);
            }
            return maxEval;
        }
        else { //not my turn
            int minEval = 1000;
            for (int m=1; m<=state.getBoardWidth(); m++) {
                int eval = miniMax(new BoardState(state, m), depth+1, true);
                minEval = Math.min(minEval, eval);
            }
            return  minEval;
        }
    }


}
