package search;

public class SequentialSearchST <Key, Value>{
    private Node first;

    private class Node {
        Key key;
        Value value;
        Node next;

        public Node(Key key, Value value, Node next) {
            this.key = key;
            this.value = value;
            this.next = next;
        }
    }

    public Value get(Key key) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                return x.value;
            }
        }
        return null;
    }

    public void put(Key key, Value val) {
        for (Node x = first; x != null; x = x.next) {
            if (x.key.equals(key)) {
                x.value = val;
                return;
            }
        }
        first = new Node(key, val, first);
    }

    public static void main(String[] args) {
        int[] nums={11,1,15,4,2,16,8,13,7,3,10,9,5,12,6,14};

        SequentialSearchST st = new SequentialSearchST();
        for (int i = 0; i < nums.length; i++) {
            st.put(i, nums[i]);
        }
        for (int i = 0; i < nums.length; i++) {
            System.out.print(" " + st.get(i));
        }
    }
}
