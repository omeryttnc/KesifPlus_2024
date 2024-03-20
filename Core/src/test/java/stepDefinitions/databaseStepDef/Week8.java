package stepDefinitions.databaseStepDef;

import com.KesifPlus.database.DatabaseUtilities;
import com.KesifPlus.database.PromoCodeDatabase;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.junit.Assert;
import org.junit.Test;
import utility.FakerData;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

public class Week8 {
    PromoCodeDatabase promoCodeDatabase = new PromoCodeDatabase();
    FakerData fakerData = new FakerData();
    String startDate = promoCodeDatabase.getTimeForPromocode(-3);
    String endDate = promoCodeDatabase.getTimeForPromocode(3);
    String promocodeName = fakerData.getFirstName();

    @Given("database den promocode olustur")
    public void databaseDenPromocodeOlustur() {

        System.out.println("promocodeName = " + promocodeName);
        promoCodeDatabase.createPromocode(
                promocodeName,
                startDate,
                endDate,
                "10",
                "20",
                "money"
        );

    }

    @Given("read database")
    public void readDatabase() {

        Assert.assertTrue(
                promoCodeDatabase
                        .readPromoCode()
                        .stream()
                        .anyMatch(t -> t.code().equals(promocodeName)));
    }

    @When("databaseden update et")
    public void databasedenUpdateEt() {
    }

    @Then("databaseden sil")
    public void databasedenSil() {

        promoCodeDatabase.deletePromocode(promocodeName);

        Assert.assertTrue(
                promoCodeDatabase
                        .readPromoCode()
                        .stream()
                        .noneMatch(t -> t.code().equals(promocodeName)));
    }
}



