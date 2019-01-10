import javax.imageio.plugins.jpeg.JPEGQTable;
import java.util.ArrayList;
import java.util.List;

public class Driver {
    public static void main(String [] args)
    {
        double[][] qtable = new double[70][8];
        //Initialize at first state
        State state = new State();
        int episodes=1000; // Number of episodes
        double eps = 0.1; // Take a random move 10% of the time
        double learningRate = 0.5; // Same as the one given in the windy world problem
        double discount = 0.9; //
        boolean done=false; // Will be set to true when solution is found



        //Initialize all qtable values to 0
        for(int i =0;i<qtable.length;i++){
            for(int j=0;j<qtable[i].length;j++){
                qtable[i][j]=0;
            }
        }




        for(int i = 0;i < episodes;i++){
            done=false;
            state = new State();
            int moves=0;

           //For info purposes
            if(i==25){
                System.out.println("Q-Table after 25 episodes");
                HelperMethods.displayQtable(qtable);
            }else if(i==100){
                System.out.println("Q-Table after 100 episodes");
                HelperMethods.displayQtable(qtable);
            }


            while(!done){
                int[] xyPosition=state.getPosition();
                int qPosition=HelperMethods.QTablePositionOfState(xyPosition);
                int nextMove=HelperMethods.selectMove(eps,state,qtable,qPosition);

                done=state.makeAMove(nextMove);

                int[] newXyPosition=state.getPosition();
                int newQPosition=HelperMethods.QTablePositionOfState(newXyPosition);



                double maxQ=HelperMethods.maxQ(state,qtable[newQPosition]);


                qtable[qPosition][nextMove]= qtable[qPosition][nextMove] + (learningRate*((-1)+(discount*maxQ)-qtable[qPosition][nextMove]));
                moves++;

            }
            System.out.println("Episode "+i+" is done; Moves: " + moves);
        }


        System.out.println("-------------------------FINAL Q TABLE-------------------------");
        HelperMethods.displayQtable(qtable);


        state = new State();

        System.out.println("\nHere is the puzzle being solved the optimal way!");
        HelperMethods.displayBestPath(state,qtable);

    }
}
