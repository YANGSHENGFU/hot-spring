package com.restaurant.ui.adapter;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.modulebase.toolkit.Utils;
import com.restaurant.R;
import com.restaurant.ui.bean.OrderResult;
import com.sunmi.utils.AidlUtil;


import java.text.DecimalFormat;
import java.util.ArrayList;

public class OrdreResultAdapter extends RecyclerView.Adapter<OrdreResultAdapter.ViewHolder> {

    private Context mContext;

    public ArrayList<OrderResult> datas;

    public OrdreResultAdapter(Context context) {
        mContext = context;
        datas = new ArrayList();
    }

    public void addDatas(ArrayList<OrderResult> data) {
        datas.clear();
        OrderResult orderResult = new OrderResult();
        data.add(0, orderResult);
        OrderResult sums = new OrderResult();
        sums.setSL("0");
        sums.setLSJG("0");
        DecimalFormat decimalFormat = new DecimalFormat(".0");//构造方法的字符格式这里如果小数不足2位,会以0补足.

        for (OrderResult d : data) {
            sums.setSL(decimalFormat.format(Utils.convertToDouble(d.getSL(), 0) + Utils.convertToDouble(sums.getSL(), 0)));
            sums.setLSJG(decimalFormat.format(Utils.convertToDouble(d.getLSJG(), 0) + Utils.convertToDouble(sums.getLSJG(), 0)));
        }
        data.add(1, sums);

        datas.addAll(data);
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return datas != null ? datas.size() : 0;
    }

    @NonNull
    @Override
    public OrdreResultAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.recyc_item_order_result_layout, null);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull OrdreResultAdapter.ViewHolder viewHolder, int i) {
        String line = "";
        if (i == 0) {
            viewHolder.tv_no.setText("序号");
            viewHolder.tv_mc.setText("名称");
            viewHolder.tv_num.setText("数量");
            viewHolder.tv_price.setText("价格");
            viewHolder.tv_flavor.setText("口味");
        } else if (i == 1) {
            viewHolder.tv_no.setText("");
            viewHolder.tv_mc.setText("合计");
            line = String.format("%2s", datas.get(i).getSL());
            viewHolder.tv_num.setText(line);
            line = String.format("%6s", datas.get(i).getLSJG());
            viewHolder.tv_price.setText(line);
            viewHolder.tv_flavor.setText("");
        } else {
            line = String.format("%2s", i - 1);
            viewHolder.tv_no.setText(line);
            String s = datas.get(i).getMC();
//        if(s.length()>6)
//            line = line + s.substring(0,5)+"...";
//        else
            line = String.format("%-20s", Utils.handleText(s, 20));
            viewHolder.tv_mc.setText(line);

            line = String.format("%2s", datas.get(i).getSL());
            viewHolder.tv_num.setText(line);
            line = String.format("%6s", datas.get(i).getLSJG());
            viewHolder.tv_price.setText(line);

            if (datas.get(i).getFLAG() == null || !"Y".equals(datas.get(i).getFLAG().toUpperCase()))
                viewHolder.tv_mc.setTextColor(Color.BLUE);
        }
    }

    class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tv_no;
        private TextView tv_mc;
        private TextView tv_num;
        private TextView tv_price;
        private TextView tv_flavor;
        private TextView tv_bt;


        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tv_no = itemView.findViewById(R.id.tv_no);
            tv_mc = itemView.findViewById(R.id.tv_mc);
            tv_num = itemView.findViewById(R.id.tv_num);
            tv_price = itemView.findViewById(R.id.tv_price);
            tv_flavor = itemView.findViewById(R.id.tv_flavor);
            //tv_bt = itemView.findViewById(R.id.tv_bt);
            //tv_bt.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            int id = v.getId();
            /*
            if (id == R.id.tv_bt) {
                int position = getAdapterPosition();
                OrderResult ordata = datas.get(position);
                // 调用打印方法
                if (mOnClickListener != null && ordata != null) {
                    mOnClickListener.onClickPintItem(ordata, position);
                    mOnClickListener.onClickPintItem();
                }
            }
            */
        }

    }

    public OnClickListener mOnClickListener;

    public void setOnClickListener(OnClickListener onClickListener) {
        mOnClickListener = onClickListener;
    }

    public interface OnClickListener {
        void onClickPintItem(OrderResult ordata, int position);

        void onClickPintItem();
    }


}
