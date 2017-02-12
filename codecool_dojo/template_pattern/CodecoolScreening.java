package template_pattern;

public abstract class CodecoolScreening {
    abstract void initialTest();
    abstract void logicalTest();
    abstract void languageTest();
    abstract void createVideo();

    void screen()
    {
        initialTest();
        logicalTest();
        languageTest();
        createVideo();
    }
}
