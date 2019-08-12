package com.hotspr.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.ui.bean.Xl;

import java.util.ArrayList;

public class BaggageFindAdapter extends RecyclerView.Adapter<BaggageFindAdapter.ViewHolder> {

    private Context mContext ;
    private ArrayList<Xl> datas ;

    public BaggageFindAdapter(Context context){
        mContext = context ;
        datas = new ArrayList<>();
    }

    /**
     * 添加数据
     * @param data
     */
    public void addDatas(ArrayList<Xl> data){
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
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup , int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyc_baggage_find_layout , null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder , int i) {
        viewHolder.reserva_order_no_tv.setText("预约号：" + datas.get(i).getOrder_NO());
        viewHolder.reserva_name_tv.setText("名字：" + datas.get(i).getName());
        viewHolder. reserva_tel_tv.setText("电话："+datas.get(i).getTel());
        viewHolder. reserva_room_tv.setText("房间号："+datas.get(i).getRoom());
        viewHolder. reserva_onduty1n_tv.setText("登记人："+datas.get(i).getOnduty1n());
        viewHolder. reserva_date1_tv.setText("登记日期："+datas.get(i).getDate1());
        viewHolder. reserva_time1_tv.setText("登记时间："+datas.get(i).getTime1());

        viewHolder. reserva_onduty2n_tv.setText("寄存人："+datas.get(i).getOnduty2n());
        viewHolder. reserva_date2_tv.setText("寄存日期："+datas.get(i).getDate2());
        viewHolder. reserva_time2_tv.setText("寄存时间："+datas.get(i).getTime2());

        viewHolder. reserva_onduty3n_tv.setText("领取人："+datas.get(i).getOnduty3n());
        viewHolder. reserva_date3_tv.setText("领取日期："+datas.get(i).getDate3());
        viewHolder. reserva_time3_tv.setText("领取时间："+datas.get(i).getTime3());

        if (datas.get(i).getTag().equals("2") ){
            viewHolder. reserva_tag_tv.setText("状态：登记");
        }

        if (datas.get(i).getTag().equals("1") ){
            viewHolder. reserva_tag_tv.setText("状态：寄存");
        }
        if (datas.get(i).getTag().equals("0") ){
            viewHolder. reserva_tag_tv.setText("状态：领取");
        }
    }

    private OnItemClickListener mOnItemClickListener ;

    public interface OnItemClickListener{
        void onItemClick(Xl deposit);
    }

    public void setOnItemClickListener(OnItemClickListener onItemClickListener ){
        mOnItemClickListener = onItemClickListener ;
    }

    class ViewHolder extends RecyclerView.ViewHolder{

        private TextView reserva_order_no_tv ;
        private TextView reserva_name_tv ;
        private TextView reserva_tel_tv ;
        private TextView reserva_room_tv ;
        private TextView reserva_onduty1n_tv ;
        private TextView reserva_date1_tv ;
        private TextView reserva_time1_tv ;
        private TextView reserva_onduty2n_tv ;
        private TextView reserva_date2_tv ;
        private TextView reserva_time2_tv ;
        private TextView reserva_onduty3n_tv ;
        private TextView reserva_date3_tv ;
        private TextView reserva_time3_tv ;
        private TextView reserva_tag_tv ;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            reserva_order_no_tv = itemView.findViewById(R.id.reserva_order_no_tv);
            reserva_name_tv = itemView.findViewById(R.id.reserva_name_tv);
            reserva_tel_tv = itemView.findViewById(R.id.reserva_tel_tv);
            reserva_room_tv = itemView.findViewById(R.id.reserva_room_tv);
            reserva_onduty1n_tv = itemView.findViewById(R.id.reserva_onduty1n_tv);
            reserva_date1_tv = itemView.findViewById(R.id.reserva_date1_tv);
            reserva_time1_tv = itemView.findViewById(R.id.reserva_time1_tv);
            reserva_onduty2n_tv = itemView.findViewById(R.id.reserva_onduty2n_tv);
            reserva_date2_tv = itemView.findViewById(R.id.reserva_date2_tv);
            reserva_time2_tv = itemView.findViewById(R.id.reserva_time2_tv);
            reserva_onduty3n_tv = itemView.findViewById(R.id.reserva_onduty3n_tv);
            reserva_date3_tv = itemView.findViewById(R.id.reserva_date3_tv);
            reserva_time3_tv = itemView.findViewById(R.id.reserva_time3_tv);

            reserva_tag_tv = itemView.findViewById(R.id.reserva_tag_tv);

            itemView.findViewById(R.id.item_layout).setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClickListener!=null){
                        int i = getAdapterPosition()-1;
                        Xl deposit = datas.get(i);
                        mOnItemClickListener.onItemClick(deposit);
                    }
                }
            });
        }
    }

}
