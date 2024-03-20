package com.KesifPlus.api;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import lombok.SneakyThrows;

import java.util.List;
import java.util.stream.Collectors;

import static com.KesifPlus.api.ApiUtilities.*;
import static io.restassured.RestAssured.given;

public class Products {

    public List<String> getAllAddedVegetableProducts() {
        response = given()
                .spec(requestSpecification)
                .post("/account/hub/getHubDetails/VEGETABLES_AND_FRUITS_HUB");
        return response.jsonPath().getList("products.product.uniqueName");
    }

    @SneakyThrows
    public List<String> getAllVegetableProducts() {
        ObjectMapper objectMapper = new ObjectMapper();
        AllProducts allProducts = objectMapper.readValue(getAllProducts().asString(), AllProducts.class);
        return allProducts
                .getProducts()
                .stream()
                .filter(t -> t.hubTitle.equals("VEGETABLES_AND_FRUITS_HUB"))
                .map(Product::getUniqueName)
                .collect(Collectors.toList());
    }

    private Response getAllProducts() {
        return given()
                .spec(requestSpecification)
                .post("/public/product/getAllProducts");
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class AllProducts {
        @JsonProperty("products")
        private List<Product> products;

        @JsonProperty("products")
        public List<Product> getProducts() {
            return products;
        }
    }

    @JsonIgnoreProperties(ignoreUnknown = true)
    private static class Product {
        @JsonProperty("id")
        private int id;
        @JsonProperty("title")
        private String title;
        @JsonProperty("hubTitle")
        private String hubTitle;
        @JsonProperty("uniqueName")
        private String uniqueName;

        @JsonProperty("id")
        public int getId() {
            return id;
        }

        @JsonProperty("title")
        public String getTitle() {
            return title;
        }

        @JsonProperty("hubTitle")
        public String getHubTitle() {
            return hubTitle;
        }

        @JsonProperty("uniqueName")
        public String getUniqueName() {
            return uniqueName;
        }
    }

    public String getOneNotAddedVegetableProducts() {
        List<String> all = getAllVegetableProducts();
        List<String> added = getAllAddedVegetableProducts();
        all.removeAll(added);
        return all.getFirst();
    }

    public void addNewVegetableProduct(String addedProduct) {
        AddProduct addProduct = new AddProduct(
                "VEGETABLES_AND_FRUITS_HUB",
                5,
                addedProduct,
                50,
                "UNIT_LIBRE",
                false,
                false
        );
        given()
                .spec(requestSpecification)
                .body(addProduct)
                .when()
                .post("/account/hub/product/add")
                .then()
                .assertThat()
                .spec(responseSpecification);
    }

    private record AddProduct(
            String hubUniqueName,
            int price,
            String productUniqueName,
            int stock,
            String unit,
            boolean isOrganic,
            boolean isTrade) {

    }


}
