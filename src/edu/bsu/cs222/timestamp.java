package edu.bsu.cs222;

import java.text.SimpleDateFormat;
import java.text.DateFormat;
import java.util.Date;

@SuppressWarnings("unused")
public class timestamp {
    public static void main(String[] args) {
        //This provides a string containing the current time and date
        DateFormat currentDateAndTime = new SimpleDateFormat("hh:mm aa dd/MM/yyyy");
        String currentDateAndTimeString  = currentDateAndTime.format(new Date());
        System.out.println(currentDateAndTimeString);
    }
}