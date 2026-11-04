import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

public class WritingAssertions {

    Random random = new Random();

    List<String> checkList() {
        return List.of(random.toString(), random.toString(), random.toString(), random.toString(), random.toString());
    }


    @Test
    void isPositiveTest() {
        int testNumber = random.nextInt(-10, 3);
        assertTrue(FirstTask.isPositive(testNumber), "Ожидаемое число должно быть больше 0. Число " + testNumber + " меньше нуля");
    }

    @Test
    void removeSpecificNameTest() {
        List<String> list = checkList();
        String nameToRemove = list.get(random.nextInt(list.size()));

        assertThat(FirstTask.removeSpecificName(list, nameToRemove)).doesNotContain(nameToRemove, "Строка " + nameToRemove + " не удалена из списка");
    }

    @Test
    void checkAccessTest() {
        int ageTest = random.nextInt(0, 17);
        assertEquals("Allowed", FirstTask.checkAccess(ageTest), "Ожидаемое число больше или равно 18. Число " + ageTest + " меньше 18");
    }

    @Test
    void calcAverageTest() {
        List<Integer> list = new ArrayList<>();
        list.add(random.nextInt(5, 20));
        list.add(random.nextInt(10, 18));
        list.add(random.nextInt(3, 27));

        int sum = list.stream().mapToInt(Integer::intValue).sum();
        int count = list.size();
        int average = sum / count;

        assertEquals(average, FirstTask.calcAverage(list), "Среднее арифметическое высчитано не верно");
    }
}
