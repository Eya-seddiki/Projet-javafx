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
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.sql.SQLException;
import javax.servlet.ServletException;
import model.Demande;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author dell
 */
public class cv extends HttpServlet{
     public void doGet(String filePath, HttpServletResponse response) throws ServletException, IOException {
      //  String filePath = request.getParameter("filePath"); // récupère le chemin du fichier à télécharger
        File file = new File(filePath); // crée un objet File à partir du chemin du fichier
        byte[] content = Files.readAllBytes(Paths.get(file.getAbsolutePath())); // lit le contenu du fichier dans un tableau de bytes
        
        response.setContentType("application/octet-stream"); // définit le type de contenu de la réponse à binaire
        response.setHeader("Content-Disposition", "attachment; filename=\"" + file.getName() + "\""); // définit le nom du fichier à télécharger
        response.getOutputStream().write(content); // envoie le contenu du fichier en tant que réponse HTTP
        response.getOutputStream().flush();
        response.getOutputStream().close();
    }
}
