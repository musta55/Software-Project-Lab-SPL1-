package spl1;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;

public class EmotionCalculation {
    public String[] emotion= new String[85000];
    public String[] word= new String[85000];
    int i=0,k=0,totalEmotionCount=0;
    int Frequency[] = new int[8];
    public ArrayList<String> emotionList= new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList= new ArrayList<String>();         //Finding emotion from word

    int intensity;


    public  void searchEmotion() throws IOException {

        //get the file
        System.out.print("After Lemmatization : ");
        for (i = 0; i < Operations.outList.size(); i++) {
            System.out.print(Operations.outList.get(i) + " ");
        }
        System.out.println("");

     //   FileWriter newDb = new FileWriter("src/spl1/emotions.txt");

        File files = new File("src/spl1/emotion.txt");

        FileReader fr = new FileReader(files);
        BufferedReader br = new BufferedReader(fr);

        String currentLine="";


        int p = 0;
        int bool;
        while ((currentLine = br.readLine()) != null) {

            String[] firstSplit = currentLine.split("\t", 0);
              //  System.out.println(firstSplit[0]);

            if(firstSplit[2].matches("1"))
            {
                if(!(firstSplit[1].matches("positive")))
                {
                    if(!(firstSplit[1].matches("negative")))
                    {
                      //  System.out.println(firstSplit[0] + "\t" + firstSplit[1] );
                   //     newDb.write(firstSplit[0] + "\t" + firstSplit[1] + "\n");

                        wordList.add(firstSplit[0]);
                        emotionList.add(firstSplit[1]);
                        p++;



                    }
                }

            }

        }

//        for(int j=0;j<wordList.size();j++)
//        {
//            System.out.println("word="+wordList.get(j));
//        }
//
//        for(int j=0;j<emotionList.size();j++)
//        {
//            System.out.println(" emotion="+emotionList.get(j));
//        }

        //   Operations op=new Operations(outList);

        //   outList=op.getOutList();
        Boolean found = false;
        for (int w = 0; w < 8; w++) Frequency[w] = 0;


        for (i = 0; i < Operations.outList.size();) {

            found = false;
            NEW:
         //   for (int j = 0; j < wordList.size(); j++) {

                //   System.out.println(wordList.get(j)+"  ");

                if (Operations.outList.get(i).equals("so")  || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely"))

                {
                 //   System.out.println("intensity");
                    i++;
                    System.out.println(Operations.outList.get(i));


                    for (int j = 0; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {
                            System.out.println("wordlist is" + wordList.get(j));
                            //  System.out.print("Emotion :" + emotionList.get(j) + " ");
                            //     System.out.println("Intensity");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[0]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[1]++;
                                intensity += 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[2]++;
                                intensity -= 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[4]++;
                                intensity += 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[6]++;
                                intensity += 2;
                                System.out.println("INtensity is" + intensity);
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[7]++;
                                intensity -= 3;
                            }


                            found = true;
                            break NEW;
                        }
                    }
                }
                else if (Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("rare") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor"))
                {
                 //   System.out.println("negative");
                    i++;
                    for (int j = 0; j < wordList.size(); j++) {
                        if (Operations.outList.get(i).equals(wordList.get(j))) {

                            //   System.out.println("sadsa");
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
                            //     System.out.println("negative");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[0]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[1]++;
                                intensity -= 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[2]++;
                                intensity += 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[3]++;
                                intensity += 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[4]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[5]++;
                                intensity += 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[6]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[7]++;
                                intensity += 3;
                            }


                            found = true;
                            break NEW;

                        }
                    }
                } else {

//                    if (!(Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("notonly") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor"))) {
//
//
//                        if (!(
//                                Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely"))) {
//

                    for (int j = 0; j < wordList.size(); j++) {
                        if (Operations.outList.get(i).equals(wordList.get(j))) {
                            //   System.out.println("sadsa");
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
                            //     System.out.println("normal");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) Frequency[0]++;
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) Frequency[1]++;
                            if (emotionList.get(j).equalsIgnoreCase("fear")) Frequency[2]++;
                            if (emotionList.get(j).equalsIgnoreCase("sadness")) Frequency[3]++;
                            if (emotionList.get(j).equalsIgnoreCase("trust")) Frequency[4]++;
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) Frequency[5]++;
                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) Frequency[6]++;
                            if (emotionList.get(j).equalsIgnoreCase("anger")) Frequency[7]++;


                            found = true;
                            break NEW;
                        }
                    }

                    }
//                }
//            }

                if (!found) {
                    //     System.out.print(outList.get(i) + " ");
                    System.out.print("Emotion : Neutral ");
                }
                i++;
            }

        }


    public void emotionCalc()
    {


        int max=-1,temp=0;

        for(int iteration=0;iteration<8;iteration++)
        {
            if(Frequency[iteration]>max)
            {
                max=Frequency[iteration];
                temp=iteration;
            }

        }

        totalEmotionCount=4*Frequency[0]+3*Frequency[1]+(-1)*Frequency[2]+(-2)*Frequency[3]+(+4)*Frequency[4]+(-4)*Frequency[5]+(+2)*Frequency[6]+(-3)*Frequency[7];

        totalEmotionCount+=intensity;
        System.out.println("");
        System.out.println("Scoring :");
        for(int w=0;w<8;w++)
        {
            System.out.println(Frequency[w]);
        }

        System.out.println("Total emotion score :"+totalEmotionCount);
        if(totalEmotionCount>0) System.out.println("Overall the sentence is positive ");
        else if(totalEmotionCount==0) System.out.println("Overall the sentence is neutral");
        else System.out.println("Overall the sentence is negative ");
        if(max==0) System.out.println("Emotion Preference is neutral");

        else {
            System.out.println("");
            if (temp == 0) {
                System.out.println("Emotion Preference is Joy");
            }
            if (temp == 1) {
                System.out.print("Emotion Preference is Surprised");
            }
            if (temp == 2) {
                System.out.print("Emotion Preference is Fear");
            }
            if (temp == 3) {
                System.out.print("Emotion Preference is Sadness");
            }
            if (temp == 4) {
                System.out.print("Emotion Preference is Trust");
            }
            if (temp == 5) {
                System.out.print("Emotion Preference is Disgust");
            }
            if (temp == 6) {
                System.out.print("Emotion Preference is Anticipation");
            }

            if (temp == 7) {
                System.out.print("Emotion Preference is Anger");
            }
        }

    }

}
