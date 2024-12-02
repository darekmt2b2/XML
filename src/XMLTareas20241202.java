import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;

public class XMLTareas20241202 {
    private final String filePath;

    // Constructor que recibe la ruta del archivo XML
    public EntrenamientoDAO_sostyle(String filePath) {

        this.filePath = filePath;
    }

    // Método para leer todos los entrenamientos del XML
    public List<Entrenamiento> leerTodos() {
        List<Entrenamiento> entrenamientos = new ArrayList<>();

        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;
                    int id = Integer.parseInt(elemento.getAttribute("id"));
                    String nombre = elemento.getElementsByTagName("nombre").item(0).getTextContent();
                    int duracion = Integer.parseInt(elemento.getElementsByTagName("duracion").item(0).getTextContent());
                    String nivel = elemento.getElementsByTagName("nivel").item(0).getTextContent();
                    entrenamientos.add(new Entrenamiento(id, nombre, duracion, nivel));
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

        return entrenamientos;
    }

    // Método para agregar un nuevo entrenamiento al XML
    public void agregar(Entrenamiento entrenamiento) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            Element root = doc.getDocumentElement();

// Crear un nuevo elemento "entrenamiento"
            Element nuevoEntrenamiento = doc.createElement("entrenamiento");
            nuevoEntrenamiento.setAttribute("id", String.valueOf(entrenamiento.getId()));

            Element nombre = doc.createElement("nombre");
            nombre.setTextContent(entrenamiento.getNombre());

            Element duracion = doc.createElement("duracion");
            duracion.setTextContent(String.valueOf(entrenamiento.getDuracion()));

            Element nivel = doc.createElement("nivel");
            nivel.setTextContent(entrenamiento.getNivel());

            nuevoEntrenamiento.appendChild(nombre);
            nuevoEntrenamiento.appendChild(duracion);
            nuevoEntrenamiento.appendChild(nivel);

            root.appendChild(nuevoEntrenamiento);

// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para actualizar un entrenamiento existente
    public void actualizar(int id, Entrenamiento nuevoEntrenamiento) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    if (Integer.parseInt(elemento.getAttribute("id")) == id) {
                        elemento.getElementsByTagName("nombre").item(0).setTextContent(nuevoEntrenamiento.getNombre());
                        elemento.getElementsByTagName("duracion").item(0).setTextContent(String.valueOf(nuevoEntrenamiento.getDuracion()));
                        elemento.getElementsByTagName("nivel").item(0).setTextContent(nuevoEntrenamiento.getNivel());
                    }
                }
            }

// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    // Método para eliminar un entrenamiento por ID
    public void eliminar(int id) {
        try {
            File file = new File(filePath);
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");

            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);

                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element elemento = (Element) node;

                    if (Integer.parseInt(elemento.getAttribute("id")) == id) {
                        elemento.getParentNode().removeChild(elemento);
                        break;
                    }
                }
            }

// Guardar los cambios en el archivo XML
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(file);
            transformer.transform(source, result);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


//Individual reading method
public static NodeList getXML(String path){

    NodeList nodeList = null;

    try {
        File file = new File(path);
        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        DocumentBuilder builder = factory.newDocumentBuilder();
        Document doc = builder.parse(file);

        nodeList = doc.getElementsByTagName("entrenamiento");
    }catch (Exception e) {
        e.printStackTrace();
    }
    return nodeList;
}
