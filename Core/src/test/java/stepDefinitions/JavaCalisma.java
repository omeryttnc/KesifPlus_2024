package stepDefinitions;

import junit.framework.TestCase;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.Select;

import java.io.File;

public class JavaCalisma extends TestCase {
    public void deleteQRPicture() {
        File file = new File(getTargetPath());
        File[] files = file.listFiles();
        for (File innerFile : files) {
            if (innerFile.getName().equals("QRCode.png")) {
                innerFile.delete();
                break;
            }
        }
    }

    public String getTargetPath() {
        return System.getProperty("user.dir") + "\\target";
    }

    public String getFlagPath(String flag) {
        return System.getProperty("user.dir") + "\\src\\test\\resources\\userLogo\\"+flag+".png";
    }

    public void testName() {
        WebElement webElement = null;
        Select select = new Select(webElement );
        select.selectByIndex(2);
    }
}
