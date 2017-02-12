public class Main
{
    public static void dumpPatternCount(PatternFinder patternFinder)
    {
        int[] myTomb = new int[]{1, 1, 2, 10, 11, 10, 11, 10, 11};
//        System.out.println(patternFinder.countPattern(NumberGenerator.generateNumbers(1000), 10,11));
        System.out.println(patternFinder.countPattern(myTomb, 10,11));
    }

    public static void main(String[] args)
    {
        PatternFinder patternFinder = new PatternFinderImpl();
        Main.dumpPatternCount(patternFinder);
    }

}