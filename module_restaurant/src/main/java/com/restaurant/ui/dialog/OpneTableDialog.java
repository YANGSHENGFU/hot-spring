package com.restaurant.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.EditText;
import android.widget.TextView;

import com.restaurant.R;

public class OpneTableDialog extends Dialog{

    private Context mContext;
    private EditText numberEt;
    private TextView okTv;


    public OpneTableDialog(@NonNull Context context) {
        super(context, com.hotspr.R.style.login_dialog_style);
        mContext = context ;
        setContentView(R.layout.dialog_opentable_layout);
        numberEt = findViewById(R.id.number_et);
        okTv = findViewById(R.id.ok_tv);
    }


    public TextView getOkText(){
        return okTv ;
    }

    public void setOkTv(boolean can){
        okTv.setClickable(can);
    }

    public String getNumberString(){
        return numberEt.getText().toString();
    }

    @Override
    public void dismiss() {
        okTv.setClickable(true);
        super.dismiss();
    }
}
