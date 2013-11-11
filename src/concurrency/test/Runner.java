package concurrency.test;

import android.graphics.Matrix;
import concurrency.stm.Metrics;

public class Runner {
    public static void main(String[] args) throws Exception{
        TransferStrategy[] tss = new TransferStrategy[] {
            new NonSyncStrategy(),
            new SyncStrategy(),
            new STMStrategy()
        };

        //Thread.sleep(1000);

        for (TransferStrategy ts : tss) {
            Bank bank = new Bank();
            System.out.println(ts.getClass().getSimpleName());
            System.out.println("Bank sum before: " + bank.sum());
            long before = System.currentTimeMillis();
            bank.simulate(100, 1000, ts);
            long after = System.currentTimeMillis();
            System.out.println("Bank sum after: " + bank.sum());
            System.out.println("Elapsed time: " + (after - before));
        }

        Metrics.showAverageValidationTime();
        Metrics.showMaxValidationTime();
        Metrics.showMaxTransactionBlockRunning();
        Metrics.showMinTransactionBlockRunning();
        Metrics.showAverageUpdateTime();
        Metrics.showMaxUpdateTime();
        Metrics.showAverageCommitTime();
        Metrics.showMaxCommitTime();
        Metrics.showMinCommitTime();
        Metrics.showMaxRetriesAmount();
    }
}
