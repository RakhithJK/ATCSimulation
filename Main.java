import java.io.*;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Random;
import java.util.concurrent.BlockingQueue;

/**
 * @author Shaifil
 *
 */
public class Main {

	/**
	 * @param args
	 * @throws InterruptedException
	 */
	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
		HashMap <Integer, ArrayList<Airplane>> threadHashTable = new HashMap<>();
		System.out.println("Enter number of threads to spawn");
		BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
		int n = Integer.parseInt(reader.readLine());
		// Thread[] threads = new Thread[n];
		Airplane[] tracker = new Airplane[n];
		Random r = new Random();
		for(int i=0;i<n;i++)
		{
			int start = r.nextInt(10);
			if(start==0)
			{
				tracker[i]=new Airplane();
				if(threadHashTable.get(start).isEmpty())
				{
					ArrayList<Airplane> threadList= new ArrayList<>();
					threadList.add(tracker[i]);
					threadHashTable.put(start,threadList);
				}
				else {
					threadHashTable.get(start).add(tracker[i]);
				}
			}
		}

		for(int i=0;;i++)
		{
			if(threadHashTable.get(i).isEmpty())
			{
				continue;
			}
			else
			{
				for(int counter=0;counter<threadHashTable.get(i).size();counter++)
				{
					threadHashTable.get(i).get(counter).start();
				}
			}
		}
//		int[] startTime = new int[n];
//		System.out.println("Enter the start time of threads in seconds");
//		int deduct = 0;
//		for (int i = 0; i < n; i++)
//			startTime[i] = Integer.parseInt(reader.readLine());
//
//		for (int t = 0; t < n; ++t) {
//			Thread.sleep((1000 * startTime[t]) - (deduct * 1000));
//			// System.out.println("Creating thread " + t);
//			deduct = startTime[t];
//			// System.out.println(deduct);
//			tracker[t] = new Airplane(t);
//			// new Thread
//			tracker[t].start();
//		}
	}
}

class Execute extends Thread {
	/*
	 * (non-Javadoc)
	 * 
	 * @see java.lang.Runnable#run()
	 */

	// AtomicReference<Runway> rw = new AtomicReference();
	static final Runway rw = new Runway();
	String[] states = { "About_to_land", "Runway", "Taxing1", "Gate", "Taxing2", "Runway", "Took_off" };

	public Execute(String state) {

	}

	public Execute(int x) {
		super(Integer.toString(x));
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		try {
			// System.out.println("Current "+Thread.currentThread().getName());
			rw.accessRunway();
		} catch (Exception ex) {
			System.out.println(ex.getStackTrace());
		}

	}
}

/*
 * class Reminder { Timer timer;
 * 
 * Reminder(int seconds) { timer = new Timer(); timer.schedule(new RemindTask(),
 * seconds * 1000); }
 * 
 * class RemindTask extends TimerTask { public void run() {
 * System.out.println("Time's up!"); timer.cancel(); // Terminate the timer
 * thread } } }
 */