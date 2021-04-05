//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         6
import java.util.*;


// The class RR is the implementation of the Round-Robin
// algorithm for task scheduling. 
public class RR implements Algorithm {


    private List<Task> queue; //queue to hold all of the Tasks (ArrayList)
    private Task currentTask;

    private int tasksRun;  // the number of tasks in the sched file
    

    //RR (Round Robin) object constructor. 
    public RR(List<Task> queue) {
        this.queue = queue;
        tasksRun = queue.size();
    }
    
    // schedule() schedules the tasks according to the RR algorithm
    // and runs them on the CPU object. 
    public void schedule() {
        //this is where the magic happens

        

        //Prints the sorted queue to the command line. 
        System.out.println("Round Robin Scheduling \n");

        while(!queue.isEmpty()) {
            currentTask = pickNextTask();

            CPU.run(currentTask, currentTask.getBurst());

            //the CPU runs in bursts of 10 units. if the remainder of the task's
            // burst is above 0 after running, then it gets put at the end of 
            // the queue. 
            if((currentTask.getBurst() - 10) > 0){
                Task temp = currentTask;
                temp.setBurst(currentTask.getBurst() -10);
                queue.add(temp);
            }else{
                System.out.println("Task " + currentTask.getName() + " finished.\n");
            }
            queue.remove(currentTask);
            
        }



    }

    // pickNextTask readies the next task in the queue
    // to be processed. 
    public Task pickNextTask() {
        return queue.get(0);
    }
}
