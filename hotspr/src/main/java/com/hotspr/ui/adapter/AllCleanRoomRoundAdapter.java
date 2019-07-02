package com.hotspr.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
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

import java.util.ArrayList;

public class AllCleanRoomRoundAdapter extends RecyclerView.Adapter<AllCleanRoomRoundAdapter.ViewHolder> {

    private ArrayList<Round> data ;
    private Context mContext ;

    private String url;

    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            String url = (String) msg.obj;

//            //新建一个显式意图，第一个参数为当前Activity类对象，第二个参数为你要打开的Activity类
//            Intent intent =new Intent(mContext,SeeLargePicActivity.class);
//            //用Bundle携带数据
//            Bundle bundle=new Bundle();
//            //传递name参数为tinyphp
//            bundle.putString("url", url);
//            intent.putExtras(bundle);
//            mContextxt.startActivity(intent);
        }
    };

    public AllCleanRoomRoundAdapter(Context context){
        mContext = context ;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public AllCleanRoomRoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyc_cleaned_round_layout , null );
        return new AllCleanRoomRoundAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AllCleanRoomRoundAdapter.ViewHolder viewHolder, int i) {

        viewHolder.roundNumberTv.setText("房号：" + data.get(i).getROOM());
        viewHolder.roundTypeTv.setText("房型：" + data.get(i).getCLASS());
        viewHolder. item_ready_clean_room_type_tv_old.setText("修改房型："+data.get(i).getCl_class_new());;//修改房型
        viewHolder. item_ready_clean_room_onduty1n.setText("安排人："+data.get(i).getCl_onduty1n());;//安排人
        viewHolder. item_ready_clean_room_onduty3n.setText("清洁人："+data.get(i).getCl_onduty3n());;//清洁人
        viewHolder. item_ready_clean_room_time3.setText("清洁时间："+data.get(i).getCl_time3());;//清洁时间
        viewHolder. item_ready_clean_room_check_er.setText("检查人："+data.get(i).getCl_check_er());;//检查人
        viewHolder. round_floor.setText("楼号："+data.get(i).getFLOOR());;//检查人

        if (data.get(i).getCl_state().equals("0") ){
            viewHolder.roundCheckTv.setVisibility(View.VISIBLE);
        }else {
            viewHolder.roundCheckTv.setVisibility(View.INVISIBLE);
        }


        //房间房态
        if(data.get(i).getSTATE2().equals("D")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_d);
        } else if(data.get(i).getSTATE2().equals("T")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_t);
        } else if(data.get(i).getSTATE2().equals("L")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_l);
        } else if(data.get(i).getSTATE2().equals("R")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_r);
        } else if(data.get(i).getSTATE2().equals("M")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_m);
        } else if(data.get(i).getSTATE2().equals("S")){
            viewHolder.roundLight.setBackgroundResource(R.drawable.round_room_state_s);
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

    public ArrayList<Round> getData(){
        return data ;
    }

    public String getRoundID(int index){

        if(data!=null && index >=  0 && index < data.size()){
            return data.get(index).getLook_id();
        }
        return "" ;
    }


    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout roundLayout ;
        TextView roundNumberTv ;
        TextView roundLight;
        TextView roundTypeTv ;
        TextView roundLookServerName;
        TextView roundLookTimeOut;
        TextView roundCheckTv ;
        View view1; //查房按钮的弹窗布局
        View view2; //查看查房结果的弹窗布局
        ImageView iv_goods ; //弹窗图片
        EditText et_ward_content; //弹窗备注
        TextView item_ready_clean_room_type_tv_old;//修改房型
        TextView item_ready_clean_room_onduty1n;//安排人
        TextView item_ready_clean_room_onduty3n;//清洁人
        TextView item_ready_clean_room_time3;//清洁时间
        TextView item_ready_clean_room_check_er;//检查人
        TextView round_floor;//楼号
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundLayout = itemView.findViewById(R.id.round_layout);
            roundNumberTv = itemView.findViewById(R.id.round_number_tv);
            roundLight = itemView.findViewById(R.id.round_light_tv);
            roundTypeTv = itemView.findViewById(R.id.round_type_tv);
            roundLookServerName = itemView.findViewById(R.id.round_look_server_name);
            roundLookTimeOut = itemView.findViewById(R.id.round_look_time_out);
            roundCheckTv = itemView.findViewById(R.id.check_tv);
            item_ready_clean_room_type_tv_old=itemView.findViewById(R.id.item_ready_clean_room_type_tv_old);
            item_ready_clean_room_onduty1n=itemView.findViewById(R.id.item_ready_clean_room_onduty1n);
            item_ready_clean_room_onduty3n=itemView.findViewById(R.id.item_ready_clean_room_onduty3n);
            item_ready_clean_room_time3=itemView.findViewById(R.id.item_ready_clean_room_time3);
            item_ready_clean_room_check_er=itemView.findViewById(R.id.item_ready_clean_room_check_er);
            round_floor=itemView.findViewById(R.id.round_floor);
            roundCheckTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){
                        int i = getAdapterPosition()-1 ; //获取点击的位置
                        checkLisnter.check(i ,data.get(i));
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

//            roundLayout.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    if(checkLisnter!=null){
//                        int i = getAdapterPosition()-1 ; //获取点击的位置
//                        checkLisnter.check(i , data.get(i));
//                    }
//                }
//            });
        }
    }

    public AllCleanRoomRoundAdapter.CheckLisnter checkLisnter ;
    public void setCheckLisnter(AllCleanRoomRoundAdapter.CheckLisnter checkLisnter) {
        this.checkLisnter = checkLisnter;
    }

    public interface CheckLisnter{
        void check(int i, Round round);

        /**
         * 设置不合格
         * @param round 数据
         * @param p 数据的序号
         */
        void unqualiFied(Round round, int p); // 不合格
    }

}
