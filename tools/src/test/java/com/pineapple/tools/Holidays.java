package com.pineapple.tools;

import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Holidays {
    public static final String[] HOLIDAY_DATES = {
            "2024-01-01", // New Year's Day
            "2024-03-28", // Martin Luther King Jr. Day
            "2024-03-29", // Presidents' Day
            "2024-04-01", // Good Friday
            "2024-05-09",
            "2024-05-10",
            "2024-05-20",
            "2024-06-05",
            "2024-12-24",
            "2024-12-25",
            "2024-12-26",
            "2024-12-31"
    };

    public static final List<Map.Entry<String, String>> MY_HOLIDAYS = List.of(
            Map.entry("2024-01-01", "2024-01-01"),
            Map.entry("2024-03-25", "2024-03-29"),
            Map.entry("2024-04-01", "2024-04-01"),
            Map.entry("2024-05-09", "2024-05-10"),
            Map.entry("2024-05-20", "2024-05-20"),
            Map.entry("2024-06-05", "2024-06-06"),
            Map.entry("2024-07-10", "2024-07-31"),
            Map.entry("2024-12-24", "2024-12-26"),
            Map.entry("2024-12-31", "2024-12-31"));

    public static final List<Map.Entry<Integer, Integer>> MY_HOLIDAYS_INT = List.of(
            Map.entry(1, 1),
            Map.entry(85, 89),
            Map.entry(92, 92),
            Map.entry(130, 131),
            Map.entry(141, 141),
            Map.entry(157, 158),
            Map.entry(192, 213),
            Map.entry(359, 361),
            Map.entry(366, 366));

    public static final int[] MONTH_DAYS = new int[] {
            31, 29, 31, 30, 31, 30,
            31, 31, 30, 31, 30, 31
    };

    public static final Set<Integer> WORKING_DAYS = Set.of(1, 2, 4);

    @Test
    public void getWorkingDays() {
        List<Map.Entry<Integer, Integer>> workingDays = new ArrayList<>();
        for (int i = 0; i < MONTH_DAYS.length; i++) {
            int[] range = getMonthDayRange(i + 1);
            int count = 0;
            for (int j = range[0]; j <= range[1]; j++) {
                if (WORKING_DAYS.contains(j % 7) && !inHolidays(j)) {
                    count++;
                }
            }
            workingDays.add(Map.entry(i, count));
            // System.out.println("range is: " + range[0] + ", " + range[1]);
            System.out.println("Month " + (i + 1) + ": " + count);
        }
    }

    @Test
    public void getMyHolidaysInt() {
        int index = 0;
        for (Map.Entry<String, String> entry : MY_HOLIDAYS) {
            /*
             * MY_HOLIDAYS_INT.set(index, Map.entry(
             * getDayOfYear(entry.getKey()),
             * getDayOfYear(entry.getValue())));
             */
            System.out.println(MY_HOLIDAYS_INT.get(index));
            index++;
        }
    }

    public static boolean inHolidays(int dayOfYear) {
        for (Map.Entry<Integer, Integer> entry : MY_HOLIDAYS_INT) {
            if (dayOfYear < entry.getKey()) {
                break;
            }
            if (dayOfYear <= entry.getValue()) {
                return true;
            }
        }
        return false;
    }

    public static int[] getMonthDayRange(int month) {
        int start = 1;
        for (int i = 0; i < month - 1; i++) {
            start += MONTH_DAYS[i];
        }
        int end = start + MONTH_DAYS[month - 1] - 1;
        return new int[] { start, end };
    }

    public static int getDayOfYear(String date) {
        String[] parts = date.split("-");
        int month = Integer.parseInt(parts[1]);
        int day = Integer.parseInt(parts[2]);
        int dayOfYear = 0;
        for (int i = 0; i < month - 1; i++) {
            dayOfYear += MONTH_DAYS[i];
        }
        dayOfYear += day;
        return dayOfYear;
    }
}
