package KayitFormDepo;

public class KayitMain {
    public static void main(String[] args) {
        KayitImpl kayit = new KayitImpl();
        kayit.register("omer", "ali", 15, "year 5");
        kayit.register("oguz", "okkes", 26, "year 6");
        kayit.register("mahmut", "murtaza", 8, "year 4");
        kayit.register("hasan", "huseyin", 7, "year 3");
        kayit.showAllStudents();
        kayit.removeStudent(1001);
        kayit.showAllStudents();

//        String name = "omer";
//        String lastName = "ali";
//        System.out.println("     "+name+"          " + lastName);
//        System.out.printf("%5s %10s",name,lastName);

    }
}
