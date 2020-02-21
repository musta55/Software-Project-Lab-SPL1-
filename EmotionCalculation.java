
package spl1;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;

public class EmotionCalculation {
    public String[] emotion= new String[85000];
     public String[] word= new String[85000];
     public ArrayList<String>outList=new ArrayList();
    int i=0;
     public  void emotionCalc() throws IOException {

        //get the file
        File file = new File("C:\\Users\\User\\Desktop\\emotion.txt");
        
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){                                     
            emotion[i] = sc.nextLine();                                  

            String[] firstSplit= emotion[i].split("\t",0);
            word[i]=firstSplit[0];
            emotion[i]=firstSplit[1];
        }
        i++;

}

    public void searchEmotion()
        {
           Operations op=new Operations(outList);

            outList=op.getOutList();
            Boolean found = false;


            for(i=0; i < op.getOutList().size(); i++){

                System.out.println("sadsa");
                found = false;
                NEW:
                for (int j=0; j<word.length; j++){

                        if(op.getOutList().get(j).equals(word[j])){
                            System.out.print("Emotion :"+emotion[j]+" ");
                            found = true;
                            break NEW;
                        }
                    }
                }
                if(!found){
                    System.out.print(op.outList.get(i)+" ");
                }
            }
        }


