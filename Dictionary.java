package sample.spl1;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.HashMap;

public class Dictionary {
    private HashMap<String, String> map;

    public Dictionary(String filePath) throws Exception{

        map = new HashMap<String, String>();
        BufferedReader mainBR = new BufferedReader(new InputStreamReader(new FileInputStream("src/sample/spl1/Translation.txt")));

        String line = mainBR.readLine();
        while(line != null){
            int seperator = line.indexOf('|', 1);
            map.put(line.substring(seperator+1), line.substring(1, seperator));
            line = mainBR.readLine();
        }

        mainBR.close();
    }

    public String search(String word){
       // if(word=="null")return word;
        return map.get(word) +" "+ word;
    }


}