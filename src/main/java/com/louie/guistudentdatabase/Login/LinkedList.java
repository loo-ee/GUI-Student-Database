package com.louie.guistudentdatabase.Login;

public class LinkedList<T> {
    private class NodeList<E> {
        private E element;
        private NodeList<E> next;

        private NodeList() {}
    }

    private NodeList<T> head = new NodeList<>();
    private String course;
    private String listName;

    public LinkedList() {
        head = null;
    }

    public void setListName(String name) {
        this.listName = name;
    }

    public String getListName() {
        return this.listName;
    }

    public String getCourse() {
        return this.course;
    }

    public void appendList(T val) {
        NodeList<T> newNode;
        NodeList<T> nodePtr = head;

        newNode = new NodeList<T>();
        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        }
        else {
            while (nodePtr.next != null) {
                nodePtr = nodePtr.next;
            }
            nodePtr.next = newNode;
        }
    }

    public T returnNode(int index) {
        NodeList<T> nodePtr = head;
        NodeList<T> previousNode = null;

        for (int i = 0; i <index+1; i++) {
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }
        assert previousNode != null;
        return previousNode.element;
    }

    public T returnNode(String toString) {
        NodeList<T> nodePtr = head;
        T targetElement = null;

        while(nodePtr != null) {
            String nodeHolder = nodePtr.element.toString();

            if (nodeHolder.compareTo(toString) == 0) {
                targetElement = nodePtr.element;
            }
            nodePtr = nodePtr.next;
        }
        return targetElement;
    }

    public void insertList(int index, T val) {
        NodeList<T> nodePtr;
        NodeList<T> previousNode;
        NodeList<T> newNode = new NodeList<>();

        newNode.element = val;
        newNode.next = null;

        nodePtr = head;
        previousNode = null;

        for (int i=0; i<index; i++) {
            previousNode = nodePtr;
            nodePtr = nodePtr.next;
        }

        if (previousNode == null) {
            head = newNode;
        }
        else {
            previousNode.next = newNode;
        }
        newNode.next = nodePtr;
    }

    public void insertList(T val) {
        NodeList<T> nodePtr = head;
        NodeList<T> previousNode = null;
        NodeList<T> newNode = new NodeList<>();
        boolean onLoop = true;

        newNode.element = val;
        newNode.next = null;

        if (head == null) {
            head = newNode;
        }
        else {
            while (nodePtr != null && onLoop) {
                String elementHolder = nodePtr.element.toString();
                String valHolder = val.toString();

                if (elementHolder.compareTo(valHolder) < 0) {
                    previousNode = nodePtr;
                    nodePtr = nodePtr.next;
                }
                else {
                    onLoop = false;
                }
            }

            if (previousNode == null) {
                head = newNode;
            }
            else {
                previousNode.next = newNode;
            }
            newNode.next = nodePtr;
        }
    }

    public boolean checkIfNull() {
        return head == null;
    }

    public int getListSize() {
        NodeList<T> nodePtr = head;
        int size = 0;

        while(nodePtr != null) {
            size += 1;
            nodePtr = nodePtr.next;
        }
        return size;
    }

    public void displayListContents(String listType) {
        NodeList<T> nodePtr = head;
        int counter = 1;

        if (head == null) {
            System.out.println("\nNO DATA");
            return;
        }

        System.out.println("\n[INFO] Showing list of " + listType + "s");

        while (nodePtr != null) {
            System.out.println(listType + " #" + (counter++) + ":\n" + nodePtr.element);
            nodePtr = nodePtr.next;
        }
    }
 
    public void deleteNode(T val) {
        NodeList<T> nodePtr = null;
        NodeList<T> previousNode = null;
        
        if (head.element == val) {
            head = head.next;
        }
        else {
            nodePtr = head;

            while (nodePtr != null && nodePtr.element != val) {
                previousNode = nodePtr;
                nodePtr = nodePtr.next;
            }
                
            if (nodePtr != null) {
                previousNode.next = nodePtr.next;
            }
        }
    }

    public boolean validateNode(String name) {
        NodeList<T> nodePtr = head;

        while (nodePtr != null) {
            if (nodePtr.element.toString().compareTo(name) == 0) {
                return true;
            }
            nodePtr = nodePtr.next;
        }
        return false;
    }

    public void clearList() {
        NodeList<T> nodePtr = head;

        while (head != null) {
            if (nodePtr.next == null) {
                head = null;
            }
            else {
                nodePtr = head.next;
                head = null;
                head = nodePtr;
            }
        }
    }
}
