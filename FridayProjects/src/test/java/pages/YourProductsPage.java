package pages;

import com.KesifPlus.api.ApiUtilities;
import com.KesifPlus.database.DatabaseUtilities;
import com.KesifPlus.ui.Utilities;
import enums.USERINFO;
import org.junit.Assert;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class YourProductsPage extends CommonPage{

public static String oneNotAddedVegetableProducts="";
    public void addNewVegetable(){
        ApiUtilities apiUtilities = new ApiUtilities(USERINFO.BUYER.getEmail(), USERINFO.BUYER.getPassword());
        oneNotAddedVegetableProducts = apiUtilities.getProducts().getOneNotAddedVegetableProducts();

        System.out.println("oneNotAddedVegetableProducts = " + oneNotAddedVegetableProducts);
        Assert.assertTrue(isNone(apiUtilities));

        apiUtilities.getProducts().addNewVegetableProduct(oneNotAddedVegetableProducts);
        Assert.assertTrue(isAny(apiUtilities));
    }

    public boolean isNone(ApiUtilities apiUtilities){
        boolean isNone = apiUtilities.getProducts()
                .getAllAddedVegetableProducts()
                .stream()
                .noneMatch(t -> t.equals(oneNotAddedVegetableProducts));
        return isNone;
    }

    public boolean isAny(ApiUtilities apiUtilities){
        boolean isAny = apiUtilities.getProducts()
                .getAllAddedVegetableProducts()
                .stream()
                .anyMatch(t -> t.equals(oneNotAddedVegetableProducts));
        return isAny;
    }

    public void updateStatus(){
        DatabaseUtilities.createConnection();
        String sqlQuery = "UPDATE hub_product SET product_listing_state = 'APPROVED' " +
                "WHERE id = (SELECT id FROM (SELECT MAX(id) AS id FROM hub_product " +
                "WHERE unique_name = '" + oneNotAddedVegetableProducts + "') AS subquery) " +
                "AND product_listing_state = 'IN_REVIEW';";

        DatabaseUtilities.updateQueryStatement(sqlQuery);
    }

    @FindBy(css = "div[class*='HubManagement_hubProductCartContainer']>span")
    private List<WebElement> productStatusList;
    @FindBy(css = "div[class*='HubManagement_hubProductCartContainer']>img")
    private List<WebElement> productNameList;

    public void verifyStatus(){
        Utilities.waitFor(3);
        String status = "";
        for (int i = 0; i < productNameList.size(); i++) {
            if(productNameList.get(i).getAttribute("alt").equalsIgnoreCase(oneNotAddedVegetableProducts)){
                status = productStatusList.get(i).getText();
                System.out.println("status = " + status);
            }
        }
        Assert.assertEquals("APPROVED", status);

    }

}
