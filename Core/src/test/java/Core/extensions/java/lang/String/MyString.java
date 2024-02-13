package Core.extensions.java.lang.String;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import org.junit.Assert;

import java.lang.String;

import static com.KesifPlus.HooksImp.driver;


@Extension
public class MyString {
  public static void helloWorld(@This String thiz) {
    System.out.println("hello world!");
  }
  public static void assertTitleIfEquals(@This String expectedTitle) {
    Assert.assertEquals(expectedTitle, driver.getTitle());
  }

}