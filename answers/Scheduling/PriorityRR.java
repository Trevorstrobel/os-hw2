//Author:           Trevor Strobel
//Date:             4/2/21
//Assn:             CSCI-3000 Assignment2
//Question:         6
import java.util.*;


// The class PriorityRR is the implementation of the Priority
// Round-Robin algorithm for task scheduling. 
public class PriorityRR implements Algorithm {


    private List<Task> queue; //queue to hold all of the Tasks (ArrayList)
    private Task currentTask;

    private int tasksRun;  // the number of tasks in the sched file
    

    //PriorityRR object constructor. 
    public PriorityRR(List<Task> queue) {
        this.queue = queue;
        tasksRun = queue.size();
    }
    
    // schedule() schedules the tasks according to the PriorityRR algorithm
    // and runs them on the CPU object. 
    public void schedule() {


        // Prints descriptive line to the terminal 
        System.out.println("Priority Round-Robin Scheduling \n");

        while(!queue.isEmpty()) {

            queue = prioritySort(queue); //sorts the queue between each burst
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

    // prioritySort() sorts the ArrayList<Task> 
    // 'queue' based on priority. 
    public List<Task> prioritySort(List<Task> queue2){
        
        //bubble sort because it doesn't need to be fast here. 
        boolean changed = true; //indicates a swap was made this round
        while(changed){
            boolean flag = false;

            for(int i = 1; i < queue2.size(); i++){
                //if priority of Pi is greater than P(i-1), swap those elements
                if(queue2.get(i).getPriority() > queue2.get(i-1).getPriority()){
                    Task temp = queue2.get(i);
                    queue2.remove(i); //removes i from the list
                    queue2.add(i-1, temp); //insert the bigger priority task, effectively
                                          //swapping the elements.
                   flag = true;
                }
            }
            
            if(flag == false){
                changed = false;
            }
        }

        return queue2;
    }


    // pickNextTask readies the next task in the queue
    // to be processed. 
    public Task pickNextTask() {
        return queue.get(0);
    }
}
