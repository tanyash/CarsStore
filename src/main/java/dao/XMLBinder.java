package dao;

import store.Car;
import store.Order;
import store.User;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Marshaller;
import javax.xml.bind.Unmarshaller;
import java.io.*;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/**
 * Created by tanya on 3/9/14.
 */
public class XMLBinder {
    private static String dirPath;
    private final static String XML_NAME = "/store.xml";

    public static void setDirPath(String dirPath) {
        XMLBinder.dirPath = dirPath;
    }

    public static Object getDataFromXML(Class type){
        Object o = null;
        File xmlFile = new File(dirPath + XML_NAME);
        if (!(xmlFile.exists())){
            return o;
        }

        try {
            JAXBContext jc = JAXBContext.newInstance(type);
            Unmarshaller unmarshaller = jc.createUnmarshaller();
            InputStream stream = null;
            stream = new FileInputStream(xmlFile);
            o = unmarshaller.unmarshal(stream);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        return o;
    }

    public static void setDataToXML(Object obj){
        File xmlFile = new File(dirPath + XML_NAME);

        try {
            JAXBContext jc = JAXBContext.newInstance(obj.getClass());
            Marshaller m = jc.createMarshaller();

            m.setProperty(Marshaller.JAXB_FORMATTED_OUTPUT, Boolean.TRUE);
            OutputStream os = new FileOutputStream(xmlFile, false);
            m.marshal(obj, os);

        }
        catch (JAXBException e) {
            e.printStackTrace();
        }
        catch(FileNotFoundException e){
            e.printStackTrace();
        }

    }
}
