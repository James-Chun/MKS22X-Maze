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
      while (temp.hasNextLine()){       //This block is to simply make the char[][]maze with proper dimensions
        t++;
        temp.nextLine();
      }
      Scanner temp2=new Scanner(text);
      maze = new char[t][temp2.nextLine().length()];

      Scanner inf = new Scanner(text);  //inf stands for the input file

      int index=0;
      while(inf.hasNextLine()){
          String line = inf.nextLine();     //adding values to
          for (int i=0;i<line.length();i++){
            maze[index][i]= line.charAt(i);
          }
          index++;
      }

      if (!checkFile()){throw new IllegalStateException("FILE IS STARTING WITH INCORRECT NUMBER OF ENDS OR STARTS");}
    }



    private boolean checkFile(){
      boolean s = false;
      boolean e = false;
      for (int i1=0;i1<maze.length;i1++){
        for (int i2=0;i2<maze[i1].length;i2++){
          if (maze[i1][i2]=='S'&& s)return false;
          if (maze[i1][i2]=='E'&& e)return false;
          if (maze[i1][i2]=='S')s=true;
          if (maze[i1][i2]=='S')e=true;
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
          int row=0;                             //find the location of the S.
          int col=0;                             //erase the S
          for (int i1=0;i1<maze.length;i1++){    //and start solving at the location of the s.
            for (int i2=0;i2<maze[i1].length;i2++){
              if (maze[i1][i2]=='S'){
                row=i1; col=i2; maze[i1][i2]='@';
              }
            }
          }
          return solve(row,col,1);
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
    private int solve(int row, int col, int a){ //you can add more parameters since this is private

        if(animate){  //automatic animation! You are welcome.
            clearTerminal();
            System.out.println(this);
            wait(20);
        }


        if (maze[row+1][col]=='E' || maze[row-1][col]=='E' || maze[row][col+1]=='E' || maze[row][col-1]=='E')return a;

        if (maze[row+1][col]!='#' && maze[row+1][col]!='@' && maze[row+1][col]!='X'){
          maze[row+1][col]='@';
          if (solve(row+1,col,a+1)!=-1){
            return solve(row+1,col,a+1);
          }
          maze[row+1][col]='X';
        }

        if (maze[row-1][col]!='#' && maze[row-1][col]!='@' && maze[row-1][col]!='X'){
          maze[row-1][col]='@';
          if (solve(row-1,col,a+1)!=-1){
            return solve(row-1,col,a+1);
          }
          maze[row-1][col]='X';
        }

        if (maze[row][col+1]!='#' && maze[row][col+1]!='@' && maze[row][col+1]!='X'){
          maze[row][col+1]='@';
          if (solve(row,col+1,a+1)!=-1){
            return solve(row,col+1,a+1);
          }
          maze[row][col+1]='X';
        }

        if (maze[row][col-1]!='#' && maze[row][col-1]!='@' && maze[row][col-1]!='X'){
          maze[row][col-1]='@';
          if (solve(row,col-1,1)!=-1){
            return solve(row,col-1,a+1);
          }
          maze[row][col-1]='X';
        }

        return -1; //so it compiles
    }

    public String toString(){
      String visual = "";
      for (int i1=0;i1<maze.length;i1++){
        for (int i2=0;i2<maze[i1].length;i2++){
          visual += maze[i1][i2];
        }
        visual+="\n";
      }
      return visual;
    }


    public static void main(String[] args){
      try{
        Maze m = new Maze(args[0]);
        m.animate = true;
        System.out.println(m.solve());
      }catch(FileNotFoundException e){
        System.out.println("File Not Found");
      }


    }


}




// mark where you have been on the Maze (@)
// mark unsuccessful moves also (if a spot has no outgoing moves then mark with x or something)
//
