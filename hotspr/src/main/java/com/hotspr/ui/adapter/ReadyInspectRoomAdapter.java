package com.hotspr.ui.adapter;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.hotspr.R;
import com.hotspr.ui.bean.Round;
import com.modulebase.log.LogF;

import java.util.ArrayList;

public class ReadyInspectRoomAdapter extends RecyclerView.Adapter<ReadyInspectRoomAdapter.ViewHolder> {

    private String TAG = "ReadyInspectRoomAdapter" ;

    private ArrayList<Round> data ;
    private Context mContext ;

    public ReadyInspectRoomAdapter(Context context){
        mContext = context ;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public ReadyInspectRoomAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ready_clean_room_layout , null );
        return new ReadyInspectRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadyInspectRoomAdapter.ViewHolder viewHolder, int i) {

        viewHolder.roundNumberTv.setText("房号："+data.get(i).getROOM());
        viewHolder.roundTypeTv.setText("房型："+data.get(i).getCLASS());

        viewHolder. item_ready_clean_room_type_tv_old.setText("修改房型："+data.get(i).getCl_class_new());;//修改房型
        viewHolder. item_ready_clean_room_onduty1n.setText("安排人："+data.get(i).getCl_onduty1n());;//安排人
        viewHolder. item_ready_clean_room_onduty3n.setText("清洁人："+data.get(i).getCl_onduty3n());;//清洁人
        viewHolder. item_ready_clean_room_time3.setText("时间："+data.get(i).getCl_time3());;//清洁时间
        viewHolder. item_ready_clean_room_check_er.setText("检查人："+data.get(i).getCl_check_er());;//检查人



        //安排清洁

        if (data.get(i).getSTATE2().equals("D") ){
            viewHolder.roundArrangeTv.setVisibility(View.VISIBLE);
            if(data.get(i).getCl_state().equals("0")){
                viewHolder.roundArrangeTv.setText("重新安排");
            } else {
                viewHolder.roundArrangeTv.setText("安排清洁");
            }
        }else {
            viewHolder.roundArrangeTv.setVisibility(View.INVISIBLE);
        }
        if (data.get(i).getCl_state().equals("1") ){
            viewHolder.item_ready_inspect_room_unpass_tv.setVisibility(View.VISIBLE);
        }else {
            viewHolder.item_ready_inspect_room_unpass_tv.setVisibility(View.INVISIBLE);
        }

        //待检查房
//        if ( data.get(i).Getcl_state().equals("1") ){
//            viewHolder.item_ready_clean_room_li_yesno.setVisibility(View.VISIBLE);
//        } else {
//            viewHolder.item_ready_clean_room_li_yesno.setVisibility(View.GONE);
//        }
        //房间房态
        if(data.get(i).getSTATE2().equals("D")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_d);
        } else if(data.get(i).getSTATE2().equals("T")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_t);
        } else if(data.get(i).getSTATE2().equals("L")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_l);
        } else if(data.get(i).getSTATE2().equals("R")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_r);
        } else if(data.get(i).getSTATE2().equals("M")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_m);
        } else if(data.get(i).getSTATE2().equals("S")){
            viewHolder.item_ready_clean_room_state.setBackgroundResource(R.drawable.round_room_state_s);
        }
        //房间房态结束

    }

    @Override
    public int getItemCount() {
        return data != null?data.size():0 ;
    }

    /**
     * 更新数据
     * @param rounds
     */
    public void upData(ArrayList<Round> rounds){
        LogF.i(TAG,"into upData");
        if(rounds!=null && rounds.size()>0){
            data.clear();
            data.addAll(rounds);
            notifyDataSetChanged();
        }else{  //搜索为空也一样
            data.clear();
            data.addAll(rounds);
            notifyDataSetChanged();
        }
    }

    /**
     * 添加数据
     * @param rounds
     */
    public void addData(ArrayList<Round> rounds){
        LogF.i(TAG,"into addData");
        if(rounds != null && rounds.size()>0){
            data.addAll(rounds);
            notifyDataSetChanged();
        }
    }

    /**
     * 获取数据量的条数
     * @return
     */
    public int getDataSize(){
        return data !=null?data.size():0 ;
    }

    public String getRoundID(int index){
        LogF.i(TAG,"into getRoundID");
        if(data!=null && index >=  0 && index < data.size()){
            return data.get(index).getLook_id();
        }
        return "" ;
    }


    class ViewHolder extends RecyclerView.ViewHolder {

        LinearLayout roundLayout;
        TextView roundNumberTv;
        TextView roundTypeTv;
        TextView roundArrangeTv; //安排按钮
        View view1; //安排按钮的弹窗布局
        TextView item_ready_clean_room_state; //房间状态

        TextView item_ready_inspect_room_unpass_tv;//不合格按钮
        TextView item_ready_clean_room_type_tv_old;//修改房型
        TextView item_ready_clean_room_onduty1n;//安排人
        TextView item_ready_clean_room_onduty3n;//清洁人
        TextView item_ready_clean_room_time3;//清洁时间
        TextView item_ready_clean_room_check_er;//检查人

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundLayout = itemView.findViewById(R.id.item_ready_clean_room_layout);
            roundNumberTv = itemView.findViewById(R.id.item_ready_clean_room_number_tv);
            roundTypeTv = itemView.findViewById(R.id.item_ready_clean_room_type_tv);
            roundArrangeTv = itemView.findViewById(R.id.item_ready_clean_room_arrange_tv);
            item_ready_clean_room_state = itemView.findViewById(R.id.item_ready_clean_room_state);
            item_ready_inspect_room_unpass_tv=itemView.findViewById(R.id.item_ready_inspect_room_unpass_tv);//不合格按钮
            item_ready_clean_room_type_tv_old=itemView.findViewById(R.id.item_ready_clean_room_type_tv_old);
            item_ready_clean_room_onduty1n=itemView.findViewById(R.id.item_ready_clean_room_onduty1n);
            item_ready_clean_room_onduty3n=itemView.findViewById(R.id.item_ready_clean_room_onduty3n);
            item_ready_clean_room_time3=itemView.findViewById(R.id.item_ready_clean_room_time3);
            item_ready_clean_room_check_er=itemView.findViewById(R.id.item_ready_clean_room_check_er);
            roundArrangeTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {

                    if(checkLisnter!=null){
                        int p = getAdapterPosition()-1 ;
                        checkLisnter.clean(data.get(p) ,p );

                    }
                }
            });




            item_ready_inspect_room_unpass_tv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){
                        int p = getAdapterPosition()-1 ;
                        checkLisnter.unqualiFied(  data.get(p) ,p );
                    }
                }
            });

            roundLayout.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){
                        int p = getAdapterPosition()-1 ;
                        checkLisnter.unqualiFied(  data.get(p) ,p );
                    }
                }
            });


        }
    }

    public ReadyInspectRoomAdapter.CheckLisnter checkLisnter ;

    public void setCheckLisnter(ReadyInspectRoomAdapter.CheckLisnter checkLisnter) {
        this.checkLisnter = checkLisnter;
    }

    public interface CheckLisnter{
        /**
         * 安排清洁人员
         * @param round 数据
         * @param p 数据的序号
         */
        void clean(Round round , int p);



        /**
         * 设置不合格
         * @param round 数据
         * @param p 数据的序号
         */
        void unqualiFied(Round round , int p); // 不合格
    }
}