import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Practical4 {
    private static final String E_NOT_FOUND = "No words ending with e found";

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<String> l2 = new ArrayList<>(Arrays.asList("The", "quick", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"));
        List<String> l3 = new ArrayList<>(Arrays.asList("The", "quick", "quick", "jumps", "the", "lazy"));
        //Task 1
        System.out.println("Task 1");
        Integer[] ans_1 = evenNumberArray(l1);
        for(int i : ans_1)System.out.print(i + ", ");

        // Task 2
        System.out.println("\n\nTask 2");
        String ans_2 = firstWordEndingWithE(l2);
        System.out.println(ans_2);

        // Task 3
        System.out.println("\nTask 3");
        String ans_3 = parallelFirstWordEndingWithE(l2);
        System.out.println(ans_3);

        // Task 4
        System.out.println("\nTask 4");
        int ans_4 = lengthOfLongestOWord(l2);
        System.out.println(ans_4);

        // Task 5
        System.out.println("\nTask 5");
        List<String> ans_5 = uppercaseLowercaseList(l2);
        ans_5.stream().forEach(System.out::println);

        // Task 6
        System.out.println("\nTask 6");
        int ans_6 = sumOfSquaredNumbers(l1);
        System.out.println(ans_6);

        // Task 7
        System.out.println("\nTask 7");
        String ans_7 = stringOfFirstCharacters(l2);
        System.out.println(ans_7);
    }

    public static Integer[] evenNumberArray(List<Integer> list){
        return list.stream().filter(i -> i % 2 == 0).toArray(Integer[]::new);
    }

    public static String firstWordEndingWithE(List<String> list){
        return list.stream().filter(s -> s.charAt(s.length() - 1) == 'e').findFirst().orElse(E_NOT_FOUND);
    }

    //To be changed
    public static String parallelFirstWordEndingWithE(List<String> list){
        return list.parallelStream().filter(s -> s.charAt(s.length() - 1) == 'e').findFirst().orElse(E_NOT_FOUND);
    }
    
    public static Integer lengthOfLongestOWord(List<String> list){
        return list.stream().filter(s -> s.contains("o")).map(s -> s.length()).max((i1, i2) -> i1 - i2).orElse(-1);
    }

    public static List<String> uppercaseLowercaseList(List<String> list){
        return list.stream().flatMap(s -> Stream.of(s.toUpperCase(), s.toLowerCase())).distinct().collect(Collectors.toList());
    }

    public static Integer sumOfSquaredNumbers(List<Integer> list){
        return list.stream().reduce(0, (carry, num) -> carry + num * num);
    }

    public static String stringOfFirstCharacters(List<String> list){
        return list.stream().reduce("", (carry, s) -> carry.concat(Character.toString(s.charAt(0))));
    }
}
