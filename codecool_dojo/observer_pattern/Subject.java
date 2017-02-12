package observer;

import java.util.ArrayList;
import java.util.List;

public class Subject {

    private int state;
    private List<Observer> oList;

    public int getState() {
        return state;
    }

    public Subject() {
        oList = new ArrayList<>();
    }

    public void setState(int state) {
        this.state = state;
        alarm();
    }

    public void attach(Observer observer) {
        oList.add(observer);
    }

    public void alarm() {
        oList.forEach((item)->item.refresh(state));
    }
}
