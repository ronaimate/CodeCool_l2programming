package sortedlinkedlist;

/**
 * Created by Én on 2016. 09. 02..
 */
public class Main {
    public static void main(String[] args) {
        LinkedListItem item1 = new LinkedListItem("i");
        LinkedListItem item2 = new LinkedListItem("ii");
        LinkedListItem item3 = new LinkedListItem("iii");
        LinkedListItem item4 = new LinkedListItem("iiii");

        SortedLinkedList sll = new SortedLinkedList();
        sll.setRoot(item2);
        sll.insert(item4);
        sll.insert(item3);
        sll.insert(item1);
        sll.insert(new LinkedListItem("iiiii"));

        LinkedListItem temp = sll.getRoot();
        int i = 0;
        while (temp.next() != null) {
            System.out.println(i + temp.getName());
            i++;
            temp = temp.next();
        }
        System.out.println(i + temp.getName());
    }
}
