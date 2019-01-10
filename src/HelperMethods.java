import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class HelperMethods {

    static public int selectMove(double eps,State state,double[][] qtable,int qPosition){

        List<Integer> possibleMoves;
        double random=new Random().nextDouble();

        possibleMoves=state.possibleMoves();

        //If random value lower than our epsilon choose a random value
        if(random<eps){
            int randomInt = new Random().nextInt(possibleMoves.size());
            return possibleMoves.get(randomInt);
        }
        else{

            //This keeps track of best possible move
            double bestMoveQValue=-1000000; //Really high so that first q value overwrites it
            int bestMoveIndex=0;

            for(int i =0;i<qtable[qPosition].length;i++){
                //If this move is possible at this state check its q value
                if(possibleMoves.contains(i)){
                    if(qtable[qPosition][i]>bestMoveQValue){
                        bestMoveIndex=i;
                        bestMoveQValue = qtable[qPosition][i];
                    }
                }
            }

            return bestMoveIndex;


        }

    }

    static public int QTablePositionOfState(int[] position){
        return (10*position[1]+position[0]);
    }

    static public double maxQ(State state,double[] qRow){
        List<Integer> possibleMoves = state.possibleMoves();

        //This keeps track of best possible move
        double bestMoveQValue= -1000000; //Really high so that first q value overwrites it

        for(int i =0;i<qRow.length;i++){
            //If this move is possible at this state check its q value
            if(possibleMoves.contains(i)){
                if(bestMoveQValue== -1000000){

                  bestMoveQValue = qRow[i];
                }
                if(qRow[i]>bestMoveQValue){
                    bestMoveQValue = qRow[i];
                }
            }
        }

        return bestMoveQValue;
    }

    static void displayBestPath(State state,double[][] qtable){

        List<Integer> movesMade = new ArrayList<Integer>();
        boolean done = false;
        int qPosition;
        state.displayGrid();
        System.out.println();
        System.out.println();

        while(!done){
            qPosition = QTablePositionOfState(state.getPosition());
            int move = selectMove(0,state,qtable,qPosition);//Epsilon of 0 because we want no random moves
            movesMade.add(move);
            done = state.makeAMove(move);
            state.displayGrid();
            System.out.println();
            System.out.println();
        }

        System.out.println("Here is the optimal path to take ("+movesMade.size()+" moves):");
        for(int i =0;i<movesMade.size();i++){
            if(movesMade.get(i)==0){
                System.out.print("\"UP\" ");
            }
            else if(movesMade.get(i)==1){
                System.out.print("\"DOWN\" ");
            }
            else if(movesMade.get(i)==2){
                System.out.print("\"lEFT\" ");
            }
            else if(movesMade.get(i)==3){
                System.out.print("\"RIGHT\" ");
            }
            else if(movesMade.get(i)==4){
                System.out.print("\"UPPER RIGHT\" ");
            }
            else if(movesMade.get(i)==5){
                System.out.print("\"DOWN RIGHT\" ");
            }
            else if(movesMade.get(i)==6){
                System.out.print("\"UPPER LEFT\" ");
            }
            else if(movesMade.get(i)==7){
                System.out.print("\"DOWN lEFT\" ");
            }


        }

    }

    static public void displayQtable(double[][] qtable){
        for(int i =0;i<qtable.length;i++){
            for(int j=0;j<qtable[i].length;j++){
                System.out.printf("%.3f ",qtable[i][j]);
            }
            System.out.println();
        }
    }

}
