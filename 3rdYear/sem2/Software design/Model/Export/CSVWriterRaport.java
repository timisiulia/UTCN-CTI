package Model.Export;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

import Model.Floare;
import com.opencsv.CSVWriter;

public interface CSVWriterRaport {
    default void exportToCSV(List<Floare> flori) throws IOException {
        CSVWriter writer;
        writer =  new CSVWriter(new FileWriter("flori.csv"));

        String line1[] = {"pret", "culoare", "nrFlori", "disponibilitate", "tip"};
        writer.writeNext(line1);
        for (Floare f : flori){

            String pret = Float.toString(f.getPretFloare());
            String culoare = f.getCuloareFloare() ;
            String nrFlori = Integer.toString(f.getNrFlori());
            String disponibilitate = f.getDisponibilitateFloare() ;
            String tip =f.getFlowerType() ;

            String line[]={pret,culoare,nrFlori,disponibilitate,tip};
            writer.writeNext(line);
        }

        try {
            writer.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        System.out.println("Exported to csv!");
    }
}
