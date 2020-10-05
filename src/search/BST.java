package search;

import javafx.scene.shape.VLineTo;

public class BST<Key extends Comparable<Key>, Value> {
    private Node root;

    private class Node {
        private Key key;
        private Value val;
        private Node left, right;
        private int N;

        public Node(Key key, Value val, int n) {
            this.key = key;
            this.val = val;
            this.N = n;
        }
    }

    public int size() {
        return size(root);
    }

    public int size(Node x) {
        if (x == null) {
            return 0;
        } else {
            return x.N;
        }
    }

    /**
     * 如果树是空的，则查找未命中。
     * 如果被查找的键和根节点（递归路径上的每一个节点）的键相等，查找命中。
     * 如果被查找的键较小就选择左子树，较大则选择右子树。
     *
     * @param key
     * @return
     */
    // 获取二叉搜索树对应键的值
    public Value get(Key key) {
        return get(root, key);
    }

    private Value get(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return get(x.left, key);
        } else if (cmp > 0) {
            return get(x.right, key);
        } else {
            return x.val;
        }
    }

    /**
     * 如果找到指定的key值，则更新为val，如果没有找到，则在恰当的位置新建节点
     *
     * @param key
     * @param value
     */
    public void put(Key key, Value value) {
        root = put(root, key, value);
    }

    private Node put(Node x, Key key, Value val) {
        if (x == null) {
            return new Node(key, val, 1);
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = put(x.left, key, val);
        } else if (cmp > 0) {
            x.right = put(x.right, key, val);
        } else {
            // 如果找到当前节点为需要更新的节点
            x.val = val;
        }
        //  更新当前节点的计数
        x.N = size(x.left) + size(x.right) + 1;

        // 往上回溯传递x
        return x;
    }

    // 获取最小值
    public Key min() {
        return min(root).key;
    }

    /**
     * 如果左链接为空，则最小值为根节点（这里的根节点依旧是递归的根节点，指的不仅是root节点），
     * 如果左链接非空，树中的最小键就是左子树种的最小键。
     *
     * @param x
     * @return
     */
    private Node min(Node x) {
        if (x.left == null) {
            return x;
        }
        return min(x.left);
    }

    // 获取最大值
    public Key max() {
        return max(root);
    }

    private Key max(Node x) {
        if (x.right == null) {
            return x.key;
        }
        return max(x.right);
    }

    // 找到小于等于key的最大值
    public Key floor(Key key) {
        Node t = floor(root, key);
        if (t == null) {
            return null;
        }
        return t.key;
    }

    /**
     * 如果给定的键key小于二叉查找树的根节点（递归的根节点）的键，那么小于等于key的最大键floor(key)一定在根节点的左子树中
     * 如果给定的键key大于二叉查找树的根结点（递归的根节点），只有当右子树存在小于等于key的节点时，小于等于key的最大键才会出现在右子树中，
     * 否则根节点就是小于等于key的最大键。
     *
     * @param x
     * @param key
     * @return
     */
    private Node floor(Node x, Key key) {
        // 1. 跳出条件
        if (x == null) {
            return null;
        }

        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp < 0) {
            return floor(x.left, key);
        }

        // 当key比当前节点大时，则进入右节点才能找到有可能的值
        Node t = floor(x.right, key);
        if (t == null) {
            return x;
        } else {
            return t;
        }

    }

    // 找到大于等于key的最小值
    public Key ceiling(Key key) {
        Node t = ceiling(root, key);
        if (t == null) {
            return null;
        }
        return t.key;
    }

    private Node ceiling(Node x, Key key) {
        // 1. 跳出条件
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp == 0) {
            return x;
        }
        if (cmp > 0) {
            return ceiling(x.right, key);
        }

        // 如果找到比key要小的
        Node t = ceiling(x.left, key);
        if (t == null) {
            return x;
        } else {
            return t;
        }
    }

    // 返回排名为k的节点
    public Key select(int k) {
        return select(root, k).key;
    }

    /**
     * 如果左子树（递归的左子树）中的结点数t大于k，就继续（递归的）在左子树中查找排名为k的键
     * 如果t等于k，就返回根结点中的键
     * 如果t小于k，就递归的在右子树中查找排名为（k-t-1）的键
     *
     * @param x
     * @param k
     * @return
     */
    private Node select(Node x, int k) {
        if (x == null) {
            return null;
        }
        int t = size(x.left);
        if (t > k) {
            return select(x.left, k);
        } else if (t < k) {
            return select(x.right, k - t - 1);
        } else {
            return x;
        }
    }

    // 找到对应键的排名
    public int rank(Key key) {
        return rank(key, root);
    }

    /**
     * 它的实现和select()类似，如果给定的键和根结点（递归的根节点）的键相等，返回左子树中的结点总数
     * 如果给定的键小于根节点，返回该键在左子树中的排名（递归计算）
     * 如果给定的键大于根节点，返回t+1加上它在右子树中的排名（递归计算）
     *
     * @param key
     * @param x
     * @return
     */
    private int rank(Key key, Node x) {
        if (x == null) {
            return 0;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            return rank(key, x.left);
        } else if (cmp > 0) {
            return 1 + size(x.left) + rank(key, x.right);
        } else {
            return size(x.left);
        }
    }

    // 删除最小的键
    public void deleteMin() {
        root = deleteMin(root);
    }

    /**
     * 要不断深入根节点的左子树中直到遇见一个空链接，
     * 然后将指向该节点的链接指向该节点的右子树（需要在递归调用中返回它的右链接）
     *
     * @param x
     * @return
     */
    private Node deleteMin(Node x) {
        // 1. 退出条件
        if (x.left == null) {
            return x.right;
        }
        // 2.递归逻辑
        x.left = deleteMin(x.left);
        x.N = size(x.left) + size(x.right) + 1;

        // 3.递归返回
        return x;
    }

    // 删除最大的键
    public void deleteMax() {
        root = deleteMax(root);
    }

    private Node deleteMax(Node x) {
        if (x.right == null) {
            return x.left;
        }
        x.right = deleteMax(x.right);
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    // 删除指定的键
    public void delete(Key key) {
        root = delete(root, key);
    }

    private Node delete(Node x, Key key) {
        if (x == null) {
            return null;
        }
        int cmp = key.compareTo(x.key);
        if (cmp < 0) {
            x.left = delete(x.left, key);
        } else if (cmp > 0) {
            x.right = delete(x.right, key);
        } else {
            // 判断左右子树是否为空
            if (x.left == null) {
                return x.right;
            }
            if (x.right == null) {
                return x.left;
            }
            // 1. 先记录target节点
            Node t = x;

            // 2. 把x指向target右子树的最小节点，该节点为替换target的BST节点
            x = min(t.right);

            // 3. 把x.right指向delete(t.right)后的根节点，即删除掉x节点本身的子树中
            x.right = deleteMin(t.right);

            // 4. 左子树指回target的左子树中
            x.left = t.left;
        }

        //5. 更新节点数量并返回该节点
        x.N = size(x.left) + size(x.right) + 1;
        return x;
    }

    public void show() {
        show(root);
        System.out.println();
    }

    private void show(Node x) {
        if (x == null) {
            return;
        }
        show(x.left);
        System.out.print(" " + x.key);
        show(x.right);
    }

    public static void main(String[] args) {
        int[] nums = {11, 1, 15, 4, 2, 17, 8, 13, 7, 3, 10, 9, 12, 6, 14};
        BST bst = new BST();
        for (int i = 0; i < nums.length; i++) {
            bst.put(nums[i], i);
        }

        System.out.println("Begin:");
        bst.show();

        System.out.println("min number:" + bst.min());
        System.out.println("max number:" + bst.max());
        System.out.println("floor:" + bst.floor(18));
        System.out.println("ceiling:" + bst.ceiling(5));
        System.out.println("select N:" + bst.select(5));
        System.out.println("rank N:" + bst.rank(6));

        System.out.println("delete minimum:");
        bst.deleteMin();
        System.out.println("minimum number:" + bst.min());

        System.out.println("delete maximum");
        bst.deleteMax();
        System.out.println("maximum number:" + bst.max());

        bst.show();
        System.out.println("delect random number key: 4");
        bst.delete(4);
        System.out.println("after delete select(4):" +bst.select(4));

        bst.show();

    }
}
