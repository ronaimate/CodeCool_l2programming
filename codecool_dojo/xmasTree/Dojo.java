import java.util.stream.IntStream;

public class Dojo {
    public static void printXmasTree(String inputString) {
        IntStream.range(0, inputString.length()).forEach(i -> System.out.print(new String(new char[inputString.length() - (i + 1)]).replace("\0", " ")
                + new String(new char[i + (i + 1)]).replace('\u0000', inputString.charAt(i)) + "\n"));
    }

    public static void main(String[] args) {
        printXmasTree("merry");
    }
}
