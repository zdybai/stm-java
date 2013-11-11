package concurrency.stm;

/**
 * @author mishadoff
 */
public final class STM {
    private STM() {}

    public static final Object commitLock = new Object();

    public static void transaction(TransactionBlock block) {
        boolean committed = false;
        Long retry = -1L;
        while (!committed) {
            retry++;
            Transaction tx = new Transaction();
            block.setTx(tx);
            Long beforeRun = System.nanoTime();
            block.run();
            Long afterRun = System.nanoTime();
            Metrics.addTransactionBlockRunningTime(afterRun - beforeRun);
            Long beforeCommit = System.nanoTime();
            committed = tx.commit();
            Long afterCommit = System.nanoTime();
            Metrics.addWholeCommitTimes(afterCommit - beforeCommit);
        }
        Metrics.addTransactionRetriesAmount(retry);
    }

}
