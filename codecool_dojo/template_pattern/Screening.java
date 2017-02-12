package template_pattern;

public class Screening extends CodecoolScreening {
    @Override
    void initialTest() {
        System.out.println("Screening.initialTest");
    }

    @Override
    void logicalTest() {
        System.out.println("Screening.logicalTest");
    }

    @Override
    void languageTest() {
        System.out.println("Screening.languageTest");
    }

    @Override
    void createVideo() {
        System.out.println("Screening.createVideo");
    }
}
