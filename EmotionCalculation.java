package sample.spl1;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardOpenOption;
import java.util.ArrayList;

import com.restfb.Connection;
import com.restfb.DefaultFacebookClient;
import com.restfb.FacebookClient;
import com.restfb.types.Post;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.chart.*;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.Text;
import javafx.scene.text.TextAlignment;
import javafx.stage.Stage;

import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;
import java.util.stream.Stream;

public class EmotionCalculation {




    InputStream is = null;
    DataInputStream dis = null;
    FileOutputStream fos = null;
    DataOutputStream dos = null;
    public String[] emotion = new String[85000];
    public String[] word = new String[85000];
    public static String s=null;
    int i = 0, k = 0, totalEmotionCount = 0;
    int Frequency[] = new int[8];
    public ArrayList<String> emotionList = new ArrayList<String>();      // Emotion Word List
    public ArrayList<String> wordList = new ArrayList<String>();         //Finding emotion from word
    int exClaimCount = 0;
    int intensity;
//    public static double JoyCal = 0;
//    public static double SurpriseCal = 0;
//    public  static double AngerCal = 0;
//    public  static double SadnessCal = 0;
//    public  static double FearCal = 0;
//    public static  double anticipationCal = 0;
//    public static  double TrustCal = 0;
    public  double JoyCal = 0;
    public  double SurpriseCal = 0;
    public   double AngerCal = 0;
    public   double SadnessCal = 0;
    public   double FearCal = 0;
    public   double anticipationCal = 0;
    public   double TrustCal = 0;
    public   double DisgustCal = 0;
    public String sf=null;


    public void searchEmotion() throws IOException {


        //get the file
        System.out.print("After Lemmatization : ");
        for (i = 0; i < Operations.outList.size(); i++) {
            System.out.print(Operations.outList.get(i) + " ");
            if (Operations.outList.get(i).contains("!")) {
                exClaimCount++;
            }
            if (Operations.outList.get(i).contains("?")) {
                exClaimCount++;
            }
        }

        System.out.println("Hey What's up");

        //   FileWriter newDb = new FileWriter("src/spl1/emotions.txt");

        File files = new File("src/sample/spl1/emotion.txt");

        //   FileReader fr = new FileReader(files);
        //   BufferedReader br = new BufferedReader(fr);

        try (BufferedReader br = new BufferedReader(new FileReader(files))) {              //Exception With Resources


            String currentLine = "";


            int p = 0;
            int bool;
            while ((currentLine = br.readLine()) != null) {

                String[] firstSplit = currentLine.split("\t", 0);
                //  System.out.println(firstSplit[0]);

                if (firstSplit[2].matches("1")) {
                    if (!(firstSplit[1].matches("positive"))) {
                        if (!(firstSplit[1].matches("negative"))) {
                            //  System.out.println(firstSplit[0] + "\t" + firstSplit[1] );
                            //     newDb.write(firstSplit[0] + "\t" + firstSplit[1] + "\n");

                            wordList.add(firstSplit[0]);
                            emotionList.add(firstSplit[1]);
                            p++;


                        }
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




      //      FileWriter fw=new FileWriter("sample/spl1/emotionOutput.txt");
         //   fw.close();



        for (i = 0; i < Operations.outList.size(); ) {

            found = false;
            NEW:
            //   for (int j = 0; j < wordList.size(); j++) {

            //   System.out.println(wordList.get(j)+"  ");

            if (Operations.outList.get(i).equals("so") || Operations.outList.get(i).equals("more") || Operations.outList.get(i).equals("most") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("quite") || Operations.outList.get(i).equals("just") || Operations.outList.get(i).equals("almost") || Operations.outList.get(i).equals("too") || Operations.outList.get(i).equals("enough") || Operations.outList.get(i).equals("very") || Operations.outList.get(i).equals("extremely") || Operations.outList.get(i).equals("terribly") || Operations.outList.get(i).equals("amazingly") || Operations.outList.get(i).equals("wonderfully") || Operations.outList.get(i).equals("insanely")) {

                int q=0;
                //   System.out.println("intensity");
                while(q<5)
                {

               // i++;
                System.out.println(Operations.outList.get(i));

                    int j = 0,a=0;
                for (; j < wordList.size(); j++) {

                    if (Operations.outList.get(i).equals(wordList.get(j))) {
                        System.out.print("word :" + wordList.get(j)+" ");
                          System.out.print("Emotion :" + emotionList.get(j) + " ");
                        //  fw.write(emotionList.get(j)+" ");

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
                            //  System.out.println("INtensity is" + intensity);
                        }
                        if (emotionList.get(j).equalsIgnoreCase("anger")) {
                            Frequency[7]++;
                            intensity -= 3;
                        }


                        found = true;
                      //  break NEW;
                    }else
                    {
                        a++;
                    }
                }
                if(a==j)
                {
                    i++;
                    q++;
                }
                else break;
            }

            } else if (Operations.outList.get(i).equals("never") || Operations.outList.get(i).equals("rarely") || Operations.outList.get(i).equals("not") || Operations.outList.get(i).equals("no") || Operations.outList.get(i).equals("rare") || Operations.outList.get(i).equals("scarcely") || Operations.outList.get(i).equals("seldom") || Operations.outList.get(i).equals("few") || Operations.outList.get(i).equals("little") || Operations.outList.get(i).equals("bad") || Operations.outList.get(i).equals("sad") || Operations.outList.get(i).equals("dont") || Operations.outList.get(i).equals("") || Operations.outList.get(i).equals("poor")) {
                //   System.out.println("negative");
                int q=0;
                while(q<5) {


                   // i++;
                  //  NEWS:

                    int j= 0,a=0;
                    for (; j < wordList.size(); j++) {

                        if (Operations.outList.get(i).equals(wordList.get(j))) {

                            System.out.print("Word :"+wordList.get(j));
                            System.out.print("Emotion :" + emotionList.get(j) + " ");
//                            //     System.out.println("negative");
                            if (emotionList.get(j).equalsIgnoreCase("joy")) {
                                Frequency[5]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("surprise")) {
                                Frequency[2]++;
                                intensity -= 3;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("fear")) {
                                Frequency[1]++;
                                intensity += 1;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("sadness")) {
                                Frequency[6]++;
                                intensity += 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("trust")) {
                                Frequency[7]++;
                                intensity -= 4;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("disgust")) {
                                Frequency[0]++;
                                intensity += 4;
                            }

                            if (emotionList.get(j).equalsIgnoreCase("anticipation")) {
                                Frequency[3]++;
                                intensity -= 2;
                            }
                            if (emotionList.get(j).equalsIgnoreCase("anger")) {
                                Frequency[4]++;
                                intensity += 3;
                            }


                            found = true;
                       //     break NEWS;


                        }
                        else
                        {
                            a++;
                            q++;
                        }
                    }
                    if(a==j)
                    {
                        i++;
                    }
                    else break;
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
                        System.out.print("Word :"+wordList.get(j)+" ");
                        System.out.print("Emotion :" + emotionList.get(j) + " ");
                    //    fw.write(emotionList.get(j)+" ");
                        //     System.out.println("normal");
                        if (emotionList.get(j).equalsIgnoreCase("joy")) Frequency[0]+=2;
                        if (emotionList.get(j).equalsIgnoreCase("surprise")) Frequency[1]+=3;
                        if (emotionList.get(j).equalsIgnoreCase("fear")) Frequency[2]++;
                        if (emotionList.get(j).equalsIgnoreCase("sadness")) Frequency[3]++;
                        if (emotionList.get(j).equalsIgnoreCase("trust")) Frequency[4]++;
                        if (emotionList.get(j).equalsIgnoreCase("disgust")) Frequency[5]+=2;
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
                //     System.out.print("Emotion : Neutral ");
            }
            i++;
        }
     //   fw.close();
        Operations.outList.clear();

    }

    public static double calculateSD(double emoArray[]) {
        double sum = 0.0, standardDeviation = 0.0;
        int length = emoArray.length;

        for (double num : emoArray) {
            sum += num;
        }

        double mean = sum / length;

        for (double num : emoArray) {
            standardDeviation += Math.pow(num - mean, 2);
        }

        return Math.sqrt(standardDeviation / length);

    }

    public void emotionCalc(Stage stage) throws Exception {


        int max = -1, temp = 0;

        for (int iteration = 0; iteration < 8; iteration++) {
            if (Frequency[iteration] > max) {
                max = Frequency[iteration];
                temp = iteration;
            }

        }

        totalEmotionCount = 4 * Frequency[0] + 3 * (Frequency[1] + exClaimCount) + (-1) * Frequency[2] + (-2) * Frequency[3] + (+4) * Frequency[4] + (-4) * Frequency[5] + (+2) * Frequency[6] + (-3) * Frequency[7];

        totalEmotionCount += intensity;
        System.out.println("");
        System.out.println("Scoring :");
        double tot = 0;
        for (int i = 0; i < 8; i++) {
            tot += Frequency[i];
        }


        JoyCal = (Frequency[0] * 100) / tot;
        SurpriseCal= ((Frequency[1] + exClaimCount) * 100) / tot;
        FearCal = (Frequency[2] * 100) / tot;
        SadnessCal= (Frequency[3] * 100) / tot;
        TrustCal = (Frequency[4] * 100) / tot;
        DisgustCal = (Frequency[5] * 100) / tot;
        anticipationCal = (Frequency[6] * 100) / tot;
        AngerCal = (Frequency[7] * 100) / tot;
//
//        Main pie=new Main();
//        Stage stage = new Stage();
//        pie.start(stage);
        double[] emoArray = {JoyCal, SurpriseCal, FearCal, SadnessCal, TrustCal, DisgustCal, anticipationCal, AngerCal};
        double SD = calculateSD(emoArray);

        System.out.format("Standard Deviation = %.6f\n", SD);


        System.out.println("Joy =" + String.format("%.1f", ((Frequency[0] * 100) / tot)) + "%");
        System.out.println("Surprised =" + String.format("%.1f", ((Frequency[1] * 100) / tot)) + "%");
        System.out.println("Fear =" + String.format("%.1f", ((Frequency[2] * 100) / tot)) + "%");
        System.out.println("Sadness =" + String.format("%.1f", ((Frequency[3] * 100) / tot)) + "%");
        System.out.println("Trust =" + String.format("%.1f", ((Frequency[4] * 100) / tot)) + "%");
        System.out.println("Disgust =" + String.format("%.1f", ((Frequency[5] * 100) / tot)) + "%");
        System.out.println("anticipation=" + String.format("%.1f", ((Frequency[6] * 100) / tot)) + "%");
        System.out.println("Anger=" + String.format("%.1f", ((Frequency[7] * 100) / tot)) + "%");

//     DataOutputStream();

        // pieChartExample.start(Stage stage);


        System.out.println("Total emotion score :" + totalEmotionCount);
        if (totalEmotionCount > 0) System.out.println("Overall the sentence is positive ");
        else if (totalEmotionCount == 0) System.out.println("Overall the sentence is neutral");
        else System.out.println("Overall the sentence is negative ");
        if (max == 0) System.out.println("Emotion Preference is neutral");

        else {
            System.out.println("");
            if (temp == 0) {
                System.out.println("Emotion Preference is Joy");
            }
            if (temp == 1) {
                System.out.println("Emotion Preference is Surprised");
            }
            if (temp == 2) {
                System.out.println("Emotion Preference is Fear");
            }
            if (temp == 3) {
                System.out.println("Emotion Preference is Sadness");
            }
            if (temp == 4) {
                System.out.println("Emotion Preference is Trust");
            }
            if (temp == 5) {
                System.out.println("Emotion Preference is Disgust");
            }
            if (temp == 6) {
                System.out.println("Emotion Preference is anticipation");
            }

            if (temp == 7) {
                System.out.println("Emotion Preference is Anger");
            }
        }

    }

    public void fileOpen() {

        try {
            File myObj = new File("src/sample/spl1/out.txt");
            if (myObj.createNewFile()) {
                System.out.println("File created: " + myObj.getName());
            } else {
                System.out.println("File already exists.");
            }
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }

    public void DataOutputStream() throws IOException {


        String[] dbuf = {String.valueOf(JoyCal), String.valueOf(SurpriseCal), String.valueOf(FearCal), String.valueOf(SadnessCal), String.valueOf(TrustCal), String.valueOf(DisgustCal), String.valueOf(anticipationCal), String.valueOf(AngerCal)};

//Double []dbuf={JoyCal,SurpriseCal,FearCal,SadnessCal,TrustCal,DisgustCal,AngerCal};




         //   fw.write(dbuf[0]+"#"+dbuf[1]+"#"+dbuf[2]+"#"+dbuf[3]+"#"+dbuf[4]+"#"+dbuf[5]+"#"+dbuf[6]+"#"+dbuf[7]+"#");
        //    fw.write("Yo Little piece of Shit");



        try {
          //  FileWriter fw = new FileWriter("src/sample/spl1/out.txt");
            Path path = Paths.get("src/sample/spl1/out.txt");
        //    fw.write("Files in Java might be tricky, but it is fun enough!");
          //  fw.write(dbuf[0]+"#"+dbuf[1]+"#"+dbuf[2]+"#"+dbuf[3]+"#"+dbuf[4]+"#"+dbuf[5]+"#"+dbuf[6]+"#"+dbuf[7]+"#");
         String   textToAppend=dbuf[0]+" "+dbuf[1]+" "+dbuf[2]+" "+dbuf[3]+" "+dbuf[4]+" "+dbuf[5]+" "+dbuf[6]+" "+dbuf[7]+" ";
            Files.write(path, textToAppend.getBytes(), StandardOpenOption.APPEND);
         //   fw.close();
            System.out.println("Successfully wrote to the file.");
        } catch (IOException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
        }

    }


    public void VisualOutput( Stage stage) {

        Button back = new Button("Back");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);

        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        Pane root = new Pane();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });



        ObservableList<PieChart.Data> pieChartData = FXCollections.observableArrayList(
                new PieChart.Data("Joy", JoyCal),
                new PieChart.Data("Surprise", SurpriseCal),
                new PieChart.Data("Anger", AngerCal),
                new PieChart.Data("anticipation", anticipationCal),
                new PieChart.Data("Sadness", SadnessCal),
                new PieChart.Data("Disgust", DisgustCal),
                new PieChart.Data("Fear", FearCal),
                new PieChart.Data("Trust", TrustCal));

        //Creating a Pie chart
        PieChart pieChart = new PieChart(pieChartData);

        //Setting the title of the Pie chart
        pieChart.setTitle("Emotion ");


        //setting the direction to arrange the data
        pieChart.setClockwise(true);

        //Setting the length of the label line
        pieChart.setLabelLineLength(70);

        //Setting the labels of the pie chart visible
        pieChart.setLabelsVisible(true);

        //Setting the start angle of the pie chart
        pieChart.setStartAngle(180);

        pieChart.setTranslateX(300);
        pieChart.setTranslateY(200);
        pieChart.setScaleX(1.5);
        pieChart.setScaleY(1.5);

        //Creating a Group object
        Group roots = new Group(pieChart);


        Text headning = new Text(s);
        s=null;
        headning.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
        headning.setScaleX(1);
        headning.setScaleY(1);
        headning.setTranslateX(50);
        headning.setTranslateY(40);
        headning.setFill(Color.DARKCYAN);

        //Creating a scene object
        Scene scene = new Scene(root, 1400, 750);

        //Setting title to the Stage
        stage.setTitle("Pie Chart");
        root.getChildren().addAll(pieChart,back ,headning);
        //Adding scene to the stage
        stage.setScene(scene);

        //Displaying the contents of the stage
        stage.show();
        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });


        BackgroundImage bi = new BackgroundImage(background,
                BackgroundRepeat.NO_REPEAT,
                BackgroundRepeat.NO_REPEAT,
                BackgroundPosition.DEFAULT,
                BackgroundSize.DEFAULT);
        Background bg = new Background(bi);
        root.setBackground(bg);


        // Scene scene = new Scene(root, 1600, 800);
        stage.setScene(scene);
        //primaryStage.setFullScreen(true);
        stage.show();


//        CategoryAxis xAxis = new CategoryAxis();
//        xAxis.setCategories(FXCollections.<String>
//                observableArrayList(Arrays.asList("Speed", "User rating", "Milage", "Safety")));
//        xAxis.setLabel("category");
//
//        NumberAxis yAxis = new NumberAxis();
//        yAxis.setLabel("score");
//
//        //Creating the Bar chart
//        BarChart<String, Number> barChart = new BarChart<>(xAxis, yAxis);
//        barChart.setTitle("Comparison between various cars");
//
//        //Prepare XYChart.Series objects by setting data
//        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
//        series1.setName("Fiat");
//        series1.getData().add(new XYChart.Data<>("Speed", 1.0));
//        series1.getData().add(new XYChart.Data<>("User rating", 3.0));
//        series1.getData().add(new XYChart.Data<>("Milage", 5.0));
//        series1.getData().add(new XYChart.Data<>("Safety", 5.0));
//
//        XYChart.Series<String, Number> series2 = new XYChart.Series<>();
//        series2.setName("Audi");
//        series2.getData().add(new XYChart.Data<>("Speed", 5.0));
//        series2.getData().add(new XYChart.Data<>("User rating", 6.0));
//        series2.getData().add(new XYChart.Data<>("Milage", 10.0));
//        series2.getData().add(new XYChart.Data<>("Safety", 4.0));
//
//        XYChart.Series<String, Number> series3 = new XYChart.Series<>();
//        series3.setName("Ford");
//        series3.getData().add(new XYChart.Data<>("Speed", 4.0));
//        series3.getData().add(new XYChart.Data<>("User rating", 2.0));
//        series3.getData().add(new XYChart.Data<>("Milage", 3.0));
//        series3.getData().add(new XYChart.Data<>("Safety", 6.0));
//
//        //Setting the data to bar chart
//        barChart.getData().addAll(series1, series2, series3);
//
//        //Creating a Group object
//        Group root = new Group(barChart);
//
//        //Creating a scene object
//        Scene scene = new Scene(root, 600, 400);
//
//        //Setting title to the Stage
//        stage.setTitle("Bar Chart");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();

    }

    public void VisualOutputFacebook( Stage stage) throws FileNotFoundException  {

     Scanner scan;
        File file = null;
        file = new File("src/sample/spl1/out.txt");
        scan = new Scanner(file);

        Double[] out=new Double[10000];
        Double[] sentimentPos=new Double[100000];
        Double[] sentimentNeg=new Double[100000];
        int i=0;
        int m=0;
        while (scan.hasNextLine()) {
       //
            String currentLine=scan.nextLine();
           // System.out.println("Current Line "+currentLine);
            String[] firstSplits = currentLine.split(" ", 0);
            for(;m<firstSplits.length;m++) {
           //     System.out.println("String is " + firstSplits[m]);
                try {
                    if(firstSplits[m]=="NaN"||firstSplits[m]=="Infinity");
                    else {
                        out[m] = Double.parseDouble(firstSplits[m]);
                        System.out.print(out[m] +"          ");
                    }
                }catch (Exception q)
                {
                   out[m]=0.000;
                }
           //     System.out.println("Value is " + out[m]);
            }
           // double d = Double.parseDouble();
        }
    Group root = new Group();
   //   stage.setScene(new Scene(root));
        Image background = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
        //   Image fusics = new Image("fusics.png");
        Canvas canvas = new Canvas(1800,900);

        GraphicsContext gc = canvas.getGraphicsContext2D();
        gc.drawImage(background,0,0);
        Scene scene = new Scene(root, 2000, 900);
        stage.setScene(scene);
        stage.setFullScreen(true);
        stage.show();
        CategoryAxis xAxis =new CategoryAxis();
        xAxis.setLabel("EMOTIONAL PROGRESSION OVER STATUS");

       // CategoryAxis xAxis = new CategoryAxis("Emotions\n"+"1.Joy\n"+"2.Surprise\n"+"3.Fear\n"+"4.Sadness\n"+"5.Trust\n"+"6.Disgust\n"+"7.anticipation\n"+"8.Disgust\n");
        NumberAxis yAxis = new NumberAxis("INTENSITY", 0, 100, 10);
        Button back = new Button("Back");
        back.setTranslateX(1100);
        back.setTranslateY(650);
        back.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                "        #8d9092,\n" +
                "        #717375,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.1em;");
        back.setPrefSize(60, 30);


        back.setOnAction(e -> {
            try {
                Main goBack = new Main();
                goBack.start(stage);
            } catch (Exception ex) {
                ex.printStackTrace();
            }
        });
        Button more = new Button("More Analysis");
        more.setTranslateX(1050);
        more.setTranslateY(700);
        more.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        more.setPrefSize(160, 40);
        more.setTextFill(Color.WHITE);

        more.setOnAction(e->{
            try {




                double joyOut=0;

                for(int y=0;y<46;y+=8)joyOut+=out[y];
                joyOut=joyOut/6;




                double surpriseOut=0;
                for(int y=1;y<46;y+=8)surpriseOut+=out[y];
                surpriseOut=surpriseOut/6;

                double fearOut=0;
                for(int y=2;y<46;y+=8)fearOut+=out[y];
                fearOut=fearOut/6;

                double sadnessOut=0;
                for(int y=3;y<46;y+=8)sadnessOut+=out[y];
                sadnessOut=sadnessOut/6;

                double trustOut=0;
                for(int y=4;y<46;y+=8)trustOut+=out[y];
                trustOut=trustOut/6;

                double disgustOut=0;
                for(int y=5;y<48;y+=8)disgustOut+=out[y];
                disgustOut=disgustOut/6;

                double anticipationOut=0;
                for(int y=6;y<48;y+=8)anticipationOut+=out[y];
                anticipationOut=anticipationOut/6;

                double angerOut=0;
                for(int y=7;y<48;y+=8)angerOut+=out[y];
                angerOut=angerOut/6;

                Image backgrounds = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
                Pane roots = new Pane();

        CategoryAxis xAxises = new CategoryAxis();
        xAxises.setCategories(FXCollections.<String>
                observableArrayList(Arrays.asList("Joy", "Surprise", "Fear", "Sadness","Trust","Disgust","anticipation","Anger")));
        xAxises.setLabel("EMOTION");

        NumberAxis yAxises = new NumberAxis();
        yAxises.setLabel("INTENSITY");

        //Creating the Bar chart
        BarChart<String, Number> barChart = new BarChart<>(xAxises, yAxises);
        barChart.setTitle("Comparison between emotions");

        //Prepare XYChart.Series objects by setting data
        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("EMOTION");
        series1.getData().add(new XYChart.Data<>("Joy", joyOut));
        series1.getData().add(new XYChart.Data<>("Surprise", surpriseOut));
        series1.getData().add(new XYChart.Data<>("Fear", fearOut));
        series1.getData().add(new XYChart.Data<>("Sadness", sadnessOut));
        series1.getData().add(new XYChart.Data<>("Trust", trustOut));
        series1.getData().add(new XYChart.Data<>("Disgust", disgustOut));
        series1.getData().add(new XYChart.Data<>("anticipation", anticipationOut));
        series1.getData().add(new XYChart.Data<>("Anger", angerOut));
                barChart.setTranslateX(400);
                barChart.setTranslateY(120);
                barChart.setScaleX(1.2);
                barChart.setScaleY(1.2);


                barChart.getData().addAll(series1);


                BackgroundImage bi = new BackgroundImage(background,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundRepeat.NO_REPEAT,
                        BackgroundPosition.DEFAULT,
                        BackgroundSize.DEFAULT);
                Background bg = new Background(bi);
                roots.setBackground(bg);
                double maxa=-1,maxb=-1;
                int temp=0,temp2=0;
                Double[] freq = new Double[]{ joyOut,surpriseOut,fearOut,sadnessOut,trustOut,disgustOut,anticipationOut,angerOut };
                for (int iteration = 0; iteration < 8; iteration++) {
//                    if (freq[iteration] > maxa) {
//                        maxa = freq[iteration];
//                        temp = iteration;

                        if (freq[iteration] > maxa)
                        {
                            maxb = maxa;
                            maxa = freq[iteration];
                            temp=iteration;
                        }

        /* If arr[i] is in between first and
           second then update second  */
                        else if (freq[iteration] > maxb && freq[iteration] != maxa) {
                            maxb = freq[iteration];
                            temp2=iteration;
                        }


                    }
                System.out.println("Highest is "+temp+"Second is "+temp2);
                String text=null;


                if(temp==0)
                {
                    text="This person has a kind heart and jolly mind.Her post is full of serenity and ecstasy";
                }
               else if(temp==1)
                {
                    text="As a person,you are curious.Surprising things happening around you.Live with amazement";
                }
                else if(temp==2)
                {
                    text="Some terrible things is happening in your mind.Stay safe and sound";
                }
                else if(temp==3)
                {
                    text="Sadness grabs you severely.But remember, heavy hearts , like heavy clouds in the sky, are best relieved by the letting of a little water";
                }
                else if(temp==4 )
                {
                    text="You accept and adapt things happening around you wisely.Have enough courage to trust love one more time and always one more time";
                }
                else if(temp==5 )
                {
                    text="Disgust and boredom is the key of your status";
                }
                else if(temp==6 )
                {
                    text="Keep smiling that reflects on your posts.Spread positivity and be optimistic";
                }
                else if(temp==7 )
                {
                    text="Keep smiling that reflects on your posts.Spread positivity and be optimistic";

                }
                Text texts = new Text(text);

                texts.setFont(Font.font(Font.getFontNames().get(7), FontPosture.REGULAR, 12));
                texts.setScaleX(1);
                texts.setScaleY(1);
                texts.setTranslateX(50);
                texts.setTranslateY(40);
                texts.setFill(Color.DARKCYAN);







//                    Label placeholder = new Label(text);
//                    placeholder.setFont(new Font(30.0));
//                    placeholder.setTextFill(Color.gray(0.4));
//                    placeholder.setTextAlignment(TextAlignment.CENTER);
//                    placeholder.setAlignment(Pos.CENTER);
//                    placeholder.setStyle("-fx-background-color: #f8f8f8");
//                    placeholder.setScaleX(1);
//                placeholder.setScaleY(1);
//
//                        placeholder.setMinSize(600,600);



                roots.getChildren().addAll(barChart,texts);
                Scene scenea = new Scene(roots, 1400, 750);
                // Scene scene = new Scene(root, 1600, 800);
                stage.setScene(scenea);
                //primaryStage.setFullScreen(true);
                stage.show();

            }catch (Exception ex)
            {
                ex.printStackTrace();
            }
        });
        String accessToken = "EAAJfo73qhKQBAGRA9WJ657oxyiGZBg4XirgKuCjMG9frJFmpamR15ia6osKlP2waeqVIVQpZAz7BYhHZAZC3id4D8ZAL07Ass7NZBu3G15sTczsF4uBlkuiNV65qrlBvbFlFRdsS3ZBIZCUMvxMHnrmJotU7PMzyzy3Y6hxfOEaGcB5YJmAf0EJqlwBBpm2dMQUZD ";

        Button status1 = new Button("Status 1");
        status1.setTranslateX(100);
        status1.setTranslateY(620);
        status1.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
       status1.setPrefSize(100, 30);
        status1.setTextFill(Color.WHITE);






        status1.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 1) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                   s+=aPost.getMessage().substring((number/16)+1,number/8);
                                   s+="\n";
                                   s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                               //     s+=aPost.getMessage().substring(2100,number);




                                      char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                          operations.splitInput(aPost.getMessage());
                                         aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                           inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                    emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );


        Button status2 = new Button("Status 2");
        status2.setTranslateX(280);
        status2.setTranslateY(620);
        status2.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status2.setPrefSize(100, 30);
        status2.setTextFill(Color.WHITE);






        status2.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 2) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                                    //     s+=aPost.getMessage().substring(2100,number);




                                    char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                        operations.splitInput(aPost.getMessage());
                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );

        Button status3 = new Button("Status 3");
        status3.setTranslateX(460);
        status3.setTranslateY(620);
        status3.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status3.setPrefSize(100, 30);
        status3.setTextFill(Color.WHITE);






        status3.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 3) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                                    //     s+=aPost.getMessage().substring(2100,number);




                                    char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                        operations.splitInput(aPost.getMessage());
                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );

        Button status4 = new Button("Status 4");
        status4.setTranslateX(640);
        status4.setTranslateY(620);
        status4.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status4.setPrefSize(100, 30);
        status4.setTextFill(Color.WHITE);






        status4.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 4) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                                    //     s+=aPost.getMessage().substring(2100,number);




                                    char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                        operations.splitInput(aPost.getMessage());
                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );

        Button status5 = new Button("Status 5");
        status5.setTranslateX(820);
        status5.setTranslateY(620);
        status5.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status5.setPrefSize(100, 30);
        status5.setTextFill(Color.WHITE);






        status5.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 5) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                                    //     s+=aPost.getMessage().substring(2100,number);




                                    char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                        operations.splitInput(aPost.getMessage());
                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );

        Button status6 = new Button("Status 6");
        status6.setTranslateX(1070);
        status6.setTranslateY(620);
        status6.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        status6.setPrefSize(100, 30);
        status6.setTextFill(Color.WHITE);






        status6.setOnAction(e->
                {
                    Operations operations = new Operations();


                    FacebookClient fbClient = new DefaultFacebookClient(accessToken);
                    //   FacebookClient.AccessToken exAccessToken = fbClient.obtainExtendedAccessToken("668106823992484 ", "f63f747f31e390a44f93891920364794");

                    Connection<Post> result;
                    result = fbClient.fetchConnection("me/feed", Post.class);


                    String userInput = null;
                    EmotionCalculation emCal = new EmotionCalculation();
                    emCal.fileOpen();
                    int counter=0;
                    for (List<Post> apost : result) {
                        for (Post aPost : apost) {
                            counter++;
                            if (counter == 6) {

                                try {
                                    int number=aPost.getMessage().length();
                                    s=aPost.getMessage().substring(0,number/16);
                                    s+=aPost.getMessage().substring((number/16)+1,number/8);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/8)+1,number/4);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/4)+1,number/2);
                                    s+="\n";
                                    s+=aPost.getMessage().substring((number/2)+1,number);
                                    s+="\n";
                                    //     s+=aPost.getMessage().substring(2100,number);




                                    char c;
                                    c = aPost.getMessage().charAt(0);
                                    char d;
                                    d=aPost.getMessage().charAt(1);

                                    if (((c >= 'a' && c <= 'z') || (c >= 'A' && c <= 'Z') )&&((d >= 'a' && d <= 'z') || (d >= 'A' && d <= 'Z'))) {

                                        operations.splitInput(aPost.getMessage());
                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");



                                    } else {
                                        System.out.println("The Post is Bangla");
                                        OperationsBangla operationsBangla = new OperationsBangla();
                                        operationsBangla.splitInputBangla();
                                        String[] inArray = null;
                                        String[] inArray2 = new String[10000];
                                        Dictionary dictionary = new Dictionary("src/sample/spl1/Translation.txt");

                                        inArray = aPost.getMessage().split("[ ,/;>.*'|\"(){+></@$%^&_=}]", 0);

                                        aPost.getMessage().replaceAll(aPost.getMessage(), "");
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = operationsBangla.searchBan(inArray[j]);
                                        }
                                        String inp = "";
                                        for (int j = 0; j < inArray.length; j++) {
                                            inArray2[j] = dictionary.search(inArray[j]);

                                            inp = inp + inArray2[j] + " ";


                                        }
                                        operations.splitInput(inp);
                                    }
                                    operations.removeWord();
                                    operations.search();
                                    emCal.searchEmotion();
                                    emCal.emotionCalc(stage);
                                    emCal.DataOutputStream();

                                } catch (Exception ea) {
                                    System.out.println("");
                                }
                                emCal.VisualOutput(stage);

                                break;
                            }
                        }

                    }

                }
        );

        NumberAxis xAxisq = new NumberAxis();
        xAxisq.setLabel("Status");

        NumberAxis yAxisq = new NumberAxis();
        yAxisq.setLabel("Emotion Intensity");


        LineChart lineChart = new LineChart(xAxisq, yAxisq);

        XYChart.Series dataSeries1 = new XYChart.Series();
        dataSeries1.setName("JOY");

        for(int w=0,e=1;w<50 &&e<10;w+=8,e++)
        dataSeries1.getData().add(new XYChart.Data( e, out[w]));



        XYChart.Series dataSeries2 = new XYChart.Series();
        dataSeries2.setName("SURPRISE");

        for(int w=1,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries2.getData().add(new XYChart.Data( e, out[w]));

        XYChart.Series dataSeries3 = new XYChart.Series();
        dataSeries3.setName("FEAR");
        for(int w=2,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries3.getData().add(new XYChart.Data( e, out[w]));



        XYChart.Series dataSeries4 = new XYChart.Series();
        dataSeries4.setName("SADNESS");

        for(int w=3,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries4.getData().add(new XYChart.Data( e, out[w]));


        XYChart.Series dataSeries5 = new XYChart.Series();
        dataSeries5.setName("TRUST");

        for(int w=0,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries5.getData().add(new XYChart.Data( e, out[w]));

        XYChart.Series dataSeries6 = new XYChart.Series();
        dataSeries6.setName("DISGUST");

        for(int w=5,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries6.getData().add(new XYChart.Data( e, out[w]));

        XYChart.Series dataSeries7 = new XYChart.Series();
        dataSeries1.setName("anticipation");

        for(int w=6,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries7.getData().add(new XYChart.Data( e, out[w]));



        XYChart.Series dataSeries8 = new XYChart.Series();
        dataSeries8.setName("ANGER");

        for(int w=7,e=1;w<50 &&e<10;w+=8,e++)
            dataSeries8.getData().add(new XYChart.Data( e, out[w]));


        lineChart.getData().addAll(dataSeries1,dataSeries2,dataSeries3,dataSeries4,dataSeries5,dataSeries6,dataSeries7,dataSeries8);




//
//
//        ObservableList<XYChart.Series<String,Double>> lineChartData = FXCollections.observableArrayList(
//                new LineChart.Series<String,Double>("Joy", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[0]),
//                        new XYChart.Data<String,Double>("Status 2", out[8]),
//                        new XYChart.Data<String,Double>("Status 3", out[16]),
//                        new XYChart.Data<String,Double>("Status 4", out[24]),
//                        new XYChart.Data<String,Double>("Status 5", out[32]),
//                        new XYChart.Data<String,Double>("Status 6", out[40])
//
//                )),
//                new LineChart.Series<String,Double>("Surprise", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1", out[1]),
//                        new XYChart.Data<String,Double>("Status 2", out[9]),
//                        new XYChart.Data<String,Double>("Status 3", out[17]),
//                        new XYChart.Data<String,Double>("Status 4", out[25]),
//                        new XYChart.Data<String,Double>("Status 5", out[33]),
//                        new XYChart.Data<String,Double>("Status 6", out[41])
//
//                )),
//                new LineChart.Series<String,Double>("Fear", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[2]),
//                        new XYChart.Data<String,Double>("Status 2", out[10]),
//                        new XYChart.Data<String,Double>("Status 3", out[18]),
//                        new XYChart.Data<String,Double>("Status 4", out[26]),
//                        new XYChart.Data<String,Double>("Status 5", out[34]),
//                        new XYChart.Data<String,Double>("Status 6", out[42])
//
//                )),
//                new LineChart.Series<String,Double>("Sadness", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[3]),
//                        new XYChart.Data<String,Double>("Status 2", out[11]),
//                        new XYChart.Data<String,Double>("Status 3", out[19]),
//                        new XYChart.Data<String,Double>("Status 4", out[27]),
//                        new XYChart.Data<String,Double>("Status 5", out[35]),
//                        new XYChart.Data<String,Double>("Status 6", out[43])
//
//                )),
//                new LineChart.Series<String,Double>("Trust", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[4]),
//                        new XYChart.Data<String,Double>("Status 2", out[12]),
//                        new XYChart.Data<String,Double>("Status 3", out[20]),
//                        new XYChart.Data<String,Double>("Status 4", out[28]),
//                        new XYChart.Data<String,Double>("Status 5", out[36]),
//                        new XYChart.Data<String,Double>("Status 6", out[44])
//                )),
//
//                new LineChart.Series<String,Double>("Disgust", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[5]),
//                        new XYChart.Data<String,Double>("Status 2", out[13]),
//                        new XYChart.Data<String,Double>("Status 3", out[21]),
//                        new XYChart.Data<String,Double>("Status 4", out[29]),
//                        new XYChart.Data<String,Double>("Status 5", out[37]),
//                        new XYChart.Data<String,Double>("Status 6", out[45])
//
//                )),
//
//                new LineChart.Series<String,Double>("anticipation", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[6]),
//                        new XYChart.Data<String,Double>("Status 2", out[14]),
//                        new XYChart.Data<String,Double>("Status 3", out[22]),
//                        new XYChart.Data<String,Double>("Status 4", out[30]),
//                        new XYChart.Data<String,Double>("Status 5", out[38]),
//                        new XYChart.Data<String,Double>("Status 6", out[46])
//
//                )),
//
//                new LineChart.Series<String,Double>("Anger", FXCollections.observableArrayList(
//                        new XYChart.Data<String,Double>("Status 1",out[7]),
//                        new XYChart.Data<String,Double>("Status 2", out[15]),
//                        new XYChart.Data<String,Double>("Status 3", out[23]),
//                        new XYChart.Data<String,Double>("Status 4", out[31]),
//                        new XYChart.Data<String,Double>("Status 5", out[39]),
//                        new XYChart.Data<String,Double>("Status 6", out[47])
//
//                ))
//        );


        for(int s=0,t=1;t<=10;s+=8,t++)
        {
            sentimentPos[t]=out[s]+out[s+1]+out[s+4]+out[s+6];
        }

        for(int s=0,t=1;t<=10;s+=8,t++)
        {
            sentimentNeg[t]=out[s+2]+out[s+3]+out[s+5]+out[s+7];
        }


        //LineChart chart = new LineChart(xAxis, yAxis, lineChart);
        lineChart.setPrefSize(1200,700);




        Button moreSenti = new Button("Sentiment Analysis");
        moreSenti.setTranslateX(450);
        moreSenti.setTranslateY(700);
        moreSenti.setStyle("-fx-padding: 8 15 15 15;\n" +
                "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                "    -fx-background-radius: 8;\n" +
                "    -fx-background-color: \n" +
                "        linear-gradient(from 0% 93% to 0% 100%, #0B2058 0%, #030B21 100%),\n" +
                "        #030B21,\n" +
                "        #0B2058,\n" +
                "        radial-gradient(center 50% 50%, radius 100%, #143389, #09236B);\n" +
                "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                "    -fx-font-weight: bold;\n" +
                "    -fx-font-size: 1.5em;");
        moreSenti.setPrefSize(360, 40);
        moreSenti.setTextFill(Color.WHITE);
        moreSenti.setOnAction(e->
        {

            Group roota = new Group();
            //   stage.setScene(new Scene(root));
            Image backgrounda = new Image(getClass().getClassLoader().getResource("emotionSide.png").toString(), true);
            //   Image fusics = new Image("fusics.png");
            Canvas canvasa = new Canvas(1800,900);


            Button backa = new Button("Back");
            backa.setTranslateX(1100);
            backa.setTranslateY(650);
            backa.setStyle("-fx-padding: 8 15 15 15;\n" +
                    "    -fx-background-insets: 0,0 0 5 0, 0 0 6 0, 0 0 7 0;\n" +
                    "    -fx-background-radius: 8;\n" +
                    "    -fx-background-color: \n" +
                    "        linear-gradient(from 0% 93% to 0% 100%, #8d9092 0%, #717375 100%),\n" +
                    "        #8d9092,\n" +
                    "        #717375,\n" +
                    "        radial-gradient(center 50% 50%, radius 100%, #ffffff, #a1a3a6);\n" +
                    "    -fx-effect: dropshadow( gaussian , rgba(0,0,0,0.75) , 4,0,0,1 );\n" +
                    "    -fx-font-weight: bold;\n" +
                    "    -fx-font-size: 1.1em;");
            backa.setPrefSize(60, 30);
            backa.setOnAction(ed -> {
                try {
                    Main goBack = new Main();
                    goBack.start(stage);
                } catch (Exception ex) {
                    ex.printStackTrace();
                }
            });
            GraphicsContext gca = canvasa.getGraphicsContext2D();
            gca.drawImage(backgrounda,0,0);
            Scene scenea = new Scene(roota, 2000, 900);
            stage.setScene(scenea);
            stage.setFullScreen(true);
            stage.show();
            stage.setTitle("Sentiment Analysis");

            CategoryAxis xAxiss = new CategoryAxis();
            xAxiss.setLabel("Sentiment");
            xAxiss.getCategories().addAll("Positive", "Negative");

            NumberAxis yAxiss = new NumberAxis();
            yAxiss.setLabel("Intensity");

            StackedBarChart    stackedBarChart = new StackedBarChart(xAxiss, yAxiss);
            stackedBarChart.getAnimated();

            XYChart.Series dataSeriesSenti1 = new XYChart.Series();
            dataSeriesSenti1.setName("Positive Sentiment");

            for(int a=1;a<10;a++)
                dataSeriesSenti1.getData().add(new XYChart.Data("Status "+a, sentimentPos[a]));
            //    dataSeries1.getData().add(new XYChart.Data("Negative", 540));

            stackedBarChart.getData().add(dataSeriesSenti1);


            XYChart.Series dataSeriesSenti2 = new XYChart.Series();
            dataSeriesSenti2.setName("Negative Sentiment");
            for(int a=1;a<10 ;a++)
                dataSeriesSenti2.getData().add(new XYChart.Data("Status "+a  ,sentimentNeg[a]));


            stackedBarChart.getData().add(dataSeriesSenti2);

           stackedBarChart.setPrefSize(1100,700);
            roota.getChildren().addAll(canvasa,stackedBarChart,backa);


        });




        root.getChildren().addAll(canvas,lineChart,more,status1,status2,status3,status4,status5,status6,moreSenti);
 //   scan.nextLine();
//
//        for(int a=0,k=0;k<m;a++,k++)
//        {
//            if(a==8) {
//                a = 0;
//
//            }
//        series.getData().add(new XYChart.Data(a, out[k]));
//
//}
//
//
//        //Setting the data to Line chart
//        linechart.getData().add(series);
//
//        //Creating a Group object
//        Group root = new Group(linechart);

//        //Creating a scene object
//        Scene scene = new Scene(root, 1000, 1100);
//
//        //Setting title to the Stage
//        stage.setTitle("Line Chart");
//
//        //Adding scene to the stage
//        stage.setScene(scene);
//
//        //Displaying the contents of the stage
//        stage.show();





    }



}