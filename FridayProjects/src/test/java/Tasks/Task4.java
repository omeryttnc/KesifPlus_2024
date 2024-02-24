package Tasks;

import org.junit.Test;
import org.openqa.selenium.WebElement;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Task4 {

//    @Test
//    public void map_count_collect() {
//        map.put("elma", 24);
//        map.put("armut", 12);
//        map.put("ayva", 36);
//        map.put("kiraz", 60);
//        map.put("muz", 5);
//        map.put("cilek", 2);
//        // ---------------------------- map ---------------------------------
//        List<WebElement> webElementList = new ArrayList<>();
//        // webelementlerin ici bos ama dolu oldugunu varsayin her webelementin value attribute nu alin
//        // urunlerin fiyatlarini 5 ile carpin
//        //----------------------------- count -------------------------------
//        List<String> names = List.of("kesif","plus","selenium","dersleri");
//        // name listesinin icinde kac tane String "le" iceriyor
//        // map icerisinde kac tane urun 30 dan daha pahali
//        // --------------------------- collect -------------------------------
//        // names listesin de "l" bulunduran kelimeleri yeni bir listeye ekleyin
//        // fiyati 30 dan fazla olan urunlerin isimlerini farkli bir listeye ekleyin
//        // fiyati 30 dan fazla olan urunlerin isimlerini farkli bir Map e ekleyin
//    }

    @Test
    public void getElementValue() {

//        List<WebElement> webElementList = new ArrayList<>();
//        // webelementlerin ici bos ama dolu oldugunu varsayin her webelementin value attribute nu alin

        List<WebElement> webElementList = new ArrayList<>();

        //Structural
        for (WebElement element : webElementList) {
            System.out.println("element.getAttribute(\"value\") = " + element.getAttribute("value"));
            System.out.println("element.getCssValue(\"value\") = " + element.getCssValue("value"));
        }

        // functional
        webElementList.forEach(t -> System.out.println(t.getAttribute("value")));
        webElementList.stream().forEach((t -> System.out.println((t.getCssValue("value")))));

    }


    //        map.put("elma", 24);
//        map.put("armut", 12);
//        map.put("ayva", 36);
//        map.put("kiraz", 60);
//        map.put("muz", 5);
//        map.put("cilek", 2);


    Map<String, Integer> map = new HashMap<>();

    @Test
    public void map_count_collect() {

        //        // urunlerin fiyatlarini 5 ile carpin

        map.put("elma", 24);
        map.put("armut", 12);
        map.put("ayva", 36);
        map.put("kiraz", 60);
        map.put("muz", 5);
        map.put("cilek", 2);

        // Structural-1
        for (Map.Entry<String, Integer> pairs : map.entrySet()) {
            int carpim = pairs.getValue() * 5;
            System.out.println("carpim structural-1 = " + carpim);
        }

        // Structural-2
        for (int value : map.values()) {
            System.out.println("(value*5) structural-2 = " + (value * 5));
        }

        for (String key : map.keySet()) {          //map içindeki key anahtar değerlerini alma
            System.out.println("key = " + key);
        }

        // Functional-1
        map.values().forEach(t -> System.out.println("t*5 Functional-1 =" + t * 5));

        // Functional-2
        map.values().stream()
                .map(t -> t * 5)
                .forEach(System.out::println);

        map.values().stream()              // Yeni bir listeye değerleri atama
                .map(t -> t * 5)
                .collect(Collectors.toList());


        // map icerisinde kac tane urun 30 dan daha pahali

        //Structural
        int count = 0;
        for(int value : map.values()){
            if (value>30){
                count++;
            }
        }
        System.out.println("count structural (30 dan pahalı) = " + count);

        // Functional
        long countLambda = map.values().stream()
                .filter(t -> t>30)
                .count();
        System.out.println("countLambda Functional (30 dan pahalı) = " + countLambda);


         // fiyati 30 dan fazla olan urunlerin isimlerini farkli bir listeye ekleyin

        //Structural
        List<String> pahaliUrunler = new ArrayList<>();
        for(Map.Entry<String, Integer> pairs : map.entrySet() ){
            if(pairs.getValue()>30) {
                pahaliUrunler.add(pairs.getKey());
            }
        }
        System.out.println("pahaliUrunler = " + pahaliUrunler);

        //Functional-1
        List<String> pahaliUrunlerLambda1 = map.entrySet().stream()
                .filter(t -> t.getValue()>30)
                .map(t -> t.getKey())
                .collect(Collectors.toList());

        System.out.println("pahaliUrunlerLambda1 = " + pahaliUrunlerLambda1);

        // Functional-2
        List<String> pahaliUrunlerLambda2 = map.entrySet().stream()
                .filter(t -> t.getValue()>30)
                .map(Map.Entry::getKey)
                .toList();
        System.out.println("pahaliUrunlerLambda2 = " + pahaliUrunlerLambda2);


        // fiyati 30 dan fazla olan urunlerin isimlerini farkli bir Map e ekleyin

        //Structural
        Map<String, Integer> pahaliUrunlerMap = new HashMap<>();
        for(Map.Entry<String, Integer> pairs : map.entrySet()){
            if(pairs.getValue()>30){
                pahaliUrunlerMap.put(pairs.getKey(), pairs.getValue());
            }
        }
        System.out.println("pahaliUrunlerMap = " + pahaliUrunlerMap);

        // Functional-1
        Map<String, Integer> pahaliUrunlerMapLambda1 = map.entrySet().stream()
                .filter(t -> t.getValue()>30)
                .collect(Collectors.toMap(t -> t.getKey(), t -> t.getValue()));
        // Functional-2
        Map<String, Integer> pahaliUrunlerMapLambda2 = map.entrySet().stream()
                .filter(t -> t.getValue()>30)
                .collect(Collectors.toMap(Map.Entry::getKey, Map.Entry::getValue));

        System.out.println("pahaliUrunlerMapLambda1 = " + pahaliUrunlerMapLambda1);
        System.out.println("pahaliUrunlerMapLambda2 = " + pahaliUrunlerMapLambda2);
    }

    @Test
    public void count_Words() {
        //        //----------------------------- count -------------------------------
        List<String> names = List.of("kesif", "plus", "selenium", "dersleri");
        // name listesinin icinde kac tane String "le" iceriyor

        //Structural
        int total = 0;
        for (String name : names) {
            if (name.contains("le")) {
                total++;
            }
        }
        System.out.println("total = " + total);

        // Functional
        long totalLambda = names.stream()
                .filter(t -> t.contains("le"))
                .count();
        System.out.println("totalLambda = " + totalLambda);


        // names listesin de "l" bulunduran kelimeleri yeni bir listeye ekleyin

        //Structural
        List<String> lName = new ArrayList<>();
        for(String name : names){
            if (name.contains("l")){
                lName.add(name);
            }
        }
        System.out.println("lName Structural = " + lName);

        //Functional-1
        List<String> lNameLambda = names.stream()
                .filter(t -> t.contains("l"))
                .collect(Collectors.toList());

        System.out.println("lNameLambda = " + lNameLambda);



        List<String> lNameLambda2 = names.stream()
                .filter(t -> t.contains("l"))
                .toList();

        System.out.println("lNameLambda2 = " + lNameLambda2);

    }


}
