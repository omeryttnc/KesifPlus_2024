package Core.extensions.java.lang.Integer;

import manifold.ext.rt.api.Extension;
import manifold.ext.rt.api.This;
import java.lang.Integer;

@Extension
public class MyIntExtenation {
  public static void helloWorld(@This Integer thiz) {
    System.out.println("hello world!");
  }

  public static void dogumYili(@This Integer yas){
    System.out.println("dogum yiliniz: " + (2024-yas));
  }
}