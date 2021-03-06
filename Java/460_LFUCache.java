Design and implement a data structure for Least Frequently Used (LFU) cache. It should support the 
following operations: get and put.

get(key) - Get the value (will always be positive) of the key if the key exists in the cache, otherwise return -1.
put(key, value) - Set or insert the value if the key is not already present. When the cache reaches its
capacity, it should invalidate the least frequently used item before inserting a new item. For the purpose of this 
problem, when there is a tie (i.e., two or more keys that have the same frequency), the least recently used key would 
be evicted.

Follow up:
Could you do both operations in O(1) time complexity?

Example:

LFUCache cache = new LFUCache( 2 /* capacity */ );

cache.put(1, 1);
cache.put(2, 2);
cache.get(1);       // returns 1
cache.put(3, 3);    // evicts key 2
cache.get(2);       // returns -1 (not found)
cache.get(3);       // returns 3.
cache.put(4, 4);    // evicts key 1.
cache.get(1);       // returns -1 (not found)
cache.get(3);       // returns 3
cache.get(4);       // returns 4

Method 1:
Time complexity: O(logn)
class LFUCache {
    class Pair{
        int key;
        int val;
        int freq;
        int stamp;
        public Pair(int key, int value, int freq, int stamp){
            this.key = key;
            this.val = value;
            this.freq = freq;
            this.stamp = stamp;
        }
    }
    private Map<Integer, Pair> map;
    private Queue<Pair> pq;
    private int capacity;
    private int num;
    private int stamp;
    
    public LFUCache(int capacity) {
        this.num = 0;
        this.stamp = 0;
        this.capacity = capacity;
        this.map = new HashMap<Integer, Pair>();
        this.pq = new PriorityQueue<Pair>(new Comparator<Pair>(){
            public int compare(Pair p1, Pair p2){
                if (p1.freq == p2.freq){
                    return p1.stamp - p2.stamp;
                }
                return p1.freq - p2.freq;
            }
        });
    }
    
    public int get(int key) {
        if (capacity == 0){
            return -1;
        }
        int value = -1;
        if (map.containsKey(key)){
            Pair oldPair = map.get(key);
            pq.remove(oldPair); //O(n) for priorityQueu, O(logn) for treemap
            value = oldPair.val;
            Pair newPair = new Pair(key, value, oldPair.freq + 1, stamp++);
            map.put(key, newPair);
            pq.offer(newPair);
        }
        return value;
    }
    
    public void put(int key, int value) {
        if (capacity == 0){
            return;
        }
        if (!map.containsKey(key)){
            if (num == capacity){
                Pair oldPair = pq.poll();
                map.remove(oldPair.key);
            }else{
                num++;
            }
            Pair newPair = new Pair(key, value, 1, stamp++);
            map.put(key, newPair);
            pq.offer(newPair);
        }else{
            Pair oldPair = map.get(key);
            pq.remove(oldPair);
            Pair newPair = new Pair(key, value, oldPair.freq + 1, stamp++);
            map.put(key, newPair);
            pq.offer(newPair);
        }
    }
}

/**
 * Your LFUCache object will be instantiated and called as such:
 * LFUCache obj = new LFUCache(capacity);
 * int param_1 = obj.get(key);
 * obj.put(key,value);
 */
