package gauge;

import com.thoughtworks.gauge.Step;

public class GaugeStepImpl {

    @Step("Say <greeting> to <product name>")
    public void helloWorld(String greeting, String name) {
        System.out.printf("Greeting: " + greeting);
        System.out.printf("Name: " + name);
    }

    @Step("Vowels in English language are <aeiou>.")
    public void vowelsInEnglishLanguageAre(Object arg0){
        System.out.printf("Test 2: " + arg0);;
    }

    @Step("The word <gauge> has <3> vowels.")
    public void theWordHasVowels(Object arg0, Object arg1){
        System.out.printf("Test 3: " + arg0 + " and " + arg1);
    }

    @Step("Almost all words have vowels <table>")
    public void almostAllWordsHaveVowels(Object arg0){
        System.out.printf("Test 4: " + arg0);
    }
}
