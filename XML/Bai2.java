package XML;

import java.io.*;
import java.util.ArrayList;
import javax.xml.parsers.*;
import org.w3c.dom.*;
import javax.xml.transform.*;
import javax.xml.transform.dom.*;
import javax.xml.transform.stream.*;

public class Bai2 {
    public static void main(String[] args) {
        ArrayList<SinhVien> danhSachSinhVien = new ArrayList<>();
        danhSachSinhVien.add(new SinhVien("Nuyen Van A", 20, 3.8));
        danhSachSinhVien.add(new SinhVien("Tran B", 21, 3.5));
        danhSachSinhVien.add(new SinhVien("Le C", 19, 3.2));

        try {
            DocumentBuilderFactory docFactory = DocumentBuilderFactory.newInstance();
            DocumentBuilder docBuilder = docFactory.newDocumentBuilder();

            Document doc = docBuilder.newDocument();
            Element rootElement = doc.createElement("DanhSachSinhVien");
            doc.appendChild(rootElement);

            for (SinhVien sinhVien : danhSachSinhVien) {
                Element sinhVienElement = doc.createElement("SinhVien");
                rootElement.appendChild(sinhVienElement);

                Element tenElement = doc.createElement("Ten");
                tenElement.appendChild(doc.createTextNode(sinhVien.getTen()));
                sinhVienElement.appendChild(tenElement);

                Element tuoiElement = doc.createElement("Tuoi");
                tuoiElement.appendChild(doc.createTextNode(String.valueOf(sinhVien.getTuoi())));
                sinhVienElement.appendChild(tuoiElement);

                Element gpaElement = doc.createElement("GPA");
                gpaElement.appendChild(doc.createTextNode(String.valueOf(sinhVien.getGpa())));
                sinhVienElement.appendChild(gpaElement);
            }

            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            StringWriter writer = new StringWriter();
            transformer.transform(new DOMSource(doc), new StreamResult(writer));

            String xmlString = writer.getBuffer().toString();
            System.out.println(xmlString);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

class SinhVien {
    private String ten;
    private int tuoi;
    private double gpa;

    public SinhVien(String ten, int tuoi, double gpa) {
        this.ten = ten;
        this.tuoi = tuoi;
        this.gpa = gpa;
    }

    public String getTen() {
        return ten;
    }

    public int getTuoi() {
        return tuoi;
    }

    public double getGpa() {
        return gpa;
    }
}