package Model;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class PersistentaFlorarie implements RaportGeneratorJSON, RaportGeneratorCSV {
    private List<Florarie> listaFlorarie;
    private List<Floare> listaflori;

    public PersistentaFlorarie() {
        this.listaFlorarie = new ArrayList<>();
        this.listaflori = new ArrayList<>();
    }

    public static Document getDocumentElements() {
        Document doc = null;
        try {

            File file = new File("src/main/java/florarie.xml");
            DocumentBuilderFactory docBuildF = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuild = docBuildF.newDocumentBuilder();
            doc = docBuild.parse(file);

        } catch (Exception e) {
            e.printStackTrace();
            doc = null;
        }
        return doc;
    }

    public static void saveXmlContent(Document doc) {
        String path = "src/main/java/florarie.xml";

        try {
            TransformerFactory trnsFact = TransformerFactory.newInstance();
            Transformer trans = trnsFact.newTransformer();
            trans.setOutputProperty(OutputKeys.ENCODING, "yes");
            DOMSource ds = new DOMSource(doc);
            StreamResult sr = new StreamResult(path);
            trans.transform(ds, sr);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static List<Florarie> readXmlFile() {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");
        String numeFlorarie = doc.getDocumentElement().getFirstChild().getTextContent();

        List<Florarie> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();
                lista.add(floare);
                Florarie f = new Florarie(numeFlorarie, lista);
                flori.add(f);
            }
        }
        return flori;
    }

    public static List<Floare> readXmlFileFlowers() {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");
        String numeFlorarie = doc.getDocumentElement().getFirstChild().getTextContent();

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();
                lista.add(floare);

                flori.add(floare);
            }
        }
        return flori;
    }

    public static void addFlowerToFile(Floare florarie) {
        Document doc = getDocumentElements();
        Element flori = doc.getDocumentElement();
        Element floriCreate = doc.createElement("floare");

        Element numeFlorarie = doc.createElement("numeFlorarie");
        numeFlorarie.appendChild(doc.createTextNode("Depozit"));
        floriCreate.appendChild(numeFlorarie);

        Element flowerType = doc.createElement("flowerType");
        flowerType.appendChild(doc.createTextNode(florarie.getFlowerType()));
        floriCreate.appendChild(flowerType);

        Element DisponibilitateFloare = doc.createElement("disponibilitateFloare");
        DisponibilitateFloare.appendChild(doc.createTextNode(String.valueOf(florarie.getDisponibilitateFloare())));
        floriCreate.appendChild(DisponibilitateFloare);

        Element nrFlori = doc.createElement("nrFlori");
        nrFlori.appendChild(doc.createTextNode(String.valueOf(florarie.getNrFlori())));
        floriCreate.appendChild(nrFlori);

        Element culoareFloare = doc.createElement("culoareFloare");
        culoareFloare.appendChild(doc.createTextNode(String.valueOf(florarie.getCuloareFloare())));
        floriCreate.appendChild(culoareFloare);

        Element pretFloare = doc.createElement("pretFloare");
        pretFloare.appendChild(doc.createTextNode(String.valueOf(florarie.getPretFloare())));
        floriCreate.appendChild(pretFloare);

        flori.appendChild(floriCreate);
        saveXmlContent(doc);
    }

    public static void deleteFlowerFromFile(Floare florarie) {
        Document doc = getDocumentElements();
        NodeList lista = doc.getElementsByTagName("floare");
        for (int i = 0; i < lista.getLength(); i++) {
            Element elementCurent = (Element) lista.item(i);
            if (elementCurent.getElementsByTagName("flowerType").item(0).getTextContent().equals(florarie.getFlowerType())) {
                elementCurent.getParentNode().removeChild(elementCurent);
            }
        }

        saveXmlContent(doc);
    }

    public boolean existaFloare(Floare florarie) {
        Document doc = getDocumentElements();
        NodeList lista = doc.getElementsByTagName("floare");
        for (int i = 0; i < lista.getLength(); i++) {
            Element elementCurent = (Element) lista.item(i);
            if (elementCurent.getElementsByTagName("flowerType").item(0).getTextContent().equals(florarie.getFlowerType())) {
                return true;
            }
        }
        return false;
    }

    public static List<Floare> cautareFlorarie(String nume) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (numeFlorarie.equals(nume)) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public static List<Floare> cautareDisponibilitate(Boolean disp) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (disponibilitateFloare == disp) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public static List<Floare> cautareDupaPret(Float pmin, Float pmax) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (pretFloare <= pmax && pretFloare >= pmin) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public static List<Floare> cautareDupaCuloare(String culoare) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (culoareFloare.equals(culoare)) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public static List<Floare> cautareDupaCantitate(Integer cant) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (nrFlori.equals(cant)) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public static List<Floare> cautareDupaDenumire1(String denume) {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList nodeList = doc.getElementsByTagName("floare");

        List<Floare> flori = new ArrayList<>();
        for (int i = 0; i < nodeList.getLength(); i++) {
            Node node = nodeList.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String numeFlorarie = eElement.getElementsByTagName("numeFlorarie").item(0).getTextContent();
                String flowerType = eElement.getElementsByTagName("flowerType").item(0).getTextContent();
                Boolean disponibilitateFloare = Boolean.parseBoolean(eElement.getElementsByTagName("disponibilitateFloare").item(0).getTextContent());
                Integer nrFlori = Integer.parseInt(eElement.getElementsByTagName("nrFlori").item(0).getTextContent());
                String culoareFloare = eElement.getElementsByTagName("culoareFloare").item(0).getTextContent();
                Float pretFloare = Float.parseFloat(eElement.getElementsByTagName("pretFloare").item(0).getTextContent());
                Floare floare = new Floare(pretFloare, culoareFloare, nrFlori, disponibilitateFloare, flowerType);
                ArrayList<Floare> lista = new ArrayList<>();

                if (flowerType.equals(denume)) {
                    System.out.println("checked");
                    lista.add(floare);
                    flori.add(floare);
                }

            }
        }
        return flori;
    }

    public void addFlower(Floare f) {
        if (!existaFloare(f)) {
            addFlowerToFile(f);
            listaflori.add(f);

        }

    }

    public void updateFlower(Floare f) {
        if (existaFloare(f)) {
            updateFlower(f);
            for (int i = 0; i < listaflori.size(); i++) {
                if (listaflori.get(i).equals(f)) {
                    listaflori.set(i, f);
                }
            }

        }

    }

    public void deleteFlower(Floare f) {
        if (existaFloare(f)) {
            deleteFlowerFromFile(f);
            listaflori.remove(f);
        }
    }

    public List<Florarie> getListaFlorarie() {
        return listaFlorarie;
    }

    public List<Floare> getListaflori() {
        return listaflori;
    }

}
