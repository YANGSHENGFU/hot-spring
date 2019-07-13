package com.hotspr.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;
import com.hotspr.R;
import com.hotspr.ui.bean.LuggageQuiryData;

import java.util.ArrayList;

public class LuggageQueryInfoAdapter extends RecyclerView.Adapter<LuggageQueryInfoAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<LuggageQuiryData> datas;

    public LuggageQueryInfoAdapter(Context context){
        mContext = context ;
        datas = new ArrayList<>();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addDatas(ArrayList<LuggageQuiryData> data){
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
    public LuggageQueryInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyc_baggage_query_layout , null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull LuggageQueryInfoAdapter.ViewHolder viewHolder , int i) {
        viewHolder.reservaNumberTv.setText("预约号：" + datas.get(i).getOrder_no());
        viewHolder.reservaNameTv.setText("名字：" + datas.get(i).getName());
        viewHolder. reservaPhotoTv.setText("号码："+datas.get(i).getTel());//修改房型
    }

    private OnItemClickListener mOnItemClickListener ;

    public interface OnItemClickListener{
        void onItemClick(LuggageQuiryData info);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        mOnItemClickListener = onItemClickListener ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView reservaNumberTv ;
        private TextView reservaNameTv ;
        private TextView reservaPhotoTv ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reservaNumberTv = itemView.findViewById(R.id.reserva_number_tv);
            reservaNameTv = itemView.findViewById(R.id.reserva_name_tv);
            reservaPhotoTv = itemView.findViewById(R.id.reserva_phone_tv);
            itemView.findViewById(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        int i = getAdapterPosition()-1;
                        LuggageQuiryData info = datas.get(i);
                        mOnItemClickListener.onItemClick(info);
                    }
                }
            });
        }
    }

}
