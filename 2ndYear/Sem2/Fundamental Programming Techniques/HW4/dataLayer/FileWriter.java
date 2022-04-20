package dataLayer;

import bussinessLayer.BaseProduct;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

public class FileWriter {

    public static class FilWriter {
        //end of line has not comma!
        public static List<BaseProduct> ReadItems(String filename) {
            Scanner scan = null;
            try {
                scan = new Scanner(new File(filename));
                List<BaseProduct> result = scan.findAll(Pattern.compile("\n")).distinct().map(line -> {
                    String[] param = line.group().split(",");
                    BaseProduct product = new BaseProduct();
                    product.setTitle(param[0]);
                    product.setRating(Float.parseFloat(param[1]));
                    product.setCalories(Float.parseFloat(param[2]));
                    product.setProteins(Float.parseFloat(param[3]));
                    product.setFats(Float.parseFloat(param[4]));
                    product.setSodium(Float.parseFloat(param[5]));
                    product.setPrice(Float.parseFloat(param[6]));
                    return product;
                }).collect(Collectors.toList());
                scan.close();
                return result;
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            }
            return null;
        }

    }
}
