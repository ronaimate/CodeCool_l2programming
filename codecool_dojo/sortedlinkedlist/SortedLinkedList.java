package sortedlinkedlist;

public class SortedLinkedList {
    private LinkedListItem root;

    public LinkedListItem getRoot() {
        return root;
    }

    public void setRoot(LinkedListItem root) {
        this.root = root;
    }

    public void insert(LinkedListItem linkedListItem)
    {
        LinkedListItem temp = root;
        boolean changed = false;
        int different = temp.compareTo(linkedListItem);
        if (temp.next() == null)
        {
            if(different == -1) {
                temp.setNext(linkedListItem);
                return;
            }
            else if (different == 0)
            {
                System.out.println("first equal");
                return;
            }
            else
            {
                root = linkedListItem;
                root.setNext(temp);
                return;
            }
        }
        else {
            different = temp.compareTo(linkedListItem);
            if(different == 0)
            {return;}
            else if(different == 1)
            {LinkedListItem lliTemp = root;
                root = linkedListItem;
                insert(lliTemp);
                return;
            }
        }
        while (temp.next() != null)
        {
            changed = true;
            different = temp.next().compareTo(linkedListItem);
            if(different == -1)
            {
                temp = temp.next();
            }
            else if(different == 0)
            {
                System.out.println("equal");
                return;
            }
            else if(different == 1)
            {
                LinkedListItem lliTemp = temp.next();
                temp.setNext(linkedListItem);
                insert(lliTemp);
                return;
            }
        }
        if(changed)
        {
            temp.setNext(linkedListItem);
        }
    }

    public LinkedListItem find(String s)
    {
        LinkedListItem temp = root;
        LinkedListItem item = new LinkedListItem(s);
        if (root.compareTo(item) == 0) {
            return item;
        }
        while (temp.next() != null) {
            if (temp.compareTo(item) == 0 ) {
                return item;
            }
        }
        return null;
    }


}
