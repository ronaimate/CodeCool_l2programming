import java.util.Arrays;
import java.util.TreeSet;

public class WhichAreIn {

    public static String[] inArray(String[] array1, String[] array2) {
        TreeSet<String> resultString = new TreeSet<String>();
        for (String array2Item : array2) {
            for (String array1Item : array1) {
                if (array2Item.contains(array1Item)) {
                    resultString.add(array1Item);
                }
            }
        }
        return resultString.toArray(new String[resultString.size()]);
    }

    public static void main(String[] args) {
        String a[] = new String[]{"live", "strong", "arp",};
        String b[] = new String[]{"lively", "alive", "harp", "sharp", "armstrong"};
        System.out.println(Arrays.deepToString(inArray(a, b)));
    }
}
