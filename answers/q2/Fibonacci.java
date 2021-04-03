//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         2

//Write a multithreaded program that generates the Fibonacci sequence. 
//This program should work as follows: On the command line, the user will
//enter the number of Fibonacci numbers that the program is to generate.
//The program will then create a separate thread that will generate the Fibonacci
//numbers, placing the sequence in data that can be shared by the threads 
//(an array is probably the most convenient data structure). When the thread 
//finishes execution, the parent thread will output the sequence generated 
//by the child thread. Because the parent thread cannot begin outputting
//the Fibonacci sequence until the child thread finishes, the parent thread 
//will have to wait for the child thread to finish. Use the techniques 
//described in Section 4.4 to meet this requirement.

import java.util.concurrent.*;

/*
the class CalcFib is used to calculate the fibonacci sequence
for a length of 'fibLength' numbers. As it implements Callable,
objects of this class will be run in a child thread.
*/
class CalcFib implements Callable<int[]> {

    private int fibLength; //The length of the fib sequence

    //constructor - The length is passed from the parent.
    public CalcFib(int fibLength){
        this.fibLength = fibLength;
    }
    
    //The thread will execute in this method.
    public int[] call() {
        
        //declare the array
        int fibSequence[] = new int[fibLength];

        //initializing the first two elements of the array to match
        //the prerequisites of the fibonacci sequence.
        fibSequence[0] = 0;
        fibSequence[1] = 1;

        //populates 'fibSequence' where each element ascending is the
        //next number in the fibonacci sequence.
        for(int i = 2; i < fibLength; i++){
            fibSequence[i] = fibSequence[i-1] + fibSequence[i-2];
        }
    
        return fibSequence;
    }
}


//The main class. The program executes this class first.
class Fibonacci {
    public static void main(String[] args) {
        int fib = 0; //init the length of the sequence.
        
        

        //parsing the command line arguments.
        if(args.length > 0){
           try{
               fib = Integer.parseInt(args[0]);
           } catch(NumberFormatException ne){
               //If the user entered characters that can't be parsed as an integer
            System.out.println("please enter an integer as a command line argument.");
            //Exit JVM
            System.exit(0);
           }
        } else {
            //This fires if the user did not provide a command line argument to the program.
            System.out.println("please enter an integer as a command line argument.");
            //Exit JVM
            System.exit(0);
        }



        //create executor service pool
        ExecutorService t1 = Executors.newSingleThreadExecutor();
        
        //create Future array (we're expecting an array to be returned from the child thread)
        Future<int[]> result = t1.submit(new CalcFib(fib));

        try {
            //try to get the results from the child thread.
            int seq[] = result.get();

            //print the results.
            for(int i = 0; i < fib; i++){
                System.out.print(seq[i]);
                if(i != fib -1){
                    System.out.print(", ");
                } else {
                    System.out.print("\n");
                }
            }


        } catch (InterruptedException | ExecutionException ie) {
            //catching exceptions caused by the child thread not completing successfully. 
            System.out.println("Exception caught: " + ie);
        }
        
        //closing the pool down. 
        t1.shutdown();
    }
}

