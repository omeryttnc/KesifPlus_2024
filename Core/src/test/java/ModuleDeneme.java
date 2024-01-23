
import com.KesifPlus.ui.Utility;
import enums.ClassDeneme;
import enums.ENUMDENEME;
import org.junit.Test;
import pojos.ClassRecord;
import pojos.RecordClass;
import utilities.BrowserUtilities;

public class ModuleDeneme {

    @Test
    public void classRecord() {
        ClassRecord classRecord = new ClassRecord("omer", "Ali");
        RecordClass recordClass = new RecordClass("omer", "ali");

        System.out.println(classRecord.getEmail());
        System.out.println(recordClass.email());

    }

    @Test
    public void enumClassKarsilastirmasi() {
        ClassDeneme classDeneme = new ClassDeneme("omer", 35);
        classDeneme.printUserInfo();

        ENUMDENEME.CLASS_DENEME.printUserInfo();

    }

    @Test
    public void manifoldSample() {
        Integer age = 28;
        age.helloWorld();
        BrowserUtilities.dogumYili(28);
        age.dogumYili();
    }

    @Test
    public void name() {
        Utility.printName();
        Utility.printKesif();
        Utility.printMail("omer");
    }
}
