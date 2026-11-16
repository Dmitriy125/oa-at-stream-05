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

        assertEquals(isExpectedResult, FirstTask.isEven(nTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");
    }

    @Test
    void test2() {
        int ageTest = random.nextInt(21);

        String expectedResult = (ageTest >= 18) ? "Allowed" : "Denied";
        assertEquals(expectedResult, FirstTask.checkAccess(ageTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");
    }

    @RepeatedTest(3)
    void test3() {
        int nTest = random.nextInt(1, 10);

        boolean isExpectedResult = nTest > 0;
        assertEquals(isExpectedResult, FirstTask.isPositive(nTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");
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
        assertThat(actualResult)
                .as("TEST FAILED!")
                .isIn("A", "B", "C", "D", "E");
        System.out.println("TEST PASSED!");
    }

    @ParameterizedTest
    @ValueSource (ints = {1, 2, 12})
    void test5(int startTest) {

        String countDown = "";
        for (int i = startTest; i >= 1; i--) {
            countDown += i + " ";
        }
        countDown += "Поехали!";

assertEquals(countDown, FirstTask.blastOff(startTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");

    }
    @RepeatedTest(3)
    void test6() {
        int nTest = random.nextInt(1, 10);

        int expectedNum = 0;
        int sum = nTest;
        for (int i = 0; i <= sum; i++) {
            expectedNum += i;
        }

        assertEquals(expectedNum, FirstTask.sumToN(nTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");
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
        assertEquals(isExpectedResult, FirstTask.hasBug(messagesTest), "TEST FAILED!");
        System.out.println("TEST PASSED!");
    }

    @Test
    void test8() {
        int startTest = random.nextInt(0, 10);
        int endTest = random.nextInt(10, 20);
        String evenNumber = "";

        for (int i = startTest; i <= endTest; i++) {
            if (i % 2 == 0) evenNumber += i + " ";
        }

            assertEquals(evenNumber.trim(), FirstTask.getEvenInRange(startTest, endTest), "TEST FAILED!");
            System.out.println("TEST PASSED!");
        }


    @RepeatedTest(3)
    void test9() {
        int test = getRandomArray[0];
        for (int i = 1; i < getRandomArray.length; i++) {
            if (getRandomArray[i] > test) test = getRandomArray[i]; {
            }
        }
        assertThat(test)
                .as("TEST FAILED!")
                .isEqualTo(FirstTask.findMax(getRandomArray));
        System.out.println("TEST PASSED!");
    }

    @RepeatedTest(3)
    void test10() {
    String firstString = getStringArray[0];
    String firstString2 = getStringArray[4];
    String lastString = FirstTask.reverse(getStringArray)[4];
    String LastString2 = FirstTask.reverse(getStringArray)[0];
        assertThat(firstString)
                .as("TEST FAILED!")
                .isEqualTo(lastString);

        assertThat(firstString2)
                .as("TEST FAILED!")
                .isEqualTo(LastString2);
        System.out.println("TEST PASSED!");
    }

    @Test
    void test11() {
        List<Integer> list = new ArrayList<>();
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(10);
        int expectedResult = (list.get(0)+list.get(1)+list.get(2)+list.get(3))/list.size();
                assertThat(FirstTask.calcAverage(list)).as("TEST FAILED!")
                        .isEqualTo(expectedResult);
        System.out.println("TEST PASSED!");
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
            assertThat(FirstTask.removeSpecificName(checkList(), excessString))
                    .as("TEST FAILED!")
                    .doesNotContain(excessString);
        System.out.println("TEST PASSED!");
        }
    }

