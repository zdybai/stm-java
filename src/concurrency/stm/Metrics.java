package concurrency.stm;


import java.util.concurrent.CopyOnWriteArrayList;

/**
 * Created with IntelliJ IDEA.
 * User: Zdybai
 * Date: 11.11.13
 * Time: 11:37
 * To change this template use File | Settings | File Templates.
 */

public class Metrics {

    private static CopyOnWriteArrayList<Long> checkingValidationTime = new CopyOnWriteArrayList<Long>();
    private static CopyOnWriteArrayList<Long> updateRefTimes = new CopyOnWriteArrayList<Long>();
    private static CopyOnWriteArrayList<Long> wholeCommitTimes = new CopyOnWriteArrayList<Long>();
    private static CopyOnWriteArrayList<Long> retriesAmount = new CopyOnWriteArrayList<Long>();
    private static CopyOnWriteArrayList<Long> transactionBlocksRunningTime = new CopyOnWriteArrayList<Long>();


    public static void addValidationTime(Long val) {
        checkingValidationTime.add(val);
    }

    public static void addUpdateRefTime(Long val) {
        updateRefTimes.add(val);
    }

    public static void addWholeCommitTimes(Long val) {
        wholeCommitTimes.add(val);
    }

    public static void addTransactionRetriesAmount(Long val) {
        retriesAmount.add(val);
    }

    public static void addTransactionBlockRunningTime(Long val) {
        transactionBlocksRunningTime.add(val);
    }

    private static Long getMaxFromArray(CopyOnWriteArrayList<Long> list) {
        Long max = 0L;
        for(Long l : list) {
            if (max < l) {
                max = l;
            }
        }
        return max;
    }

    private static Long getMinFromArray(CopyOnWriteArrayList<Long> list) {
        Long min = Long.MAX_VALUE;
        for(Long l : wholeCommitTimes) {
            if (min > l) {
                min = l;
            }
        }
        return min;
    }

    private static Long getAverageFromArray(CopyOnWriteArrayList<Long> list) {
        int n = list.size();
        long sum = 0;
        for (Long l : list) {
            sum += l;
        }
        return sum/n;
    }

    public static Long getAverageValidationTime() {
        return getAverageFromArray(checkingValidationTime);
    }

    public static Long getMaxValidationTime() {
        return getMaxFromArray(checkingValidationTime);
    }

    public static Long getAverageUpdateTime() {
        return getAverageFromArray(updateRefTimes);
    }

    public static Long getMaxUpdateTime() {
        return getMaxFromArray(updateRefTimes);
    }

    public static Long getAverageCommitTime() {
        return getAverageFromArray(wholeCommitTimes);
    }

    public static Long getMaxCommitTime() {
        return getMaxFromArray(wholeCommitTimes);
    }

    public static Long getMinCommitTime() {
        return getMinFromArray(wholeCommitTimes);
    }

    public static Long getMaxRetriesAmount() {
        return getMaxFromArray(retriesAmount);
    }

    public static Long getMaxTransactionBlockRunning() {
        return getMaxFromArray(transactionBlocksRunningTime);
    }

    public static Long getMinTransactionBlockRunning() {
        return getMinFromArray(transactionBlocksRunningTime);
    }

    public static void showAverageValidationTime() {
        System.out.println("Average validation transaction time: " + getAverageValidationTime() + " ns");
    }

    public static void showMaxValidationTime() {
        System.out.println("Max validation transaction time: " + getMaxValidationTime() + " ns");
    }

    public static void showMaxTransactionBlockRunning() {
        System.out.println("Max transaction block running time: " + getMaxTransactionBlockRunning() + " ns");
    }

    public static void showMinTransactionBlockRunning() {
        System.out.println("Min transaction block running time: " + getMinTransactionBlockRunning() + " ns");
    }

    public static void showAverageUpdateTime() {
        System.out.println("Average update transaction time: " + getAverageUpdateTime() + " ns");
    }

    public static void showMaxUpdateTime() {
        System.out.println("Max update transaction time: " + getMaxUpdateTime() + " ns");
    }

    public static void showAverageCommitTime() {
        System.out.println("Average commit transaction time: " + getAverageCommitTime() + " ns");
    }

    public static void showMaxCommitTime() {
        System.out.println("Max transaction commit time: " + getMaxCommitTime() + " ns");
    }

    public static void showMinCommitTime() {
        System.out.println("Min commit transaction time: " + getMinCommitTime() + " ns");
    }

    public static void showMaxRetriesAmount() {
        System.out.println("Max retries transaction amount: " + getMaxRetriesAmount() + " times");
    }
}
