import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;
import org.junit.jupiter.params.provider.ValueSource;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;

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
    if(FirstTask.isEven(nTest)) System.out.println("Число " + nTest + " является четным. TEST PASSED!");
    else System.out.println("Число " + nTest + " является не четным. TEST FAILED!");
    }

    @Test
    void test2() {
        int ageTest = random.nextInt(35);
        if(FirstTask.checkAccess(ageTest) == "Allowed") System.out.println("Ваш возраст: " + ageTest + ". TEST PASSED!");
        else System.out.println("Ваш возраст: " + ageTest + ". Дождитесь когда Вам станет 18. TEST FAILED!");
    }

    @RepeatedTest(3)
    void test3() {
        int nTest = random.nextInt(-10, 10);
        if(FirstTask.isPositive(nTest)) System.out.println("Число " + nTest + " положительное. TEST PASSED!");
        else System.out.println("Число " + nTest + " является отрицательным. TEST FAILED!");
    }
    @RepeatedTest(3)
    void test4() {
        int scoreTest = random.nextInt(-5, 200);
        String actualResult = FirstTask.getGrade(scoreTest);
        switch(actualResult) {
            case "E":
                System.out.println("Число " + scoreTest + " входит в предел от 0 до 20\nTEST PASSED!");
                break;
            case "D":
                System.out.println("Число " + scoreTest + " входит в предел от 21 до 40\nTEST PASSED!");
                break;
            case "C":
                System.out.println("Число " + scoreTest + " входит в предел от 41 до 60\nTEST PASSED!");
                break;
            case "B":
                System.out.println("Число " + scoreTest + " входит в предел от 61 до 80\nTEST PASSED!");
                break;
            case "A":
                System.out.println("Число " + scoreTest + " входит в предел от 81 до 100\nTEST PASSED!");
                break;
            default:
                System.out.println("Число " + scoreTest + " не входит в предел от 0 до 100\nTEST FAILED!");
                break;
        }
    }

    @ParameterizedTest
    @ValueSource (ints = {-1, 0, 1, 2, 12})
    void test5(int startTest) {
        if(FirstTask.blastOff(startTest).toString() == "") System.out.println("Обратный отчет не может начаться с " + startTest +". TEST FAILED!");
        else System.out.println(FirstTask.blastOff(startTest).toString() + ". Обратный отчет стартовал. TEST PASSED!");


    }
    @RepeatedTest(3)
    void test6() {
        int nTest = random.nextInt(-10, 10);
        if(FirstTask.sumToN(nTest) < 1) System.out.println("Не получается рассчитать сумму чисел от 1 до "+ nTest + ". TEST FAILED!");
        else System.out.println("Сумма чисел от 1 до "+ nTest + " равняется " + FirstTask.sumToN(nTest)+ ".TEST PASSED!");
    }
    @Test
    void test7() {
        // Тут не придумал как сделать проверку, захардкодил
        String[] messagesTest = {"hEllO", "mY", "naMe", "bUG", "iS", "AlEx"};
        if(FirstTask.hasBug(messagesTest)) System.out.println("Строка в массиве содержит слово \"Bug\". TEST PASSED!");
        else System.out.println("Строка в массиве не содержит слово \"Bug\".TEST FAILED!");
    }
    @Test
    void test8() {
        int startTest = random.nextInt(0, 20);
        int endTest = random.nextInt(0, 20);
        if(FirstTask.getEvenInRange(startTest, endTest) == "") System.out.println("В промежутках между "+ startTest + " и "+ endTest + " отсутствуют четные числа. TEST FAILED!");
        else System.out.println("В промежутках между числами "+ startTest + " и "+ endTest + " есть строка из четных чисел " + FirstTask.getEvenInRange(startTest, endTest).toString() + ". TEST PASSED!");

    }
    @RepeatedTest(3)
    void test9() {
        int test = getRandomArray[0];
        for (int i = 1; i < getRandomArray.length; i++) {
            if (getRandomArray[i] > test) test = getRandomArray[i]; {
            }
        }
        if(FirstTask.findMax(getRandomArray) == test) System.out.println("Максимальным числом в массиве " + Arrays.toString(getRandomArray) + " является " + test + ". TEST PASSED!");
        else System.out.println("Число " + test + " не является максимальным в массиве " + Arrays.toString(getRandomArray) + ". TEST PASSED!");

    }
    @RepeatedTest(3)
    void test10() {
    String firstString = getStringArray[0];
    String lastString = FirstTask.reverse(getStringArray)[4];
    if (firstString.equals(lastString)) System.out.println("TEST PASSED!");
    else System.out.println("TEST FAILED!");
    }
    @Test
    void test11() {
        List<Integer> list = new ArrayList<>();
                list.add(7);
                list.add(8);
                list.add(9);
                list.add(10);
        if (FirstTask.calcAverage(list) == (list.get(0)+list.get(1)+list.get(2)+list.get(3))/list.size())
            System.out.println("TEST PASSED!");
        else System.out.println("TEST FAILED!");

    }
    @Test
    void test12() {
        String excessString = checkList().get(2);
        List<String> newList = FirstTask.removeSpecificName(checkList(), excessString);
        if(newList.contains(excessString)) System.out.println("TEST FAILED!");
        else System.out.println("TEST PASSED!");



    }
}

