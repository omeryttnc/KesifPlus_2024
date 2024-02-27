package Tasks;

import com.KesifPlus.ui.Utilities;
import org.junit.Test;

import java.util.List;

public class Task2 {

    List<String> names = List.of("Kesif","Plus","Selenium","Dersleri", "ile", "eğlenceli");

    /* ---------------- TASK 2 ----------------------------
    print words only if they have equal or less than 5 letters
    STRUCTURAL PROGRAMING AND
    FUNCTIONAL PROGRAMING
     */

    @Test
    public void structural1(){
        System.out.println("------------ Structural Programming, Alternative 1");
        for (int i=0; i<names.size(); i++){
            if(names.get(i).length() <= 5){
                System.out.println(names.get(i));
            }
        }
    }

    @Test
    public void structural2(){
        System.out.println("------------ Structural Programming, Alternative 2");
        for(int i=0; i<names.size(); i++){
            System.out.print(names.get(i).length() <=5 ? names.get(i) + "\n" : "");
        }
    }

    @Test
    public void structural3(){
        System.out.println("------------ Structural Programming, Alternative 3");
        for (String name : names){
            if (name.length() <=5) {
                System.out.println("length = " + name.length() + " letter, \t" + name);
            }
        }
    }

    @Test
    public void structural4(){
        System.out.println("------------ Structural Programming, Alternative 4");
        for (int i=0; i<names.size(); i++){
            if(Utilities.filterLessThanFive((names.get(i)))){
                Utilities.printName(names.get(i));
            }
        }
    }

    @Test
    public void functional1(){
        System.out.println();
        System.out.println();
        System.out.println("------------ Functional Programming, Alternative 1");
        names.stream().filter(name -> name.length() <=5).forEach(System.out::println);
        // Aynı işlemi yapıyor   -------------------->  .forEach(name -> System.out.println(name));
    }

    @Test
    public void functional2(){
        System.out.println("------------ Functional Programming, Alternative 2");
        names.stream().filter(Utilities::filterLessThanFive)
                     .forEach(Utilities::printName);
    }


}
