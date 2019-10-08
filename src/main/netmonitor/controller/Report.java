package main.netmonitor.controller;

import fr.bmartel.speedtest.SpeedTestReport;
import fr.bmartel.speedtest.SpeedTestSocket;
import fr.bmartel.speedtest.inter.ISpeedTestListener;
import fr.bmartel.speedtest.model.SpeedTestError;
import javafx.application.Platform;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.chart.XYChart;
import javafx.scene.control.Label;
import main.netmonitor.model.Tables.Plan;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Report {
    @FXML Label txtexpected ;
    @FXML Label txtactual ;


    public void Calculate(ActionEvent event){
        //get expected from database
        Plan plan = new Plan();
        String speed = plan.GetSpeed();
        String price = plan.GetPrice();

        //JSPEEDTEST HERE
        SpeedTestSocket speedTestSocket = new SpeedTestSocket();


// add a listener to wait for speedtest completion and progress
        speedTestSocket.addSpeedTestListener(new ISpeedTestListener() {



            @Override
            public void onCompletion(SpeedTestReport report) {
                // called when download/upload is complete
                System.out.println("[COMPLETED] rate in octet/s : " + report.getTransferRateOctet());
                System.out.println("[COMPLETED] rate in bit/s   : " + report.getTransferRateBit());
            }

            @Override
            public void onError(SpeedTestError speedTestError, String errorMessage) {
                // called when a download/upload error occur
            }

            @Override
            public void onProgress(float percent, SpeedTestReport report) {
                Platform.runLater(new Runnable() {
                    @Override
                    public void run() {
//                        1 =  1048576;
//                        13 *1 = bits
//
//                       System.out.println(report.getTransferRateBit() - BigDecimal.valueOf(13));
                        txtactual.setText(report.getTransferRateBit()+" b/s");
                    }
                });

                // called to notify download/upload progress
                System.out.println("[PROGRESS] progress : " + percent + "%");
                System.out.println("[PROGRESS] rate in octet/s : " + report.getTransferRateOctet());
                System.out.println("[PROGRESS] rate in bit/s   : " + report.getTransferRateBit());
            }
        });
        speedTestSocket.startDownload("http://ipv4.ikoula.testdebit.info/1M.iso", 1500);

        txtexpected.setText(speed+" mb/s");
        //calculating variance

        System.out.println(price);

    }
}
