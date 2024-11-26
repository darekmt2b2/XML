import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.io.IOException;

public class XMLread {

    public static void read(){
        //asaptador
        //operaciones con fixero
        //operaciones con contenido

        ///nodelist = lista elementos xml. (nodo = etiquietas)
        try{
            File file = new File("xml/xmlJava");
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);

            NodeList nodeList = doc.getElementsByTagName("persona");
            for (int i = 0;i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    int id = Integer.parseInt(element.getElementsByTagName("id").item(0).getTextContent());
                    String nombre =element.getElementsByTagName("nombre").item(0).getTextContent();
                    String ciudad =element.getElementsByTagName("ciudad").item(0).getTextContent();

                    System.out.println(id+","+nombre+","+ciudad);
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }
    public static void readTrainings(){
        //asaptador
        //operaciones con fichero
        //operaciones con contenido

        ///nodelist = lista elementos xml. (nodo = etiquietas)
        try{
            File file = new File("xml/entrenamientos"); //receives the XML file path
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance(); //adapts the file to a format readable by Java
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file); //gets content

            int id;
            String nombre;
            int duracion;
            String nivel;

            NodeList nodeList = doc.getElementsByTagName("entrenamiento");
            for (int i = 0;i < nodeList.getLength(); i++){
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE){
                    Element element = (Element) node;

                    id = Integer.parseInt(element.getAttribute("id"));
                    nombre =element.getElementsByTagName("nombre").item(0).getTextContent();
                    duracion =Integer.parseInt(element.getElementsByTagName("duracion").item(0).getTextContent());
                    nivel =element.getElementsByTagName("nombre").item(0).getTextContent();

                    System.out.println(id+","+nombre+","+duracion+","+nivel);
                }
            }

        } catch (ParserConfigurationException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SAXException e) {
            throw new RuntimeException(e);
        }

    }

    public static void createHangar() {

        try {
            //Create a new Document Builder
            DocumentBuilderFactory documentFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder documentBuilder = documentFactory.newDocumentBuilder();

            //Create a new doc (root element)
            Document document = documentBuilder.newDocument();

            //Create the root element <hangars>
            Element root = document.createElement("hangars");
            document.appendChild(root);

            // create the element
            Element hangar1 = document.createElement("hangar");
            hangar1.setAttribute("id", "1");  // Set the attribute "id" for the hangar
            root.appendChild(hangar1);

            // create the child elements
            Element plane1 = document.createElement("plane");
            plane1.setAttribute("model", "Boeing 737");
            plane1.setAttribute("capacity", "200");
            plane1.setAttribute("year", "2015");
            hangar1.appendChild(plane1);

            Element plane2 = document.createElement("plane");
            plane2.setAttribute("model", "Airbus A320");
            plane2.setAttribute("capacity", "180");
            plane2.setAttribute("year", "2018");
            hangar1.appendChild(plane2);

        //export
            // writes the document to a file
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            // Output the XML content to a file
            DOMSource source = new DOMSource(document);
            StreamResult result = new StreamResult(new File("xml/planes"));
            transformer.transform(source, result);
        //export

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    //usar ajax
}
