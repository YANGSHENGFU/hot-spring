package com.hotspr.ui.dialog;

import android.app.Dialog;
import android.content.Context;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.AdapterView;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.ui.adapter.RoomTypeAndCleanAdapter;
import com.modulebase.toolkit.DisplayUtil;

import java.util.ArrayList;


public class ArrangeCleaningDialog extends Dialog implements View.OnClickListener {

    private Context mContext ;
    private TextView roundNumberTv ;
    private LinearLayout roomTypeLayout ;
    private EditText roomTypeEt ;
    private ImageView roomTypeIv ;
    private LinearLayout cleanerLayout ;
    private EditText cleanerEt ;
    private ImageView cleanerIm ;
    private CheckBox cbArrangeUrgent ;
    private TextView cancelTv ;
    private TextView againCleanTv ;
    private PopupWindow roomTypePopupWindow;
    private PopupWindow cleanPopupWindow;
    private RoomTypeAndCleanAdapter roomTypeAdapter ;
    private RoomTypeAndCleanAdapter cleanAdapter ;

    private String mRoomType ;
    private String mCleaner ;


    public ArrangeCleaningDialog(@NonNull Context context) {
        super(context, R.style.login_dialog_style);
        mContext = context ;
        setContentView(R.layout.dialog_arrange_cleaning_layout);
        roundNumberTv = findViewById(R.id.round_number_tv);
        roomTypeLayout = findViewById(R.id.room_type_layout);
        roomTypeEt = findViewById(R.id.room_type_et);
        roomTypeIv = findViewById(R.id.room_type_iv);
        cleanerLayout = findViewById(R.id.cleaner_layout);
        cleanerEt = findViewById(R.id.cleaner_et);
        cleanerIm = findViewById(R.id.cleaner_im);
        cbArrangeUrgent = findViewById(R.id.cb_arrange_urgent);
        cancelTv = findViewById(R.id.cancel_tv);
        againCleanTv = findViewById(R.id.again_clean_tv);

        roomTypeIv.setOnClickListener(this);
        cleanerIm.setOnClickListener(this);

        cancelTv.setOnClickListener(this);
        againCleanTv.setOnClickListener(this);
        initRoomtypePopupWindow();
        initrCleanPopupWindow();
    }

    //房型
    private void initRoomtypePopupWindow(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_roomtype_and_clean_layout , null);
        roomTypePopupWindow =  new PopupWindow( view , DisplayUtil.dip2px(mContext,110) , DisplayUtil.dip2px(mContext,130));
        roomTypePopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        roomTypeAdapter = new RoomTypeAndCleanAdapter(mContext );
        list.setAdapter(roomTypeAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mRoomType = roomTypeAdapter.getContent(position);
                roomTypeEt.setText(mRoomType);
                roomTypePopupWindow.dismiss();
            }
        });
    }

    //房型
    private void initrCleanPopupWindow(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_roomtype_and_clean_layout , null);
        cleanPopupWindow =  new PopupWindow( view , DisplayUtil.dip2px(mContext,110) , DisplayUtil.dip2px(mContext,130));
        cleanPopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        cleanAdapter = new RoomTypeAndCleanAdapter(mContext );
        list.setAdapter(cleanAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                mCleaner = cleanAdapter.getContent(position);
                cleanerEt.setText(mCleaner);
                cleanPopupWindow.dismiss();
            }
        });
    }

    public ArrangeCleaningDialog(@NonNull Context context, int themeResId) {
        super(context, themeResId);
    }

    public ArrangeCleaningDialog(@NonNull Context context, boolean cancelable, @Nullable OnCancelListener cancelListener) {
        super(context, cancelable, cancelListener);
    }

    /**
     * duifanghua
     */
    public void dismiss(){
        roomTypePopupWindow.dismiss();
        cleanPopupWindow.dismiss();
        super.dismiss();
    }

    /**
     * 设置房间型号
     * @param roomUnmber
     */
    public void setRoundNumberTvContent(String roomUnmber ){
        roundNumberTv.setText(roomUnmber);
    }

    /**
     * 设置房型的数据原
     */
    public void setRoomTypeListData(ArrayList<String> datas){
        roomTypeAdapter.setDatas(datas);
    }

    /**
     * 设置清洁员数据原
     */
    public void setCleanerListData(ArrayList<String> datas){
        cleanAdapter.setDatas(datas);
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.room_type_iv){
            cleanPopupWindow.dismiss();
            if(roomTypePopupWindow.isShowing()){
                roomTypePopupWindow.dismiss();
            } else {
                roomTypePopupWindow.setClippingEnabled(false);
                roomTypePopupWindow.showAsDropDown(roomTypeLayout,0 , 2 );
            }
        } else if(id == R.id.cleaner_im){
            roomTypePopupWindow.dismiss();
            if(cleanPopupWindow.isShowing()){
                cleanPopupWindow.dismiss();
            } else {
                cleanPopupWindow.setClippingEnabled(false);
                cleanPopupWindow.showAsDropDown(cleanerLayout,0 , 2 );
            }
        } else if(id == R.id.cancel_tv){
            dismiss();
        } else if(id == R.id.again_clean_tv){


            if(againCleanLisnter!=null){
                againCleanLisnter.againClean();
            }
        }
    }

    /**
     * 获取房间型号
     * @return
     */
    public String getmRoomType() {
        return mRoomType;
    }

    /**
     * 获取清洁员
     * @return
     */
    public String getmClean() {
        return mCleaner;
    }


    private AgainCleanLisnter againCleanLisnter ;
    public interface AgainCleanLisnter{
        void againClean();
    }


    public void setAgainCleanLisnter(AgainCleanLisnter againCleanLisnter) {
        this.againCleanLisnter = againCleanLisnter;
    }
}
