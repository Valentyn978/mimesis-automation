package com.helpers;


import java.util.*;

public class TestQ {

        public static String reverseString1(String args22) {
            char[] arr = args22.toCharArray();
            int low = 0;
            int high = arr.length - 1;
            String result = "";
            while (low < high) {
                arr[low] = (char) (arr[low] ^ arr[high]);
                arr[high] = (char) (arr[low] ^ arr[high]);
                arr[low] = (char) (arr[low] ^ arr[high]);
                low++;
                high--;
            }
            for (int i = 0; i < arr.length; i++) {
                result = result + arr[i];
            }
            return result;
        }

        public static String reverseString2(String str) {
            char[] array = str.toCharArray();
            String result = "";
            for (int i = array.length - 1; i >= 0; i--) {
                result = result + array[i];
            }
            return result;
        }

        public static String reverseString3(String str) {
            String result = "";
            for (int i = 0; i < str.length(); i++) {
                result = str.charAt(i) + result;
            }
            return result;
        }


        public static String reverseString4(String str) {
            Stack<Character> stack = new Stack<>();
            String result = "";
            for (Character character : str.toCharArray()) {
                stack.add(character);
            }
            while (!stack.isEmpty()) {
                result = result + stack.pop();
            }
            return result;
        }


        public static void main(String[] args) {
            String strData = "nytnelaV";

            String result = "";
            char[] strToChar = strData.toCharArray();
            int i = strToChar.length - 1;
            while (i >= 0) {
                result += strToChar[i];
                i--;
            }

            int[] nums = new int[]{5,6,18,55,1,9,42,51};
            int target = 27;
            int[] iResult = new int[2];
            HashMap<Integer, Integer> temp = new HashMap<>();
            int len = nums.length - 1;

            while (len >= 0) {
                int twoValue = target - nums[len];
                if (temp.containsKey(twoValue)) {
                    iResult[0] = len;
                    iResult[1] = temp.get(twoValue);
                    break;
                } else {
                    temp.putIfAbsent(nums[len], len);
                }
                len--;
            }

            System.out.println("Result of string reverting: ".concat(result));
            System.out.println("Result of Two Sum: ".concat(Arrays.toString(iResult)));
        }

        public static void main1(String[] args)
        {
            String input = "Geeks For Geeks";
            char[] hello = input.toCharArray();
            List<Character> trial1 = new ArrayList<>();

            for (char c : hello)
                trial1.add(c);

            Collections.reverse(trial1);
            ListIterator li = trial1.listIterator();
            while (li.hasNext())
                System.out.print(li.next());
        }

        /**
         * Revert string data
         * @return String
         */
        public String revertingString(String strData) {
            String result = "";
            char[] strToChar = strData.toCharArray();
            int i = strToChar.length;
            while (i > 0) {
                result = strToChar[i] + result;
                i--;
            }
            return result;
        }

    }
