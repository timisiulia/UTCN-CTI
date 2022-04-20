package dataLayer;

import bussinessLayer.MenuItem;
import bussinessLayer.Order;


import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import java.io.FileNotFoundException;
import java.io.FileOutputStream;


import java.util.List;

public class Bon {

    private static double computePrice(List<MenuItem> lista) {
        double price = 0.0;
        for (MenuItem b : lista) {
            price += b.getPrice();
        }
        return price;
    }

    public static String creareBon(Order o, List<MenuItem> lista) {
        StringBuilder build = new StringBuilder();
        try {

            Document document = new Document();

            PdfWriter.getInstance(document, new FileOutputStream("Bon" + o.getOrderID() + ".pdf"));
            document.open();


            build.append("Bonul pentru clientul cu id :" + o.getClientID() + "\n");
            build.append("Are urmatoarele produse comandate:\n");
            for (MenuItem b : lista) {
                build.append("Produsul: " + b.getTitle() + " cu pretul: " + b.getPrice() + "\n");
            }

            build.append("Cu totalul de plata: " + computePrice(lista) + "\n");
            build.append("Data: " + o.getOrderDate() + "\n");

            Paragraph paragraph = new Paragraph(build.toString());
            document.add(paragraph);
            document.close();

        } catch (DocumentException documentException) {
            documentException.printStackTrace();
        } catch (FileNotFoundException fileNotFoundException) {
            fileNotFoundException.printStackTrace();
        }

        return build.toString();
    }
}
