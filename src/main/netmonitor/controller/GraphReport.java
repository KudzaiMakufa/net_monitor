package main.netmonitor.controller;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.chart.CategoryAxis;
import javafx.scene.chart.LineChart;
import javafx.scene.chart.NumberAxis;
import javafx.scene.chart.XYChart;
import javafx.stage.Stage;
import main.netmonitor.encryption.Aes;
import main.netmonitor.model.Tables.Plan;

import java.net.URL;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.ResourceBundle;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.ThreadLocalRandom;
import java.util.concurrent.TimeUnit;

public class GraphReport implements Initializable {
    @FXML NumberAxis yAxis ;
    @FXML
    CategoryAxis xAxis ;
    @FXML public LineChart <String, Number> lineChart ;
    final int WINDOW_SIZE = 10;
    private ScheduledExecutorService scheduledExecutorService;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {

        //defining the axes
//        final CategoryAxis xAxis = new CategoryAxis(); // we are gonna plot against time
//        final NumberAxis yAxis = new NumberAxis();
        xAxis.setLabel("Time/s");
        xAxis.setAnimated(false); // axis animations are removed
        yAxis.setLabel("Loss in Usd ");
        yAxis.setAnimated(false); // axis animations are removed

        //creating the line chart with two axis created above
        //final LineChart<String, Number> lineChart = new LineChart<>(xAxis, yAxis);
        lineChart.setTitle("Graph Report ");
        lineChart.setAnimated(false); // disable animations

        //defining a series to display data
        XYChart.Series<String, Number> series = new XYChart.Series<>();
        series.setName("Actual");

        XYChart.Series<String, Number> series1 = new XYChart.Series<>();
        series1.setName("Expected");

        // add series to chart
        lineChart.getData().add(series);
        lineChart.getData().add(series1);

        // this is used to display time in HH:mm:ss format
        final SimpleDateFormat simpleDateFormat = new SimpleDateFormat("HH:mm:ss");

        // setup a scheduled executor to periodically put data into the chart
        scheduledExecutorService = Executors.newSingleThreadScheduledExecutor();
        final int[] count = {1};
        // put dummy data onto graph per second
        scheduledExecutorService.scheduleAtFixedRate(() -> {
            // get a random integer between 0-10
            Integer random = ThreadLocalRandom.current().nextInt(10);

            // Update the chart
            Platform.runLater(() -> {
                // get current time
                Date now = new Date();

                Connection c = null;
                Statement stmt = null;


                try {

                    Class.forName("org.sqlite.JDBC");
                    c = DriverManager.getConnection("jdbc:sqlite:netmonitor.db");
                    c.setAutoCommit(false);
                    // System.out.println("Opened database successfully");
                    stmt = c.createStatement();


                    ResultSet rs = stmt.executeQuery( "SELECT * FROM Reports WHERE  ID='"+ count[0] +"';");
                    Plan plan = new Plan();
                    String speed = plan.GetSpeed();
                    count[0]++ ;
                    series.getData().add(new XYChart.Data<>(simpleDateFormat.format(now), rs.getInt("loss")));
                    series1.getData().add(new XYChart.Data<>(simpleDateFormat.format(now),5));
                    if (series1.getData().size() > WINDOW_SIZE)
                        series1.getData().remove(0);
                    if (series.getData().size() > WINDOW_SIZE)
                        series.getData().remove(0);

                    rs.close();
                    stmt.close();
                    c.close();
                } catch ( Exception e ) {
                    System.err.println( e.getClass().getName() + ": " + e.getMessage() );
                    System.exit(0);
                }

                // put random number with current time


            });
        }, 0, 1, TimeUnit.SECONDS);
    }


}