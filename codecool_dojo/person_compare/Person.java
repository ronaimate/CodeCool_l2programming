import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class Person {
    private String name;
    private Integer age;

    public Person(String name, int age) {
        this.name = name;
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public Integer getAge() {
        return age;
    }

    public static Person parseStringToPerson(String personString) {
        String[] splittedPersonString = personString.split(",");
        return new Person(splittedPersonString[0], Integer.valueOf(splittedPersonString[1]));
    }

    @Override
    public String toString() {
        return name + "," + age;
    }

    public static void main(String[] args) {
        String inputFile = "C:\\Users\\Én\\Desktop\\untitled\\names.txt", outputFile = "C:\\Users\\Én\\Desktop\\untitled\\out.txt", line;
        HashSet<String> personHashSet = new HashSet<>();
        List<Person> outputList = new ArrayList<>();
        try {
            BufferedReader br = new BufferedReader(new FileReader(inputFile));
            BufferedWriter bw = new BufferedWriter(new FileWriter(outputFile, false));
            while ((line = br.readLine()) != null) {
                Person currPersom = Person.parseStringToPerson(line);
                outputList.add(currPersom);
                personHashSet.add(currPersom.getName());
            }
            br.close();
            outputList.sort((o1, o2) -> {
                return (o1.getName().compareTo(o2.getName()) != 0 ? o1.getName().compareTo(o2.getName()) : o1.getAge().compareTo(o1.getAge()));
            });
            for (Person p : outputList) {
                bw.write(p.toString() + "\n");
            }
            bw.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        System.out.println("PersonList size without duplication: " + personHashSet.size());
        System.out.println("OutputList size: " + outputList.size());
    }
}
