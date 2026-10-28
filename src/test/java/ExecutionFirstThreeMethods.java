import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.RepeatedTest;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.MethodSource;


import java.util.List;
import java.util.Random;


public class ExecutionFirstThreeMethods {
    Random random = new Random();


    static List<Integer> getRandomList() {
        Random random = new Random();
        return List.of(random.nextInt(200), random.nextInt(200), random.nextInt(200), random.nextInt(200));
    }

    @BeforeEach
    void beforeMethod() {
        System.out.println("========================\nTest method start");
    }

    @AfterEach
    void afterMethod() {
        System.out.println("Test method end\n========================");
    }

    @Test
    void test1() {
        int nTest = random.nextInt(1, 101);
        if (FirstTask.isEven(nTest)) System.out.println("Число " + nTest + " является четным");
        else System.out.println("Число " + nTest + " является не четным");
    }

    @RepeatedTest(20)
    void test2() {
        int ageTest = random.nextInt(0, 100);
        if ("Allowed".equals(FirstTask.checkAccess(ageTest))) {
            System.out.println("Число " + ageTest + " больше или равно 18");
        } else System.out.println(("Число " + ageTest + " меньше 18"));
    }

    @ParameterizedTest
    @MethodSource("getRandomList")
    void test3(int scoreTest) {
        String actualResult = FirstTask.getGrade(scoreTest);
        switch (actualResult) {
            case "E":
                System.out.println("Число " + scoreTest + " входит в предел от 0 до 20");
                break;
            case "D":
                System.out.println("Число " + scoreTest + " входит в предел от 21 до 40");
                break;
            case "C":
                System.out.println("Число " + scoreTest + " входит в предел от 41 до 60");
                break;
            case "B":
                System.out.println("Число " + scoreTest + " входит в предел от 61 до 80");
                break;
            case "A":
                System.out.println("Число " + scoreTest + " входит в предел от 81 до 100");
                break;
            default:
                System.out.println("Число " + scoreTest + " не входит в предел от 0 до 100");
                break;
        }
    }
}
