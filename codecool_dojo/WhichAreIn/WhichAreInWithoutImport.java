public class WhichAreInWithoutImport {

    public static String[] inArray(String[] array1, String[] array2) {
        String result = "";
        for (String array2Item : array2) {
            for (String array1Item : array1) {
                if (array2Item.contains(array1Item) && !result.contains(array1Item)) {
                    result += array1Item + " ";
                }
            }
        }
        String[] resultArray = result.trim().split(" ");
        sortStringExchange(resultArray);
        return resultArray;
    }

    public static void sortStringExchange(String x[]) {
        int i, j;
        String temp;

        for (i = 0; i < x.length - 1; i++) {
            for (j = i + 1; j < x.length; j++) {
                if (x[i].compareToIgnoreCase(x[j]) > 0) {
                    temp = x[i];
                    x[i] = x[j];
                    x[j] = temp;

                }
            }
        }
    }


    public static void main(String[] args) {
        String a[] = new String[]{"live", "strong", "arp",};
        String b[] = new String[]{"lively", "alive", "harp", "sharp", "armstrong"};
        String[] result = inArray(a, b);
        for(String item : result)
        {
            System.out.println(item);
        }
    }
}
