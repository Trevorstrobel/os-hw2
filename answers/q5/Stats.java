/**
 * @author Jan Andre Verceles-Zara
 */


import java.util.Scanner;
public class Stats {
	public static void main(String []args){
        int n; //holds amount of values in the set
        int[] data; //holds the data set
        final int[] unitSet = new int[2]; // This holds the minimum and maximum for the set given
    final double[] unitAverage = new double[1]; // This holds the average of the entire set
        Scanner kbd = new Scanner(System.in);
        System.out.print("How many values are in the set? :");
        n = kbd.nextInt();
        data = new int[n];
        
    System.out.println("Enter the values in the set :");
        for (int i = 0; i < n; i++)
            data[i] = kbd.nextInt();
        //Thread where the average of the set is calculated
        Thread avgThread = new Thread(new Runnable() {
            @Override
            public void run() {
        double avg = 0;
                for (int i = 0; i < n ; i++)
                    avg += data[i];
                avg = avg / n;
        unitAverage[0] = avg;
            }
        });
        //Thread where the minimum is found
        Thread minThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int min = data[0];
                for (int i = 1; i < n ; i++)
                    if (min > data[i])
                        min = data[i];
                unitSet[0] = min;
            }
        });
        //Thread where the maximum is found
        Thread maxThread = new Thread(new Runnable() {
            @Override
            public void run() {
                int max = data[0];
                for (int i = 1; i < n ; i++)
                    if (max < data[i])
                        max = data[i];
                unitSet[1] = max;
            }
        });
        //each thread is started
        avgThread.start(); 
        minThread.start();
        maxThread.start();
    try {
	//each thread is into a join/wait state where each thread awaits termination    
        avgThread.join();
        minThread.join();
        maxThread.join();
    }
    catch (Exception e) {
    }
        System.out.printf("Average of Data : %f\nMinimum of Data : %d\nMaximum of Data :%d\n", unitAverage[0], unitSet[0],unitSet[1]);
        kbd.close();
     }
}
