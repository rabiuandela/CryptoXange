package utils;


import android.app.ProgressDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.graphics.Typeface;
import android.support.v7.app.AlertDialog;
import android.text.Spannable;
import android.text.SpannableString;
import android.widget.TextView;
import android.widget.Toast;

/**
 * Created by rabiu on 24/10/2017.
 */

public class Util {

    static ProgressDialog pDialog;
    static AlertDialog dialog;


    public static void showLoadingDialog(Context mContext, String message) {
        pDialog = new ProgressDialog(mContext);
        pDialog.setMessage(message);
        pDialog.setCancelable(false);
        pDialog.show();
    }
    public static void hideLoadingDialog() {
        if(pDialog.isShowing()){
            pDialog.dismiss();
        }
    }

    public static void DialogBox(Context mContext, String title, String message) {
        android.app.AlertDialog.Builder alertDialogBuilder = new android.app.AlertDialog.Builder(mContext);
        alertDialogBuilder.setTitle(title);
        alertDialogBuilder
                .setMessage(message)
                .setCancelable(false)
                .setNegativeButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {

                        dialog.cancel();
                    }
                });

        android.app.AlertDialog alertDialog = alertDialogBuilder.create();
        alertDialog.show();
    }

    public static void showToastL(Context mContext, String message) {

        Toast.makeText(mContext, message, Toast.LENGTH_LONG).show();

    }
}
