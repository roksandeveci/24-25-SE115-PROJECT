day) {
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
        if (comm == null) return "INVALIDCOMMODITY";
        int cIdx = -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(comm)) {
                cIdx = i;
                break;
            }
        }
        if (cIdx == -1) return "INVALID_COMMODITY";
        int bestProfit = Integer.MIN_VALUE;
        int bestMonth=0;
        for (int m = 0; m < MONTHS; m++) {
            int monthProfit = 0;
            for (int d = 0; d < DAYS; d++) {
                monthProfit += profit[m][cIdx][d];

            }if(monthProfit > bestProfit) {
                bestProfit = monthProfit;
                bestMonth =m;

            }
        }return months[bestMonth];
    }

    public static int consecutiveLossDays(String comm) {
        if (comm == null) return -1;
        int cIdx = -1;
        for (int i = 0; i < COMMS; i++) {
            if (commodities[i].equals(comm)) {
                cIdx = i;
                break;
            }
        }if (cIdx == -1) return -1;
        int maxStreakCount=0;
        int streakCount=0;

        for(int m=0;m<MONTHS;m++){
            for(int d=0;d<DAYS;d++){
                if(profit[m][cIdx][d]<0){
                    streakCount++;
                    if(streakCount>maxStreakCount){
                        maxStreakCount=streakCount;
                    }
                }else{
                    streakCount=0;
                }
            }
        }return maxStreakCount;
    }

    public static int daysAboveThreshold(String comm, int threshold) {
            if (comm == null) return -1;

            int cIdx = -1;
            for (int i = 0; i < COMMS; i++) {
                if (commodities[i].equals(comm)) {
                    cIdx = i;
                    break;
                }
            }
            if (cIdx == -1) return -1;

            int count = 0;

            for (int m = 0; m < MONTHS; m++) {
                for (int d = 0; d < DAYS; d++) {
                    if (profit[m][cIdx][d] > threshold) {
                        count++;
                    }
                }
            }

            return count;
        }



    public static int biggestDailySwing(int month) {
            if (month < 0 || month >= 12) return -9999;

            int maxSwing = 0;

            for (int d = 0; d < 27; d++) {
                int totalToday = 0;
                int totalTomorrow = 0;

                for (int c = 0; c < 5; c++) {
                    totalToday += profit[month][c][d];
                    totalTomorrow += profit[month][c][d + 1];
                }

                int diff = totalToday - totalTomorrow;
                if (diff < 0) diff = -diff;

                if (diff > maxSwing) {
                    maxSwing = diff;
                }
            }

            return maxSwing;
        }



    public static String compareTwoCommodities(String c1, String c2) {
            if (c1 == null || c2 == null) return "INVALID_COMMODITY";

            int idx1 = -1, idx2 = -1;

            for (int i = 0; i < COMMS; i++) {
                if (commodities[i].equals(c1)) idx1 = i;
                if (commodities[i].equals(c2)) idx2 = i;
            }

            if (idx1 == -1 || idx2 == -1) return "INVALID_COMMODITY";

            int sum1 = 0, sum2 = 0;

            for (int m = 0; m < MONTHS; m++) {
                for (int d = 0; d < DAYS; d++) {
                    sum1 += profit[m][idx1][d];
                    sum2 += profit[m][idx2][d];
                }
            }

            if (sum1 > sum2)
                return "C1 is better by " + (sum1 - sum2);
            else if (sum2 > sum1)
                return "C2 is better by " + (sum2 - sum1);
            else
                return "Equal";


    }

    public static String bestWeekOfMonth(int month) {
            if (month < 1 || month > MONTHS) {
                return "INVALID_MONTH";
            }

            int mIdx = month - 1;
            int[] weekSums = new int[4];

            for (int d = 0; d < 28; d++) {
                int weekIndex = d / 7;

                int dayTotal = 0;
                for (int c = 0; c < COMMS; c++) {
                    dayTotal += profit[mIdx][c][d];
                }

                weekSums[weekIndex] += dayTotal;
            }

            int bestWeek = 0;
            for (int i = 1; i < 4; i++) {
                if (weekSums[i] > weekSums[bestWeek]) {
                    bestWeek = i;
                }
            }

            return "Week " + (bestWeek + 1);


    }

    public static void main(String[] args) {
        loadData();
        System.out.println("Data loaded – ready for queries");
    }
    }
