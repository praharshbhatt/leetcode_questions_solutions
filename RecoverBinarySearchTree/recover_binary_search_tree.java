/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetcodeQuestionsSolvedJava.RecoverBinarySearchTree;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author prahu
 */
public class recover_binary_search_tree {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {

        //Input: [1,3,null,null,2]
        //   1
        //  /
        // 3
        //  \
        //   2
        //
        //Output: [3,1,null,null,2]
        //
        //   3
        //  /
        // 1
        //  \
        //   2
        //Create a Tree
        //[1,3,null,null,2]
        TreeNode left1 = new TreeNode(3);

        TreeNode right2 = new TreeNode(2);
        left1.right = right2;

        TreeNode root = new TreeNode(1);
        root.left = left1;

        recoverTree(root);
    }

    /*
    The idea is essentailly to solve the problem:
    recover an almost sorted array with TWO elements swapped. Swap them back to make 
    the array sorted. 
    
    We also know the inorder of a binary tree is a sorted array. So,
    Let's first get the inorder and find the two mismatched values
    
    To find the two, traverse from rightmost side and find the first out of order 
    element (element which is smaller than previous element). Once first element 
    is found, find the second one by traversing the array from left side (element 
    which is bigger than next element).
    
     */
    static TreeNode m1 = null;
    static TreeNode m2 = null;

    public static void recoverTree(TreeNode root) {
        List<Integer> lst = new ArrayList<>();
        getInorder(root, lst);

        // An almost sorted array with two elements swapped
        int mismatch1 = 0;
        int mismatch2 = 0;
        // from left to right
        for (int i = 0; i < lst.size() - 1; i++) {
            if (lst.get(i) > lst.get(i + 1)) {
                mismatch1 = lst.get(i);
                break;
            }
        }

        // from right to left
        for (int i = lst.size() - 1; i > 0; i--) {
            if (lst.get(i) < lst.get(i - 1)) {
                mismatch2 = lst.get(i);
                break;
            }
        }

        // find the two nodes with val to mismatch1 and 2
        dfs(root, mismatch1, mismatch2);

        // swap the value
        int temp = m1.val;
        m1.val = m2.val;
        m2.val = temp;
    }

    public static void getInorder(TreeNode root, List<Integer> lst) {
        if (root == null) {
            return;
        }
        getInorder(root.left, lst);
        lst.add(root.val);
        getInorder(root.right, lst);
    }

    public static void dfs(TreeNode root, int mismatch1, int mismatch2) {
        if (root == null) {
            return;
        }
        if (root.val == mismatch1) {
            m1 = root;
        } else if (root.val == mismatch2) {
            m2 = root;
        }

        dfs(root.left, mismatch1, mismatch2);
        dfs(root.right, mismatch1, mismatch2);
    }
// Definition for a binary tree node.

    public static class TreeNode {

        int val;
        TreeNode left;
        TreeNode right;

        TreeNode() {
        }

        TreeNode(int val) {
            this.val = val;
        }

        TreeNode(int val, TreeNode left, TreeNode right) {
            this.val = val;
            this.left = left;
            this.right = right;
        }
    }

}
