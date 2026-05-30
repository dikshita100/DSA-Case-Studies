class Node {
    int bookId;
    String title;
    int height;
    Node left, right;

    Node(int bookId, String title) {
        this.bookId = bookId;
        this.title = title;
        this.height = 1;
    }
}

class AVLTree {

    int height(Node n) {
        return (n == null) ? 0 : n.height;
    }

    int getBalance(Node n) {
        return (n == null) ? 0 : height(n.left) - height(n.right);
    }

    Node rightRotate(Node y) {
        System.out.println("Right Rotation at node " + y.bookId);

        Node x = y.left;
        Node t2 = x.right;

        x.right = y;
        y.left = t2;

        y.height = Math.max(height(y.left), height(y.right)) + 1;
        x.height = Math.max(height(x.left), height(x.right)) + 1;

        return x;
    }

    Node leftRotate(Node x) {
        System.out.println("Left Rotation at node " + x.bookId);

        Node y = x.right;
        Node t2 = y.left;

        y.left = x;
        x.right = t2;

        x.height = Math.max(height(x.left), height(x.right)) + 1;
        y.height = Math.max(height(y.left), height(y.right)) + 1;

        return y;
    }

    Node insert(Node node, int bookId, String title) {

        if (node == null) {
            System.out.println("Inserted Book ID: " + bookId);
            return new Node(bookId, title);
        }

        if (bookId < node.bookId)
            node.left = insert(node.left, bookId, title);
        else if (bookId > node.bookId)
            node.right = insert(node.right, bookId, title);
        else
            return node;

        node.height = 1 + Math.max(height(node.left), height(node.right));

        int balance = getBalance(node);

        // LL
        if (balance > 1 && bookId < node.left.bookId) {
            System.out.println("LL Imbalance at " + node.bookId);
            return rightRotate(node);
        }

        // RR
        if (balance < -1 && bookId > node.right.bookId) {
            System.out.println("RR Imbalance at " + node.bookId);
            return leftRotate(node);
        }

        // LR
        if (balance > 1 && bookId > node.left.bookId) {
            System.out.println("LR Imbalance at " + node.bookId);
            node.left = leftRotate(node.left);
            return rightRotate(node);
        }

        // RL
        if (balance < -1 && bookId < node.right.bookId) {
            System.out.println("⚖️ RL Imbalance at " + node.bookId);
            node.right = rightRotate(node.right);
            return leftRotate(node);
        }

        return node;
    }

    Node search(Node root, int bookId) {
        if (root == null || root.bookId == bookId)
            return root;

        if (bookId < root.bookId)
            return search(root.left, bookId);

        return search(root.right, bookId);
    }
}

public class LibraryAVL {
    public static void main(String[] args) {

        AVLTree tree = new AVLTree();
        Node root = null;

        System.out.println("AVL Insertion (Library Books)\n");

        int[] ids = {30, 20, 40, 10, 25, 50, 60};

        for (int id : ids) {
            root = tree.insert(root, id, "Book_" + id);
        }

        System.out.println("\n Searching for Book ID 25...");
        Node result = tree.search(root, 25);

        if (result != null)
            System.out.println("Book Found: " + result.title);
        else
            System.out.println("Book Not Found");

        System.out.println("\n AVL ensures O(log n) operations");
    }
}