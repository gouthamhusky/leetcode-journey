package org.slidingwindow;

import java.util.*;

public class SlidingWindow {

    static long maximumSumSubarray(int K, ArrayList<Integer> Arr, int N){
        int i = 0, j = 0;
        int sum = 0, max = Integer.MIN_VALUE;

        while (j < N){
            sum += Arr.get(j);
            if (j - i + 1 < K){
                j++;
            }
            else {
                max = Integer.max(sum, max);
                sum -= Arr.get(i);
                i++; j++;
            }
        }
        return max;
    }


    //https://practice.geeksforgeeks.org/problems/first-negative-integer-in-every-window-of-size-k3345/1
    public long[] printFirstNegativeInteger(long A[], int N, int K)
    {
        int i = 0, j = 0;
        // queue to store the occurences of negative integers
        Queue<Long> negatives = new LinkedList<>();
        List<Long> ans = new ArrayList<>();
        while (j < N){
            // calculations: adds to the queue if negative
            if (A[j] < 0)
                negatives.add(A[j]);
            // increase window size until it hits size K
            if (j - i + 1 < K) // not yet reached given window size
                j++;
            else {  // given window size reached
                // if no negatives, return 0 as the value for current window
                if (negatives.isEmpty())
                    ans.add(Integer.toUnsignedLong(0));
                // else return front of the queue
                else
                    ans.add(negatives.peek());

                // As we shift the window, dequeue as we no longer need that negative value
                if (A[i] < 0)
                    negatives.poll();

                // slide the window
                i++; j++;
            }
        }
        return ans.stream().mapToLong(l -> l).toArray();
    }

    // https://practice.geeksforgeeks.org/problems/count-occurences-of-anagrams5839/1
    public int search(String pat, String txt) {
        int N = txt.length(), K = pat.length();
        int count = 0;
        // map that has the char and their frequencies for the input pattern
        Map<Character, Integer> patFrequency = new HashMap<>();
        // dynamic map that has the char and their frequencies for each window in the input string
        Map<Character, Integer> windowFreq = new HashMap<>();
        //  update the pattern map with char and their frequencies
        for (char c : pat.toCharArray()){
            patFrequency.put(c, patFrequency.getOrDefault(c, 0) + 1);
        }
        int i = 0, j = 0;
        while (j < N){
            // get the current char and update its freq in the window map
            char current = txt.charAt(j);
            windowFreq.put(current, windowFreq.getOrDefault(current, 0) + 1);
            if (j - i + 1 < K) {
                j++;
            }
            else {
                // call the utility method to check if the pattern map and current window
                // map contains the same char and frequencies
                if (mapMatcher(patFrequency, windowFreq))
                    count++;
                // update the window map by decrementing the count of the first char in current window
                char firstOfWindow = txt.charAt(i);
                windowFreq.put(firstOfWindow, windowFreq.get(firstOfWindow) -1 );
                // slide the window
                i++; j++;
            }
        }
        return count;
    }

    /*
    utility method that checks if the target map contains all the
    chars in the source map with the same frequency as well
     */
    boolean mapMatcher(Map<Character, Integer> source, Map<Character, Integer> target){
        for (char c : source.keySet()){
            // ignore chars with freq as 0 in the source map
            if (source.get(c) > 0 && !Objects.equals(target.get(c), source.get(c)))
                return false;
        }
        return true;
    }

    public int longestSubarrayOfSumK(int[] A, int K){

        int i = 0, j = 0;
        int N = A.length;
        int max = Integer.MIN_VALUE, sum = 0;
        while (j < N){
            sum += A[j];
            if (sum < K){
                j++;
            }
            else if (sum == K){
                max = Math.max(max, j - i + 1);
                j++;
            }
            else {
                // sum > K
                while (sum > K && i < N){
                    sum -= A[i];
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    // https://practice.geeksforgeeks.org/problems/longest-k-unique-characters-substring0853/1
    public int longestkSubstr(String s, int k) {
        int i = 0, j = 0;
        int max = Integer.MIN_VALUE;
        // setup a map to keep track of the unique chars and their counts in each window
        Map<Character, Integer> freq = new HashMap<>();
        while(j < s.length()){
            char charAtJ = s.charAt(j);
            // put the current char into the map
            freq.put(charAtJ, freq.getOrDefault(charAtJ, 0) + 1);
            // if the size of the map is < k, we increase the size of window
            if (freq.size() < k)
                j++;
            // if it equals k, it means this is a candidate. check size with current max and increase window size
            else if (freq.size() == k){
                max = Integer.max(max, j - i + 1);
                j++;
            }
            // goes here if the map has more than k unique elements
            // keep removing the ith element from the map until its size goes <=k
            // if a char has 0 as freq, remove it from the map
            else{
                while (freq.size() > k){
                    char charAtI = s.charAt(i);
                    freq.put(charAtI, freq.get(charAtI) - 1);
                    if (freq.get(charAtI) == 0)
                        freq.remove(charAtI);
                    i++;
                }
                j++;
            }
        }
        return max;
    }

    public int lengthOfLongestSubstring(String s) {
        int i = 0, j = 0;
        int max = Integer.MIN_VALUE;

        if (s.isEmpty())
            return 0;

        // map to keep track of the chars and their counts in each window
        Map<Character, Integer> freq = new HashMap<>();
        while(j < s.length()){
            char charAtJ = s.charAt(j);
            // put the current char into the map
            freq.put(charAtJ, freq.getOrDefault(charAtJ, 0) + 1);
            // if the size of the map equals size of current window, this is a potential answer
            if (freq.size() == j - i + 1){
                max = Integer.max(max, j - i + 1);
                j++;
            }
            // goes here if current window has duplicate elements
            else if (freq.size() < j - i + 1){
                while (freq.size() < j - i + 1){
                    char charAtI = s.charAt(i);
                    freq.put(charAtI, freq.get(charAtI) - 1);
                    if (freq.get(charAtI) == 0)
                        freq.remove(charAtI);
                    i++;
                }
                j++;
            }
        }

        return max;
    }


    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        System.out.println(sw.longestSubarrayOfSumK(new int[]{4, 2, 1, 1, 3, 2, 5}, 5));
    }
}
