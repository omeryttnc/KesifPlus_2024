package Tasks;

public class Letters {

    public static void main(String[] args) {

        long baslangicZamani = System.currentTimeMillis();
            terstenYazdir1();
        long bitisZamani = System.currentTimeMillis();

        long gecenSure = bitisZamani - baslangicZamani;
        System.out.println();
        System.out.println("Toplam süre : " + gecenSure + " ms");



        baslangicZamani = System.currentTimeMillis();
        terstenYazdir2();
        bitisZamani = System.currentTimeMillis();

        gecenSure = bitisZamani - baslangicZamani;
        System.out.println();
        System.out.println("Toplam süre : " + gecenSure + " ms");

    }

    public static void terstenYazdir1(){
        String letter = "ayeia ylmkaığ9aylUEAkeangğalkaeiü90ğ4ı0934oa ğlm aek89g4ğıalemaki u";

        for (int i=letter.length()-1; i>=0; i--){
            System.out.print(letter.substring(i,i+1));
        }
    }


    public static void terstenYazdir2(){
        String letter = "ayeia ylmkaığ9aylUEAkeangğalkaeiü90ğ4ı0934oa ğlm aek89g4ğıalemaki u";
        char[] l = letter.toCharArray();
        for (int i = l.length-1; i>=0; i--){
            System.out.print(l[i]);
        }

    }

}

