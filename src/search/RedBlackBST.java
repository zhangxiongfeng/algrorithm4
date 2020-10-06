package search;

public class RedBlackBST<Key extends Comparable<Key>, Value> {
    private static final boolean RED = true;
    private static final boolean BLACK = false;
    private Node root;

    public class Node {
        Key key;
        Value val;
        Node left, right;
        int N;
        boolean color;

        public Node(Key key, Value val, int n, boolean color) {
            this.key = key;
            this.val = val;
            N = n;
            this.color = color;
        }
    }

    private boolean isRed(Node x) {
        if (x == null) {
            return false;
        }
        return x.color == RED;
    }

    public int size(Node x) {
        return x.N;
    }

    /**
     * 将红色右链接转换为红色左链接，称为左旋转
     *
     * @param h
     * @return
     */
    private Node rotateLeft(Node h) {
        Node x = h.right;
        // 1. 父节点h先下，指向子节点的左链接
        h.right = x.left;

        // 2. 子节点x上移，左节点指向h
        x.left = h;

        // 3. 改变x属性
        x.color = h.color;
        x.N = h.N;

        // 4. 改变h属性(旋转后h必为红链接，为保持一致性)
        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;

        return x;
    }

    /**
     * 将红色左链接变为红色右链接称为右旋转
     *
     * @param h
     * @return
     */
    private Node rotateRight(Node h) {
        Node x = h.left;
        h.left = x.right;

        x.right = h;

        x.color = h.color;
        x.N = h.N;

        h.color = RED;
        h.N = size(h.left) + size(h.right) + 1;
        return x;
    }

    private void flipColors(Node h) {
        h.color = RED;
        h.left.color = BLACK;
        h.right.color = BLACK;
    }

    public void put(Key key, Value val) {
        root = put(root, key, val);
        root.color = BLACK;
    }

    private Node put(Node h, Key key, Value val) {
        if (h == null) {
            // 插入时为了保证平衡性，color必须为red
            return new Node(key, val, 1, RED);
        }
        int cmp = key.compareTo(h.key);
        if (cmp < 0) {
            h.left = put(h.left, key, val);
        } else if (cmp > 0) {
            h.right = put(h.right, key, val);
        } else {
            h.val = val;
        }
        // 判断当前h结点的状态并对其进行修复操作
        if (h.left.color != RED && h.right.color == RED) {
            rotateLeft(h);
        }
        if (h.left.color == RED && h.left.left.color == RED) {
            rotateRight(h);
        }
        if (h.left.color == RED && h.right.color == RED) {
            flipColors(h);
        }
        h.N = size(h.left) + size(h.right) + 1;
        return h;
    }


}
