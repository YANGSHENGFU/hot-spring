package com.modulebase.ui.dialog;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.app.Dialog;
import android.content.Context;
import android.text.TextUtils;
import android.view.animation.LinearInterpolator;
import android.widget.ImageView;
import android.widget.TextView;

import com.modulebase.R;

public class LoadDialog extends Dialog {

    private TextView textView ;
    private ImageView imageView ;
    private ObjectAnimator objectAnimator ;

    public LoadDialog( Context context) {
        super(context, R.style.login_dialog_style);
        setContentView(R.layout.dialog_load_layout);
        textView = findViewById(R.id.stip_tv);
        imageView = findViewById(R.id.img);
    }

    public void setTextViewContent(String conteny){
        if(textView!=null && !TextUtils.isEmpty(conteny)){
            textView.setText(conteny);
        }
    }

    @Override
    public void show(){
        creatAndStartAnimator(imageView ,500 );
        super.show();
    }

    public void dismiss(){
        closeAndStarAnimator();
        super.dismiss();
    }


    private void creatAndStartAnimator(final ImageView view , long duration){
        if(objectAnimator == null ){
            objectAnimator = ObjectAnimator.ofFloat(view ,"rotation" ,-360f);
            objectAnimator.setDuration(duration);
            objectAnimator.setRepeatCount(ValueAnimator.INFINITE);
            objectAnimator.setRepeatMode(ValueAnimator.RESTART);
            objectAnimator.setInterpolator(new LinearInterpolator());
        }
        if(!objectAnimator.isRunning()){
            objectAnimator.start();
        }
    }

    /**
     *
     */
    private void closeAndStarAnimator(){
        if(objectAnimator!=null){
            objectAnimator.cancel();
        }
    }

}
