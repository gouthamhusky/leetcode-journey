package org.slidingwindow;

import java.util.*;

import static java.lang.Integer.max;


public class SlidingWindow {

    static long maximumSumSubarray(int K, ArrayList<Integer> Arr,int N){
        int i = 0, j = 0;
        int max = Integer.MIN_VALUE;
        int sum = 0;
        // loop until end pointer equals size of array
        while( j < N){
            // keep incrementing sum
            sum += Arr.get(j);
            // if size of window < K, increase the window size
            if (j - i + 1 < K)
                j++;
                // if window size == K
            else if (j - i + 1 == K){
                // get the max, check if it can be updated
                max = max(max, sum);
                // slide the window
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

    public ArrayList<Integer> slidingMaximum(final List<Integer> A, int B) {
        int i = 0, j = 0;
        PriorityQueue<Integer> queue = new PriorityQueue<>(Collections.reverseOrder());
        ArrayList<Integer> ans = new ArrayList<>();
        while (j < A.size()){
            queue.add(A.get(j));
            if (j - i + 1 < B)
                j++;
            else{
                ans.add(queue.peek());
                queue.remove(A.get(i));
                i++; j++;
            }
        }
        return ans;
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
                max = max(max, j - i + 1);
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
                max = max(max, j - i + 1);
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

    public int maximumToys(String s){
        int i = 0, j = 0;
        int max = Integer.MIN_VALUE;

        Map<Character, Integer> freq = new HashMap<>();
        while(j < s.length()){
            char charAtJ = s.charAt(j);
            // put the current char into the map
            freq.put(charAtJ, freq.getOrDefault(charAtJ, 0) + 1);

            // if the size of the map equals max unique toys, this is a potential answer
            if (freq.size() == 2){
                max = max(max, freq.values().stream().reduce(0, Integer::sum));
                j++;
            } else if (freq.size() < 2){
                j++;
            } else {
                while (freq.size() > 2){
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

    // https://leetcode.com/problems/minimum-window-substring/
    public int minWindow(String s, String t) {
        int i = 0, j = 0;
        int min = Integer.MAX_VALUE;

        // build the frequency map for the target string
        Map<Character, Integer> freq = new HashMap<>();
        for (char c : t.toCharArray()){
            freq.put(c, freq.getOrDefault(c, 0) + 1);
        }

        int count = freq.size();

        while(j < s.length()){
            char charAtJ = s.charAt(j);
            // put the current char into the map
            freq.put(charAtJ, freq.getOrDefault(charAtJ, 0) - 1);

            if (count == 0){
                while(count == 0){

                }
            }

        }
        return min;

    }



    public static void main(String[] args) {
        SlidingWindow sw = new SlidingWindow();
        System.out.println(sw.longestSubarrayOfSumK(new int[]{4, 2, 1, 1, 3, 2, 5}, 5));
    }
}
