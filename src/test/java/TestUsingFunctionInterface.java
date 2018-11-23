import com.sun.istack.Nullable;
import org.testng.annotations.Test;

import java.util.*;
import java.util.function.Supplier;
import java.util.regex.Pattern;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class TestUsingFunctionInterface {

    enum TypesOfConnect {
        WI_FI, HUB, ZIK_BE
    }

    class Places {
        String nameOfPlace;

        Places(String nameOfPlace) {
            this.nameOfPlace = nameOfPlace;
        }

        @Override
        public String toString() {
            return nameOfPlace;
        }
    }

    class SmartHomeItems {
        String name;
        TypesOfConnect type;
        int priority;
        List<Places> placesList;

        SmartHomeItems(String name, TypesOfConnect type, int priority, List<Places> placesList) {
            this.name = name;
            this.type = type;
            this.priority = priority;
            this.placesList = placesList;
        }

        @Override
        public String toString() {
            return String.format("N: %s%n T: %s%n P: %s%n LP: %s%n%n", name, type, priority, placesList.toString());
        }
    }

    @Test
    public void testRegularExpressions() {
        System.out.printf("Matcher: %s%n", Pattern.compile("(?<=href=)(\"([^\"]*\")|'[^']*'|([^'\">\\s]+))")
                .matcher("<a href=\"http://www.mkyong.com/regular-expressions/how-to-validate-html-tag-with-regular-expression/\" " +
                        "target=\"_blank\">See the explanation and example here</a>")
                .replaceAll("MY_LINK"));


        System.out.printf("Matcher: %s%n", Pattern.compile("/?\\W{3}|\\d{2}|;")
                .matcher("a;lsdf87u23ijrl---a;lsdfu98").replaceAll("_"));
    }

    @Test
    public void testJava10 () {
        IntSummaryStatistics stat = new IntSummaryStatistics();
        stat.accept(2);
        stat.accept(2);
//        stat.accept(55);
        stat.accept(6);
        stat.accept(5);
        stat.accept(3);

        IntStream.range(50, 100).forEach(stat);

        final String result = "Result";
        System.out.printf("%s Average: %s%n", result, stat.getAverage());
        System.out.printf("%s Sum: %d%n", result, stat.getSum());
        System.out.printf("%s Count: %d%n", result, stat.getCount());
    }

    @Test
    public void simpleOne() {

        // Rich SmartHome class
        List<Places> placesListCommon = new ArrayList<>();
        placesListCommon.add(new Places("Bedroom"));
        placesListCommon.add(new Places("Office"));
        placesListCommon.add(new Places("Kitchen"));
        ArrayList<Places> placesListSpecific = new ArrayList<>();
        IntStream.range(1, 4).forEach(i -> placesListSpecific.add(new Places("SpecPlace#" + i)));
        List<SmartHomeItems> homeItems = Arrays.asList(
                new SmartHomeItems("WebCam360", TypesOfConnect.WI_FI, 1, placesListCommon),
                new SmartHomeItems("HUB", TypesOfConnect.HUB, 0, placesListCommon),
                new SmartHomeItems("Sensor", TypesOfConnect.ZIK_BE, 2, placesListSpecific),
                new SmartHomeItems("Socket", TypesOfConnect.ZIK_BE, 2, placesListSpecific),
                new SmartHomeItems("Lamp", TypesOfConnect.WI_FI, 3, placesListSpecific),
                new SmartHomeItems("Temperature sensor", TypesOfConnect.ZIK_BE, 4, placesListSpecific),
                new SmartHomeItems("LedLight line", TypesOfConnect.WI_FI, 5, placesListCommon)
        );

        Map<TypesOfConnect, List<SmartHomeItems>> sByPriority = homeItems
                .stream()
                .collect(Collectors.groupingBy(i -> i.type));
        sByPriority.forEach((a, b) -> System.out.format("#%s info: %s\n", a, b));
        int priority = 0;
        String fResult = homeItems
                .stream()
                .filter(i -> i.priority > priority)
                .map(i -> i.name)
                .collect(Collectors.joining(" and ",
                        "In home present ",
                        " with priority more then #" + priority));
        System.out.println("\n-----------\n" + fResult);
        System.out.println("\n---------------------------\n");

        homeItems.stream().flatMap(p -> p.placesList.stream()).forEach(i -> System.out.println(i.nameOfPlace));
        System.out.println("\n---------------------------\n");


        HashMap<String, Integer> hMap = new HashMap<>();

        Integer result = hMap.computeIfAbsent("John", String::length);

        System.out.println("Result: " + result);
        System.out.println("Result Map: " + hMap);


        Supplier<Double> doubleSupplier = () -> (double) (51 << 2);
        System.out.println("Supplier: " + squareLazy(doubleSupplier));
        System.out.println("Supplier with null value: " + squareLazy(null));
    }

    private Double squareLazy(@Nullable Supplier<Double> lazyValue) {
        return Math.toRadians((Optional.ofNullable(lazyValue).orElse(() -> (double) 1000)).get());
    }
}
