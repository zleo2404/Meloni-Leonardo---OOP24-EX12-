package p12.exercise;

import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.Queue;
import java.util.Set;
import java.util.TreeMap;
import java.util.TreeSet;

public class MultiQueueImpl<T, Q> implements MultiQueue<T, Q>{

    Map<Q,Queue<T>> map;
    //Queue<T>[] code = new Queue[50]; 
    //int ne=0;

    public MultiQueueImpl(){

        /*
        for(int i=0;i<50;i++){
            code[i] = new LinkedList<T>();
        }*/
        map = new TreeMap<>();
    }



    @Override
    public Set<Q> availableQueues() {
        /*Set<Q> set = new HashSet<>();

        for(int i=0;i<ne;i++){

            set.add(map.get(i));
        } 

        return set;*/
        
        
        Set<Q> set = new TreeSet<>();
        for(Q key : map.keySet()){
            set.add(key);
        }
        
        return set;
    }

    @Override
    public void openNewQueue(Q queue) {
        //map.put(ne, queue);
        //ne++;

        if(existQueue(queue)) throw new IllegalArgumentException();
        map.put(queue, new LinkedList<T>());

    }

    private boolean existQueue(Q queue){
        for(Q q : map.keySet()){
            if(q.equals(queue)){
                return true;
            }
        }
        return false;
    }

    @Override
    public boolean isQueueEmpty(Q queue) {
        
        if(!existQueue(queue)) throw new IllegalArgumentException();
        return map.get(queue).isEmpty();


    }

    @Override
    public void enqueue(T elem, Q queue) {
       /*int index=-1;
        boolean trovato=false;
        for(int i=0;i<ne;i++){

            if(queue.equals(map.get(i))){
                index=i;
                trovato=true;
            }
        }
        if(trovato){

            System.out.println(code[index]);
            code[index].add(elem);
        }else{
            System.out.println("Cassa non trovata");

        }*/
        if(!existQueue(queue)) throw new IllegalArgumentException();
        map.get(queue).add(elem);
        
    }

    @Override
    public T dequeue(Q queue) {
        if(!existQueue(queue)) throw new IllegalArgumentException();
        return map.get(queue).poll();
    }

    @Override
    public Map<Q, T> dequeueOneFromAllQueues() {
        
        Map<Q,T> test = new TreeMap<>();
        for(Q queue : map.keySet()){

            test.put(queue, map.get(queue).poll());

        }
        return test;
    }

    @Override
    public Set<T> allEnqueuedElements() {
        
        Set<T> set = new TreeSet<>();
        for(Q key : map.keySet()){
            for(T elem : map.get(key)){
                set.add(elem);
            }
        }
        
        return set;
        


    }

    @Override
    public List<T> dequeueAllFromQueue(Q queue) {
        
        if(!existQueue(queue)) throw new IllegalArgumentException();
        List<T> prova = new LinkedList<>();

        while(!(map.get(queue).isEmpty())){

            prova.add(map.get(queue).poll());
        }

        return prova;
    }

    @Override
    public void closeQueueAndReallocate(Q queue) {

        if(!existQueue(queue)) throw new IllegalArgumentException();
        if(map.keySet().size()==1) throw new IllegalStateException();
        List<T> prova = dequeueAllFromQueue(queue);
        map.remove(queue);
        map.get(map.keySet().iterator().next()).addAll(prova);
        
        /*
        for(Q q : map.keySet()){
            if(!q.equals(queue)){
                map.get(q).addAll(dequeueAllFromQueue(queue));
                break;
            }
        }

        map.remove(queue);*/
        
    }   

}
