package com.hotspr.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.ui.bean.Deposit;

import java.util.ArrayList;

public class BaggageQueryAdapter extends RecyclerView.Adapter<BaggageQueryAdapter.ViewHolder> {

    private Context mContext ;
    private ArrayList<Deposit> datas ;

    public BaggageQueryAdapter(Context context){
        mContext = context ;
        datas = new ArrayList<>();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addDatas(ArrayList<Deposit> data){
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
    public BaggageQueryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyc_baggage_query_layout , null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull BaggageQueryAdapter.ViewHolder viewHolder , int i) {
        viewHolder.reservaNumberTv.setText("预约号：" + datas.get(i).getGROUPNO());
        viewHolder.reservaNameTv.setText("名字：" + datas.get(i).getNAME());
        viewHolder. reservaPhotoTv.setText("电话："+datas.get(i).getTELE());
        viewHolder. reserva_room_tv.setText("房间号："+datas.get(i).getROOM());
    }

    private OnItemClickListener mOnItemClickListener ;

    public interface OnItemClickListener{
        void onItemClick(Deposit deposit);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        mOnItemClickListener = onItemClickListener ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView reservaNumberTv ;
        private TextView reservaNameTv ;
        private TextView reservaPhotoTv ;
        private TextView reserva_room_tv ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reservaNumberTv = itemView.findViewById(R.id.reserva_number_tv);
            reservaNameTv = itemView.findViewById(R.id.reserva_name_tv);
            reservaPhotoTv = itemView.findViewById(R.id.reserva_phone_tv);
            reserva_room_tv = itemView.findViewById(R.id.reserva_room_tv);
            itemView.findViewById(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        int i = getAdapterPosition()-1;
                        Deposit deposit = datas.get(i);
                        mOnItemClickListener.onItemClick(deposit);
                    }
                }
            });
        }
    }

}
