import javax.swing.*;
import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;

public class FileInspector {
    public static void main(String[] args) {

        JFileChooser chooser = new JFileChooser();
        File selectedFile;
        String rec = "";

        try {
            // use the toolkit to get the current working directory of the IDE
            File workingDirectory = new File(System.getProperty("user.dir"));

            chooser.setCurrentDirectory(workingDirectory);

            if (chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION) {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                // wrap a BufferedReader around a lower level BufferedInputStream
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                int line = 0;
                int words = 0;
                int characters = 0;
                String dataElements[];
                while (reader.ready()) {
                    rec = reader.readLine();
                    characters = characters + rec.length();
                    dataElements = rec.split("\\W");
                    words = words + dataElements.length;
                    line++;
                }
                reader.close(); // must close the file to seal it and flush buffer
                System.out.println("\n\nData file read!\n");
                System.out.println("Summary");
                System.out.println(selectedFile.getName());
                System.out.println("Lines: " + line);
                System.out.println("Words: " + words);
                System.out.println("Characters: " + characters);


            } else  // User closed the chooser without selecting a file
            {
                System.out.println("No file selected! ... exiting.\nRun the program again and select a file.");
            }
        }
        catch (FileNotFoundException e)
        {
            System.out.println("File not found.");
            e.printStackTrace();
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}