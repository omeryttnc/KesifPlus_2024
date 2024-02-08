package stepDefinitions;

import junit.framework.TestCase;

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
}
