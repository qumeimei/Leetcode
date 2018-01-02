Write a program to find the n-th ugly number.

Ugly numbers are positive numbers whose prime factors only include 2, 3, 5. 
For example, 1, 2, 3, 4, 5, 6, 8, 9, 10, 12 is the sequence of the first 10 ugly numbers.

Note that 1 is typically treated as an ugly number, and n does not exceed 1690.

class Solution {
    public int nthUglyNumber(int n) {
        Queue<Long> pq = new PriorityQueue<Long>();
        Set<Long> set = new HashSet<Long>();
        long[] primes = new long[3];
        primes[0] = Long.valueOf(2);
        primes[1] = Long.valueOf(3);
        primes[2] = Long.valueOf(5);
        set.add(Long.valueOf(1));
        pq.offer(Long.valueOf(1));
        Long v = Long.valueOf(1);
        for (int i = 1 ; i <= n; i++){
            v = pq.poll();
            for (int j = 0; j < 3; j++){
                if (!set.contains(v * primes[j])){
                    set.add(v*primes[j]);
                    pq.offer(v*primes[j]);
                }
            }
        }
        return v.intValue();
    }
}
