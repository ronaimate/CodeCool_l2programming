package observer;

public abstract class Observer {
    public abstract void refresh(int value);
    protected Subject subject;

}
