
package spl1;

import java.io.IOException;
import java.util.Scanner;

public class SPL1 {

    public static void main(String[] args) throws IOException  {
        Operations operations = new Operations();
        int k=0;
        operations.spliter();

        Scanner scanner = new Scanner(System.in);
        String inputString = scanner.nextLine();
        
        operations.splitInput(inputString);
        operations.removeWord();
        operations.search();
        
      /* do
        {

             System.out.print(operations.outList.get(k)+ " ");
            k++;
        } while(!operations.outList.isEmpty());
*/

   //  EmotionCalculation emCal=new EmotionCalculation();
   //  emCal.emotionCalc();
   //  emCal.searchEmotion();

        System.out.println("");
        
    }
    
}
