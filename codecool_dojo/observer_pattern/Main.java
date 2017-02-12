package observer;

/**
 * Created by Én on 2016. 09. 02..
 */
public class Main {
    public static void main(String[] args) {
        Subject subject = new Subject();
        ConsolObserver consolObserver = new ConsolObserver(subject);
        subject.setState(8);

    }
}
