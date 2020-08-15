/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package LeetcodeQuestionsSolvedJava.edit_distance_2;

/**
 *
 * @author prahu
 */
public class edit_distance_2 {

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        System.out.println(minDistance("horse", "ros"));
    }

    public static int minDistance(String word1, String word2) {
        //        h o r s e
        //     0 1 2 3 4 5
        // r   1 1 2 2 3 4
        // o  2 2 1 3 3 4
        // s   3 3 2 2 3 4

        int[][] matrix = new int[word1.length() + 1][word2.length() + 1];
        matrix[0][0] = 0;//if both word1 and word 2 are empty we need not perform any operations.

        //if the string to be matched(word1 in this case) is empty then we need to remove all the characters from the input string(word2)
        for (int i = 1; i < matrix[0].length; i++) {
            matrix[0][i] = i;
        }
// if the the input word is empty we need to add all characters of the word1 to it.

        for (int i = 1; i < matrix.length; i++) {
            matrix[i][0] = i;
        }
        //explanation is above the code        
        for (int i = 1; i < matrix.length; i++) {
            for (int j = 1; j < matrix[0].length; j++) {

                if (word1.charAt(i - 1) == word2.charAt(j - 1)) {
                    matrix[i][j] = matrix[i - 1][j - 1];

                } else {
                    matrix[i][j] = 1 + Math.min(matrix[i - 1][j - 1], Math.min(matrix[i - 1][j], matrix[i][j - 1]));

                }
            }
        }
        return matrix[word1.length()][word2.length()];

    }
}
