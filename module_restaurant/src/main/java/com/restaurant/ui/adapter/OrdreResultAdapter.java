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
import com.sunmi.utils.AidlUtil;


import java.util.ArrayList;

public class OrdreResultAdapter extends RecyclerView.Adapter<OrdreResultAdapter.ViewHolder> {

    private Context mContext ;

    public ArrayList<OrderResult> datas ;

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
        viewHolder.tv.setText(datas.get(i).getSL()+" X "+datas.get(i).getMC() );
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView tv ;
        private TextView tv_bt ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv = itemView.findViewById(R.id.tv);
            tv_bt = itemView.findViewById(R.id.tv_bt);
            tv_bt.setOnClickListener(this);
        }
        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(id == R.id.tv_bt){
                int position = getAdapterPosition();
                OrderResult ordata = datas.get(position);
                // 调用打印方法
                if(mOnClickListener!=null && ordata!=null){
                    mOnClickListener.onClickPintItem(ordata , position);
                    mOnClickListener.onClickPintItem();
                }
            }
        }

    }
    public OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }
    public interface OnClickListener{
        void onClickPintItem( OrderResult ordata , int position);
        void onClickPintItem( );
    }



}
