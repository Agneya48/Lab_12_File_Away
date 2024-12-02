import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Scanner;

import static java.nio.file.StandardOpenOption.CREATE;

public class DataSaver {
    public static void main(String[] args) {

        ArrayList<String> recs = new ArrayList<>();

        Scanner input = new Scanner(System.in);
        boolean dataEntryDone = false;
        String firstName = "";
        String lastName = "";
        String idNumber = "";
        String email = "";
        String yearofBirth = "";
        String dataEntry = "";
        String div = ", ";


        do {
            dataEntry = "";
            firstName = SafeInput.getNonZeroLenString(input, "Enter First Name");
            lastName = SafeInput.getNonZeroLenString(input, "Enter Last Name");
            idNumber = SafeInput.getRegExString(input, "Enter ID [xxxxxx]", "\\d{6}");
            email = SafeInput.getNonZeroLenString(input, "Enter email");
            yearofBirth = SafeInput.getRegExString(input, "Enter year of birth", "\\d{4}");

            dataEntry = firstName + div + lastName + div + idNumber + div + email + div + yearofBirth;
            System.out.println();
            System.out.println(dataEntry);
            recs.add(dataEntry);
            System.out.println(recs);

            dataEntryDone = SafeInput.getYNConfirm(input, "Data entry complete? [Y/N]");

        } while (!dataEntryDone);

        String fileName = SafeInput.getNonZeroLenString(input, "Enter file name to save");
        fileName = fileName + ".csv";

        File workingDirectory = new File(System.getProperty("user.dir"));
        Path file = Paths.get(workingDirectory.getPath() + "\\src\\" + fileName);

        try
        {   OutputStream out = new BufferedOutputStream(Files.newOutputStream(file, CREATE));
            BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(out));

            for(String rec : recs)
            {
                writer.write(rec, 0, rec.length());
                writer.newLine();  // adds the new line

            }
            writer.close();
            input.close();
            System.out.println("Data file written!");
        }
        catch (IOException e)
        {
            e.printStackTrace();
        }

    }
}
