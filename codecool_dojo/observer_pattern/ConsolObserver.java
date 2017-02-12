package observer;

/**
 * Created by Én on 2016. 09. 02..
 */
public class ConsolObserver extends Observer {
    @Override
    public void refresh(int value) {
        System.out.println(value);
    }

    public ConsolObserver(Subject subject) {
        this.subject = subject;
        this.subject.attach(this);
    }
}
