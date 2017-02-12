import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        String outputFile = "C:\\Users\\Én\\Desktop\\Dojo_2016_09_29\\src\\output.ser";
        List<Person> personList = new ArrayList<>(Arrays.asList(
                new Person("Ronai", "Mate", 22),
                new Person("Bathory", "Alex", 23),
                new Person("Skoda", "Andras", 25)
        ));
        try {
            ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(outputFile));
            oos.writeObject(personList);
            oos.flush();
            oos.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        new ShowPerson(Language.EN).printPersons();
    }
}
