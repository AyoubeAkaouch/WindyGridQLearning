import java.util.ArrayList;
import java.util.List;

public class State {
    int[][] grid;//Each cell is the wind effect.
    int[] position;
    int gridLengthX=10;
    int gridLengthY=7;
    final int up=0,down=1,left=2,right=3,uright=4,dright =5,uleft=6,dleft=7;

    //Initialize the grid to a windy world grid.
    public State (){
        grid= new int[7][10];
        position=new int[2];
        position[0]=0;//X position
        position[1]=3;//Y position
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++) {
                if(j==3||j==4||j==5||j==8){
                    grid[i][j]=1;
                }
                else if(j==6||j==7){
                    grid[i][j]=2;
                }
                else{
                    grid[i][j]=0;
                }
            }
        }
    }

    public void displayGrid(){
        for(int i=0;i<grid.length;i++){
            for(int j=0;j<grid[i].length;j++) {
                if(i==position[1]&&j==position[0]){
                    System.out.print('x' + " ");
                }
                else{
                    System.out.print(grid[i][j] + " ");
                }
            }
            System.out.println();
        }
    }

    public List<Integer> possibleMoves(){
        List<Integer> possibleMoves = new ArrayList<Integer>();
        if(position[0]!=0){
            possibleMoves.add(left);
        }
        if(position[0]<grid[0].length -1){
            possibleMoves.add(right);
        }
        if(position[1]!=0){
            possibleMoves.add(up);
        }
        if(position[1]<grid.length -1){
            possibleMoves.add(down);
        }

        if(possibleMoves.contains(up)&&possibleMoves.contains(right)){
            possibleMoves.add(uright);
        }
        if(possibleMoves.contains(up)&&possibleMoves.contains(left)){
            possibleMoves.add(uleft);
        }
        if(possibleMoves.contains(down)&&possibleMoves.contains(right)){
            possibleMoves.add(dright);
        }
        if(possibleMoves.contains(down)&&possibleMoves.contains(left)){
            possibleMoves.add(dleft);
        }

        return possibleMoves;
    }

    public boolean makeAMove(int move){
        int wind=grid[position[1]][position[0]];

        if(move==0){
            position[1]--;
        }else if(move==1){
            position[1]++;
        }else if(move==2){
            position[0]--;
        }else if(move==3){//up=0,down=1,left=2,right=3,uright=4,dright =5,uleft=6,dleft=7;
            position[0]++;
        }else if(move==4){
            position[0]++;
            position[1]--;
        }else if(move==5){
            position[0]++;
            position[1]++;
        }else if(move==6){
            position[0]--;
            position[1]--;
        }else if(move==7){
            position[0]--;
            position[1]++;
        }

        position[1]-=wind;
        if(position[1]<0){
            position[1]=0;
        }


        //If we reached the goal state return true
        if(position[1]==3&&position[0]==7){
            return true;
        }
        else{
            return false;
        }

    }

    public int[] getPosition(){
        return position;
    }

    public int gridLengthi(){
        return this.gridLengthY;
    }

    public int gridLengthj(){
        return this.gridLengthX;
    }


}
