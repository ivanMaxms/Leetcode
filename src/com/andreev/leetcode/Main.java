package com.andreev.leetcode;

import java.util.Arrays;


public class Main
{
    public static void main(String[] args) {
        Solution solution = new Solution();
        int[] intArray = new int[]{-10,-1,-18,-19};
        System.out.println(Arrays.toString(solution.twoSum(intArray, -19)));
    }
}

class Solution {
    public int[] twoSum(int[] nums, int target) {
        int[] sortedNums = Arrays.copyOf(nums, nums.length);
        Arrays.sort(sortedNums);
        if (sortedNums[0] >= 0) {
            return resolveInputWithoutNegativeNumbers(nums, sortedNums, target);
        } else if (sortedNums[sortedNums.length - 1] <= 0) {
            return resolveInputWithoutPositiveNumbers(nums, sortedNums, target);
        } else if (sortedNums[sortedNums.length - 1] >= 0) {
            return resolveMixedCase(nums, sortedNums, target);
        }
        return new int[0];
    }

    private int[] resolveInputWithoutPositiveNumbers(int[] nums, int[] sortedNums, int target) {
        int indexFromNecessaryCalculationNeeded = sortedNums.length;
        for (int i = 0; i < sortedNums.length; i++) {
            if (sortedNums[i] >= target) {
                if (0 < (i - 1) && sortedNums[i - 1] == sortedNums[i]) {
                    indexFromNecessaryCalculationNeeded = i - 1;
                }
                indexFromNecessaryCalculationNeeded = i;
                break;
            }
        }
        for (int j = indexFromNecessaryCalculationNeeded; j < sortedNums.length; j++) {
            for (int k = j + 1; k < sortedNums.length; k++) {
                if (target == (sortedNums[j] + sortedNums[k])) {
                    return findIndexes(nums, sortedNums[j], sortedNums[k]);
                }
            }
        }
        return new int[0];
    }

    private int[] resolveInputWithoutNegativeNumbers(int[] nums, int[] sortedNums, int target) {
        int indexTillNecessaryCalculationNeeded = 0;
        for (int i = sortedNums.length - 1; i >= 0; i--) {
            if (sortedNums[i] <= target) {
                if (sortedNums.length > (i + 1) && sortedNums[i + 1] == sortedNums[i]) {
                    indexTillNecessaryCalculationNeeded = i + 1;
                }
                indexTillNecessaryCalculationNeeded = i;
                break;
            }
        }
        for (int j = 0; j < indexTillNecessaryCalculationNeeded; j++) {
            for (int k = j + 1; k < indexTillNecessaryCalculationNeeded + 1; k++) {
                if (target == (sortedNums[j] + sortedNums[k])) {
                    return findIndexes(nums, sortedNums[j], sortedNums[k]);
                }
            }
        }
        return new int[0];
    }

    private int[] resolveMixedCase(int[] nums, int[] sortedNums, int target) {
        int upperThresholdIndex = 0;
        int lowerThresholdIndex = 0;
        for (int i = sortedNums.length - 1; i > 0; i--) {
            int tmpSum = sortedNums[i] + sortedNums[0];
            if (target > tmpSum) {
                upperThresholdIndex = i;
                break;
            } else if (target == tmpSum) {
                return findIndexes(nums, sortedNums[i], sortedNums[0]);
            }
        }
        for (int j = 0; j < upperThresholdIndex; j++) {
            int tmpSum = sortedNums[j] + sortedNums[upperThresholdIndex];
            if (target < tmpSum) {
                lowerThresholdIndex = j;
                break;
            } else if (target == tmpSum) {
                return findIndexes(nums, sortedNums[j], sortedNums[upperThresholdIndex]);
            }
        }
        for (int j = lowerThresholdIndex; j < upperThresholdIndex; j++) {
            for (int k = lowerThresholdIndex + 1; k < upperThresholdIndex + 1; k++) {
                if (target == (sortedNums[j] + sortedNums[k])) {
                    return findIndexes(nums, sortedNums[j], sortedNums[k]);
                }
            }
        }
        return new int[0];
    }
    private int[] findIndexes(int[] nums, int firstNum, int secondNum) {
        int indexesFound = 0;
        int[] result = new int[2];
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] == firstNum || nums[i] == secondNum) {
                indexesFound++;
                result[indexesFound - 1] = i;
                if (indexesFound == 2) {
                    break;
                }
            }
        }
        return result;
    }
}