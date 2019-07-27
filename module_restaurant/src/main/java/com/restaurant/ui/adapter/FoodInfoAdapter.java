package com.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.restaurant.R;
import com.restaurant.ui.bean.FoodCategory;
import com.restaurant.ui.bean.VarietyDishes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.ViewHolder> {

    private Context mContext ;

    private ArrayList<VarietyDishes> datas ;

    public FoodInfoAdapter(Context context){
        mContext = context ;
        datas = new ArrayList();
    }


    public void addData(ArrayList<VarietyDishes> data ){
        datas.clear();
        datas.addAll(data);
        datas = data ;
        notifyDataSetChanged();
    }

    /***
     * 更行某一项数据
     * @param vd
     * @param i
     */
    public void upData(VarietyDishes vd , int i ){
        if(vd == null || (i<0) || i >=datas.size()){
            return;
        }
        datas.remove(i);
        datas.add(i, vd);
        notifyDataSetChanged();

    }

    public ArrayList<VarietyDishes> getDatas(){
        return datas;
    }

    @NonNull
    @Override
    public FoodInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_food_info_list_layout ,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodInfoAdapter.ViewHolder viewHolder, int i) {
        VarietyDishes vd = datas.get(i);
        if(vd == null){
            return;
        }
        LogF.i("TAGURL" , "URL = "+ vd.getPicture_path());
        Picasso.with(mContext).load(HttpConfig.PIC_HOST_NAME+ vd.getPicture_path()).into(viewHolder.foofIV);
        viewHolder.foodNameTv.setText(vd.getMC());
        if(vd.getSLOrder().equals("0")){
            viewHolder.numberTv.setVisibility(View.GONE);
        } else {
            viewHolder.numberTv.setText(vd.getSLOrder() + " 份");
        }
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    public OnItemClickListener listener ;

    public interface OnItemClickListener{
        void onItmeClick(VarietyDishes vd , int i);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements  View.OnClickListener{

        private LinearLayout food_name_layout;
        private ImageView foofIV;
        private TextView foodNameTv;
        private TextView numberTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_layout = itemView.findViewById(R.id.food_name_layout);
            foofIV = itemView.findViewById(R.id.image_view);
            foodNameTv = itemView.findViewById(R.id.name_tv);
            numberTv = itemView.findViewById(R.id.number_tv);
            food_name_layout.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.food_name_layout){
                if(listener!=null){
                    int i = getAdapterPosition();
                    VarietyDishes vd = datas.get(i);
                    listener.onItmeClick(vd , i);
                }
            }
        }
    }


}
