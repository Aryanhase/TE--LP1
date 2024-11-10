import java.util.Scanner;

// Shortest Job First (SJF) CPU Scheduling Algorithm
public class SJF {
    public static void main(String[] args) {
    
        Scanner in = new Scanner(System.in);

        // Prompt user to enter the number of processes
        System.out.println("Enter the number of processes");
        int n = in.nextInt();
    
        // Initialize arrays to store process details
        int pid[] = new int[n];   // Process IDs
        int at[] = new int[n];    // Arrival Times
        int ct[] = new int[n];    // Completion Times
        int bt[] = new int[n];    // Burst Times
        int tat[] = new int[n];   // Turnaround Times
        int wt[] = new int[n];    // Waiting Times
        boolean isCompleted[] = new boolean[n]; // Track completion status of processes
        
        float avgTAT = 0;  // Avg Turnaround Time
        float avgWT = 0;   // Avg Waiting Time

        // Input process details: Arrival Time and Burst Time for each process
        for(int i = 0; i < n; i++){
            pid[i] = i;
            System.out.println("Enter the arrival time for P" + i);
            at[i] = in.nextInt();
            System.out.println("Enter the burst time for P" + i);
            bt[i] = in.nextInt();
            isCompleted[i] = false;  // Mark each process as not completed initially
        }

        // Sort processes by Arrival Time 
        for (int i = 0; i < n - 1; i++) {
            for (int j = i + 1; j < n; j++) {
                if (at[i] > at[j]) {  // Swap based on arrival time

                    int temp = bt[i];
                    bt[i] = bt[j];
                    bt[j] = temp;

                    temp = at[i];
                    at[i] = at[j];
                    at[j] = temp;

                    temp = pid[i];
                    pid[i] = pid[j];
                    pid[j] = temp;

                    boolean t = isCompleted[i];
                    isCompleted[i] = isCompleted[j];
                    isCompleted[j] = t;
                }
            }
        }

        int currentTime = 0, completed = 0;  // Track current time and completed processes count

        // Continue until all processes are completed
        while (completed < n) {
            int minBurst = Integer.MAX_VALUE;  
            int minIndex = -1;  

            // Find the process with shortest burst time among those that have arrived
            for (int i = 0; i < n; i++) {
                if (!isCompleted[i] && at[i] <= currentTime && bt[i] < minBurst) {
                    minBurst = bt[i];
                    minIndex = i;
                }
            }

            // If no process is available to run, increment current time
            if (minIndex == -1) {
                currentTime++;
            } else {
                // Schedule process with the shortest burst time
                ct[minIndex] = currentTime + bt[minIndex];  
                currentTime += bt[minIndex];  
                isCompleted[minIndex] = true;  
                completed++;  
            }
        }

        // Calculate Turnaround Time (TAT) = Completion Time (CT) - Arrival Time (AT)
        for(int i = 0; i < n; i++) {
            tat[i] = ct[i] - at[i];
        }

        // Calculate Waiting Time (WT) = Turnaround Time (TAT) - Burst Time (BT)
        for(int i = 0; i < n; i++) {
            wt[i] = tat[i] - bt[i];
        }

        // Display process details including AT, BT, CT, TAT, and WT
        System.out.println("  Pid\tAT\tBT\tCT\tTAT\tWT\t ");
        System.out.println("---------------------------------------------");
        for(int i = 0; i < n; i++){
            StringBuilder string = new StringBuilder();
            string.append("  P"+pid[i]).append("\t");
            string.append(at[i]).append("\t");
            string.append(bt[i]).append("\t");
            string.append(ct[i]).append("\t");
            string.append(tat[i]).append("\t");
            string.append(wt[i]).append("\t");
            System.out.println(string.toString());
        }
        System.out.println("---------------------------------------------");
    
        // Calculate average Turnaround Time and Waiting Time
        for(int i = 0; i < n; i++){
            avgWT += wt[i];
            avgTAT += tat[i];
        }
        
        avgWT = avgWT / n;  // Average Waiting Time
        avgTAT = avgTAT / n;  // Average Turnaround Time
    
        System.out.println("  Avg\t\t\t\t" + avgTAT + "\t" + avgWT + "\t");
        in.close();
    }
}
