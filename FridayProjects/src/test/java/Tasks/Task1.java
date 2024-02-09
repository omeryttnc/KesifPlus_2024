package Tasks;

import io.cucumber.java.ja.但し;
import org.junit.Test;

import javax.swing.text.Utilities;
import java.util.List;
import java.util.stream.Collectors;

public class Task1 {

    List<String> names = List.of("Kesif","Plus","Selenium","Dersleri","ile","cok","keyifli");

    /* ---------------- TASK 1 ----------------------------
    print list into your console as
    STRUCTURAL PROGRAMING AND
    FUNCTIONAL PROGRAMING
     */


    //  ----------------------------------------------------------------------------------
    // Structural (Geleneksel) Programming

    @Test
    public void structural1(){
        System.out.println("----------- Structural Programming, Alternatif 1");
        for (int i = 0; i < names.size(); i++) {
            System.out.print(names.get(i) + " ");
        }
    }

    @Test
    public void structural2(){
        System.out.println("--------- Alternatif 2");
        for (String name : names){
            System.out.print(name + " ");
        }
    }

    //-----------------------------------------------
    // Functional Programming
    @Test
    public void functional1(){
        System.out.println();
        System.out.println("----------------- Functional Programming, Alternatif 1");
        names.forEach(kelime -> System.out.print(kelime + " "));
    }

    @Test
    public void functional2(){
        System.out.println();
        System.out.println("-------------Alternatif 2");
        String result = names.stream().collect(Collectors.joining(" "));
        System.out.println(result);
    }
}
