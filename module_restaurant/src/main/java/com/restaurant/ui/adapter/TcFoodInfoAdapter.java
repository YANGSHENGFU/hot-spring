package com.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.modulebase.HttpConfig;
import com.modulebase.log.LogF;
import com.restaurant.R;
import com.restaurant.ui.bean.SetMeal;
import com.restaurant.ui.bean.TcVarietyDishes;
import com.squareup.picasso.Picasso;

import java.util.ArrayList;

public class TcFoodInfoAdapter extends RecyclerView.Adapter<TcFoodInfoAdapter.ViewHolder> {

    private Context mContext;

    private ArrayList<TcVarietyDishes> datas;
    private String selectValue = "";

    public TcFoodInfoAdapter(Context context) {
        mContext = context;
        datas = new ArrayList();
    }


    public void addData(ArrayList<TcVarietyDishes> data) {
        datas.clear();
        datas.addAll(data);
        datas = data;
        notifyDataSetChanged();
    }

    /***
     * 更行某一项数据
     * @param vd
     * @param i
     */
    public void upData(TcVarietyDishes vd, int i) {
        if (vd == null || (i < 0) || i >= datas.size()) {
            return;
        }
        datas.remove(i);
        datas.add(i, vd);
        notifyDataSetChanged();

    }

    public ArrayList<TcVarietyDishes> getDatas() {
        return datas;
    }

    @NonNull
    @Override
    public TcFoodInfoAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_tc_food_info_list_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TcFoodInfoAdapter.ViewHolder viewHolder, int i) {
            TcVarietyDishes vd = datas.get(i);
            if (vd == null) {
                return;
            }
            LogF.i("TAGURL", "URL = " + vd.getPicture_path());
            viewHolder.selectCB.setText(vd.getJDBH());
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

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    public OnItemClickListener listener;

    public interface OnItemClickListener {
        void onItmeClick(TcVarietyDishes vd, int i);
    }

    public void setListener(OnItemClickListener listener) {
        this.listener = listener;
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private LinearLayout food_name_layout;
        private CheckBox selectCB;
        private ImageView foofIV;
        private TextView foodNameTv;
        private TextView numberTv;
        private TextView priceTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            food_name_layout = itemView.findViewById(R.id.food_name_layout);
            selectCB = itemView.findViewById(R.id.select_cb);
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
                    TcVarietyDishes vd = null;
                    listener.onItmeClick(vd, i);
                }
            }
        }
    }


}
