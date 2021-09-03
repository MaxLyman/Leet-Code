package main;

import java.util.Arrays;

public class Solution {

    /**
     * RUBBER DUCKIE
     * So we know that the first value nums[0] is going to be moved to
     * nums[k-1 + nums[0]] and that every other element in the array is going to be
     * moved k-1 + nums[n] (n being the position of the element in the array)
     * spaces unless it is in the position nums[nums.length - 1]
     *
     * if in pos nums[nums.length - 1] it will be moved to nums[k]
     *
     * I think i can do this with just math to get the O(1) time complexity
     * maybe just an if statement, but I also need to not make another array
     * (that would cause a time complexity of O(n))
     *
     * I also know that in cases where the k value is even the array will be
     * split and then flipped ex. nums[] = {1,2} k = 2 -> nums[] = {2,1}
     * - actually if the value is evan and > 2 then the array will
     * end up the same as it started.
     * ex nums[] = {1,2,3,4} k = 4
     *           = {4,1,2,3}
     *           = {3,4,1,2}
     *           = {2,3,4,1}
     *           = {1,2,3,4}
     * ex nums[] = {1,2,3} k = 6
     *           = {3,1,2}
     *           = {2,3,1}
     *           = {1,2,3}
     *           = {3,1,2}
     *           = {2,3,1}
     *           = {1,2,3}
     *
     *  did i misunderstand?
     *  = {1,2,3,4,5,6,7} k = 4
     *  = {7,1,2,3,4,5,6}
     *  = {6,7,1,2,3,4,5}
     *  = {5,6,7,1,2,3,4}
     *  = {4,5,6,7,1,2,3}
     *
     *  Might only work if the k value is greater then the len of the array?
     *
     *  So if k > 2 and k/2 = 0 then return the current array
     *  if k == 2 then hold nums[0], nums[k-nums.length + 1]
     *
     *
     *  ITS O(1) SPACE NOT O(1) TIME COMPLEXITY!!
     *  rotate through: ex. {1,2,3}
     *  starting at n[0]
     *  n[0] -> n[1] -> n[2] -> n[0]
     *
     *   one rotation should hold the n[len] element then shift n[0] -> n[1]
     * @return nums
     */
    public static int[] rotate(int[] nums, int k) {
        int i, temp, count;
        int len = nums.length;
        count = k;

        while(count > 0) {
            temp = nums[len-1]; //start with temp as the end val
            for(i = 0; i < len; i++){
                int next = nums[i];
                nums[i] = temp;
                temp = next;
            }
            count--;
        }
        return nums;
    }

    /**
     * SOLUTION REFLECTION
     * So my answer in rotate works, the runtime is just the issue
     * my solution requires me to go through the entire array step by step
     * and while the space complexity is technically O(1) the time complexity
     * is O(n^2).
     *
     * the correct solution is one using reversing, and a tool method.
     * 1) reverse all the numbers
     * 2) reverse k numbers (k being the number of times you need to rotate)
     * 3) reverse n-k numbers
     *
     * ex. = {1,2,3,4,5,6,7}  k = 3
     *     = {7,6,5,4,3,2,1}
     *     = {5,6,7,4,3,2,1}
     *     = {5,6,7,1,2,3,4}
     *
     * Who knew reversing a few times would rotate an array? I guess in hindsight it makes sense.
     * this is just one of those things that you have to know and understand
     * before an interview. The algorithm's implementation will require a helper
     * method - Rotate() - that will take the array,the start of the rotation and the end
     * of the rotation
     * Then just do a basic rotation:
     * while start < end { for the range of the scope we have (start to end) rotate
     *      temp = start
     *      start = end
     *      end = temp
     *      start++
     *      end--
     *  }
     *
     *
     *
     * @param nums array
     * @param k rotations
     *
     */
    public static void rotateAnswer(int[] nums, int k){
        k %= nums.length; // splitting the array with k being the first k num of elements
        int n = nums.length - 1;
        reverse(nums, 0, n); //reversing the whole thing
        reverse(nums, 0, k-1); //reversing the first half (excluding k) 
        reverse(nums, k, n); //reversing the remainder including K

        System.out.println(Arrays.toString(nums));
    }

    public static void reverse(int[] nums, int start, int end){
        while (start < end){
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--; //pinching the two sides.
        }
    }


    public static int removeDuplicates(int[] nums) {
        int index = 1; //operating on the knowledge that the first one is never going to change
        for(int i = 0; i < nums.length - 1; i++){
            if(nums[i] == nums[i+1]){
                nums[index++] = nums[i+1]; //bubbles the second one down
            }
        }
        return index;
    }

    public static void main(String[] args) {
//      int[] testArr1 = {0,0,1,1,1,2,2,3};
//      System.out.println(removeDuplicates(testArr1));

        int[] testArr = {1,2,3,4,5,6,7};
        rotateAnswer(testArr,3);
    }
}

