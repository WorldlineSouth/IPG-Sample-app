package com.worldline.in.worldlineipgsdk;

import android.content.Context;
import android.content.DialogInterface;


import androidx.appcompat.app.AlertDialog;

import java.util.Random;

/**
 * Created by sunny.birman on 22-09-2017.
 */

public class Utility {


    public static int getRandomOrderId()
    {
        Random random = new Random();
        int randomOrderId = random.nextInt();
        if(randomOrderId<0)
        {
            randomOrderId = randomOrderId*-1;
        }
        return randomOrderId;
    }


    public static void showAlertDialog(Context context, String msg){

        new AlertDialog.Builder(context)
                .setTitle("Ipg Response")
                .setMessage(msg)
                .setPositiveButton("Ok", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {

                    }
                }).show();

    }
}
