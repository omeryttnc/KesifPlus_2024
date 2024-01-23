package enums;

public enum ENUMDENEME {
    CLASS_DENEME("omer enum",350)
    ;
    private String name;
    private int age;

    ENUMDENEME(String name, int age) {
        this.name = name;
        this.age = age;
    }
    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }
    public void printUserInfo(){
        System.out.println("name: " +this.name + ", age : " +this.age);
    }
}
