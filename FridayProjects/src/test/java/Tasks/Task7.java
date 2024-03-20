package Tasks;

import enums.USERINFO;
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
        String token = USERINFO.BUYER.getToken();
        Response response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getMyHubs");
        List<String> hubList = response.jsonPath().getList("hubs.uniqueName");
        System.out.println("hubList = " + hubList);


                //    kullanicinin ekledigi butun urunleri cekin -> https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB
        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB");
        List<String> listAvailable = response.jsonPath().getList("products.product.uniqueName");
        System.out.println("listAvailable = " + listAvailable);


        // Tüm ürünlerin listesine ulaşıyoruz
        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/public/product/getAllProducts");
        List<String> nameAll = response.jsonPath().getList("products.uniqueName");
        List<String> grupAll = response.jsonPath().getList("products.hubTitle");
//        System.out.println("nameAll = " + nameAll);
//        System.out.println("grupAll = " + grupAll);

        System.out.println("nameAll.size() = " + nameAll.size());
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

        System.out.println("justFruits = " + justFruits);
        System.out.println("justFruits.size() = " + justFruits.size());

        // ilk listede bulunmayanları yeni bir listeye alalım.
        Map<String, String> farkMap = new HashMap<>();
        for (String meyve : justFruits) {
            if (!listAvailable.contains(meyve)) {
                farkMap.put(meyve, "VEGETABLES_AND_FRUITS_HUB");
            }
        }


        //    kullanicinin eklemedigi bir urunu kullaniciya ekleyin       -> https://test.urbanicfarm.com/api/account/hub/product/add


        // kullanıcının eklemediği bir ürünü seçelim
        Optional<String> selectedFruit = farkMap.keySet().stream()
                .skip(new Random().nextInt((farkMap.keySet().size())))
                .findFirst();
//        Optional<String> selectedFruit2 = farkMap.keySet().stream()
//                .findFirst();

        String newFruit = selectedFruit.orElse("");
        System.out.println("newFruit = " + newFruit);

        Response response2 = given()
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
        response2.prettyPrint();

//    kullanicinin ekledigi urunlerde yeni eklenen urunun olduguna bakin
        // mevcut ekli ürünleri yeniden alalım
        response = given()
                .contentType(ContentType.JSON)
                .auth().oauth2(token)
                .post("https://test.urbanicfarm.com/api/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB");
        List<String> listAvailableNew = response.jsonPath().getList("products.product.uniqueName");

        // Structural
        boolean flag = false;
        for(String name: listAvailableNew){
            if(name.contains(newFruit)){
                flag = true;
            }
        }
        Assert.assertTrue(flag);

        // functional
        boolean isThere = listAvailableNew.stream().anyMatch(t -> t.contains(newFruit));
        Assert.assertTrue(isThere);


    }


}
