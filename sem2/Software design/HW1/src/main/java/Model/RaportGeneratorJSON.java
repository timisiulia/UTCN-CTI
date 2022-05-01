package Model;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public interface RaportGeneratorJSON {
    default void exportToJSON(List<Floare> flori){
        JSONArray floriList = new JSONArray();
        for (Floare f :flori){
            String pret = Float.toString(f.getPretFloare());
            String culoare = f.getCuloareFloare() ;
            String nrFlori = Integer.toString(f.getNrFlori());
            String disponibilitate =Boolean.toString(f.getDisponibilitateFloare()) ;
            String tip =f.getFlowerType() ;

            JSONObject utilizatorDetails = new JSONObject();
            utilizatorDetails.put("pret", pret);
            utilizatorDetails.put("culoare", culoare);
            utilizatorDetails.put("nrFlori", nrFlori);
            utilizatorDetails.put("disponibilitate", disponibilitate);
            utilizatorDetails.put("tip", tip);

            JSONObject utilizatorObject = new JSONObject();
            utilizatorObject.put("floare", utilizatorDetails);

            floriList.add(utilizatorObject);

        }
        //Write JSON file
        try (FileWriter file = new FileWriter("flori.json")) {
            //We can write any JSONArray or JSONObject instance to the file
            file.write(floriList.toJSONString());
            file.flush();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
