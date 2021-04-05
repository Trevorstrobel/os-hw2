//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         6
import java.util.*;


// The class Priority is the implementation of the Priority
// algorithm for task scheduling. 
public class Priority implements Algorithm {


    private List<Task> queue; //queue to hold all of the Tasks (ArrayList)
    private Task currentTask;

    private int tasksRun;  // the number of tasks in the sched file
    

    //Priority object constructor. 
    public Priority(List<Task> queue) {
        this.queue = queue;
        tasksRun = queue.size();
    }
    
    // schedule() schedules the tasks according to the Priority algorithm
    // and runs them on the CPU object. 
    public void schedule() {
        //this is where the magic happens

        //bubble sort because it doesn't need to be fast here. 
        boolean changed = true; //indicates a swap was made this round
        while(changed){
            boolean flag = false;

            for(int i = 1; i < queue.size(); i++){
                //if priority of Pi is greater than P(i-1), swap those elements
                if(queue.get(i).getPriority() > queue.get(i-1).getPriority()){
                    Task temp = queue.get(i);
                    queue.remove(i); //removes i from the list
                    queue.add(i-1, temp); //insert the bigger priority task, effectively
                                          //swapping the elements.
                   flag = true;
                }
            }
            
            if(flag == false){
                changed = false;
            }
        }


        //Prints the sorted queue to the command line. 
        System.out.println("Priority Scheduling \n");

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
