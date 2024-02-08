package timingtest;
import edu.princeton.cs.algs4.Stopwatch;

/**
 * Created by hug.
 */
public class TimeSLList
{
    private static void printTimingTable(AList<Integer> Ns, AList<Double> times, AList<Integer> opCounts) {
        System.out.printf("%12s %12s %12s %12s\n", "N", "time (s)", "# ops", "microsec/op");
        System.out.printf("------------------------------------------------------------\n");
        for (int i = 0; i < Ns.size(); i += 1) {
            int N = Ns.get(i);
            double time = times.get(i);
            int opCount = opCounts.get(i);
            double timePerOp = time / opCount * 1e6;
            System.out.printf("%12d %12.2f %12d %12.2f\n", N, time, opCount, timePerOp);
        }
    }

    public static void main(String[] args) {
        timeGetLast();
    }

    public static void timeGetLast()
    {
        // TODO: YOUR CODE HERE
        /*Create an SLList*/
        AList<Integer> ListSize = new AList<>();
        AList<Double> ListTime = new AList<> ();
        AList<Integer> ListCall = new AList<>();
        int M=10000;
        /*Add N items to the SLList.*/
       for(int base=1000;base<=128000;base*=2)
       {
           SLList<Integer> ListTest = new SLList<>();
           for(int i=1;i<=base;i++)
           {
               ListTest.addFirst(i);
           }
           /*Start the timer*/
           Stopwatch time = new Stopwatch();
           for(int i=1;i<=M;i++)
           {
               ListTest.getLast();
           }
           ListSize.addLast(ListTest.size());
           ListTime.addLast(time.elapsedTime());
           ListCall.addLast(M);
       }




    printTimingTable(ListSize,ListTime,ListCall);
    }

}
