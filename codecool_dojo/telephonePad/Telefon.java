import java.util.HashMap;
import java.util.Map;

public class Telefon {
    public static char getChar(int key, int count) {
        Map<Integer, char[]> dictionary = new HashMap<>();
        dictionary.put(0, new char[]{' '});
        dictionary.put(2, new char[]{'a', 'b', 'c'});
        dictionary.put(3, new char[]{'d', 'e', 'f'});
        dictionary.put(4, new char[]{'g', 'h', 'i'});
        dictionary.put(5, new char[]{'j', 'k', 'l'});
        dictionary.put(6, new char[]{'m', 'n', 'o'});
        dictionary.put(7, new char[]{'p', 'q', 'r', 's'});
        dictionary.put(8, new char[]{'t', 'u', 'v'});
        dictionary.put(9, new char[]{'w', 'x', 'y', 'z'});

        int actualArrayLenght = dictionary.get(key).length;
        while (count > actualArrayLenght) {
            count -= actualArrayLenght;
        }
        return dictionary.get(key)[count - 1];
    }

    public static String getNumberCount(String numberString) {
        int counter = 0;
        char lastCharacter = numberString.charAt(0);
        int penultIndex = 0;
        String result = "";
        for (int i = 0; i < numberString.length(); i++) {
            char actualCharacter = numberString.charAt(i);
            if (actualCharacter != lastCharacter) {
                result += getChar(Integer.valueOf(String.valueOf(lastCharacter)), counter);
                counter = 1;
                penultIndex = i;
            } else {
                counter++;
            }
            lastCharacter = numberString.charAt(i);
        }
        result += getChar(Integer.valueOf(String.valueOf(numberString.charAt(penultIndex))), (numberString.length() - penultIndex));
        return result;
    }

    public static void getText(String stringNumber) {
        String result = "";
        if (stringNumber.contains("1")) {
            String[] splittedStringNumber = stringNumber.split("1");
            for (String actualString : splittedStringNumber) {
                String getNumberCountString = getNumberCount(actualString);
                result += getNumberCountString;
            }
        } else {
            result = getNumberCount(stringNumber);
        }
        System.out.println(result);
    }

    public static void main(String[] args) {
        getText("3666088777733130866602221255515550633066616606999022233555155574466616633");
    }
}