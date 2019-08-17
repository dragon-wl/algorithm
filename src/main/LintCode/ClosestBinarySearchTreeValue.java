/**
 * @author wanglong
 * @brief
 * @date 2019-08-17 14:35
 */


import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

/**
 * https://www.lintcode.com/problem/closest-binary-search-tree-value/description
 *
 * 900. Closest Binary Search Tree Value
 * 中文English
 * Given a non-empty binary search tree and a target value, find the value in the BST that is closest to the target.
 *
 * Example
 * Example1
 *
 * Input: root = {5,4,9,2,#,8,10} and target = 6.124780
 * Output: 5
 * Explanation：
 * Binary tree {5,4,9,2,#,8,10},  denote the following structure:
 *         5
 *        / \
 *      4    9
 *     /    / \
 *    2    8  10
 * Example2
 *
 * Input: root = {3,2,4,1} and target = 4.142857
 * Output: 4
 * Explanation：
 * Binary tree {3,2,4,1},  denote the following structure:
 *      3
 *     / \
 *   2    4
 *  /
 * 1
 * Notice
 * Given target value is a floating point.
 * You are guaranteed to have only one unique value in the BST that is closest to the target.
 */

public class ClosestBinarySearchTreeValue {

    // Definition of TreeNode:
    class TreeNode {
        public int val;
        public TreeNode left, right;
        public TreeNode(int val) {
            this.val = val;
            this.left = this.right = null;
        }
    }

    /**
     * @param root: the given BST
     * @param target: the given target
     * @return: the value in the BST that is closest to the target
     */
    public int closestValue(TreeNode root, double target) {
        // write your code here
        int minVal = root.val;
        Queue<TreeNode> queue = new LinkedList<>();
        queue.offer(root);
        while(!queue.isEmpty()){
            TreeNode  node = queue.poll();
            if( firstBigger( minVal - target, node.val - target))
                minVal = node.val;
            if(node.right != null)
                queue.offer(node.right);
            if(node.left != null)
                queue.offer(node.left);
        }
        return minVal;
    }

    private boolean firstBigger(double x, double y){
        x = x < 0 ? -x :x;
        y = y < 0 ? -y :y;
        return x>y;
    }


    //即比target小的最大值  -- 该思路来源于九章算法
    private int lowerBound(TreeNode head, float target){
        TreeNode maxLower = head;
        TreeNode node = head;
        while(node != null){
            if(node.val < target && node.val > maxLower.val)
                maxLower = node;
            if(node.val < target)
                node = node.right;
            else
                node = node.left;
        }
        return maxLower.val;
    }

    //比target大的最小值 -- 该思路来源与九章算法
    private int upperBound(TreeNode head, float target){
        TreeNode minUpper = head;
        TreeNode node = head;
        while(node != null){
            if(node.val > target && node.val < minUpper.val)
                minUpper = node;
            if(target >= node.val)
                node = node.left;
            else
                node = node.right;
        }
        return minUpper.val;
    }

   //九章算法的第三步，则是比较upperBound和lowerBound谁更加接近target

    @Test
    public void test(){
        TreeNode root = new TreeNode(3);
        root.left = new TreeNode(2);
        root.right = new TreeNode(4);
        root.right.right = new TreeNode(1);
        System.out.println(closestValue(root,4.142786));
        System.out.println(lowerBound(root,3.14234f));
        System.out.println(upperBound(root,2.143256f));
    }
}

//笔记
/**
 *
 * 九章算法的思路：
 *   寻找比target小的最大值，即target下门限值；
 *   寻找比target大的最小值，即target上门限值；
 *
 *   比较两个值，谁离target更近。
 *
 *   https://www.jiuzhang.com/solution/closest-binary-search-tree-value/
 *
 *
 *   我的解题思路则是全部遍历，复杂度是o(n)
 *
 *   九章算法的思路则是o(h) h在log(n) 到 n 之间
 */