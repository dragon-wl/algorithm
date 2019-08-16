import org.junit.Test;

/**
 * @author wanglong
 * @brief
 * @date 2019-08-16 16:36
 */


/**
 *
 * 73. 前序遍历和中序遍历树构造二叉树
 * 中文English
 * 根据前序遍历和中序遍历树构造二叉树.
 *
 * Example
 * 样例 1:
 *
 * 输入：[],[]
 * 输出：{}
 * 解释：
 * 二叉树为空
 * 样例 2:
 *
 * 输入：[2,1,3],[1,2,3]
 * 输出：{2,1,3}
 * 解释：
 * 二叉树如下
 *   2
 *  / \
 * 1   3
 * Notice
 * 你可以假设树中不存在相同数值的节点
 *
 * https://www.lintcode.com/problem/construct-binary-tree-from-preorder-and-inorder-traversal/description
 */
public class BuildTree {

    //Definition of TreeNode:
    class TreeNode {
      public int val;
      public TreeNode left, right;
      public TreeNode(int val) {
          this.val = val;
          this.left = this.right = null;
      }
  }

    /**
     * @param inorder: A list of integers that inorder traversal of a tree
     * @param preorder: A list of integers that preorder traversal of a tree
     * @return: Root of a tree
     */
    public TreeNode buildTree(int[] preorder, int[] inorder) {
        // write your code here
        if(preorder == null || inorder == null)
            return null;
        return getHead(preorder,0,preorder.length - 1,
                inorder,0,inorder.length - 1);
    }

    private TreeNode getHead(int[] preOrder, int preStart, int preEnd,
                        int[] inOrder, int inStart, int inEnd){
        if(preEnd < preStart || inEnd < inStart)
            return null;
        if(preEnd == preStart)
            return new TreeNode(preOrder[preStart]);
        if(inEnd == inStart)
            return new TreeNode(inOrder[inStart]);
        TreeNode root = new TreeNode(preOrder[preStart]);
        for(int i = inStart; i <= inEnd; i ++){
            if(inOrder[i] == preOrder[preStart]){
                root.left = getHead(preOrder, preStart + 1, preStart + i - inStart,
                        inOrder,inStart,i - 1);
                root.right = getHead(preOrder,preStart + i - inStart + 1,preEnd,
                        inOrder,i + 1, inEnd);
            }
        }
        return root;
    }


    @Test
    public void test(){
        int[] pre = {1, 2 ,4, 8, 5, 3, 6, 9, 7, 10};
        int[] in = {4, 8, 2, 5 ,1, 6, 9, 3, 7, 10};
        TreeNode node = buildTree(pre, in);
        System.out.println("finish");
    }


}
/**
 *        1
 *     2      3
 *  4    5  6     7
 *     8      9      10
 *
 *     preOrder: 1、 2 、4、 8、 5、 3、 6 、9 、7 、10
 *     inOrder：4、 8、 2、 5 、1 、6 、9 、3、 7、10
 *
 */