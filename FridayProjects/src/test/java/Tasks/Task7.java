package Tasks;

import enums.USERINFO_TEST;
import io.restassured.http.ContentType;
import io.restassured.response.Response;
import org.junit.Assert;
import org.junit.Test;

import java.util.*;

import static io.restassured.RestAssured.given;

public class Task7 {

//    kullanicinin ekleyebilecegi butun urunleri cekin            -> https://test.urbanicfarm.com/api/account/hub/getMyHubs
//    kullanicinin ekledigi butun urunleri cekin                  -> https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB
//    kullanicinin eklemedigi bir urunu kullaniciya ekleyin       -> https://test.urbanicfarm.com/api/account/hub/product/add
//    kullanicinin ekledigi urunlerde yeni eklenen urunun olduguna bakin


    @Test
    public void tasks() {

//    kullanicinin ekleyebilecegi butun urunleri cekin            -> https://test.urbanicfarm.com/api/account/hub/getMyHubs

        String token = USERINFO_TEST.BUYER.getToken();
        Response response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getMyHubs");
        List<String> list = response.jsonPath().getList("hubs.uniqueName");
//        response.prettyPrint();
        System.out.println("list = " + list);


//    kullanicinin ekledigi butun urunleri cekin                  -> https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB
        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB");
        List<String> listAvailable = response.jsonPath().getList("products.product.uniqueName");
//        response.prettyPrint();
        System.out.println("listAvailable = " + listAvailable);


        // KULLANICININ EKLEYEBİLECEĞİ TÜM ÜRÜNLERİ GÖRELİM
        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/public/product/getAllProducts");
        List<String> nameAll = response.jsonPath().getList("products.uniqueName");
        List<String> grupAll = response.jsonPath().getList("products.hubTitle");
        System.out.println("nameAll = " + nameAll.size());
        System.out.println("grupAll.size() = " + grupAll.size());


        Map<String, String> map = new HashMap<>();
        for (int i = 0; i < nameAll.size(); i++) {
            map.put(nameAll.get(i), grupAll.get(i));
        }

        List<String> justFruits = new ArrayList<>();
        for (Map.Entry<String, String> pairs : map.entrySet()) {
            if (pairs.getValue().contains("VEGETABLES_AND_FRUITS_HUB")) {
                justFruits.add(pairs.getKey());
            }
        }

        // İlk listede bulunmayanları yeni listeye al
        // listAvailable içeriğini çıkartalım ve yeni bir liste bulalım.
        Map<String, String> farklarMap = new HashMap<>();
        for (String meyve : justFruits) {
            if (!listAvailable.contains(meyve)) {
                farklarMap.put(meyve, "VEGETABLES_AND_FRUITS_HUB");
            }
        }

        System.out.println("farklarMap = " + farklarMap);
        System.out.println("farklarMap.size() = " + farklarMap.size());

//        System.out.println("grupAll = " + grupAll);
//        System.out.println("nameAll = " + nameAll);
//        System.out.println("list = " + map);
//        System.out.println("map.size() = " + map.size());


        //    kullanicinin eklemedigi bir urunu kullaniciya ekleyin       -> https://test.urbanicfarm.com/api/account/hub/product/add

        Optional<String> selectedFruit = farklarMap.keySet().stream()   //rastgele üretilen sayı kadar atla ve ilk öğeyi al
                .skip(new Random().nextInt(farklarMap.keySet().size()))
                .findFirst();
//        Optional<String> selectedFruit = farklarMap.keySet().stream()
//                .findFirst();
        String newFruit = selectedFruit.orElse("");
        System.out.println("newFruit = " + newFruit);

        // 1. yöntem
        response = given()
                .contentType(ContentType.MULTIPART)
                .auth().oauth2(token)
                .multiPart("hubUniqueName", "VEGETABLES_AND_FRUITS_HUB")
                .multiPart("price", "2")
                .multiPart("productUniqueName", newFruit)
                .multiPart("stock", "50")
                .multiPart("unit", "UNIT_LIBRE")
                .multiPart("isOrganic", "false")
                .multiPart("isTrade", "false")
                .post("https://test.urbanicfarm.com/api/account/hub/product/add");
        response.prettyPrint();

        // 2. yöntem
/*
        Map<String, String> map2 = new HashMap<>();
        map2.put("hubUniqueName", "VEGETABLES_AND_FRUITS_HUB");
        map2.put("price", "2");
        map2.put("productUniqueName", newFruit);
        map2.put("stock", "50");
        map2.put("unit", "UNIT_LIBRE");
        map2.put("isOrganic", "false");
        map2.put("isTrade", "false");

        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .body(map2)
                .post("https://test.urbanicfarm.com/api/account/hub/product/add");
        response.prettyPrint();
*/

        //    kullanicinin ekledigi urunlerde yeni eklenen urunun olduguna bakin
        response = given().
                contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB");
        List<String> listAvailableNew = response.jsonPath().getList("products.product.uniqueName");

        // structural
        boolean flag = false;
        for (String name : listAvailableNew) {
            if (name.contains(newFruit)) {
                flag = true;
            }
        }
        Assert.assertTrue(flag);

        // functional
        boolean isThere = listAvailableNew.stream().anyMatch(t -> t.contains(newFruit));
        Assert.assertTrue(isThere);

//        System.out.println("listAvailable = " + listAvailable);

    }
}


