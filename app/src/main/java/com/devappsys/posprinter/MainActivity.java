package com.devappsys.posprinter;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;

import com.cloudpos.DeviceException;
import com.cloudpos.POSTerminal;
import com.cloudpos.printer.PrinterDevice;



public class MainActivity extends AppCompatActivity {
    private PrinterDevice device = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Button btnPRint = findViewById(R.id.btnPrint);
        btnPRint.setOnClickListener(view -> {
            openPrinterDevice();
            printData();
            closePrinter();
        });

    }
    private void openPrinterDevice(){
        //Getting the POS printerDevice object reference
        if (device == null) {
            device = (PrinterDevice) POSTerminal.getInstance(this)
                    .getDevice("cloudpos.device.printer");
        }

        //Opening the Printer device for printing,
        try {
            device.open();
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }


    private void printData() {
        //Once the device is open we can send our text to print
        try {
            device.printText(
                    "\n---DevAppSys---\n" +
                            "MERCHANT COPY\n" +
                            "" +
                            "MERCHANT NAME\n" +
                            "SHXXXXXXCo.,LTD.\n" +
                            "530310041315039\n" +
                            "TERMINAL NO\n" +
                            "50000045" +
                            "OPERATOR\n" +
                            "50000045" +
                            "\n" +
                            "\n");

        } catch (DeviceException e) {
            e.printStackTrace();

        }
    }

    private void closePrinter(){
        //close the device after printing
        try{
            device.close();
        } catch (DeviceException e) {
            e.printStackTrace();
        }
    }

}

