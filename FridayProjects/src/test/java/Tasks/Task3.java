package Tasks;

import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Task3 {


    enum DOCTOR {
        POLAT,
        CAKIR,
        ELIF,
        MEMATI
    }

    enum HASTALIK {
        BAS_AGRISI,
        DIS_CEKIMI,
        ORTOPEDI,
        KBB,
        KIRIK_CIKIK,
        PSIKOLOJI
    }

    class DoctorMuayeneUcretleri {

        private DOCTOR doctorAdi;
        private HASTALIK hastalik;
        private int ucret;
        private boolean hastaneyeYatmasiGerekir;
        static int totalUcret;

        public DoctorMuayeneUcretleri(DOCTOR doctorAdi, HASTALIK hastalik, int ucret, boolean hastaneyeYatmasiGerekir) {
            this.doctorAdi = doctorAdi;
            this.hastalik = hastalik;
            this.ucret = ucret;
            this.hastaneyeYatmasiGerekir = hastaneyeYatmasiGerekir;
        }

        public DOCTOR getDoctorAdi() {
            return doctorAdi;
        }

        public HASTALIK getHastalik() {
            return hastalik;
        }

        public int getUcret() {
            return ucret;
        }

        public boolean isHastaneyeYatmasiGerekir() {
            return hastaneyeYatmasiGerekir;
        }

        public void calculateTotal() {
            totalUcret += this.ucret;
        }

    }

    List<DoctorMuayeneUcretleri> doctorMuayeneUcretleriList = new ArrayList<>();

    @Test
    public void muayeneHesaplamalari() {

        setDoktorMuayeneBilgileri();

        // polat beyin discekimi ucreti ne kadar
        // structural
        for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
            if (doctorMuayeneUcretleriList.get(i).doctorAdi.equals(DOCTOR.POLAT) &&
                    doctorMuayeneUcretleriList.get(i).hastalik.equals(HASTALIK.DIS_CEKIMI)) {
                System.out.println("doctorMuayeneUcretleriList.get(i).getUcret() = " + doctorMuayeneUcretleriList.get(i).getUcret());
            }
        }

        //functional-1
        doctorMuayeneUcretleriList.stream().filter(t -> t.doctorAdi.equals(DOCTOR.POLAT) &&
                        t.hastalik.equals(HASTALIK.DIS_CEKIMI))
                .forEach(t -> System.out.println(("functional = " + t.getUcret())));

        //functional-1
        doctorMuayeneUcretleriList.stream().filter(t -> t.doctorAdi.equals(DOCTOR.POLAT) &&
                        t.hastalik.equals(HASTALIK.DIS_CEKIMI))
                .mapToInt(DoctorMuayeneUcretleri::getUcret)
                .forEach(System.out::println);


        // hastaneye yatmasi gereken hastaliklar nelerdir

        //structural
        for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
            if (doctorMuayeneUcretleriList.get(i).hastaneyeYatmasiGerekir) {
                System.out.println("doctorMuayeneUcretleriList.get(i).getHastalik() = " + doctorMuayeneUcretleriList.get(i).getHastalik());
            }
        }

        //Functional-1
        doctorMuayeneUcretleriList.stream().filter(t -> t.isHastaneyeYatmasiGerekir())
                .forEach(t -> System.out.println(("fuctional-1 : " + t.getHastalik())));

        // Functional-2
        doctorMuayeneUcretleriList.stream().filter(DoctorMuayeneUcretleri::isHastaneyeYatmasiGerekir)
                .forEach(t -> System.out.println(("fuctional-2 : " + t.getHastalik())));

        // Functional-3
        doctorMuayeneUcretleriList.stream().filter(DoctorMuayeneUcretleri::isHastaneyeYatmasiGerekir)
                .map(t -> t.getHastalik().toString())  // ENUM değerlerini String'e dönüştürür
                .forEach(System.out::println);


        // bonus
        // bir hasta polat beyden dis cekimi yaptirdiktan sonra bozulan psikolajisini duzeltmek icin elif hanimdan 2 seans psikolojik destek almistir.
        // toplamda odemesi gereken miktar ne kadardir

        // structural
        // way 1
        int toplamUcret = 0;
        for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
            if (doctorMuayeneUcretleriList.get(i).doctorAdi.equals(DOCTOR.POLAT) &&
                    doctorMuayeneUcretleriList.get(i).hastalik.equals(HASTALIK.DIS_CEKIMI)) {
                toplamUcret += doctorMuayeneUcretleriList.get(i).getUcret();
            }
        }

        for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
            if (doctorMuayeneUcretleriList.get(i).doctorAdi.equals(DOCTOR.ELIF) &&
                    doctorMuayeneUcretleriList.get(i).hastalik.equals(HASTALIK.PSIKOLOJI)) {
                toplamUcret += (doctorMuayeneUcretleriList.get(i).getUcret()) * 2;
            }
        }

        System.out.println("toplamUcret - structural = " + toplamUcret);

        // way 2
        toplamUcret = 0;
        for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
            if (doctorMuayeneUcretleriList.get(i).doctorAdi.equals(DOCTOR.POLAT) &&
                    doctorMuayeneUcretleriList.get(i).hastalik.equals(HASTALIK.DIS_CEKIMI)) {
                doctorMuayeneUcretleriList.get(i).calculateTotal();
            }
        }

        for (int j = 0; j < 2; j++) {
            for (int i = 0; i < doctorMuayeneUcretleriList.size(); i++) {
                if (doctorMuayeneUcretleriList.get(i).doctorAdi.equals(DOCTOR.ELIF) &&
                        doctorMuayeneUcretleriList.get(i).hastalik.equals(HASTALIK.PSIKOLOJI)) {
                    doctorMuayeneUcretleriList.get(i).calculateTotal();
                }
            }
        }
        System.out.println("Total Ucret - structural-2 = " + DoctorMuayeneUcretleri.totalUcret);

        DoctorMuayeneUcretleri.totalUcret = 0;

        // Functional-1
        doctorMuayeneUcretleriList.stream()
                .filter(t -> t.doctorAdi.equals(DOCTOR.POLAT) &&
                        t.hastalik.equals(HASTALIK.DIS_CEKIMI))
                .forEach(t -> t.calculateTotal());

        doctorMuayeneUcretleriList.stream()
                .filter(t -> t.doctorAdi.equals(DOCTOR.ELIF) &&
                        t.hastalik.equals(HASTALIK.PSIKOLOJI))
                .forEach(t -> t.calculateTotal());

//        doctorMuayeneUcretleriList.stream()
//                .filter(t -> t.doctorAdi.equals(DOCTOR.ELIF) &&
//                        t.hastalik.equals(HASTALIK.PSIKOLOJI))
//                .forEach(t -> t.calculateTotal());

        doctorMuayeneUcretleriList.stream()
                .filter(t -> t.doctorAdi.equals(DOCTOR.ELIF) &&
                        t.hastalik.equals(HASTALIK.PSIKOLOJI))
                .forEach(DoctorMuayeneUcretleri::calculateTotal);

        System.out.println("Total Ucret - functional-1 = " + DoctorMuayeneUcretleri.totalUcret);


    }

    public void setDoktorMuayeneBilgileri() {
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.POLAT, HASTALIK.BAS_AGRISI, 30, false));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.POLAT, HASTALIK.DIS_CEKIMI, 40, false));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.CAKIR, HASTALIK.ORTOPEDI, 1000, false));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.CAKIR, HASTALIK.KBB, 10, false));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.KIRIK_CIKIK, 30, true));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.KBB, 20, false));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.PSIKOLOJI, 300, true));
        doctorMuayeneUcretleriList.add(new DoctorMuayeneUcretleri(DOCTOR.MEMATI, HASTALIK.BAS_AGRISI, 0, false));

    }


    Map<String, Integer> map = new HashMap<>();

    @Test
    public void map_array() {

        map.put("elma", 24);
        map.put("armut", 12);
        map.put("ayva", 36);
        map.put("kiraz", 60);
        map.put("muz", 5);
        map.put("cilek", 2);

        // 4 harfli olan urunlerin fiyatlarini console a yazdir
        //  for (String kelime : liste)

        //structural
        for (Map.Entry<String, Integer> pair : map.entrySet()){
            if(pair.getKey().length() == 4) {
                System.out.println("pair.getValue() = " + pair.getValue());
            }
        }

        //functional
        map.entrySet().stream().filter(t -> t.getKey().length() ==4)
                .forEach(k -> System.out.println("k.getValue() = " + k.getValue()));


        // fiyati 30 liradan ucuz olan urunlerin isimlerini yazdir

        //structural
        for (Map.Entry<String, Integer> pair1 : map.entrySet()){
            if (pair1.getValue() <30 ) {
                System.out.println("pair1.getKey() -structural = " + pair1.getKey());
            }
        }

        //functional
        map.entrySet().stream().filter(t -> t.getValue()<30)
                .forEach((k -> System.out.println("k.getkey() - functional = " + k.getKey())));


        // fiyati 30 dan fazla ve 4 harfli olan urunleri yazdir
        // structural
        for (Map.Entry<String, Integer> pair2 : map.entrySet()){
            if (pair2.getValue()>30 && pair2.getKey().length() == 4) {
                System.out.println("pair2.getKey() - structural = " + pair2.getKey());
            }
        }

        // functional
        map.entrySet().stream().filter(t -> t.getValue() > 30 && t.getKey().length() ==4)
                .forEach(k -> System.out.println("k.getKey() - functional = " + k.getKey()));

    }


//    class DoctorMuayeneUcretleri {
//        private DOCTOR doctorAdi;
//        private HASTALIK hastalik;
//        private int ucret;
//        private boolean hastaneyeYatmasiGerekir;
//
//        static int totalUcret;
//
//        public DoctorMuayeneUcretleri(DOCTOR doctorAdi, HASTALIK hastalik, int ucret, boolean hastaneyeYatmasiGerekir) {
//            this.doctorAdi = doctorAdi;
//            this.hastalik = hastalik;
//            this.ucret = ucret;
//            this.hastaneyeYatmasiGerekir = hastaneyeYatmasiGerekir;
//
//        }
//
//        public DOCTOR getDoctorAdi() {
//            return doctorAdi;
//        }
//
//        public HASTALIK getHastalik() {
//            return hastalik;
//        }
//
//        public int getUcret() {
//            return ucret;
//        }
//
//        public boolean isHastaneyeYatmasiGerekir() {
//            return hastaneyeYatmasiGerekir;
//        }
//
//        public void calculateTotal() {
//            totalUcret += this.ucret;
//        }
//
//    }
//
//    Map<String, Integer> map = new HashMap<>();
//    List<DoctorMuayeneUcretleri> doctorMuayeneUcretlerisList = new ArrayList<>();
//
//    @Test
//    public void map_array() {
//
//        map.put("elma", 24);
//        map.put("armut", 12);
//        map.put("ayva", 36);
//        map.put("kiraz", 60);
//        map.put("muz", 5);
//        map.put("cilek", 2);
//
//        // way 1  doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri("Polat","Bas Agrisi", 30, false));
//        // way 2 doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.POLAT, HASTALIK.BAS_AGRISI, 30, false));
//
//        // way 1 mi way 2 mi daha temiz okumasi ve kullanmasi
//
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.POLAT, HASTALIK.BAS_AGRISI, 30, false));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.POLAT, HASTALIK.DIS_CEKIMI, 40, false));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.CAKIR, HASTALIK.ORTOPEDI, 1000, false));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.CAKIR, HASTALIK.KBB, 10, false));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.KIRIK_CIKIK, 30, true));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.KBB, 20, false));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.ELIF, HASTALIK.PSIKOLOJI, 300, true));
//        doctorMuayeneUcretlerisList.add(new DoctorMuayeneUcretleri(DOCTOR.MEMATI, HASTALIK.BAS_AGRISI, 0, false));
//
//    }
}
