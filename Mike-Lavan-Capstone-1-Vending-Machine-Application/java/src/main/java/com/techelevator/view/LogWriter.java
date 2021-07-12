package com.techelevator.view;


import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.PrintWriter;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalTime;

public class LogWriter {
    public void writer(String typeOfTransaction, BigDecimal amounts, BigDecimal balance) throws FileNotFoundException {

        LocalDate today = LocalDate.now();
        LocalTime now = LocalTime.now();
        try (PrintWriter logWriter = new PrintWriter(new FileOutputStream(new File("log.txt"), true))) {


            String printToday = today.toString();
            String printTime = now.toString().substring(0, now.toString().length() - 4);
            String printTypeOfTransaction = typeOfTransaction.toString();
            String printAmount = amounts.toString();
            String printBalance = balance.toString();

            logWriter.println(printToday + " " + printTime + " " + String.format("%-25s", printTypeOfTransaction)
                    + String.format("%-10s", "$" + printAmount) + String.format("%-10s", "$" + printBalance));


//// do we need this?//
        } catch (FileNotFoundException e) {
            e.getMessage();
        }
    }
}
