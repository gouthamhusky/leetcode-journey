package org.linkedlist;

public class DLL {

    private Node head;

    public void insertFirst(int val){
        Node node = new Node(val);

        node.next = head;
        node.prev = null;
        if (head != null)
            head.prev = node;
        head = node;
    }

    public void insertLast(int val){
        Node node = new Node(val);
        if (head == null){
            node.prev = null;
            head = node;
            return;
        }

        Node trav = head;
        while (trav.next != null)
            trav = trav.next;

        trav.next = node;
        node.next = null;
        node.prev = trav;
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

    public void insert(int after, int val){
        Node p = find(after);
        if (p == null){
            System.out.println("does not exist");
            return;
        }
        Node node = new Node(val);
        node.next = p.next;
        p.next = node;
        node.prev = p;
        if (node.next != null)
            node.next.prev = node;
    }

    public void display(){
        Node node = head;
        Node last = null;
         while (node != null){
             System.out.print(node.val + " -> ");
             last = node;
             node = node.next;
         }
        System.out.println();

         while (last != null){
             System.out.print(last.val + " -> ");
             last = last.prev;
         }
        System.out.println();
    }

    private class Node{
        private int val;
        private Node prev;
        private Node next;

        public Node(int val) {
            this.val = val;
        }

        public Node(int value, Node prev, Node next) {
            this.val = value;
            this.prev = prev;
            this.next = next;
        }
    }

    public static void main(String[] args) {
        DLL list = new DLL();
        list.insertFirst(3);
        list.insertFirst(2);
        list.insertFirst(8);
        list.insertFirst(17);
        list.display();
        list.insertLast(20);
        list.insert(8, 65);
        list.display();

    }
}
