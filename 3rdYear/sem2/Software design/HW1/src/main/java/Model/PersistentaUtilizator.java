package Model;

import org.w3c.dom.Document;

import javax.xml.parsers.*;

import org.w3c.dom.*;

import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;
import java.util.ArrayList;
import java.util.List;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;

public class PersistentaUtilizator {
    private List<Utilizator> listaUtilizatori;

    public PersistentaUtilizator() {
        this.listaUtilizatori = new ArrayList<>();

    }

    public static Document getDocumentElements() {
        Document doc = null;
        try {
            File file = new File("src/main/java/utilizator.xml");
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
        String path = "src/main/java/utilizator.xml";

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

    public static List<Utilizator> readXmlFile() {
        Document doc = getDocumentElements();
        doc.getDocumentElement().normalize();
        NodeList lista = doc.getElementsByTagName("utilizator");
        List<Utilizator> users = new ArrayList<>();
        for (int i = 0; i < lista.getLength(); i++) {
            Node node = lista.item(i);
            if (node.getNodeType() == Node.ELEMENT_NODE) {
                Element eElement = (Element) node;
                String username = eElement.getElementsByTagName("username").item(0).getTextContent();
                String password = eElement.getElementsByTagName("password").item(0).getTextContent();
                String role = eElement.getElementsByTagName("role").item(0).getTextContent();
                Utilizator u = new Utilizator(username, password, role);
                users.add(u);
            }
        }
        return users;
    }

    public static void addUserToFile(Utilizator utilizator) {
        Document doc = getDocumentElements();
        Element users = doc.getDocumentElement();
        Element userCreate = doc.createElement("utilizator");

        Element username = doc.createElement("username");
        username.appendChild(doc.createTextNode(utilizator.getUsername()));
        userCreate.appendChild(username);

        Element password = doc.createElement("password");
        password.appendChild(doc.createTextNode(utilizator.getPassword()));
        userCreate.appendChild(password);

        Element role = doc.createElement("role");
        role.appendChild(doc.createTextNode(utilizator.getRole()));
        userCreate.appendChild(role);

        users.appendChild(userCreate);
        saveXmlContent(doc);
    }

    public static void deleteUserFromFile(Utilizator utilizator) {
        Document doc = getDocumentElements();
        NodeList lista = doc.getElementsByTagName("utilizator");
        for (int i = 0; i < lista.getLength(); i++) {
            Element elementCurent = (Element) lista.item(i);
            if (elementCurent.getElementsByTagName("username").item(0).getTextContent().equals(utilizator.getUsername())) {
                elementCurent.getParentNode().removeChild(elementCurent);
            }
        }

        saveXmlContent(doc);
    }

    public boolean existaUtilizator(Utilizator utilizator) {
        Document doc = getDocumentElements();
        NodeList lista = doc.getElementsByTagName("utilizator");
        for (int i = 0; i < lista.getLength(); i++) {
            Element elementCurent = (Element) lista.item(i);
            if (elementCurent.getElementsByTagName("username").item(0).getTextContent().equals(utilizator.getUsername())) {
                return true;
            }

        }
        return false;
    }

    public boolean existaUtilizatorComplet(Utilizator utilizator) {
        Document doc = getDocumentElements();
        NodeList lista = doc.getElementsByTagName("utilizator");
        for (int i = 0; i < lista.getLength(); i++) {
            Element elementCurent = (Element) lista.item(i);

            if (elementCurent.getElementsByTagName("username").item(0).getTextContent().equals(utilizator.getUsername())
                    && elementCurent.getElementsByTagName("password").item(0).getTextContent().equals(utilizator.getPassword())
                    && elementCurent.getElementsByTagName("role").item(0).getTextContent().equals(utilizator.getRole())) {

                return true;

            }
            //   return false;
        }
        return false;
    }

    public void addUser(Utilizator u) {

        if (!existaUtilizator(u)) {
            addUserToFile(u);

            listaUtilizatori.add(u);
            for (Utilizator uu : listaUtilizatori)
                System.out.println(uu);
        }
    }

    public void updateUser(Utilizator u) {
        if (existaUtilizator(u)) {
            updateUser(u);
            for (int i = 0; i < listaUtilizatori.size(); i++) {
                if (listaUtilizatori.get(i).equals(u)) {
                    listaUtilizatori.set(i, u);
                }
            }

        }

    }

    public void deleteUser(Utilizator u) {
        if (existaUtilizator(u)) {
            deleteUserFromFile(u);
            listaUtilizatori.remove(u);

        }

    }

    public Utilizator getUserByName(String nume) {
        for (Utilizator u : listaUtilizatori) {
            if (u.getUsername().equals(nume)) {
                return u;
            }
        }
        return null;
    }

    public Utilizator getUserByPass(String pass) {
        for (Utilizator u : listaUtilizatori) {
            if (u.getPassword().equals(pass)) {
                return u;
            }
        }
        return null;
    }

    public Utilizator getUserByRole(String rol) {
        for (Utilizator u : listaUtilizatori) {
            if (u.getRole().equals(rol)) {
                return u;
            }
        }
        return null;
    }

}

