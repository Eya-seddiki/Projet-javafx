/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package gui;

import java.net.URL;
import java.util.ResourceBundle;
import javafx.embed.swing.SwingNode;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.data.general.DefaultPieDataset;
import javax.swing.*;
import java.awt.*;
import java.text.NumberFormat;
import java.util.Map;
import org.jfree.chart.labels.PieSectionLabelGenerator;
import org.jfree.chart.labels.StandardPieSectionLabelGenerator;
import org.jfree.chart.plot.PiePlot;
import service.RessourceService;
/**
 * FXML Controller class
 *
 * @author Narimen
 */
public class StatistiquesController implements Initializable {
    
    
@FXML
    private SwingNode chart1;
    RessourceService rs = new RessourceService();
    @FXML
    private SwingNode chart2;
    /**
     * Initializes the controller class.
     */
    @Override
    public void initialize(URL url, ResourceBundle rb) {
        // TODO
        Map<String, Integer> res1 = rs.statistiquesRessource();
        DefaultPieDataset data1 = new DefaultPieDataset();
        DefaultPieDataset data2 = new DefaultPieDataset();
        res1.entrySet().forEach((i) -> {
            data1.setValue(i.getKey(), i.getValue());
        });
        JFreeChart graph1 = ChartFactory.createPieChart(
                "Repartition Ressource",
                data1,
                true,
                true,
                false
        );
        
        PieSectionLabelGenerator labelGenerator = new StandardPieSectionLabelGenerator("{0} ({2})", NumberFormat.getNumberInstance(), NumberFormat.getPercentInstance());
        ((PiePlot) graph1.getPlot()).setLabelGenerator(labelGenerator);
        ChartPanel chartPanel1 = new ChartPanel(graph1);
        chartPanel1.setPreferredSize(new Dimension(300, 300));
        chart1.setContent(chartPanel1);
    }

        
    }    
    

