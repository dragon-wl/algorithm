/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 11:43
 */


/**
 * 596. 最小子树
 * 中文English
 * 给一棵二叉树, 找到和为最小的子树, 返回其根节点。
 *
 * Example
 * 样例 1:
 *
 * 输入:
 * {1,-5,2,1,2,-4,-5}
 * 输出:1
 * 说明
 * 这棵树如下所示：
 *      1
 *    /   \
 *  -5     2
 *  / \   /  \
 * 1   2 -4  -5
 * 整颗树的和是最小的，所以返回根节点1.
 * 样例 2:
 *
 * 输入:
 * {1}
 * 输出:1
 * 说明:
 * 这棵树如下所示：
 *    1
 * 这棵树只有整体这一个子树，所以返回1.
 * Notice
 * LintCode会打印根节点为你返回节点的子树。保证只有一棵和最小的子树并且给出的二叉树不是一棵空树。
 */


public class FindMinSubTree {

    //Definition of TreeNode:
    class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    private int min = Integer.MAX_VALUE;

    private TreeNode minTree = null;

    /**
     * @param root: the root of binary tree
     * @return: the root of the minimum subtree
     */
    public TreeNode findSubtree(TreeNode root) {
        // write your code here
        minTreeHelper(root);
        return minTree;
    }

    private int minTreeHelper(TreeNode root){
        if(root == null)
            return 0;
        int tree =root.val;

        tree += root.left==null?0:minTreeHelper(root.left);
        tree += root.right==null?0:minTreeHelper(root.right);

        if(tree < min){
            min = tree;
            minTree = root;
        }
        return tree;
    }
}

//递归解法，将下层的结果不断往上传

// int minTreeHelper( TreeNode root) 计算root节点的值
//root节点的值 = root.val + minTreeHelper(right) + minTreeHelper(left)


//Integer类型的极值为int min = Integer.MAX_VALUE;

//九章题解：https://www.jiuzhang.com/solution/minimum-subtree/