package KayitFormDepo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class KayitImpl {
    private static int id = 1000;
    private static List<Integer> idList = new ArrayList<>();
    private static Map<Integer, KayitForm> map = new HashMap<>();

    public void register(String firstName, String lastName, int age, String year) {
        map.put(id, new KayitForm(firstName, lastName, age, year));
        idList.add(id);
        id++;
    }

    public void showAllStudents() {

        System.out.println("id \t\t first name \t\t last name \t\t age \t\t year");
        System.out.println("------------------------------------------------");
        for (int i = 0; i < idList.size(); i++) {
            System.out.printf("%s %8s %15s %10s %15s %n",
                    id,
                    map.get(idList.get(i)).firstName(),
                    map.get(idList.get(i)).lastName(),
                    map.get(idList.get(i)).age(),
                    map.get(idList.get(i)).year()
            );
            id++;
        }
    }

    public void removeStudent(int id) {
        idList.removeIf(t -> t.equals(id));
    }
}
