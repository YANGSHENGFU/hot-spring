package com.hotspr.ui.view;

import android.content.Context;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.toolkit.CacheHandle;
import com.modulebase.toolkit.DisplayUtil;
import com.modulebase.ui.adapter.SpinnerPopAdapter;

import java.util.ArrayList;

public class SearchView extends LinearLayout implements View.OnClickListener {

    private Context mContext ;
    private View mView ;
    private EditText floorEt ;
    private EditText roomTypeEt;
    private EditText roomNumberEt;
    private TextView floorTv ;
    private TextView roomtypeTv ;
    private TextView searchTv ;
    private ImageView floorImg ;
    private ImageView rtypeImg ;
    private PopupWindow floorPopupWindow ;
    private PopupWindow rtypePopupWindow ;
    private SpinnerPopAdapter buiNumberAdapter ;
    private SpinnerPopAdapter roomTypeAdapter ;

    public SearchView(Context context) {
        super(context);
        init(context);
    }

    public SearchView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public SearchView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context ;
        mView = LayoutInflater.from(context).inflate(R.layout.view_search_layout , this);
        floorImg = mView.findViewById(R.id.floor_img);
        floorTv = mView.findViewById(R.id.floor_tv);
        floorImg.setOnClickListener(this);
        rtypeImg = mView.findViewById(R.id.roomtype_img);
        roomtypeTv = mView.findViewById(R.id.roomtype_tv);
        rtypeImg.setOnClickListener(this);
        floorEt = mView.findViewById(R.id.floor_et);
        roomTypeEt = mView.findViewById(R.id.roomtype_et);
        roomNumberEt = mView.findViewById(R.id.roomnumber_et);
        searchTv = mView.findViewById(R.id.tv_search);
        searchTv.setOnClickListener(this);
        initfloorPopupWindow();
        initrtypePopupWindow();
    }

    // 楼号
    private void initfloorPopupWindow() {
        View view = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_open_place_layout , null);
        floorPopupWindow =  new PopupWindow( view , DisplayUtil.dip2px(mContext,130) , ViewGroup.LayoutParams.WRAP_CONTENT);
        floorPopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        buiNumberAdapter = new SpinnerPopAdapter(mContext );
        buiNumberAdapter.setDatas(CacheHandle.buildingNumberCach);
        list.setAdapter(buiNumberAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String floor = buiNumberAdapter.getContent(position);
                floorEt.setText(floor);
                floorPopupWindow.dismiss();
            }
        });
    }

    //房型
    private void initrtypePopupWindow(){
        View view = LayoutInflater.from(mContext).inflate(R.layout.popuwindow_open_place_layout , null);
        rtypePopupWindow =  new PopupWindow( view , DisplayUtil.dip2px(mContext,130) , ViewGroup.LayoutParams.WRAP_CONTENT);
        rtypePopupWindow.setOutsideTouchable(true);

        ListView list = view.findViewById(R.id.listView);
        roomTypeAdapter = new SpinnerPopAdapter(mContext );
        roomTypeAdapter.setDatas(CacheHandle.roomTypeCach);
        list.setAdapter(roomTypeAdapter);
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                String rtype = roomTypeAdapter.getContent(position);
                roomTypeEt.setText(rtype);
                rtypePopupWindow.dismiss();
            }
        });
    }

    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.floor_img){
            floorPopupWindow.showAsDropDown(floorTv,0 , 10 );
        } else if (id == R.id.roomtype_img){
            rtypePopupWindow.showAsDropDown(roomtypeTv,0 , 10 );
        } else if(id == R.id.tv_search){ // 搜索
            if(mSearchLisnter!=null){
                if(!TextUtils.isEmpty(floorEt.getText().toString())||
                        !TextUtils.isEmpty(roomTypeEt.getText().toString())||
                        !TextUtils.isEmpty(roomNumberEt.getText().toString())){
                    mSearchLisnter.search(floorEt.getText().toString() , roomTypeEt.getText().toString() , roomNumberEt.getText().toString() );
                }
            }
        }
    }

    /**
     * 设置楼层数据
     * @param data
     */
    public void setBuiNumberData(ArrayList<String> data){
        if(data!=null&&data.size()>0&&buiNumberAdapter!=null){
            buiNumberAdapter.setDatas(data);
        }
    }

    /**
     * 设置房型数据
     * @param data
     */
    public void setRoomTypeData(ArrayList<String> data){
        if(data!=null&&data.size()>0&&roomTypeAdapter!=null){
            roomTypeAdapter.setDatas(data);
        }
    }

    SearchLisnter mSearchLisnter ;

    public void setSearchLisnter(SearchLisnter searchLisnter){
        mSearchLisnter = searchLisnter ;
    }

    public interface SearchLisnter{
        void search(String a, String b, String c);
    }

    public String getFloor(){
        return floorEt.getText().toString() ;
    }

    public String getRoomType(){
        return roomTypeEt.getText().toString() ;
    }

    public String getRoomNumber(){
        return roomNumberEt.getText().toString() ;
    }


}
