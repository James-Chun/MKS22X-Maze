import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;



public class ReadFile {
  public static void main(String args[]) throws FileNotFoundException {
        //instead of a try/catch, you can throw the FileNotFoundException.
        //This is generally bad behavior



        File text = new File(args[0]);
        int t=0;
        Scanner temp = new Scanner(text);
        while (temp.hasNextLine()){
          t++;
          temp.nextLine();
        }
        Scanner temp2=new Scanner(text);
        Scanner inf = new Scanner(text);  //inf stands for the input file
        char[][] list = new char[t][temp2.nextLine().length()];


        int index=0;
        while(inf.hasNextLine()){
            String line = inf.nextLine();
            //System.out.println(line);//hopefully you can do other things with the line
            for (int i=0;i<line.length();i++){
              list[index][i]= line.charAt(i);
            }
            index++;
        }


        for (int c1=0;c1<list.length;c1++){
          for (int c2=0;c2<list[c1].length;c2++){
            System.out.print(list[c1][c2]);
          }
          System.out.println();
        }


    }
}
