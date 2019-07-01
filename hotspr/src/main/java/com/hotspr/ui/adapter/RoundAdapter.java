package com.hotspr.ui.adapter;

import android.content.Context;
import android.os.Handler;
import android.os.Message;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
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


public class RoundAdapter extends RecyclerView.Adapter<RoundAdapter.ViewHolder> {

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

    public RoundAdapter(Context context){
        mContext = context ;
        data = new ArrayList<>();
    }

    @NonNull
    @Override
    public RoundAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_recyc_round_layout , null );
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull RoundAdapter.ViewHolder viewHolder, int i) {

    viewHolder.roundNumberTv.setText("房号：" + data.get(i).getROOM());
    viewHolder.roundTypeTv.setText("房型：" + data.get(i).getCLASS());
    viewHolder.roundlloor.setText("楼号：" + data.get(i).getFLOOR());
    viewHolder.roundLookServerName.setText("查房人：" + data.get(i).getLook_server_name());
    viewHolder.roundLookTimeOut.setText("时间：" + data.get(i).getLook_time_out());
    if (!TextUtils.isEmpty(data.get(i).getLook_tage()) && data.get(i).getLook_tage().equals("Y")) {
        viewHolder.roundCheckTv.setVisibility(View.INVISIBLE);
    } else {
        viewHolder.roundCheckTv.setVisibility(View.VISIBLE);
    }

    if (TextUtils.isEmpty(data.get(i).getLook_tage()) || data.get(i).getLook_tage().equals("")) {
        viewHolder.roundLight.setBackgroundResource(R.drawable.check_round_light_unclear);
    } else if (!TextUtils.isEmpty(data.get(i).getLook_tage()) || data.get(i).getLook_tage().equals("Y")) {
        if (TextUtils.isEmpty(data.get(i).getLook_picture_path()) || data.get(i).getLook_picture_path().equals("")) {
            viewHolder.roundLight.setBackgroundResource(R.drawable.check_round_light_clear);
        } else {
            viewHolder.roundLight.setBackgroundResource(R.drawable.check_round_light_leave);
        }
    }

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
        TextView roundlloor ;//楼号
        View view1; //查房按钮的弹窗布局
        View view2; //查看查房结果的弹窗布局
        ImageView iv_goods ; //弹窗图片
        EditText et_ward_content; //弹窗备注
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundLayout = itemView.findViewById(R.id.round_layout);
            roundNumberTv = itemView.findViewById(R.id.round_number_tv);
            roundLight = itemView.findViewById(R.id.round_light_tv);
            roundTypeTv = itemView.findViewById(R.id.round_type_tv);
            roundLookServerName = itemView.findViewById(R.id.round_look_server_name);
            roundLookTimeOut = itemView.findViewById(R.id.round_look_time_out);
            roundCheckTv = itemView.findViewById(R.id.check_tv);
            roundlloor = itemView.findViewById(R.id.round_floor);
            roundCheckTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){
                        int i = getAdapterPosition()-1 ; //获取点击的位置
                        checkLisnter.check(i ,data.get(i));
                    }
                }
            });

            roundLayout.setOnClickListener(new View.OnClickListener(){

                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){
                        int i = getAdapterPosition()-1 ; //获取点击的位置
                        checkLisnter.check(i , data.get(i));
                    }
                }
            });
        }
    }

    public CheckLisnter checkLisnter ;
    public void setCheckLisnter(CheckLisnter checkLisnter) {
        this.checkLisnter = checkLisnter;
    }

    public interface CheckLisnter{
        void check(int i ,Round round);
    }

}
