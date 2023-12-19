package org.stacks;


import kotlin.Pair;

import java.util.*;

public class Stacks {

    public static long[] nextLargerElement(long[] arr, int n)
    {
        int i = 0;
        Stack<Long> s = new Stack<>();
        List<Long> ans = new ArrayList<>();

        for (i = arr.length - 1; i >= 0 ; i--){
            if (s.empty())
                ans.add((long) -1);
            else if (!s.isEmpty() && s.peek() > arr[i])
                ans.add(s.peek());
            else if (!s.isEmpty() && s.peek() <= arr[i]){
                while (!s.isEmpty() && s.peek() <= arr[i])
                    s.pop();
                if (s.isEmpty())
                    ans.add((long) -1);
                else ans.add(s.peek());
            }
            s.push((long) arr[i]);
        }

        // reverse the list
        long[] res = new long[ans.size()];
        for (i = 0; i < ans.size(); i++)
            res[i] = ans.get(ans.size() - i - 1);
        return res;
    }

    public static long[] nextLargerElementOnLeft(long[] arr, int n)
    {
        int i = 0;
        Stack<Long> s = new Stack<>();
        List<Long> ans = new ArrayList<>();

        for (i = 0; i < arr.length ; i++){
            if (s.empty())
                ans.add((long) -1);
            else if (!s.isEmpty() && s.peek() > arr[i])
                ans.add(s.peek());
            else if (!s.isEmpty() && s.peek() <= arr[i]){
                while (!s.isEmpty() && s.peek() <= arr[i])
                    s.pop();
                if (s.isEmpty())
                    ans.add((long) -1);
                else ans.add(s.peek());
            }
            s.push((long) arr[i]);
        }

        return ans.stream().mapToLong(Long::longValue).toArray();

    }

    public static long[] nextSmallerElementOnLeft(long[] arr, int n)
    {
        int i = 0;
        Stack<Long> s = new Stack<>();
        List<Long> ans = new ArrayList<>();

        for (i = 0; i < arr.length; i++){
            if (s.empty())
                ans.add((long) -1);
            else if (!s.isEmpty() && s.peek() < arr[i])
                ans.add(s.peek());
            else if (!s.isEmpty() && s.peek() >= arr[i]) {
                while (!s.isEmpty() && s.peek() >= arr[i])
                    s.pop();
                if (s.isEmpty())
                    ans.add((long) -1);
                else
                    ans.add(s.peek());
            }
            s.add(arr[i]);
        }
        return ans.stream().mapToLong(l -> l).toArray();
    }

    public static long[] nextSmallerElementOnRight(long[] arr, int n)
    {
        int i = 0;
        Stack<Long> s = new Stack<>();
        List<Long> ans = new ArrayList<>();

        for (i = n - 1; i >= 0; i--){
            if (s.empty())
                ans.add((long) -1);
            else if (!s.isEmpty() && s.peek() < arr[i])
                ans.add(s.peek());
            else if (!s.isEmpty() && s.peek() >= arr[i]) {
                while (!s.isEmpty() && s.peek() >= arr[i])
                    s.pop();
                if (s.isEmpty())
                    ans.add((long) -1);
                else
                    ans.add(s.peek());
            }
            s.add(arr[i]);
        }

        long[] res = new long[ans.size()];
        for (i = 0; i < ans.size(); i++)
            res[i] = ans.get(ans.size() - i - 1);
        return res;
    }

    //Function to calculate the span of stock price for all n days.
    public static int[] calculateSpan(int price[], int n)
    {
        int i = 0;
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        List<Integer> ans = new ArrayList<>();

        for (i = 0; i < n; i++){
            if (s.empty())
                ans.add(-1);
            else if (!s.isEmpty() && s.peek().getFirst() > price[i])
                ans.add(s.peek().getFirst());
            else if (!s.isEmpty() && s.peek().getSecond() <= price[i]){
                while (!s.isEmpty() && s.peek().getSecond() <= price[i])
                    s.pop();
                if (s.isEmpty())
                    ans.add(-1);
                else ans.add(s.peek().getSecond());
            }
            s.push(new Pair<>(price[i], i));
        }
        for (i = 0; i < ans.size(); i++){
            ans.set(i, i - ans.get(i));
        }
        return ans.stream().mapToInt(l -> l).toArray();
    }

    public int largestRectangleArea(int[] heights) {

        int i = 0, pseudoIndex = -1;
        List<Integer> left, right, width, area;
        left = right = width = area = new ArrayList<>();
        Stack<Pair<Integer, Integer>> s = new Stack<>();
        // get the left array
        for (i = 0; i < heights.length; i++){
            if (s.isEmpty())
                left.add(pseudoIndex);
            else if (!s.isEmpty() && s.peek().getFirst() < heights[i])
                left.add(s.peek().getSecond());
            else if(!s.isEmpty() && s.peek().getFirst() >= heights[i]){
                while (!s.isEmpty() && s.peek().getFirst() >= heights[i])
                    s.pop();
                if (s.isEmpty())
                    left.add(pseudoIndex);
                else left.add(s.peek().getSecond());
            }
            s.add(new Pair<>(heights[i], i));
        }

        s = new Stack<>();
        pseudoIndex = heights.length;
        // get the right array
        for (i = heights.length - 1; i >= 0; i++){
            if (s.isEmpty())
                right.add(pseudoIndex);
            else if (!s.isEmpty() && s.peek().getFirst() < heights[i])
                right.add(s.peek().getSecond());
            else if(!s.isEmpty() && s.peek().getFirst() >= heights[i]){
                while (!s.isEmpty() && s.peek().getFirst() >= heights[i])
                    s.pop();
                if (s.isEmpty())
                    right.add(pseudoIndex);
                else right.add(s.peek().getSecond());
            }
            s.add(new Pair<>(heights[i], i));
        }

        // calculate the width array
        for (i = 0; i< left.size(); i++){
            width.set(i, right.get(i) - left.get(i) - 1);
        }

        // calculate the area array
        for (i = 0; i< width.size(); i++){
            area.set(i, width.get(i) * heights[i]);
        }
        // return the max in area array
        return Collections.max(area);
    }

    public static void main(String[] args) {
        System.out.println(Arrays.toString(nextSmallerElementOnRight(new long[]{4, 5, 2, 10, 8}, 5)));
    }
}
