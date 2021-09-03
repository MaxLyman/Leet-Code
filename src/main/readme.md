---
What is this?
---

This is my attempt to flex some of my coding muscles after a long time of not coding. Covid-19 hit everyone 
hard but for me it made me drop school all together, during that time I was able to find a new passion 
for biking! But in the process I lost some progress I had been making in the field of computer science. 
My hope is the in doing these leet code problems semi-regularly I will be able to regain the skills I had
and find some new ones. 


<Header> How this is structured </Header>
I want to make this a kind of study guide for all my java needs. In order to do so I will be leaving in my 
initial attempts at the problem - the attempt that I didnt look at any sort of clues or outside resources - 
and then the solution or my final attempt that works. I also want to keep a record of my thought process while
I am completing these problems.
To keep my thoughts organized I have to new TODO comments:

~~~
Rubber Duckie & Solution Reflection
~~~
**Rubber Duckie:** 
Rubber Duckie will be where I think through the problem. Everything I write in this section might not make it in to my 
actual code, but It will hopefully help illustrate my thought process for me in the future.

**Solution Reflection:**
Solution Reflection is just that, here I will be going through the solution as I understand it. I intend to annotate the
code as well as wright the reflection to further understand the code if it is from an outside source, and to go more 
into depth if it is my own personal code. 


**finally** I would like this to be an exercise in not only coding but also an exercise in the documentation of my code,
and in working with github. 


---
P1 - Remove duplicates
---

Given an integer array nums sorted in non-decreasing order, remove the duplicates in-place such that each unique element
appears only once. The relative order of the elements should be kept the same.

Since it is impossible to change the length of the array in some languages, you must instead have the result be placed
in the first part of the array nums. More formally, if there are k elements after removing the duplicates, then the
first k elements of nums should hold the final result. It does not matter what you leave beyond the first k elements.

Return k after placing the final result in the first k slots of nums.

Do not allocate extra space for another array. You must do this by modifying the input array in-place with O(1) extra
memory.

**Custom Judge:**

The judge will test your solution with the following code:

~~~
int[] nums = [...]; // Input array
int[] expectedNums = [...]; // The expected answer with correct length

int k = removeDuplicates(nums); // Calls your implementation

assert k == expectedNums.length;
for (int i = 0; i < k; i++) {
    assert nums[i] == expectedNums[i];
}
If all assertions pass, then your solution will be accepted.
~~~

---

~~~
Example 1:

Input: nums = [1,1,2]
Output: 2, nums = [1,2,_]
Explanation: Your function should return k = 2, with the first two elements of nums being 1 and 2 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
~~~

~~~
Example 2:

Input: nums = [0,0,1,1,1,2,2,3,3,4]
Output: 5, nums = [0,1,2,3,4,_,_,_,_,_]
Explanation: Your function should return k = 5, with the first five elements of nums being 0, 1, 2, 3, and 4 respectively.
It does not matter what you leave beyond the returned k (hence they are underscores).
~~~

**Constraints:**

0 <= nums.length <= 3 * 104 -100 <= nums[i] <= 100 nums is sorted in non-decreasing order.

---
P2 - Rotate
---

Given an array, rotate the array to the right by k steps, where k is non-negative.

~~~
Example 1:

Input: nums = [1,2,3,4,5,6,7], k = 3
Output: [5,6,7,1,2,3,4]
Explanation:
rotate 1 steps to the right: [7,1,2,3,4,5,6]
rotate 2 steps to the right: [6,7,1,2,3,4,5]
rotate 3 steps to the right: [5,6,7,1,2,3,4]
~~~

~~~
Example 2:
Input: nums = [-1,-100,3,99], k = 2
Output: [3,99,-1,-100]


Explanation:
rotate 1 steps to the right: [99,-1,-100,3]
rotate 2 steps to the right: [3,99,-1,-100]
~~~

**Constraints:**

~~~
1 <= nums.length <= 105
-231 <= nums[i] <= 231 - 1
0 <= k <= 105
~~~

**Follow up:**

Try to come up with as many solutions as you can. There are at least three different ways to solve this problem. Could
you do it in-place with O(1) extra space?
