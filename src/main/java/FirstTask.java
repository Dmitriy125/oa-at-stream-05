import java.util.ArrayList;
import java.util.List;

public class FirstTask {
    // Задание 1
    public static boolean isEven(int n) {
        return n % 2 == 0;
    }

    // Задание 2
    public static String checkAccess(int age) {
        if (age >= 18) return "Allowed";
        return "Denied";
    }

    // Задание 3
    public static boolean isPositive(int n) {
        return n >= 0;
    }

    // Задание 4
    public static String getGrade(int score) {
        if (score >= 0 && score <= 20) return "E";
        if (score >= 21 && score <= 40) return "D";
        if (score >= 41 && score <= 60) return "C";
        if (score >= 61 && score <= 80) return "B";
        if (score >= 81 && score <= 100) return "A";
        return "Error";
    }

    // Задание 5
    public static String blastOff(int start) {
        String countDown = "";
        if (start < 1) return countDown;
        for (int i = start; i >= 1; i--) {
            countDown += i + " ";
        }
        countDown += "Поехали!";
        return countDown;
    }

    // Задание 6
    public static int sumToN(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        int sum = 0;
        for (int i = 0; i <= n; i++) {
            sum += i;
        }
        return sum;
    }

    // Задание 7
    public static boolean hasBug(String[] messages) {
        for (String message : messages) {
            if (message != null && message.equalsIgnoreCase("Bug")) {
                return true;
            }
        }
        return false;
    }

    // Задание 8
    public static String getEvenInRange(int start, int end) {
        String evenNumber = "";
        if (start > end) {
            return evenNumber;
        }
        for (int i = start; i <= end; i++) {
            if (i % 2 == 0) evenNumber += i + " ";
        }
        return evenNumber.trim();
    }

    // Задание 9
    public static int findMax(int[] arr) {
        int max = arr[0];
        for (int i = 1; i < arr.length; i++) {
            if (arr[i] > max) max = arr[i];
        }
        return max;
    }

    // Задание 10
    public static String[] reverse(String[] arr) {
        String[] reverseArray = new String[arr.length];
        for (int i = 0; i < arr.length; i++) {
            reverseArray[i] = arr[arr.length - 1 - i];
        }
        return reverseArray;
    }

    // Задание 11
    public static int calcAverage(List<Integer> list) {
        int meanValue = 0;
        int count = list.size();
        for (Integer i : list) {
            meanValue += i;
        }
        return meanValue / count;
    }

    // Задание 12
    public static List removeSpecificName(List<String> list, String nameToRemove) {
        List newList = new ArrayList();
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).equals(nameToRemove)) continue;
            newList.add(list.get(i));
            {
            }
        }
        return newList;
    }
}
