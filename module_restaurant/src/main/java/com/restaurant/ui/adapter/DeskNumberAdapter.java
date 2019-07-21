package com.restaurant.ui.adapter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.restaurant.R;
import com.restaurant.ui.bean.TableNumber;
import java.util.ArrayList;

public class DeskNumberAdapter extends RecyclerView.Adapter<DeskNumberAdapter.ViewHolder> {

    private Context mContext;
    private ArrayList<TableNumber> datas ;

    public DeskNumberAdapter(Context context){
        mContext = context ;
        datas = new ArrayList<>();
    }

    /**
     * 更新某项
     * @param position
     * @param tableNumber
     */
    public void onUpData(int position, TableNumber tableNumber){
        datas.remove(position);
        datas.add(position, tableNumber);

    }

    public void upData(ArrayList<TableNumber> rounds){
        datas.clear();
        datas.addAll(rounds);
        notifyDataSetChanged();
    }

    public void addData(ArrayList<TableNumber> rounds){
        datas.addAll(rounds);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas!=null?datas.size():0;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_desk_number_layout,null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder viewHolder , int i) {
        TableNumber t = datas.get(i);
        if(t == null){
            return;
        }
        viewHolder.tableNumberTv.setText("台号：" + t.getCZBM());
        if(t.getCZZT().equals("V")){
            viewHolder.openOrderTv.setText("开 台");
        } else if(t.getCZZT().equals("I")){
            viewHolder.openOrderTv.setText("点 菜");
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{

        private LinearLayout tableLayout;
        private TextView stateTv;
        private TextView tableNumberTv;
        private TextView openOrderTv;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tableLayout = itemView.findViewById(R.id.table_layout);
            openOrderTv = itemView.findViewById(R.id.open_order_tv);
            stateTv = itemView.findViewById(R.id.state_tv);
            tableNumberTv = itemView.findViewById(R.id.table_number_tv);
            tableLayout.setOnClickListener(this);
            openOrderTv.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            if(mOnClickListener == null){
                return;
            }
            int position = getAdapterPosition()-1;
            TableNumber table = datas.get(position);
            if(id == R.id.open_order_tv){
                mOnClickListener.onClickOpenOrder(table , position);
            } else if(id == R.id.table_layout){
                mOnClickListener.onItemClick(table , position);
            }
        }
    }

    public OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener){
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener{
        void onClickOpenOrder(TableNumber tableNumber , int position);
        void onItemClick(TableNumber tableNumber ,int position);
    }
}
