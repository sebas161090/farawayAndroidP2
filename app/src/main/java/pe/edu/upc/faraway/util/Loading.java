package pe.edu.upc.faraway.util;

import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.WindowManager;

import pe.edu.upc.faraway.R;

public class Loading {

    public static ProgressDialog init(Context context) {
        return new ProgressDialog(context);
    }

    public static ProgressDialog show(ProgressDialog dialog) {
        dialog.show();
        dialog.getWindow().setBackgroundDrawable(new ColorDrawable(android.graphics.Color.TRANSPARENT));
        dialog.getWindow().setFlags(WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE,
                WindowManager.LayoutParams.FLAG_NOT_TOUCHABLE);
        dialog.setContentView(R.layout.layout_loading);
        return dialog;
    }

    public static void close(ProgressDialog dialog) {
        dialog.dismiss();
    }

}