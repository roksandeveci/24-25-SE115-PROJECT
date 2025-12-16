// Main.java — Students version
import java.io.*;
import java.util.*;

public class Main {
    static final int MONTHS = 12;
    static final int DAYS = 28;
    static final int COMMS = 5;
    static String[] commodities = {"Gold", "Oil", "Silver", "Wheat", "Copper"};
    static String[] months = {"January","February","March","April","May","June",
            "July","August","September","October","November","December"};

    public static int[][][] profit;


    // ======== REQUIRED METHOD LOAD DATA (Students fill this) ========
    public static void loadData() {
        profit = new int[MONTHS][COMMS][DAYS];
    }

    // ======== 10 REQUIRED METHODS (Students fill these) ========

    public static String mostProfitableCommodityInMonth(int month) {
            if (month < 0 || month > 11) return "INVALID_MONTH";

            int bestCommodityIdx = 0;
            int bestTotal = Integer.MIN_VALUE;

            for (int c = 0; c < 5; c++) {
                int total = 0;
                for (int d = 0; d < 28; d++) {
                    total += profit[month][c][d];
                }

                if (total > bestTotal) {
                    bestTotal = total;
                    bestCommodityIdx = c;
                }
            }

            return commodities[bestCommodityIdx] + " " + bestTotal;
        }



    public static int totalProfitOnDay(int month, int day) {
            if (month < 0 || month > 11) return -9999;
            if (day < 1 || day > 28) return -9999;

            int total = 0;

            for (int c = 0; c < 5; c++) {
                total += profit[month][c][day - 1];
            }

            return total;
        }



    public static int commodityProfitInRange(String commodity, int from, int to) {
            if (from < 1 || from > DAYS) return -99999;
            if (to < 1 || to > DAYS) return -99999;
            if (from > to) return -99999;
            if (commodity == null) return -99999;

            int cIdx = -1;
            for (int i = 0; i < COMMS; i++) {
                if (commodities[i].equals(commodity)) {
                    cIdx = i;
                    break;
                }
            }
            if (cIdx == -1) return -99999;

            int total = 0;
            for (int m = 0; m < MONTHS; m++) {
                for (int d = from - 1; d <= to - 1; d++) {
                    total += profit[m][cIdx][d];
                }
            }

            return total;


    }

    public static int bestDayOfMonth(int month) {
            if (month < 0 || month >= MONTHS) return -1;

            int bestDay = 1;
            int bestTotal = Integer.MIN_VALUE;

            for (int d = 0; d < DAYS; d++) {
                int total = 0;
                for (int c = 0; c < COMMS; c++) {
                    total += profit[month][c][d];
                }

                if (total > bestTotal) {
                    bestTotal = total;
                    bestDay = d + 1;
                }
            }

            return bestDay;
        }



    public static String bestMonthForCommodity(String comm) {
        return "DUMMY";
    }

    public static int consecutiveLossDays(String comm) {
        return 1234;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
        return 1234;
    }

    public static int biggestDailySwing(int month) {
        return 1234;
    }

    public static String compareTwoCommodities(String c1, String c2) {
        return "DUMMY is better by 1234";
    }

    public static String bestWeekOfMonth(int month) {
        return "DUMMY";
    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
}
