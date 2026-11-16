import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class ExecutionAllMethods {

    Random random = new Random();

    static int[] getRandomArray = new int[5];

    {
        for (int i = 0; i < getRandomArray.length; i++) {
            getRandomArray[i] = random.nextInt(-100, 100);
        }
    };

    static String[] getStringArray = new String[5];
    {
        for (int i = 0; i < getStringArray.length; i++) {
            getStringArray[i] = random.toString();
        }
    };

    List<String> checkList() {
return List.of(random.toString(), random.toString(), random.toString(), random.toString(), random.toString());
    }



    @Test
    void test1() {
        int nTest = random.nextInt(100);

        boolean isExpectedResult = nTest % 2 == 0;

        if (isExpectedResult == FirstTask.isEven(nTest)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @Test
    void test2() {
        int ageTest = random.nextInt(21);

        String expectedResult = (ageTest >= 18) ? "Allowed" : "Denied";

        if (expectedResult.equals(FirstTask.checkAccess(ageTest))) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @RepeatedTest(3)
    void test3() {
        int nTest = random.nextInt(1, 10);

        boolean isExpectedResult = nTest > 0;

        if (isExpectedResult == FirstTask.isPositive(nTest)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @RepeatedTest(3)
    void test4() {
        int scoreTest = random.nextInt(0, 100);
        String actualResult = FirstTask.getGrade(scoreTest);
        switch(actualResult) {
            case "E":
                break;
            case "D":
                break;
            case "C":
                break;
            case "B":
                break;
            case "A":
                break;
            default:
                break;
        }

        if (actualResult.equals("A") || actualResult.equals("B") || actualResult.equals("C") || actualResult.equals("D") || actualResult.equals("E")) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 12})
    void test5(int startTest) {

        String countDown = "";
        for (int i = startTest; i >= 1; i--) {
            countDown += i + " ";
        }
        countDown += "Поехали!";

        if (countDown.equals(FirstTask.blastOff(startTest))) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");

    }
    @RepeatedTest(3)
    void test6() {
        int nTest = random.nextInt(1, 10);

        int expectedNum = 0;
        int sum = nTest;
        for (int i = 0; i <= sum; i++) {
            expectedNum += i;
        }

        if (expectedNum == FirstTask.sumToN(nTest)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @Test
    void test7() {
        String[] messagesTest = {"hEllO", "mY", "naMe", "bUG", "iS", "AlEx"};

        boolean isExpectedResult = false;
        for (String message : messagesTest) {
            if (message.equalsIgnoreCase("bug")) {
                isExpectedResult = true;
                break;
            }
        }
        if (isExpectedResult == FirstTask.hasBug(messagesTest)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @Test
    void test8() {
        int startTest = random.nextInt(0, 10);
        int endTest = random.nextInt(10, 20);
        String evenNumber = "";

        for (int i = startTest; i <= endTest; i++) {
            if (i % 2 == 0) evenNumber += i + " ";
        }

        if (evenNumber.trim().equals(FirstTask.getEvenInRange(startTest, endTest))) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }


    @RepeatedTest(3)
    void test9() {
        int test = getRandomArray[0];
        for (int i = 1; i < getRandomArray.length; i++) {
            if (getRandomArray[i] > test) test = getRandomArray[i]; {
            }
        }
        if (test == FirstTask.findMax(getRandomArray)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @RepeatedTest(3)
    void test10() {
    String firstString = getStringArray[0];
    String firstString2 = getStringArray[4];
    String lastString = FirstTask.reverse(getStringArray)[4];
    String LastString2 = FirstTask.reverse(getStringArray)[0];

        if (firstString == lastString && firstString2 == LastString2) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @Test
    void test11() {
        List<Integer> list = new ArrayList<>();
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(10);
        int expectedResult = (list.get(0)+list.get(1)+list.get(2)+list.get(3))/list.size();

        if (FirstTask.calcAverage(list) == expectedResult) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
    }

    @Test
    void test12() {

        List<String> expectedList = checkList();
        String excessString = expectedList.get(random.nextInt(5));
        List<String> newExpectedList = checkList();

        for (int i = 0; i < expectedList.size(); i++) {
            if (expectedList.get(i).equals(excessString)) continue;
            newExpectedList.add(expectedList.get(i));
        }
        List<String> actualList = FirstTask.removeSpecificName(checkList(), excessString);
        if (!actualList.contains(excessString)) {
            System.out.println("TEST PASSED!");
        } else System.out.println("TEST FAILED!");
        }
    }

