import java.util.ArrayList;
import java.util.List;

public class TreeNode {
    String name;
    List<TreeNode> children;
    TreeNode parent;

    public TreeNode(String name, TreeNode parent) {
        this.name = name;
        this.parent = parent;
        this.children = new ArrayList<>();
        if (parent != null) {
            parent.addChild(this);
        }
    }

    public TreeNode(String name) {
        this.name = name;
        this.children = new ArrayList<>();
    }

    public void addChild(TreeNode child) {
        child.parent = this;
        this.children.add(child);
    }

    public void addChild(String name) {
        TreeNode child = new TreeNode(name, this);
        this.children.add(child);
    }
}
