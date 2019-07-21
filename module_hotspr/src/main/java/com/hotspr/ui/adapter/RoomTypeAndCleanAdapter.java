package com.hotspr.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.hotspr.R;

import java.util.ArrayList;


public class RoomTypeAndCleanAdapter extends BaseAdapter {
    private ArrayList<String> datas ;
    private Context context ;
    private LayoutInflater mInflater ;

    public RoomTypeAndCleanAdapter(Context context ){
        this.context = context;
        mInflater = LayoutInflater.from(context);
    }



    @Override
    public int getCount() {
        return datas == null ? 0 : datas.size();
    }

    @Override
    public String getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder viewHolder = null;
        if(convertView == null){
            convertView = mInflater.inflate(R.layout.item_list_roomtype_and_clan_layout,null);
            viewHolder = new ViewHolder();
            viewHolder.tv_spinner = convertView.findViewById(R.id.roomtype_clean_tv);
            convertView.setTag(viewHolder);
        }else {
            viewHolder = (ViewHolder) convertView.getTag();
        }
        String spinnerText = datas.get(position);
        viewHolder.tv_spinner.setText(spinnerText);
        return convertView;
    }
    public static class ViewHolder{
        TextView tv_spinner ;
    }

    public ArrayList<String> getDatas() {
        return datas;
    }

    public void setDatas(ArrayList<String> datas) {
        this.datas = datas;
        notifyDataSetChanged();
    }

    public String getContent(int position){
        if(datas == null || position<0 || position>datas.size()){
            return "" ;
        }

        return datas.get(position);

    }

}
