import java.util.ArrayList;
import java.util.Collections;
import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.CopyOnWriteArrayList;

public class Counter {
	
	public volatile static  ArrayBlockingQueue<String> clientsStorage = new ArrayBlockingQueue<String>(10);
	 
    }