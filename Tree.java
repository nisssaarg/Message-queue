public class Tree {

    public void createTree() {
        // Example usage
        TreeNode A = new TreeNode("A");
        TreeNode B = new TreeNode("B");
        TreeNode C = new TreeNode("C");
        TreeNode D = new TreeNode("D");
        TreeNode E = new TreeNode("E");
        TreeNode F = new TreeNode("F");
        TreeNode G = new TreeNode("G");
        TreeNode H = new TreeNode("H");
        TreeNode I = new TreeNode("I");

        A.addChild(B);
        A.addChild(C);
        B.addChild(H);
        B.addChild(I);
        C.addChild(F);
        C.addChild(E);
        C.addChild(D);
        F.addChild(G); 

        printTree(A,0);
    }

    public static void main(String[] args){
        Tree tree = new Tree();
        tree.createTree();
    }

    public static void printTree(TreeNode node, int level) {
        if (node == null) return;

        // Print the current node
        for (int i = 0; i < level; i++) {
            System.out.print("  ");
        }
        System.out.println(node.name);

        
        for (TreeNode child : node.children) {
            printTree(child, level + 1);
        }
    }
}
