package com;

import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class XMLParser {

    public String input;
    public List<String> xmlParser(){
        List<String> res=new ArrayList<String>();
        try {
            File inputFile = new File("maps.xml");
            DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.parse(inputFile);
            doc.getDocumentElement().normalize();
            System.out.print("Root element: ");
            System.out.println(doc.getDocumentElement().getNodeName());
            NodeList nList = doc.getElementsByTagName(input);
            System.out.println("----------------------------");

            for (int temp = 0; temp < nList.getLength(); temp++) {
                Node nNode = nList.item(temp);
                System.out.println("\nCurrent Element Inside XMLParser :");
                System.out.print(nNode.getNodeName()+"\n");
                if (nNode.getNodeType() == Node.ELEMENT_NODE) {
                    Element eElement = (Element) nNode;
                    NodeList itemList = eElement.getElementsByTagName("item");

                    for (int count = 0; count < itemList.getLength(); count++) {
                        Node node1 = itemList.item(count);

                        if (node1.getNodeType() == node1.ELEMENT_NODE) {
                            Element item = (Element) node1;
                            System.out.print("item name : ");
                            System.out.println(item.getTextContent());
                            res.add(item.getTextContent());
                        }
                    }
                }
            }

        System.out.println("------------------------");
        }


        catch (Exception e) {
            e.printStackTrace();
        }

        return res;
    }


 /*   public static void main(String[] args) {

List<String> m;
    m=xmlParser();
        for (String res:
             m) {
            System.out.println(res);
        }

    }*/
}
