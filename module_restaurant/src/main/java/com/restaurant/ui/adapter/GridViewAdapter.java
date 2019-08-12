package com.restaurant.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.restaurant.R;

import java.util.ArrayList;

public class GridViewAdapter extends BaseAdapter {


    private Context mContext ;

    private ArrayList<String> datas ;


    public GridViewAdapter(Context context){
        mContext = context ;
        datas = new ArrayList<>();
    }

    public void addDatas(ArrayList<String> data){
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getCount() {
        return datas!=null?datas.size():0;
    }

    @Override
    public String getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder vh = null ;
        if(convertView == null ){
            convertView = LayoutInflater.from(mContext).inflate(R.layout.grid_item_layout , null);
            vh = new ViewHolder();
            vh.tv = convertView.findViewById(R.id.tv);
            convertView.setTag(vh);
        } else {
            vh = (ViewHolder)convertView.getTag();
        }
        vh.tv.setText(datas.get(position));
        return convertView;
    }

    class ViewHolder {
        TextView tv ;
    }

}
