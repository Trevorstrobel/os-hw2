//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         6
import java.util.*;


// The class SJF is the implementation of the Shortest
// Job First algorithm for task scheduling. 
public class SJF implements Algorithm {


    private List<Task> queue; //queue to hold all of the Tasks (ArrayList)
    private Task currentTask;

    private int tasksRun;  // the number of tasks in the sched file
    

    //Shortest Job First object constructor. 
    public SJF(List<Task> queue) {
        this.queue = queue;
        tasksRun = queue.size();
    }
    
    // schedule() schedules the tasks according to the SJF algorithm
    // and runs them on the CPU object. 
    public void schedule() {
        //this is where the magic happens

        //bubble sort because it doesn't need to be fast here. 
        boolean changed = true; //indicates a swap was made this round
        while(changed){
            boolean flag = false;

            for(int i = 1; i < queue.size(); i++){
                //if burst of Pi is less than P(i-1), swap those elements
                if(queue.get(i).getBurst() < queue.get(i-1).getBurst()){
                    Task temp = queue.get(i);
                    queue.remove(i); //removes i from the list
                    queue.add(i-1, temp); //insert the smaller burst process, effectively
                                          //swapping the elements.
                   flag = true;
                }
            }
            
            if(flag == false){
                changed = false;
            }
        }


        //Prints the sorted queue to the command line. 
        System.out.println("SJF Scheduling \n");

        while(!queue.isEmpty()) {
            currentTask = pickNextTask();

            CPU.run(currentTask, currentTask.getBurst());

            queue.remove(currentTask);
        }



    }

    // pickNextTask readies the next task in the queue
    // to be processed. 
    public Task pickNextTask() {
        return queue.get(0);
    }
}
