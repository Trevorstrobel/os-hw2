//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         3


import java.util.concurrent.Callable;
import java.io.FileNotFoundException;
import java.util.Random;


//The class MonteCarlo describes the implementation of determining Pi
// by using a Monte Carlo simulation.  Included is a sub-class called 
// MCCount that will run as a child thread and generate points.
// The parent thread within class MonteCarlo will calculate the 
// approximation of Pi based on the results of the points generated
// by MCCount.
public class MonteCarlo {

    //points generated that are within a circle with radius of 1
    static Integer in_circle = 0; 
    
    //total points generated
    static Integer total = 0; 

    //The default number of points to generate
    static Integer len = 10000; 

 

    public static void main(String[] args){

        //if the user provides a command line argument (Integer), the simulation will
        // generate that many points.
        if(args.length > 0){
            try {
                len = Integer.parseInt(args[0]);
                if(len < 0){
                    len = len * -1; //flips the sign if the user enters a negative number
                }

            } catch(NumberFormatException ne){
                //If the user entered characters that can't be parsed as an Integer
                System.out.println("please enter an integer between 0 and 2147483647");
                System.exit(0);
            }
        } 

        //creates a new thread that creates a MCCount object
        Thread worker = new Thread(new MonteCarlo().new MCCount());
        worker.start(); //executes MCCount.run()


        //try to join the worker thread
        try {
            worker.join();
        } catch (InterruptedException ie) {
            System.out.println("Caught Exception: " + ie + "\n");
        }

        System.out.println("total: " + total);
        System.out.println("in_circle: " + in_circle);

        //calculates the approximate value of pi based on the MC simulation
        double pi = 4 * ((double)in_circle / (double)total);

        //prints the results
        System.out.println("The simulation of " + len + " points approximates pi to be: " + 
                            pi + ".\n");
    }//end main



    // The Class MCCount generates points on a cartesian plane.
    // It will determine which points are within a circle with radius = 1
    // It will also determine how many points were generated.
    // Both of those values are stored in the "global" variables 
    //    in_circle & total defined within class MonteCarlo
    private class MCCount implements Runnable {

        //random number generator
        Random rand = new Random();
        public void run(){
            
            //generates 'len' random numbers in range (0, 1]
            //increments total each loop, and increments in_cirlce
            //if x*x + y*y <= 1
            for(int i = 0; i < len; i++){
                Double x = rand.nextDouble(); //gen x coordinate
                Double y = rand.nextDouble(); //gen y coordinate

                total++; //increment total
                
                //if the point is in the circle, increment 'in_circle'
                if((x*x) + (y*y) <= 1){
                    in_circle++;
                }
            }
        }   
    }    
}
