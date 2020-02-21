
package spl1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Operations {

    public ArrayList<String> emotionList= new ArrayList<String>();
    public ArrayList<String> wordList= new ArrayList<String>();
    int i=0,k=0,totalEmotionCount=0;
    int Frequency[] = new int[6];


    public String[] words= new String[85000]; //to store the base words
    public String[] stop= new String[85000];
    public ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String> >(85000);//to store all the words
    public ArrayList<String> inList = new ArrayList<String>();//to store the input words
     public ArrayList<String> outList = new ArrayList<String>();
    public Operations() {
    }

    public Operations(ArrayList<String> outList) {
        this.outList = outList;
    }

    public ArrayList<String> getOutList() {
        return outList;
    }

    //to search
    public void search(){
        Boolean found = false;
        int outIt=0;
        for(i=0; i < inList.size(); i++){
            found = false;
            NEW:
            for (int j=0; j<aList.size(); j++){
                for (int k=0; k<aList.get(j).size(); k++){
                    if(aList.get(j).get(k).equals(inList.get(i))){
                    //   System.out.print(words[j]+" ");
                        outList.add(outIt,words[j]);
                        outIt++;
                        found = true;
                        break NEW;
                    }
                }
            }
            if(!found){
           //   System.out.print(inList.get(i)+" ");
                outList.add(outIt, inList.get(i));
                outIt++;
            }
        }
    }

    //to split the input
    public void splitInput(String inputString) throws FileNotFoundException{
        String[] inArray = inputString.split(" ",0);
        for(int j=0;j<inArray.length;j++){
            inArray[j] = inArray[j].toLowerCase();
            inList.add(inArray[j]);
            
        }
    }
        public void removeWord() throws FileNotFoundException
        {
              File files = new File("C:\\Users\\User\\Desktop\\removablewords.txt");
        Scanner sc = new Scanner(files);

        while (sc.hasNextLine()){                                     
            stop[i++] = sc.nextLine();   
        }
        
        ArrayList <String> stopWordList =new ArrayList<>();
        stopWordList.addAll(Arrays.asList(stop));
       inList.removeAll(stopWordList);
        }
   
    //to split the database
    public  void spliter() throws IOException {

        //get the file
        File file = new File("C:\\Users\\User\\Desktop\\baseWords.txt");
        FileWriter newDb = new FileWriter("C:\\Users\\User\\Desktop\\newDB.txt");
        Scanner sc = new Scanner(file);

        while (sc.hasNextLine()){                                     
            words[i] = sc.nextLine();                                  

            String[] firstSplit= words[i].split(" -> ",0); 
            char[] arr = firstSplit[0].toCharArray();                  

            firstSplit[0] = "";                                      
            for(int j=0;j < arr.length ; j++){
                if( arr[j] != '/')                                   
                    firstSplit[0] += arr[j];
                else break;
            }
            words[i] = firstSplit[0];                                

            String[] secondSplit = firstSplit[1].split(",",0);
            ArrayList<String> a1 = new ArrayList<String>();            
            a1.add(words[i]);                                          
            for(int j=0;j<secondSplit.length;j++) {                    
                a1.add(secondSplit[j]);
                newDb.write(secondSplit[j] + " -> " + words[i] + "\n");
            }
            aList.add(a1);                                             
            i++;
            
        }
    }
    public  void searchEmotion() throws IOException {

        //get the file
        File files = new File("C:\\Users\\User\\Desktop\\emotion.txt");

        FileReader fr = new FileReader(files);
        BufferedReader br =new BufferedReader(fr);

        String currentLine;


        int p=0;
        while ((currentLine = br.readLine())!=null){

            String[] firstSplit= currentLine.split("\t",0);
            wordList.add(firstSplit[0]);
            emotionList.add(firstSplit[1]);
           p++;

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
        for(int w=0;w<6;w++)Frequency[w]=0;

        for(i=0; i < outList.size(); i++) {


            found = false;
            NEW:
            for (int j = 0; j < wordList.size(); j++) {

                //   System.out.println(wordList.get(j)+"  ");

                if (outList.get(i).equals(wordList.get(j))) {
                    //   System.out.println("sadsa");
                    System.out.print("Emotion :" + emotionList.get(j) + " ");
                    if(emotionList.get(j).equalsIgnoreCase("joy"))Frequency[0]++;
                    if(emotionList.get(j).equalsIgnoreCase("surprise"))Frequency[1]++;
                    if(emotionList.get(j).equalsIgnoreCase("fear"))Frequency[2]++;
                    if(emotionList.get(j).equalsIgnoreCase("sadness"))Frequency[3]++;
                    if(emotionList.get(j).equalsIgnoreCase("anger"))Frequency[4]++;
                    if(emotionList.get(j).equalsIgnoreCase("disgust"))Frequency[5]++;


                    found = true;
                    break NEW;
                }
            }

            if (!found) {
           //     System.out.print(outList.get(i) + " ");
                  System.out.print("Emotion : Neutral ");
            }
        }






    }

    public void emotionCalc()
    {


        int max=-1,temp=0;

        for(int iteration=0;iteration<6;iteration++)
        {
            if(Frequency[iteration]>=max)
            {
                max=Frequency[iteration];
                temp=iteration;
            }

        }

        totalEmotionCount=4*Frequency[0]+3*Frequency[1]+(-1)*Frequency[2]+(-2)*Frequency[3]+(-3)*Frequency[4]+(-4)*Frequency[5];

        for(int w=0;w<6;w++)
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
                 System.out.print("Emotion Preference is Anger");
             }
             if (temp == 5) {
                 System.out.print("Emotion Preference is Disgust");
             }


         }

    }


}

