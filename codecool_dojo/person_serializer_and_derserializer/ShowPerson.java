import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.List;

public class ShowPerson {
    private Language language;
    private List<Person> personList;

    public ShowPerson(Language language) {
        this.language = language;
    }

    private void personDeserializer() {
        String outputFile = "C:\\Users\\Én\\Desktop\\Dojo_2016_09_29\\src\\output.ser";
        try {
            ObjectInputStream ois = new ObjectInputStream(new FileInputStream(outputFile));
            personList = (List<Person>) ois.readObject();
            ois.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    private void personStringCreatorAndPrinter(String nameStringOne, String nameStringTwo, int age)
    {
        System.out.println((nameStringOne + " " + nameStringTwo + " " + age));
    }

    public void printPersons() {
        personDeserializer();
        switch (language) {
            case HU:
                personList.forEach((currPerson) ->
                        personStringCreatorAndPrinter(currPerson.getLastName(), currPerson.getFirstName(), currPerson.getAge()));
                break;
            case EN:
                personList.forEach((currPerson) ->
                        personStringCreatorAndPrinter(currPerson.getFirstName(), currPerson.getLastName(), currPerson.getAge()));
                break;
            default:
                System.out.println("Not imlemented language");
        }
    }
}
