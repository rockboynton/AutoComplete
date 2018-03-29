package boyntonrl;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;

public abstract class AbstractAutoCompleter implements AutoCompleter{

    protected List<String> words;

    public AbstractAutoCompleter(List<String> words) {
        this.words = words;
    }

    public AbstractAutoCompleter(){
    }

    @Override
    public void initialize(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        File file = new File(path.toString());
        int dotIndex = path.toString().lastIndexOf(".");
        String ext = path.toString().substring(dotIndex);
        try(Scanner fileIn = new Scanner(file)) {
            if (ext.equals(".csv")) {
                while (fileIn.hasNextLine()) {
                    String line = fileIn.nextLine();
                    String[] fields = line.split(",");
                    // add the second field in the csv file, which should be the domain name
                    words.add(fields[1]);
                }
            } else if (ext.equals(".txt")) {
                while (fileIn.hasNextLine()) {
                    words.add(fileIn.nextLine());
                }
            } else {
                throw new IOException("wrong file here");
            }
        }
    }
}
