package p12.exercise;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{

    private Map<Q,Queue<T>> map;


    public MultiQueueImpl(){

        map = new HashMap<>();

    }



    @Override
    public Set<Q> availableQueues() {
        
        
        Set<Q> set = new TreeSet<>();
        set.addAll(map.keySet());
        return set;
    }

    @Override
    public void openNewQueue(Q queue) {
    
        if(map.containsKey(queue)) throw new IllegalArgumentException();
        map.put(queue, new LinkedList<T>());

    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        
        if(!map.containsKey(queue)) throw new IllegalArgumentException();
        return map.get(queue).isEmpty();


    }

    @Override
    public void enqueue(T elem, Q queue) {
       
        if(!map.containsKey(queue)) throw new IllegalArgumentException();
        map.get(queue).add(elem);
        
    }

    @Override
    public T dequeue(Q queue) {
        if(!map.containsKey(queue)) throw new IllegalArgumentException();
        return map.get(queue).poll();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        
        Map<Q,T> deq = new TreeMap<>();
        for(Q queue : map.keySet()){

            deq.put(queue, map.get(queue).poll());

        }
        return deq;

    }

    @Override
    public Set<T> allEnqueuedElements() {
        
        Set<T> set = new TreeSet<>();
        for(Q key : map.keySet()){
            set.addAll(map.get(key));
        }
        
        return set;
        


    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        
        if(!map.containsKey(queue)) throw new IllegalArgumentException();
        List<T> list = new LinkedList<>();
        while(!(map.get(queue).isEmpty())){
            list.add(map.get(queue).poll());
        }

        return list;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {

        if(!map.containsKey(queue)) throw new IllegalArgumentException();
        if(map.keySet().size()==1) throw new IllegalStateException();
        List<T> list = dequeueAllFromQueue(queue);
        map.remove(queue);
        map.get(map.keySet().iterator().next()).addAll(list);
        
        
    }   

}
