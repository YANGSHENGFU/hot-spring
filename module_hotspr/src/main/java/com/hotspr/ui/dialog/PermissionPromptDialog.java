package com.hotspr.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.widget.TextView;

import com.hotspr.R;


public class PermissionPromptDialog extends Dialog {

    private Context mContext ;

    private TextView contentTv ;
    private TextView iKnoe ;

    public PermissionPromptDialog(@NonNull Context context) {
        super(context, R.style.login_dialog_style);
        mContext = context ;
        setContentView(R.layout.dialog_permission_prompt_layout);
        contentTv = findViewById(R.id.copntent_tv);
        iKnoe = findViewById(R.id.i_know);
    }

    public void setContentTvString(String content){
        if(contentTv!=null){
            contentTv.setText(content);
        }
    }

    public void setiKnoeString(String content){
        if(iKnoe!=null){
            iKnoe.setText(content);
        }
    }

    public TextView getIKnoeTv(){
        return iKnoe;
    }


}

