package main;


import java.util.Arrays;

public class Solution {

    /**
     * RUBBER DUCKIE
     * So we know that the first value nums[0] is going to be moved to
     * nums[k-1 + nums[0]] and that every other element in the array is going to be
     * moved k-1 + nums[n] (n being the position of the element in the array)
     * spaces unless it is in the position nums[nums.length - 1]
     * <p>
     * if in pos nums[nums.length - 1] it will be moved to nums[k]
     * <p>
     * I think i can do this with just math to get the O(1) time complexity
     * maybe just an if statement, but I also need to not make another array
     * (that would cause a time complexity of O(n))
     * <p>
     * I also know that in cases where the k value is even the array will be
     * split and then flipped ex. nums[] = {1,2} k = 2 -> nums[] = {2,1}
     * - actually if the value is evan and > 2 then the array will
     * end up the same as it started.
     * ex nums[] = {1,2,3,4} k = 4
     * = {4,1,2,3}
     * = {3,4,1,2}
     * = {2,3,4,1}
     * = {1,2,3,4}
     * ex nums[] = {1,2,3} k = 6
     * = {3,1,2}
     * = {2,3,1}
     * = {1,2,3}
     * = {3,1,2}
     * = {2,3,1}
     * = {1,2,3}
     * <p>
     * <p>
     * did i misunderstand?
     * = {1,2,3,4,5,6,7} k = 4
     * = {7,1,2,3,4,5,6}
     * = {6,7,1,2,3,4,5}
     * = {5,6,7,1,2,3,4}
     * = {4,5,6,7,1,2,3}
     * <p>
     * Might only work if the k value is greater then the len of the array?
     * <p>
     * So if k > 2 and k/2 = 0 then return the current array
     * if k == 2 then hold nums[0], nums[k-nums.length + 1]
     * <p>
     * <p>
     * ITS O(1) SPACE NOT O(1) TIME COMPLEXITY!!
     * rotate through: ex. {1,2,3}
     * starting at n[0]
     * n[0] -> n[1] -> n[2] -> n[0]
     * <p>
     * one rotation should hold the n[len] element then shift n[0] -> n[1]
     *
     * @return nums
     */
    public static int[] rotate(int[] nums, int k) {
        int i, temp, count;
        int len = nums.length;
        count = k;

        while (count > 0) {
            temp = nums[len - 1]; //start with temp as the end val
            for (i = 0; i < len; i++) {
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
     * <p>
     * the correct solution is one using reversing, and a tool method.
     * 1) reverse all the numbers
     * 2) reverse k numbers (k being the number of times you need to rotate)
     * 3) reverse n-k numbers
     * <p>
     * ex. = {1,2,3,4,5,6,7}  k = 3
     * = {7,6,5,4,3,2,1}
     * = {5,6,7,4,3,2,1}
     * = {5,6,7,1,2,3,4}
     * <p>
     * Who knew reversing a few times would rotate an array? I guess in hindsight it makes sense.
     * this is just one of those things that you have to know and understand
     * before an interview. The algorithm's implementation will require a helper
     * method - Rotate() - that will take the array,the start of the rotation and the end
     * of the rotation
     * Then just do a basic rotation:
     * while start < end { for the range of the scope we have (start to end) rotate
     * temp = start
     * start = end
     * end = temp
     * start++
     * end--
     * }
     *
     * @param nums array
     * @param k    rotations
     */

    public static void rotateAnswer(int[] nums, int k) {
        k %= nums.length; // splitting the array with k being the first k num of elements
        int n = nums.length - 1;
        reverse(nums, 0, n); //reversing the whole thing
        reverse(nums, 0, k - 1); //reversing the first half (excluding k)
        reverse(nums, k, n); //reversing the remainder including K

        System.out.println(Arrays.toString(nums));
    }

    public static void reverse(int[] nums, int start, int end) {
        while (start < end) {
            int temp = nums[start];
            nums[start] = nums[end];
            nums[end] = temp;
            start++;
            end--; //pinching the two sides.
        }
    }


    /**
     * Solution Reflection
     * This one was a hard one for me.
     * It was my first time coding in a while in my defence!
     * But the solution is actually pretty simple.
     * the solution requires you to understand a few things about the problem.
     * 1) its a sorted array so if there is a duplicate it has to be the next element so we are always going to compare
     * two elements that are next to each other
     * 2) we need to keep track of how many times the array is being changed in the form of an index
     * 3) the first number is always going to be unique
     * <p>
     * so its a pretty simple for loop that iterates through the array, checking each pair of numbers.
     * If the number nums [n+1] is not the same we place it at the current index point.
     * This is kind of replacing from the front instead of moving the duplicates to the back.
     * ex.             ={0,0,1,1,1,2,2,3}  (im going to do my best to stack trace this in the comments so here we go)
     * 1) i=0  ind=1  ={0,0,1,1,1,2,2,3} -> starts by comparing nums[0] and nums[1] both are the same so it moves on
     * 2) i=1  ind=1  ={0,0,1,1,1,2,2,3} -> next comparing n[1] and n[2] 0 != 1 so it take the value of n[i+1] and sets
     * it to the value of n[ind] (n[1]) and iterates index.
     * 3) i=2  ind=2  ={0,1,1,1,1,2,2,3} -> n[2] != n[3] == F nothing changes
     * 4) i=3  ind=2  ={0,1,1,1,1,2,2,3} -> n[3] != n[4] == F nothing changes
     * 5) i=4  ind=2  ={0,1,1,1,1,2,2,3} -> n[4] != n[5] == T -> n[ind] = n[5], ind++;
     * 6) i=5  ind=3  ={0,1,2,1,1,2,2,3} -> n[5] != n[6] == F nothing changes
     * 7) i=6  ind=3  ={0,1,2,1,1,2,2,3} -> n[6] != n[7] == T -> n[ind] = n[7], ind++;
     * 8) OUT OF LOOP, OUTPUT: ind = 4, nums = {0,1,2,3,1,2,2,3}
     * <p>
     * all things considered I think that's a pretty good stack trace!
     * <p>
     * The issue I was having was that I was looking at it as if I had to move the duplicates to the end
     * but instead I had to move the duplicates to the front and just overwrite the duplicates. I can do this because
     * I know that any duplicate that I have will be after the first number in the ordered sequence, and because I know
     * that the first number will always be the first number. I just had to reorder around that first number and use the
     * index not as a count of how many times I find a duplicate but as a place holder for where the unique numbers should
     * go.
     *
     * @param nums the sorted array with duplicates.
     * @return the index of the array that has no duplicates
     */
    public static int removeDuplicatesAnswer(int[] nums) {
        int index = 1; //representing where the new number should be place if it is unique
        for (int i = 0; i < nums.length - 1; i++) {
            if (nums[i] != nums[i + 1]) {  //if current index is not equal to the next index
                nums[index++] = nums[i + 1]; // then we place that number at our index.
            }
            System.out.println(Arrays.toString(nums));
        }
        System.out.println(index);
        System.out.println(Arrays.toString(nums));
        return index;
    }

    //P3

    /**
     * RUBBER DUCKIE
     * Ok so first thoughts were that this is a similar problem to the removeDuplicates problem. But there is one key
     * difference, the order of the array. The removeDuplicates problem was an ordered array meaning that the duplicate
     * would always be paired with the element so you didnt have to look to far.
     * <p>
     * a few ways to do this:
     * - the easy way:
     * I can easily grab the first number hold it and check the entire array but that time complexity is kinda shit
     * I could do a split sort? I think that has a worse Time complexity (n log(n))? The for loop time complexity is
     * O(n).
     * <p>
     * while loop
     * so the first thing I want to check is whether or not next is at the end of the arr.
     * if next is at the end of the arr I want to iterate index and iterate next at index + 1;
     * <p>
     * ugh ok so Im running in to this one fucking array out of bounds exception, im sure there is a boolean
     * statement that will clear this but throwing it does seem to work.
     * essentially the case where the exception is thrown is when the index is on less then the max value
     * but the index + 1 is out of bounds so it exhausts all the possible matches so I think it will pass all cases?
     * <p>
     * only issue is the run time is O(n) - though i have to double check that -
     * <p>
     * Imma call this one done!
     *
     * @param nums array.
     */


    public static boolean containsDuplicate(int[] nums) {

        int index = 0;
        int next = 1;
        boolean containsDup = false;
        System.out.println(nums.length);
        while (!containsDup) {

            if (index == nums.length - 1) {
                return false;
            }

            if (next == nums.length) {
                index++;
                next = index + 1;
            } //checking if out of index

            try {
                System.out.printf("index val: %s, next value: %s \n", nums[index], nums[next]);
                if (nums[index] == nums[next]) { //checking if curr and next are equal
                    containsDup = true;
                }
            } catch (ArrayIndexOutOfBoundsException e) {
                return false;
            } //im fucking lazy

            next++;

        }


        return true;
    }


    /**
     * Rubber Duckie
     * Ok so I don't have internet for this one so here we go.
     * For this method we are given an integer array nums[], my goal is to move all
     * the 0's in the array to the end. all while maintaining relative order.
     *
     * -relative order: ok so for my implementation with out the internet I will be
     * assuming that relative order is the order that the array started in. That being said
     * I can also probably order the array after it has been re-arranged!
     *
     * So for this method I think imma pull from the remove duplicate method to do this
     * the only real difference is instead of looking for duplicates its just looking
     * for the zeros.
     *
     * stack trace before I start writing:
     * {0,1,0,2,3} -> want -> {1,2,3,0,0}
     * ok so, what if i just swap?
     * if nums[i] x nums[i+1] == 0 then theres a zero so we swap
     * else we dont have a zero so we dont swap
     *
     * starting from nums[0] should bubble them all down?
     *
     * hmm.
     *
     * this might not work.
     *
     * take the case {0,0,1}, it wont do anything if its swapped it will
     * just ignore the first 0. another test case would be {0,1,0,0,0,1}
     * so my solution only works if the array has a zero in every other spot.
     *
     * so the bubble solution might be the best be.
     *
     * Stack track for remove duplicates.
     *
     * ex.
     *  index = 1 i = 0 nums = {0,0,1} -> nums[0] != nums[1] F
     *  index = 1 i = 1 nums = {0,0,1} -> nums[1] != nums[2] T nums[1] = nums[2]
     *  index = 2 i = 2 nums = {0,1,1}
     *
     * ok so few things, this solution works mostly bc the output also
     * includes the scope that your supposed to look at in the form of the
     * returned index val.
     * a good solution to this is to just swap instead of overwrite.
     *
     * Thinking out loud:
     * so far what ive came up with is something that makes of the location of a zero in the arr
     * saves that value and waits until a non-zero number comes up at which point it would
     * swap the loc of the non-zero with the zero.
     *
     *  i = 0 lz = -1 {0,1,2} -> nums[0] = 0
     *  i = 1 lz = 0; {0,1,2} -> nums[1] = 1 nz -> nums[1] = 0 nums[0] = 1;
     *  i = 2 lz = 1; {1,0,2} -> nums[2] = 2 nz -> nums[2] = 0 nums [1] = 2
     *
     * ok so instead of index lets just keep the last zero spot and swap with the current non zero
     *
     * now this doesnt work if the first value is a non-zero
     * or if its a 0101 -> 1001 -> 1010
     */
    public static void moveZeros(int[] nums){
        int i = 0;
        int end = nums.length -1;
        while(i < nums.length -1){
            if(nums[i] == 0){
                int temp = nums[end];
                nums[end] = nums[i];
                
                end--;
                i++;
            }
            i++;
        }
//        int lastZero = -1;
//        for (int i = 0; i < nums.length - 1; i++) {
//            if(nums[i] == 0){ //make note of where it is
//                lastZero = i;
//            } //anything after wont be zero.
//            if(lastZero != -1){ //meaning we've seen a zero itr and swap
//                //so the lz val should be set to the current i value
//                int temp = nums[i];
//                nums[i] = nums[lastZero];
//                nums[lastZero] = temp;
//
//                lastZero = i;
//            }
//            System.out.println(Arrays.toString(nums));
//        }
    }



    public static void main(String[] args) {

//        int[] testArr = {1, 2, 3, 4, 5, 6, 7};
//        rotate(testArr, 3);  //for smaller examples the runtime is the same but for larger examples it is much slower.
//        rotateAnswer(testArr, 3);
//
//        int[] testArr1 = {0, 0, 1, };
//        removeDuplicatesAnswer(testArr1);
//
//
//        int[] testArr2 = {2, 1, 1, 4};
//        System.out.println(containsDuplicate(testArr2));
//        int[] testArr3 = {1, 2, 4, 4};
//        System.out.println(containsDuplicate(testArr3));
//        int[] testArr4 = {1, 2, 3, 2};
//        System.out.println(containsDuplicate(testArr4));

        int[] testArr5 = {0,1,0,2,3};
        moveZeros(testArr5);

    }
}

