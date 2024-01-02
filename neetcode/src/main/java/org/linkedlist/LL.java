package org.linkedlist;

public class LL {

    private Node head;
    private Node tail;
    private int size;

    public LL() {
        this.size = 0;
    }


    public void insertFirst(int val){
        // create a new node and point its next to head
        Node node = new Node(val);
        node.next = head;
        // current head should point to the new node
        head = node;
        // if first node, then init tail
        if (tail == null)
            tail = head;

        size++;
    }

    public void insert(int val, int index){
        if (index == 0){
            insertFirst(val);
            return;
        }
        if (index == size){
            insertLast(val);
            return;
        }

        Node temp = head;
        for (int i = 1; i < index; i++) {
            temp = temp.next;
        }

        Node node = new Node(val, temp.next);
        temp.next = node;
    }

    public void insertLast(int val){
        if (tail == null){
            insertFirst(val);
            return;
        }

        Node node = new Node(val);
        tail.next = node;
        tail = node;
        size++;
    }

    public int deleteFirst(){
        int val = head.val;
        head = head.next;
        if (head == null)
            tail = null;

        return val;
    }

    public Node get(int index){
        Node node = head;
        for (int i = 0; i < index; i++) {
            node = node.next;
        }
        return node;
    }

    public int deleteLast(){
        if (size <= 1){
            int val = deleteFirst();
            return val;
        }

        Node node = get(size - 2);
        int val = tail.val;
        tail = node;
        tail.next = null;

        return val;
    }

    public int delete(int index){
        if (index == 0){
            return deleteFirst();
        }
        if (index == size - 1){
            return deleteLast();
        }

        Node prev = get(index - 1);
        int val = prev.next.val;

        prev.next = prev.next.next;

        return val;
    }

    public Node find(int value){
        Node node = head;
        while (node != null){
            if (node.val == value)
                return node;
            node = node.next;
        }
        return null;
    }

    public void insertRec(int val, int index){
        head = insertRec(val, index, head);
    }

    private Node insertRec(int val, int index, Node node){
        if (index == 0){
            Node temp = new Node(val, node);
            size++;
            return temp;
        }

        node.next = insertRec(val, index-1, node.next);
        return node;
    }

    // https://leetcode.com/problems/remove-duplicates-from-sorted-list/
    public void duplicates(){
        Node node = head;
        while (node.next != null){
            if (node.val == node.next.val){
                node.next = node.next.next;
                size--;
            }else {
                node = node.next;
            }
        }
        tail = node;
        tail.next = null;
    }

    public void display(){
        Node temp = head;
        while (temp != null){
            System.out.print(temp.val + " -> ");
            temp = temp.next;
        }
        System.out.print("END\n");
    }

    private class Node{
        private int val;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int val, Node next) {
            this.val = val;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        LL ll = new LL();
        ll.insertFirst(3);
        ll.insertFirst(2);
        ll.insertFirst(8);
        ll.insertFirst(17);
        ll.insertLast(99);
        ll.insert(100, 3);
        ll.display();
        System.out.println(ll.deleteFirst());
        ll.display();
        System.out.println(ll.deleteLast());
        ll.display();
        System.out.println(ll.delete(2));
        ll.insertRec(88, 2);
        ll.display();

    }
}
