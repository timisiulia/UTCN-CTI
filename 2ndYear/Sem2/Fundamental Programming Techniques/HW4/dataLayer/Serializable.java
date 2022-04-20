package dataLayer;

import bussinessLayer.DeliveryService;
import bussinessLayer.IDeliveryServiceProcessing;
import bussinessLayer.Order;

import javax.imageio.stream.FileImageInputStream;
import java.io.*;

public class Serializable {

    public static void serializare(String s, Object o) {
        try {
            FileOutputStream fout = new FileOutputStream(s);
            ObjectOutputStream out = new ObjectOutputStream(fout);
            out.writeObject(o);
            out.flush();
            out.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public Object deserializare(String way) {
        Object food = null;
        try {
            FileInputStream file = new FileInputStream(way);
            ObjectInputStream in = new ObjectInputStream(file);
            food = in.readObject();
            in.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return food;
    }
}
