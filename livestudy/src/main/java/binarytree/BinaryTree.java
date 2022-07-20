package binarytree;

import java.nio.file.StandardWatchEventKinds;
import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

public class BinaryTree {
    private Node root;
    public StringBuilder sb = new StringBuilder();

    public void setRoot(Node root) {
        this.root = root;
    }

    public Node getRoot() {
        return root;
    }

    public Node makeNode(Node left, int data, Node right) {
        Node node = new Node();
        node.setData(data);
        node.setLeft(left);
        node.setRight(right);
        return node;
    }

    public void BFS(Node root) {
        Queue<Node> que = new LinkedList<>();
        que.add(root);

        while (!que.isEmpty()) {
            Node node = que.poll();

            appendData(node);

            if (node.getLeft() != null) {
                que.add(node.getLeft());
            }
            if (node.getRight() != null) {
                que.add(node.getRight());
            }
        }
    }

    public void stackDFS(Node root) {
        Stack<Node> stack = new Stack<>();
        stack.push(root);

        while (!stack.isEmpty()) {
            Node node = stack.pop();

            if (node.getRight() != null) {
                stack.push(node.getRight());
            }
            appendData(node);
            if (node.getLeft() != null) {
                stack.push(node.getLeft());
            }


        }
    }

    public void recurDFS(Node root) {
        if (root == null) {
            return;
        }
        recurDFS(root.getLeft());
        appendData(root);
        recurDFS(root.getRight());
    }

    private void appendData(Node node) {
        sb.append(node.getData());
    }

}


