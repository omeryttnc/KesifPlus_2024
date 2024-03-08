package Tasks;

import io.cucumber.java.sl.In;
import org.junit.Test;

import java.util.*;

public class Task6 {

//    List<Integer> integerList = List.of(2, 3, 4, 5, 6, 7, 4, 5, 1, 0);
//    reduce
//     add all digits
//    distinct
//     how many unique digits exist in the list
//    sorted
//     sort all numbers biggest to smallest
//    skip
//     do not take first two smallest number
//    limit
//     take only two biggest number

    List<Integer> integerList = List.of(2, 3, 4, 5, 6, 7, 4, 5, 1, 0);

    @Test
    public void sumMethods() {

//    reduce
//     add all digits

        // Structural
        int total = 0;
        for (int number : integerList) {
            total = total + number;
//            total += number;
        }
        System.out.println("total = " + total);

        // Functional - 1
        int totalLambda = integerList.stream().reduce(0, (subtotal, t) -> subtotal = subtotal + t);
        System.out.println("totalLambda = " + totalLambda);

        int sumMethod = Integer.sum(2, 5);

        // Functional - 2
        int sumLambda = integerList.stream().reduce(0, Integer::sum);
        System.out.println("sumLambda = " + sumLambda);

        // Functional - 3
        int sumLambda2 = integerList.stream().mapToInt(Integer::intValue).sum();
        System.out.println("sumLambda2 = " + sumLambda2);
    }

    @Test
    public void distinctMethods() {

//    distinct
//     how many unique digits exist in the list

        // Structural -1
        Set<Integer> uniqSet = new HashSet<>(integerList);
        System.out.println("uniqSet = " + uniqSet);

        System.out.println("uniqSet.size() = " + uniqSet.size());


        // Structural - 2
        List<Integer> unipueForEach = new ArrayList<>();
        for (int i : integerList) {
            if (!unipueForEach.contains(i)) {
                unipueForEach.add(i);
            }
        }

        System.out.println("unipueForEach = " + unipueForEach);
        System.out.println("unipueForEach.size() = " + unipueForEach.size());


        // Structural - 3
        int numberOfUnique = integerList.size();
        for (int i = 0; i < integerList.size(); i++) {
            for (int j = i + 1; j < integerList.size(); j++) {
                if (integerList.get(i).equals(integerList.get(j))) {
                    numberOfUnique--;
                }
            }
        }
        System.out.println("numberOfUnique = " + numberOfUnique);


        // Functional - 1
        long numberOfUniqueLambda = integerList.stream().distinct().count();
        System.out.println("numberOfUniqueLambda = " + numberOfUniqueLambda);

        // Functional - 2
        List<Integer> listOfUniqueLambda = integerList.stream().distinct().toList();
        System.out.println("listOfUniqueLambda = " + listOfUniqueLambda);
        System.out.println("listOfUniqueLambda.size() = " + listOfUniqueLambda.size());
    }

    @Test
    public void sortMethods() {

//    sorted
//     sort all numbers biggest to smallest

        // Structural
        List<Integer> sortedList = new ArrayList<>(integerList);
        Collections.sort(sortedList);
        System.out.println("sortedList = " + sortedList);

        List<Integer> sortedList1 = new ArrayList<>(integerList);
        Collections.sort(sortedList1.reversed());
        System.out.println("sortedList1 = " + sortedList1);

        // Functional - 1
        List<Integer> sortedListLambda = integerList.stream().sorted().toList();
        System.out.println("sortedListLambda = " + sortedListLambda);

        List<Integer> sortedReverseListLambda = integerList.stream().sorted(Comparator.reverseOrder()).toList();
        System.out.println("sortedReverseListLambda = " + sortedReverseListLambda);


    }

    @Test
    public void skipMethods() {

//    skip
//     do not take first two smallest number

        // Structural
        List<Integer> sortedListForSkip = new ArrayList<>(integerList);
        Collections.sort(sortedListForSkip);
        List<Integer> skippedList = new ArrayList<>();
        for (int i = 2; i < sortedListForSkip.size(); i++) {
            skippedList.add(sortedListForSkip.get(i));
        }
        System.out.println("skippedList = " + skippedList);


        // Functional
        List<Integer> skippedListLambda = integerList.stream().sorted().skip(2).toList();
        System.out.println("skippedListLambda = " + skippedListLambda);

        List<Integer> skippedReverseListLambda = integerList.stream().sorted(Comparator.reverseOrder()).skip(2).toList();
        System.out.println("skippedReverseListLambda = " + skippedReverseListLambda);
    }


    @Test
    public void limitMethods() {

//    limit
//     take only two biggest number

        // Structural
        List<Integer> listForLimit = new ArrayList<>(integerList);
        Collections.sort(listForLimit.reversed());
        List<Integer> maxTwoNumber = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            maxTwoNumber.add(listForLimit.get(i));
        }
//        maxTwoNumber.add(listForLimit.get(0));
//        maxTwoNumber.add(listForLimit.get(1));
        System.out.println("maxTwoNumber = " + maxTwoNumber);

        // Functional
        List<Integer> maxTwoNumberLambda = integerList.stream()
                .sorted(Comparator.reverseOrder())
                .limit(2)
                .toList();
        System.out.println("maxTwoNumberLambda = " + maxTwoNumberLambda);
    }

}
