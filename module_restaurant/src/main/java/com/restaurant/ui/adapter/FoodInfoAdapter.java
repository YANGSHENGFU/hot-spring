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
import com.restaurant.ui.bean.SetMeal;
import com.restaurant.ui.bean.VarietyDishes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class FoodInfoAdapter extends RecyclerView.Adapter<FoodInfoAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<VarietyDishes> datas;
    private ArrayList<SetMeal> setMeals;
    private boolean isSetMeal = false;

    public FoodInfoAdapter(Context context) {
        mContext = context;
        datas = new ArrayList();
        setMeals = new ArrayList();
    }


    public void addData(ArrayList<VarietyDishes> data) {
        setMeals.clear();

        isSetMeal = false;
        datas.clear();
        datas.addAll(data);
        datas = data;
        notifyDataSetChanged();
    }

    public void addSetMeal(ArrayList<SetMeal> data) {
        datas.clear();
        for (SetMeal s:data){
            VarietyDishes v = new VarietyDishes();
            datas.add(v);
        }
        isSetMeal = true;
        setMeals.clear();
        setMeals.addAll(data);
        setMeals = data;
        notifyDataSetChanged();
    }
    /***
     * 更行某一项数据
     * @param vd
     * @param i
     */
    public void upData(VarietyDishes vd, int i) {
        if (vd == null || (i < 0) || i >= datas.size()) {
            return;
        }
        datas.remove(i);
        datas.add(i, vd);
        notifyDataSetChanged();

    }

    public ArrayList<VarietyDishes> getDatas() {
        return datas;
    }

    @NonNull
    @Override
    public FoodInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_food_info_list_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodInfoAdapter.ViewHolder viewHolder, int i) {
        if(isSetMeal && i<setMeals.size()){
            SetMeal vd = setMeals.get(i);
            if (vd == null) {
                return;
            }
            viewHolder.foodNameTv.setText(vd.getTc_name());
            viewHolder.numberTv.setVisibility(View.INVISIBLE);
            viewHolder.foofIV.setVisibility(View.GONE);

            String price = "";
            if (vd.getTc_price() != null)
                price = vd.getTc_price();
            viewHolder.priceTv.setText("¥：" + price);
        }else {
            VarietyDishes vd = datas.get(i);
            if (vd == null) {
                return;
            }
            LogF.i("TAGURL", "URL = " + vd.getPicture_path());
            viewHolder.foofIV.setVisibility(View.VISIBLE);
            Picasso.with(mContext).load(HttpConfig.PIC_HOST_NAME + vd.getPicture_path()).into(viewHolder.foofIV);
            viewHolder.foodNameTv.setText(vd.getMC());
            if (vd.getSLOrder().toString().equals("0") && 1 == 2) {
                viewHolder.numberTv.setVisibility(View.GONE);
            } else {
                viewHolder.numberTv.setText(vd.getSLOrder() + " 份");
            }

            String price = "";
            if (vd.getLSJG() != null)
                price = vd.getLSJG();
            viewHolder.priceTv.setText("¥：" + price);
        }
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItmeClick(Object vd, int i, boolean isSetMeal);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout food_name_layout;
        private ImageView foofIV;
        private TextView foodNameTv;
        private TextView numberTv;
        private TextView priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_layout = itemView.findViewById(R.id.food_name_layout);
            foofIV = itemView.findViewById(R.id.image_view);
            foofIV.setAdjustViewBounds(true);
            foodNameTv = itemView.findViewById(R.id.name_tv);
            numberTv = itemView.findViewById(R.id.number_tv);
            priceTv = itemView.findViewById(R.id.price_tv);
            food_name_layout.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            if (v.getId() == R.id.food_name_layout) {
                if (listener != null) {
                    int i = getAdapterPosition();
                    Object vd = null;
                    if (isSetMeal)
                        vd = setMeals.get(i);
                    else
                        vd = datas.get(i);
                    listener.onItmeClick(vd, i, isSetMeal);
                }
            }
        }
    }


}
