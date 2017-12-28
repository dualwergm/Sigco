package com.dg.sigco.common;

import android.animation.ObjectAnimator;
import android.app.Activity;
import android.content.Context;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.view.View;
import android.view.animation.DecelerateInterpolator;
import android.widget.RelativeLayout;

import com.dg.sigco.R;

/**
 * Created by dualwer on 17/11/17.
 */

public class UtilView {

    public static void showAlertDialog(final Context context, String title, String msg, final boolean finish) {
        AlertDialog.Builder builder = new AlertDialog.Builder(context);
        builder.setTitle(title)
                .setMessage(msg)
                .setPositiveButton("Aceptar",
                        new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                dialog.cancel();
                                if(finish){
                                    ((Activity)context).finish();
                                }
                            }
                        });
        final AlertDialog alertDialog = builder.create();

        alertDialog.setOnShowListener( new DialogInterface.OnShowListener() {
              @Override
              public void onShow(DialogInterface arg0) {
                  alertDialog.getButton(AlertDialog.BUTTON_POSITIVE).setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
              }
        });
        alertDialog.show();
    }

    public static void enableProgress(final ObjectAnimator progressAnimator, final RelativeLayout progressLayout){
        progressAnimator.setDuration(15000);
        progressAnimator.setInterpolator(new DecelerateInterpolator());
        progressAnimator.start();
        progressLayout.setVisibility(View.VISIBLE);
    }

    public static void disableProgress(final ObjectAnimator progressAnimator, final RelativeLayout progressLayout){
        progressAnimator.cancel();
        progressLayout.setVisibility(View.GONE);
    }
}
