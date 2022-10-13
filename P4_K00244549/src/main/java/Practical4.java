import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Practical4 {
    private static final String E_NOT_FOUND = "No words ending with e found";

    public static void main(String[] args) {
        List<Integer> l1 = new ArrayList<>(Arrays.asList(1,2,3,4,5,6,7,8,9,10));
        List<String> l2 = new ArrayList<>(Arrays.asList("The", "quick", "quick", "brown", "fox", "jumps", "over", "the", "lazy", "dog"));

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

        // Task 3
        System.out.println("\nTask 4");
        int ans_4 = lengthOfLongestOWord(l2);
        System.out.println(ans_4);
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

    // How to get -1
    public static Integer lengthOfLongestOWord(List<String> list){
        return list.stream().filter(s -> s.contains("o")).max((s1, s2) -> s1.length() - s2.length()).get().length();
    }

    public static List<String> uppercaseLowercaseList(List<String> list){

    }
}
