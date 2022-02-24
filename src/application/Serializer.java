package application;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;

public class Serializer {

  private static String OS = System.getProperty("os.name").toLowerCase();

  public static final String databasePath = OS.indexOf("win") >= 0 ? System.getProperty("user.home") + "\\database.bin"
      : System.getProperty("user.home") + "/database.bin";

  public static boolean serialize(String filePath, ArrayList<Product> productList) {
    File databaseFile = new File(filePath);
    FileOutputStream fileOutputStream = null;
    ObjectOutputStream objectOutputStream = null;

    boolean success = false;
    try {
      fileOutputStream = new FileOutputStream(databaseFile);
      objectOutputStream = new ObjectOutputStream(fileOutputStream);
      objectOutputStream.writeObject(productList);
      success = true;
    } catch (Exception exception) {
      success = false;
    }

    return success;
  }

  public static ArrayList<Product> deserialize(String filePath) {
    File databaseFile = new File(filePath);
    FileInputStream fileInputStream = null;
    ObjectInputStream objectInputStream = null;

    ArrayList<Product> list = null;

    try {
      fileInputStream = new FileInputStream(databaseFile);
      objectInputStream = new ObjectInputStream(fileInputStream);

      list = (ArrayList<Product>) objectInputStream.readObject();
    } catch (Exception exception) {
      System.out.println(exception.toString());
    }

    return list;
  }
}
