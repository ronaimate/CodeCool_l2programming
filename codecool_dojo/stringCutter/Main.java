import java.util.ArrayList;
import java.util.List;

public class Main {

    private static String[] getCustomSplittedString(String s, int n) {
        if(n <= 0 || s .length() == 0)
        {
            return null;
        }

        List<String> resultStringList = new ArrayList<>();
        StringBuilder sb = new StringBuilder();
        for (int i = 0; i < s.length(); i++) {
            sb.append(s.charAt(i));
            if ((i + 1) % n == 0) {
                resultStringList.add(sb.toString());
                sb = new StringBuilder();
            }
        }
        if(sb.length() != 0)
        {
            resultStringList.add(sb.toString());
        }
        return resultStringList.toArray(new String[resultStringList.size()]);
    }

    public static void main(String[] args) {
        for (String s : getCustomSplittedString("Hy Guys", 3)) {
            System.out.println(s);
        }
    }
}