public class NumberGenerator
{
    public static int[] generateNumbers(int n)
    {
        int[] numbers = new int[n];
        for (int i = 0; i < n; i++)
        {
            int number = (int)(Math.random() * 1000);
            numbers[i] = number;
        }
        return numbers;
    }
}