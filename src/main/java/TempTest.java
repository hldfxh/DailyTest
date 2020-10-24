import javax.swing.tree.TreeNode;
import java.util.*;
import java.util.stream.IntStream;

public class TempTest {

    public List<List<Integer>> levelOrderBottom(TreeNode root) {
        if (root == null) return Collections.EMPTY_LIST;
        LinkedList<List<Integer>> result = new LinkedList<>();
        Stack<TreeNode> from = new Stack<>();
        Stack<TreeNode> to = new Stack<>();
        from.push(root);
        collect(result, from, to);
        return result;
    }

    private void collect(LinkedList<List<Integer>> result, Stack<TreeNode> full, Stack<TreeNode> empty) {
        if (full.empty())
            return;
        List<Integer> sets = new ArrayList<Integer>();
        while (!full.empty()) {
            TreeNode node = full.pop();
            if (node.left != null)
                empty.push(node);
            if (node.right != null)
                empty.push(node);
            sets.add(node.val);
        }
        result.addFirst(sets);
        collect(result, empty, full);
    }

    static class TreeNode {
        int val;
        TreeNode left;
        TreeNode right;

        TreeNode(int x) {
            val = x;
        }
    }

    public static void main(String[] args) {
        List<Integer> integers1 = Collections.unmodifiableList(new ArrayList<>(Arrays.asList(1, 2, 3, 4)));
        List<int[]> ints = Arrays.asList(new int[]{1, 2, 3, 4});
        List<Integer> integers = Arrays.asList(new Integer[]{1, 2, 3, 4});
        System.out.println("ints:"+ints.size());
        ArrayList a = new ArrayList(10);
        a.add(3);
        a.add(4);
        a.add(5);
        a.add(2, 2);
        a.add(1, 1);
        IntStream range = IntStream.range(3, 7);
        TempTest tempTest = new TempTest();
        TreeNode treeNode = new TreeNode(3);
        treeNode.left = new TreeNode(9);
        treeNode.right = new TreeNode(20);
        treeNode.right.left = new TreeNode(15);
        treeNode.right.right = new TreeNode(7);
        tempTest.levelOrderBottom(treeNode);
    }

}


