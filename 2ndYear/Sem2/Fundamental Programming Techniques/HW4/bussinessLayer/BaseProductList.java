package bussinessLayer;

import java.util.ArrayList;
import java.io.*;
import java.util.List;

public class BaseProductList {
    private List<BaseProduct> list;

    public BaseProductList(List<BaseProduct> products) {
        list = products;
    }

    public void add(BaseProduct sb) {
        list.add(sb);
    }

    public void readFromCSV(String filename) {
        File file = new File(filename);
        FileReader reader = null;
        try {
            reader = new FileReader(file);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.exit(1);
        }
        BufferedReader infile = new BufferedReader(reader);
        String line = "";
        try {
            boolean done = false;
            while (!done) {
                line = infile.readLine();
                if (line == null) {
                    done = true;
                } else {
                    String[] tokens = line.trim().split(",");
                    String title = tokens[0].trim();
                    String rating = tokens[1].trim();
                    String calories = tokens[2].trim();
                    String protein = tokens[3].trim();
                    String fat = tokens[4].trim();
                    String sodium = tokens[5].trim();
                    String price = tokens[6].trim();
                    BaseProduct sb = new BaseProduct(title, Float.parseFloat(rating), Float.parseFloat(calories), Float.parseFloat(protein),
                            Float.parseFloat(fat), Float.parseFloat(sodium), Float.parseFloat(price));
                    list.add(sb);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
            System.exit(1);
        }
    }

    public Object[][] convert2Data() {
        Object[][] data = new Object[list.size()][7];
        for (int i = 0; i < list.size(); i++) {
            data[i][0] = list.get(i).getTitle();
            data[i][1] = list.get(i).getRating();
            data[i][2] = list.get(i).getCalories();
            data[i][3] = list.get(i).getProteins();
            data[i][4] = list.get(i).getFats();
            data[i][5] = list.get(i).getSodium();
            data[i][6] = list.get(i).getPrice();
        }
        return data;
    }


}