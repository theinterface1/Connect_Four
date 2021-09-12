import java.io.*;

public class ConnectFour {
    private BoardState boardState;
    private BufferedReader reader;
    private boolean playerTurn = true;

    private ComputerPlayer comp;

    ConnectFour(){
        this(null);
    }

    ConnectFour( ComputerPlayer comp){
        this.comp = comp;
        boardState = new BoardState(null, 0);
        reader = new BufferedReader( new InputStreamReader(System.in));
        System.out.println(boardState.toString());
    }

    public void gameLoop(){
        while(!boardState.isFinished()){
            if(boardState.isDraw())
                break;
            if(playerTurn || comp == null)
                turnTaken(prompTurn());
            else
                turnTaken( comp.makeMove(boardState));

            System.out.println(boardState.toString());
        }
        System.out.println("Game Over");
        if(boardState.isDraw())
            System.out.println("DRAW!");
        else if(playerTurn)
            System.out.println("X WINS!");
        else
            System.out.println("O WINS!");

    }

    public int prompTurn() {
        int input = 0;
        try {
            input = Integer.parseInt(reader.readLine());
        } catch (IOException e) {
            e.printStackTrace();
        }
        if(input < 1 || input > boardState.getBoardWidth() )
            throw new IllegalArgumentException();
        if(playerTurn)
            return input;
        else
            return -input;
    }

    public void turnTaken(int position){
        boardState = new BoardState(boardState, position);
        playerTurn = !playerTurn;
    }

    public static void main(String[] args){
        ComputerPlayer comp = null;
        if(args[0].equals("1"))
            comp = new ComputerPlayer();
        ConnectFour connectFour = new ConnectFour(comp);
        connectFour.gameLoop();
    }
}
