package com.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.restaurant.R;
import com.restaurant.ui.bean.FoodCategory;

import java.util.ArrayList;

public class FoodCategoryAdapter extends RecyclerView.Adapter<FoodCategoryAdapter.ViewHolder> {

    private Context mContext ;

    private ArrayList<FoodCategory> datas ;

    public FoodCategoryAdapter(Context context){
        mContext = context ;
        datas = new ArrayList();
    }


    public void setData(ArrayList<FoodCategory> data ){
        datas.clear();
        datas.addAll(data);
        datas = data ;
        notifyDataSetChanged();
    }

    public ArrayList<FoodCategory> getDatas(){
        return datas;
    }

    @NonNull
    @Override
    public FoodCategoryAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_food_category_name_list_layout ,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull FoodCategoryAdapter.ViewHolder viewHolder, int i) {
        viewHolder.foodNameTv.setText(datas.get(i).getMC());
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }


    private OnItemClickListener onItemClickListener ;

    public void setOnItemClickListener(OnItemClickListener onItemClickListener) {
        this.onItemClickListener = onItemClickListener;
    }

    public interface OnItemClickListener{
        void onItmeClick(FoodCategory fc);
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private TextView foodNameTv ;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            foodNameTv = itemView.findViewById(R.id.name_tv);
            foodNameTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if(v.getId() == R.id.name_tv){
                if(onItemClickListener!=null){
                    int i = getAdapterPosition() ;
                    FoodCategory fc = datas.get(i);
                    onItemClickListener.onItmeClick(fc);
                }
            }
        }
    }


}
