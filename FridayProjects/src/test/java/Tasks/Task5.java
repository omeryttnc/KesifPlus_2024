package Tasks;

import org.junit.Assert;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task5 {

//        List<String> names = List.of("kesif", "plus", "selenium", "dersleri");
//     butun harfler s harfi iceriyor mu
//     her hangi biri p iceriyor mu
//     hic biri x icermiyor mu
//    Map<String, Integer> map = new HashMap<>();
//    map.put("elma",24);
//    map.put("armut",12);
//    map.put("ayva",36);
//    map.put("kiraz",60);
//    map.put("muz",5);
//    map.put("cilek",2);

//     butun fiyatlar 0 dan buyuk
//     ama hicbiri 100 den buyuk degil
//     ve herhangi biri 30 dan buyuk mu

    @Test
    public void Lambda() {
        List<String> names = List.of("kesif", "plus", "selenium", "dersleri");
        System.out.println("names = " + names);

//     butun kelimeler s harfi iceriyor mu

        // Structural
        boolean flag1 = true;
        for (String name : names) {
            if (!name.contains("s")) {
                flag1 = false;
                break;
            }
        }
//        Assert.assertTrue(flag1);
        System.out.println("butun kelimeler s harfi iceriyor? =" + flag1);

        // Functional
        boolean flagLambda1 = names.stream().allMatch(t -> t.contains("s"));
        System.out.println("butun kelimeler s harfi iceriyor? =" + flag1 + " - " + flagLambda1);


        //     her hangi biri p iceriyor mu
        // Structural
        boolean flag2 = false;
        for (String name : names) {
            if (name.contains("p")) {
                flag2 = true;
                break;
            }
        }
//        Assert.assertTrue(flag2);

        // Functional
        boolean flagLambda2 = names.stream().anyMatch(t -> t.contains("p"));
        System.out.println("her hangi biri p iceriyor? = " + flag2 + " - " + flagLambda2);


        //     hic biri x icermiyor mu
        // Structural
        boolean flag3 = true;
        for (String name : names) {
            if (name.contains("x")) {
                flag3 = false;
                break;
            }
        }

        // Functional
        boolean flagLambda3 = names.stream().noneMatch(t -> t.contains("x"));
        System.out.println("hic biri x icermiyor? = " + flag3 + " - " + flagLambda3);

    }

    // ---------------------------------------------------------------

    @Test
    public void mapLambda() {

        Map<String, Integer> fruit = new HashMap<>();
        fruit.put("elma", 24);
        fruit.put("armut", 12);
        fruit.put("ayva", 36);
        fruit.put("kiraz", 60);
        fruit.put("muz", 5);
        fruit.put("cilek", 2);

//     butun fiyatlar 0 dan buyuk
//     ama hicbiri 100 den buyuk degil
//     ve herhangi biri 30 dan buyuk mu


//     butun fiyatlar 0 dan buyuk
        // Structural
        boolean flagMap1 = true;
        for (int value : fruit.values()){
            if(value <= 0){
                flagMap1 = false;
                break;
            }
        }

        // Functional
        boolean flagMapLambda1 = fruit.values().stream().allMatch(t -> t >0);
        System.out.println("butun fiyatlar 0 dan büyük? = " + flagMap1 + " - " + flagMapLambda1);

//     ama hicbiri 100 den buyuk degil
        // Structural
        boolean flagMap2 = true;
        for(int value : fruit.values()){
            if (value > 100){
                flagMap2 = false;
                break;
            }
        }


        // Functional
        boolean flagMapLambda2 = fruit.values().stream().noneMatch(t -> t>100);
        System.out.println("hicbiri 100 den buyuk degil? = " + flagMap2 + " - " + flagMapLambda2);


        //     ve herhangi biri 30 dan buyuk mu
        boolean flagMap3 = false;
        for(int value : fruit.values()){
            if (value > 30){
                flagMap3 = true;
                break;
            }
        }

        // Functional
        boolean flagMapLambda3 = fruit.values().stream().anyMatch(t -> t>30);
        System.out.println("herhangi biri 30 dan buyuk? = " + flagMap3 + " - " + flagMapLambda3);

    }


}
