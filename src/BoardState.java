import java.lang.Math;
import java.util.Arrays;

public class BoardState {
    private char[][] data;
    int movCol, movRow;
    char playerSym;
    int boardHeight = 6;
    int boardWidth = 7;
    int numberOfMoves = 0;

    public BoardState(BoardState previousState, int move){
        if(previousState == null){ // if no previous state initialize an empty board
            data = new char[boardWidth][boardHeight];
            for(int i=0; i < boardWidth; i++)
                for(int j=0; j < boardHeight; j++)
                    data[i][j] = '-';
            return;
        }

        numberOfMoves = previousState.numberOfMoves + 1;
        data = previousState.getData();
        movCol = Math.abs(move) - 1; //0-6 position in array

        //which player made the move?
        if(move > 0 )
            playerSym = 'O';
        else
            playerSym = 'X';
        //change the lowest '-' in that row to that player's symbol
        for(int i=0; i<boardHeight; i++){
            if(data[movCol][i] == '-' ){
                data[movCol][i] = playerSym;
                movRow = i;
                break;
            }
        }
    }
    public boolean isDraw(){
        return numberOfMoves >= 42;
    }
    public boolean isFinished(){
        int numConnected = 1;

        //vertical check
        for(int i=movRow+1; i<boardHeight; i++){
            if(data[movCol][i] == playerSym )
                numConnected++;
            else
                break;
        }
        for(int i=movRow-1; i>=0; i--){
            if(data[movCol][i] == playerSym )
                numConnected++;
            else
                break;
        }
        if(numConnected >= 4)
            return true;

        //horizontal check
        numConnected = 1;
        for(int i=movCol+1; i<boardWidth; i++){
            if(data[i][movRow] == playerSym )
                numConnected++;
            else
                break;
        }
        for(int i=movCol-1; i>=0; i--){
            if(data[i][movRow] == playerSym )
                numConnected++;
            else
                break;
        }
        if(numConnected >= 4)
            return true;

        //ascending diagonal
        numConnected = 1;
        for(int i=movCol+1, j=movRow+1; i<boardWidth && j<boardHeight && i>=0 && j>=0; i++, j++){
            if(data[i][j] == playerSym )
                numConnected++;
            else
                break;
        }
        for(int i=movCol-1, j=movRow-1; i<boardWidth && j<boardHeight && i>=0 && j>=0; i--, j--){
            if(data[i][j] == playerSym )
                numConnected++;
            else
                break;
        }
        if(numConnected >= 4)
            return true;

        //descending diagonal
        numConnected = 1;
        for(int i=movCol+1, j=movRow-1; i<boardWidth && j<boardHeight && i>=0 && j>=0; i++, j--){
            if(data[i][j] == playerSym )
                numConnected++;
            else
                break;
        }
        for(int i=movCol-1, j=movRow+1; i<boardWidth && j<boardHeight && i>=0 && j>=0; i--, j++){
            if(data[i][j] == playerSym )
                numConnected++;
            else
                break;
        }
        if(numConnected >= 4)
            return true;

        return false;
    }
    public String toString(){
        StringBuilder str = new StringBuilder();
        str.append('\n');
        for(int i=1; i<=boardWidth; i++){
            str.append(i);
            str.append(' ');
        }
        str.append('\n');
        for(int i=boardHeight-1; i>=0; i--){
            for(int j=0; j<boardWidth; j++){
                str.append(data[j][i] );
                str.append(' ');
            }
            str.append('\n');
        }
        return str.toString();
    }

    public boolean moveIsValid(int pos){
        pos = Math.abs(pos);
        if(pos > boardWidth )
            return false;
        return data[pos - 1][boardHeight - 1] == '-';
    }

    public char[][] getData(){
        char[][] ret = new char[boardWidth][boardHeight];
        for(int i=0; i<boardWidth;i++){
            for(int j=0; j<boardHeight; j++){
                ret[i][j] = data[i][j];
            }
        }
        return ret;
    }

    public int getBoardWidth(){
        return  boardWidth;
    }

    public int getBoardHeight(){
        return  boardHeight;
    }
}
