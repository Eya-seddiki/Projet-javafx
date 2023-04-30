/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import com.itextpdf.text.BadElementException;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Image;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.SQLException;
import model.Demande;
import model.Offre;

/**
 *
 * @author dell
 */
public class PDFGenerator {
    Demande p = new Demande();
    Offre  f =new Offre();
OffreServices ps = new OffreServices();
UserServices u = new UserServices();
	public void GeneratePdf(String filename, Demande p) throws FileNotFoundException, DocumentException,
			BadElementException, IOException, InterruptedException, SQLException {

		Document document = new Document() {
		};
		PdfWriter.getInstance(document, new FileOutputStream(filename + ".pdf"));
		document.open();

Image img = Image.getInstance("C:\\Users\\dell\\Desktop\\ado_doc java\\pidevjava\\logo2.png");
                           img.scaleAbsoluteHeight(100);
       img.scaleAbsoluteWidth(150);
       img.setAlignment(Image.ALIGN_RIGHT);
       document.add(img);
		document.add(new Paragraph("mail du candidats :" + u.finduser(p.getId_user()).getEmail()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph(
				"----------------------------------------------------------------------------------------------------------------------"));

		document.add(new Paragraph("nom de l'offre choisit  :" + ps.findoffre(p.getId_offre()).getNom_offre()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph("description  :" + p.getDescription()));
		document.add(new Paragraph("                      "));
		document.add(new Paragraph("traitement :" + p.getTraitement()));
		

		document.add(new Paragraph(
				"---------------------------------------------------------------------------------------------------------------------------------- "));
		document.add(new Paragraph("                              Welcome to ado-doc                  "));

		document.close();
		Process pro = Runtime.getRuntime().exec("rundll32 url.dll,FileProtocolHandler " + filename + ".pdf");
	}
    
}
