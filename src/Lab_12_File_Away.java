import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Spliterator;

import static java.nio.file.StandardOpenOption.CREATE;

public class Lab_12_File_Away {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String line = "null";
        ArrayList<String>lines = new ArrayList<>();
        ArrayList<String>words = new ArrayList<>();

        try{
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            // Opens the file chooser
            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION){
                //If users selects a file
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();

                Path path = Paths.get("");

                InputStream in = new BufferedInputStream(Files.newInputStream(file,CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int lineNumber=0;
                int lineLength =0;
                while(reader.ready()){
                    line = reader.readLine();
                    lines.add(line);
                    lineLength = lineLength + line.length();
                    lineNumber ++;
                    System.out.printf("\n%-4d %-60s",lineNumber,line);

                    String[] wordsInArray = line.split(" ");
                    for (String word: wordsInArray){
                        words.add(word);
                    }
//                    System.out.println(words);
                }

                int wordsInArray = words.size();

                System.out.println();
                System.out.println("\nThe name of the file read was: " + chooser);
                System.out.println("The amount of lines read was: " + lineNumber);
                System.out.println("The amount of words read was: " + wordsInArray);
                System.out.println("The amount of characters read was: " + lineLength);
                reader.close();
                System.out.println("File read successfully!");

            }else{
                //If an error occurred or user canceled
                System.out.println("Failed to choose a file to process");
                System.out.println("Run the program again");
                System.exit(0);
            }

        }catch (IOException ex) {
            ex.printStackTrace();
        }finally {

        }


    }
}
