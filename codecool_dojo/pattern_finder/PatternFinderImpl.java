public class PatternFinderImpl implements PatternFinder {

    @Override
    public int countPattern(int[] numbers, int... pattern) {
        int count = 0;
        if (pattern != null || numbers.length >= pattern.length) {
            int patternIterator = 0;
            for (int i = 0; i < numbers.length; i++) {
                if (numbers[i] == pattern[patternIterator]) {
                    if (patternIterator < pattern.length - 1) {
                        patternIterator++;
                    }
                } else {
                    patternIterator = 0;
                }
                if (patternIterator == pattern.length-1) {
                    count++;
                    patternIterator = 0;
                }
            }
        }
        return count;
    }
}