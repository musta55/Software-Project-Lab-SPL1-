
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


     EmotionCalculation emCal=new EmotionCalculation();

     emCal.searchEmotion();
     emCal.emotionCalc();

        System.out.println("");
        
    }
    
}
