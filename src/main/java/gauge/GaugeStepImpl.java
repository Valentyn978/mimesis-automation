package gauge;

import com.thoughtworks.gauge.Gauge;
import com.thoughtworks.gauge.Step;
import com.thoughtworks.gauge.Table;
import com.thoughtworks.gauge.TableRow;

import java.util.HashSet;

import static org.testng.AssertJUnit.assertEquals;


public class GaugeStepImpl {


    private HashSet<Character> vowels;

    @Step("Say <greeting> to <product name>")
    public void helloWorld(String greeting, String name) {
        Gauge.writeMessage("Greeting: %s", greeting);
        Gauge.writeMessage("Name: %s", name);
    }

    @Step("Vowels in English language are <aeiou>.")
    public void vowelsInEnglishLanguageAre(String arg0) {
        Gauge.writeMessage("############### Test 2: %s", arg0);

        vowels = new HashSet<>();
        for (char ch : arg0.toCharArray()) {
            vowels.add(ch);
        }
    }

    @Step("The word <gauge> has <3> vowels.")
    public void theWordHasVowels(String arg0, int arg1) {
        System.out.printf("Test 3: " + arg0 + " and " + arg1);
        Gauge.writeMessage("############### Test 3: %s and " + arg1, arg0);

        int actualCount = countVowels(arg0);
        assertEquals(arg1, actualCount);
    }

    @Step("Almost all words have vowels <table>")
    public void almostAllWordsHaveVowels(Table wordsTable) {
        Gauge.writeMessage("############### Test 4: \n%s", wordsTable.toString());

        for (TableRow row : wordsTable.getTableRows()) {
            String word = row.getCell("Word");
            int expectedCount = Integer.parseInt(row.getCell("Vowel Count"));
            int actualCount = countVowels(word);

            assertEquals(expectedCount, actualCount);
        }

    }

    private int countVowels(String word) {
        int count = 0;
        for (char ch : word.toCharArray()) {
            if (vowels.contains(ch)) {
                count++;
            }
        }
        return count;
    }
}
