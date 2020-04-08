package leetcode.leetc;

import java.util.Arrays;

//LeetCode网站编程题
public class Solution {

    /**
     * 给定一个数组 nums ，如果 i < j 且 nums[i] > 2*nums[j] 我们就将 (i, j) 称作一个重要翻转对。
     你需要返回给定数组中的重要翻转对的数量。

     * @param nums
     * @return
     */
    /*
    当我们希望在数组中求出逆序对的数目时，我们可以使用归并排序的方法。
    这道题中 i < j 且 nums[i] > 2 * nums[j] 的要求与逆序对类似，因此我们也可以使用归并排序的方法求出翻转对的数目。
    在归并排序中，当我们归并两个子数组 nums[start .. mid] 和 nums[mid + 1 .. end] 时，
    我们可以计算出对于前者中的每一个元素 nums[i]，后者中满足 nums[i] > 2 * nums[j] 的 j 的数目。
    由于两个子数组已经排好序，因此对于固定的 i，满足条件的 j 的区间一定是从后者的左端点开始，
    并且随着 i 的增加，j 区间的右端点不会减小。因此我们可以在 nums[mid + 1 .. end] 中维护一个指针 pt，
    表示对于当前的 i，nums[mid + 1 .. pt] 的两倍都小于 nums[i]。随着 i 的增加，
    我们尝试向右移动 pt 使得更多的数满足条件。

     */
    public int reversePairs(int[] nums) {
        return mergerSort(nums, 0, nums.length - 1);
    }

    private int mergerSort(int[] nums, int start, int end) {
        int count = 0;
        if (start < end) {
            int mid = ((start + end) >> 1);
            count = mergerSort(nums, start, mid) + mergerSort(nums, mid + 1, end);
            int j = mid + 1;
            for (int i = start; i <= mid; i++) {
                while (j <= end && ((nums[i] >> 1) + (nums[i] & 1)) > nums[j]) j++;
                count += (j - (mid + 1));
            }
            merge(nums, start, mid, end);
        }
        return count;
    }

    private void merge(int[]nums, int start, int mid, int end) {
        int[] tmp = new int[end - start + 1];
        int i = start, j = mid + 1, k = 0;
        while (i <= mid && j <= end) {
            tmp[k++] = nums[i] <= nums[j] ? nums[i++] : nums[j++];
        }
        while (i <= mid) {
            tmp[k++] = nums[i++];
        }
        while (j <= end) {
            tmp[k++] = nums[j++];
        }
        System.arraycopy(tmp, 0, nums, start, tmp.length);
    }

    public static void main(String[] args){
        Solution sl = new Solution();

        int[] nums = {1,3,2,3,1};
        System.out.println(sl.reversePairs(nums));
    }
}
