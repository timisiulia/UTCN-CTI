package Model;

public class Floare {
    private Float pretFloare;
    private String culoareFloare;
    private Integer nrFlori;
    private Boolean disponibilitateFloare;
    private String flowerType;

    public Floare(Float pretFloare, String culoareFloare, Integer nrFlori, Boolean disponibilitateFloare, String flowerType) {
        this.pretFloare = pretFloare;
        this.culoareFloare = culoareFloare;
        this.nrFlori = nrFlori;
        this.disponibilitateFloare = disponibilitateFloare;
        this.flowerType = flowerType;
    }


    public Float getPretFloare() {
        return pretFloare;
    }

    public void setPretFloare(Float pretFloare) {
        this.pretFloare = pretFloare;
    }

    public String getCuloareFloare() {
        return culoareFloare;
    }

    public void setCuloareFloare(String culoareFloare) {
        this.culoareFloare = culoareFloare;
    }

    public Integer getNrFlori() {
        return nrFlori;
    }

    public void setNrFlori(Integer nrFlori) {
        this.nrFlori = nrFlori;
    }

    public Boolean getDisponibilitateFloare() {
        return disponibilitateFloare;
    }

    public void setDisponibilitateFloare(Boolean disponibilitateFloare) {
        this.disponibilitateFloare = disponibilitateFloare;
    }

    public String getFlowerType() {
        return flowerType;
    }

    public void setFlowerType(String flowerType) {
        this.flowerType = flowerType;
    }

    @Override
    public String toString() {
        return "CaracteristiciFloare{" +
                "PretFloare='" + pretFloare + '\'' +
                ", CuloareFloare=" + culoareFloare +
                ", numarFlori=" + nrFlori +
                ", disponibilitateFlori=" + disponibilitateFloare +
                ", TipulFlorii=" + flowerType + '}';
    }
}
