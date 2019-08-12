package com.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurant.R;
import com.restaurant.ui.bean.OrderResult;

import java.util.ArrayList;

public class OrdreResultAdapter extends RecyclerView.Adapter<OrdreResultAdapter.ViewHolder> {

    private Context mContext ;

    private ArrayList<OrderResult> datas ;

    public OrdreResultAdapter (Context context){
        mContext = context;
        datas = new ArrayList();
    }

    public void addDatas( ArrayList<OrderResult> data){
        datas.clear();
        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    @NonNull
    @Override
    public OrdreResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_order_result_layout, null );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdreResultAdapter.ViewHolder viewHolder, int i) {
        viewHolder.tv.setText(datas.get(i).getMC() + "          X "+ datas.get(i).getSL());
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView tv ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
        }
    }




}
