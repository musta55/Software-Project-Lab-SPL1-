package spl1;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Scanner;

public class Operations {


    public String[] words= new String[85000]; //to store the base words
    public String[] stop= new String[85000];        //to remove stop word
    public ArrayList<ArrayList<String>> aList = new ArrayList<ArrayList<String> >(85000);//to store all the words
    public ArrayList<String> inList = new ArrayList<String>();//to store the input words
    public static ArrayList<String> outList = new ArrayList<String>();
    int i=0;

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
        String[] inArray = inputString.split("[ ,?/;>.*'|\"(){+></@#$%^&_=}]",0);
        for(int j=0;j<inArray.length;j++){
            //inArray[j].

            char str[]=inArray[j].toCharArray();
            for(i=0;i<str.length;i++)
            {
                if(str[i]>='A' && str[i]<='Z')
                {
                    str[i]=(char)((int)str[i]+32);
                }

            }
            for(i=0;i<str.length;i++) {
                inArray[j]= "";
            }
            for(i=0;i<str.length;i++) {
                inArray[j]+= Character.toString(str[i]);
            }

         //   inArray[j] = inArray[j].toLowerCase();
            inList.add(inArray[j]);

        }
    }
    public void removeWord() throws FileNotFoundException
    {
        File files = new File("src/spl1/removablewords.txt");
        Scanner sc = new Scanner(files);

        while (sc.hasNextLine()){
            stop[i++] = sc.nextLine();
        }

        ArrayList <String> stopWordList =new ArrayList<>();
        stopWordList.addAll(Arrays.asList(stop));
        inList.removeAll(stopWordList);
        System.out.print("After removing stop words : ");
        for(int i=0;i<inList.size();i++)
        {
            System.out.print(inList.get(i)+" ");
        }
        System.out.println("");
    }

    //to split the database
    public  void spliter() throws IOException {

        //get the file
        File file = new File("src/spl1/baseWords.txt");
        //FileWriter newDb = new FileWriter("newDB.txt");
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

            for(int j=0;j<secondSplit.length;j++) {
                a1.add(secondSplit[j]);
                //      newDb.write(secondSplit[j] + " -> " + words[i] + "\n");
            }
            aList.add(a1);
            i++;

        }
    }

}
