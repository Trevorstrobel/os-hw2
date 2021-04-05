This program is meant to replicate different process scheduling algorithms 
used in computer operating systems. This program has the capability to schedule
processes based on the following algorithms:

        Alg Code     |  Algorithm
        -------------|------------------------------
        FCFS         |  First Come First Serve
        RR           |  Round-Robin (Burst time: 10)
        SJF          |  Shortest Job First
        PRI          |  Priority
        PRI-RR       |  Priority Round-Robin

The above table also lists the Algorithm Code (Alg Code). These will be important
for program execution as described later.

To compile the application, run the following command from within the "Scheduling"
folder:

javac Driver.java

To execute the program, first find the Alg Code that corresponds to the algorithm
you'd like to implement. Then run the following command from within the "Scheduling"
folder:

java Driver <Alg Code> sched.txt


You can also replace the sched.txt file with another schedule as long as
it adheres to the format of the included sched.txt file. That format is not 
discussed here. 

NOTE: It may help to interpret the output of the program by piping the output
to a file. If we wanted the output to be written to a file called "A.txt" we 
would run the following command:

java Driver <Alg Code> sched.txt > A.txt

