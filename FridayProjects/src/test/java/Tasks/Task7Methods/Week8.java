package Tasks.Task7Methods;

import com.KesifPlus.api.ApiUtilities;
import enums.USERINFO;
import org.junit.Test;

import static io.restassured.RestAssured.given;

public class Week8 {
    @Test
    public void test1() {
        ApiUtilities apiUtilities = new ApiUtilities(USERINFO.BUYER.getEmail(), USERINFO.BUYER.getPassword());

        String oneNotAddedVegetableProducts = apiUtilities.getProducts().getOneNotAddedVegetableProducts();
        System.out.println("oneNotAddedVegetableProducts = " + oneNotAddedVegetableProducts);

        System.out.println("before adding product = " +
                apiUtilities
                        .getProducts()
                        .getAllAddedVegetableProducts()
                        .stream()
                        .noneMatch(t -> t.equals(oneNotAddedVegetableProducts)));

        apiUtilities.getProducts().addNewVegetableProduct(oneNotAddedVegetableProducts);

        System.out.println("after adding product = " +
                apiUtilities
                        .getProducts()
                        .getAllAddedVegetableProducts()
                        .stream()
                        .anyMatch(t -> t.equals(oneNotAddedVegetableProducts)));
    }
}
