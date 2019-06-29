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
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_ready_inspect_room_layout , null );
        return new ReadyInspectRoomAdapter.ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ReadyInspectRoomAdapter.ViewHolder viewHolder, int i) {

        viewHolder.roundNumberTv.setText("房号："+data.get(i).getROOM());
        viewHolder.roundTypeTv.setText("房型："+data.get(i).getCLASS());

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


    class ViewHolder extends RecyclerView.ViewHolder{

        LinearLayout roundLayout ;
        TextView roundNumberTv ;
        TextView roundTypeTv ;
        TextView roundInspectPassTv ; //合格
        TextView roundInspectUnpassTv ; //不合格

        View view1; //查房按钮的弹窗布局
        View view2; //查看查房结果的弹窗布局
        ImageView iv_goods ; //弹窗图片
        EditText et_ward_content; //弹窗备注

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            roundLayout = itemView.findViewById(R.id.item_ready_inspect_room_layout);
            roundNumberTv = itemView.findViewById(R.id.item_ready_inspect_room_number_tv);
            roundTypeTv = itemView.findViewById(R.id.item_ready_inspect_room_type_tv);

            //点击合格按钮触发事件
            roundInspectPassTv = itemView.findViewById(R.id.item_ready_inspect_room_pass_tv);
            roundInspectPassTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if (checkLisnter!=null){
                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
                        alertDialog
                                .setTitle("确定清洁合格吗？")
                                .setNegativeButton("取消",null)
                                .setPositiveButton("确定",new DialogInterface.OnClickListener() {
                                    @Override
                                    public void onClick(DialogInterface dialog, int which) {
                                        checkLisnter.check_pass(getRoundID(getAdapterPosition()-1));
                                        checkLisnter.reRequest();
                                    }
                                })
                                .create();
                        AlertDialog show = alertDialog.show();
                    }
                }
            });

            //点击不合格按钮触发事件
            roundInspectUnpassTv = itemView.findViewById(R.id.item_ready_inspect_room_unpass_tv);
            roundInspectUnpassTv.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(checkLisnter!=null){

//                        AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
//                        view1 = View.inflate(v.getContext(), R.layout.click_unpass_round, null);
//                        iv_goods = view1.findViewById(R.id.iv_goods);
//                        Button btn_upload_pic = view1.findViewById(R.id.btn_upload_pic);//上传图片按钮
//                        et_ward_content = view1.findViewById(R.id.et_ward_content);//备注
//
//                        TextView tv_ward_leave = view1.findViewById(R.id.tv_ward_leave); //重新清理
//
//                        alertDialog
//                                .setTitle("重新清洁")
//                                .setView(view1)
//                                .setNegativeButton("取消",null)
//                                .setPositiveButton("重新清洁",new DialogInterface.OnClickListener() {
//                                    @Override
//                                    public void onClick(DialogInterface dialog, int which) {
//                                        String memo = et_ward_content.getText().toString();
//                                        checkLisnter.check_out(getRoundID(getAdapterPosition()-1), memo);
//                                        checkLisnter.reRequest();
//                                    }
//                                })
//                                .create();
//                        final AlertDialog show = alertDialog.show();
//                        btn_upload_pic.setOnClickListener(new View.OnClickListener() {
//                            @Override
//                            public void onClick(View view) {
//                                checkLisnter.uploadPic(getRoundID(getAdapterPosition()-1),(ImageView) iv_goods );
//                            }
//                        });

//                        tv_ward_leave.setOnClickListener(new View.OnClickListener(){
//                            @Override
//                            public void onClick(View v) {
//                                String memo = et_ward_content.getText().toString();
//                                checkLisnter.check_out(getRoundID(getAdapterPosition()-1), memo);
//                                checkLisnter.reRequest();
//                                show.dismiss();
//                            }
//                        });
                    }
                }
            });

//点击整个方块触发事件
//            roundLayout.setOnClickListener(new View.OnClickListener(){
//
//                @Override
//                public void onClick(View v) {
//                    int i = getAdapterPosition()-1 ; //获取点击的位置
//                    System.out.println(i);
//                    AlertDialog.Builder alertDialog = new AlertDialog.Builder(v.getContext());
//                    alertDialog.setTitle("查房结果");
//                    if (TextUtils.isEmpty(data.get(i).getLook_tage()) || data.get(i).getLook_tage().equals("")){
//                        alertDialog.setMessage("还未查房");
//                    }else if(!TextUtils.isEmpty(data.get(i).getLook_tage()) && data.get(i).getLook_tage().equals("Y")){
//                        if (!TextUtils.isEmpty(data.get(i).getLook_picture_path())){
//                            alertDialog.setMessage("已查房，遗留物品:");
//                            view2 = View.inflate(v.getContext(), R.layout.look_ward_result, null);
//                            MyImageView iv_goods_show = view2.findViewById(R.id.iv_goods_show);
//                            TextView tv_ward_content_show = view2.findViewById(R.id.tv_ward_content_show);
//
//                            tv_ward_content_show.setText(data.get(i).getLook_server_memo());
//                            alertDialog.setView(view2);
//                        }else{
//                            alertDialog.setMessage("已查房，无遗留物品");
//                        }
//                    }
//                    alertDialog.show();
//                }
//            });

        }
    }

    public ReadyInspectRoomAdapter.CheckLisnter checkLisnter ;

    public void setCheckLisnter(ReadyInspectRoomAdapter.CheckLisnter checkLisnter) {
        this.checkLisnter = checkLisnter;
    }

    public interface CheckLisnter{
        void check_pass(String roundID);
        void uploadPic(String roundID, ImageView iv_goods);
        void check_out(String roundID, String memo);
        void reRequest();
    }

}