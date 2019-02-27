import java.util.*;
import java.io.*;
public class Maze{


    private char[][]maze;
    private boolean animate;//false by default

    /*
    Constructor loads a maze text file, and sets animate to false by default.
      1. The file contains a rectangular ascii maze, made with the following 4 characters:
      '#' - Walls - locations that cannot be moved onto
      ' ' - Empty Space - locations that can be moved onto
      'E' - the location of the goal (exactly 1 per file)
      'S' - the location of the start(exactly 1 per file)
      2. The maze has a border of '#' around the edges. So you don't have to check for out of bounds!
      3. When the file is not found OR the file is invalid (not exactly 1 E and 1 S) then:
         throw a FileNotFoundException or IllegalStateException
    */

    public Maze(String filename) throws FileNotFoundException{
      File text = new File(filename);
      int t=0;
      Scanner temp = new Scanner(text);
      while (temp.hasNextLine()){
        t++;
        temp.nextLine();
      }
      Scanner inf = new Scanner(text);  //inf stands for the input file
      maze = new char[t][inf.nextLine().length()];

      int index=0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();
          //System.out.println(line);//hopefully you can do other things with the line
          for (int i=0;i<line.length();i++){
            maze[index][i]= line.charAt(i);
          }
          index++;
      }

      if (!checkFile){
        throw new IllegalStateException("FILE IS STARTING WITH INCORRECT NUMBER OF ENDS OR STARTS");
      }
    }



    private boolean checkFile(){
      boolean s = false;
      boolean e = false;
      for (int i1=0;i1<maze.length;i1++){
        for (int i2=0;i2<maze[i1].length;i2++){
          if (maze[i1][i2]=='s'&& s)return false;
          if (maze[i1][i2]=='e'&& e)return false;
          if (maze[i1][i2]=='s')s=true;
          if (maze[i1][i2]=='e')e=true;
        }
      }
      return s && e;
    }


    private void wait(int millis){
         try {
             Thread.sleep(millis);
         }
         catch (InterruptedException e) {
         }
     }


    public void setAnimate(boolean b){
        animate = b;
    }


    public void clearTerminal(){

        //erase terminal, go to top left of screen.

        System.out.println("\033[2J\033[1;1H");

    }



    /*Wrapper Solve Function returns the helper function

      Note the helper function has the same name, but different parameters.
      Since the constructor exits when the file is not found or is missing an E or S, we can assume it exists.

    */
    public int solve(){

            //find the location of the S.


            //erase the S


            //and start solving at the location of the s.

            //return solve(???,???);
            return 1;
    }

    /*
      Recursive Solve function:

      A solved maze has a path marked with '@' from S to E.

      Returns the number of @ symbols from S to E when the maze is solved,
      Returns -1 when the maze has no solution.


      Postcondition:

        The S is replaced with '@' but the 'E' is not.

        All visited spots that were not part of the solution are changed to '.'

        All visited spots that are part of the solution are changed to '@'
    */
    private int solve(int row, int col){ //you can add more parameters since this is private


        //automatic animation! You are welcome.
        if(animate){

            clearTerminal();
            System.out.println(this);

            wait(20);
        }

        //COMPLETE SOLVE

        return -1; //so it compiles
    }


}




// mark where you have been on the Maze (@)
// mark unsuccessful moves also (if a spot has no outgoing moves then mark with x or something)
//
