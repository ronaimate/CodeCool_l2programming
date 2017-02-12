package sortedlinkedlist;

public class LinkedListItem implements Comparable {

    private String name;
    private LinkedListItem next;

    public void setNext(LinkedListItem next) {
        this.next = next;
    }

    public LinkedListItem(String name) {
        this.name = name;
    }

    public String getName() {
        return name;
    }

    @Override
    public int compareTo(Object o) {
        String objectName = ((LinkedListItem) o).getName();
        if (objectName.length() == name.length()) {
            return 0;
        } else if (objectName.length() > name.length()) {
            return 1;
        }
        return -1;
    }

    @Override
    public String toString() {
        return name;
    }

    public LinkedListItem next() {
        return next;
    }


}
