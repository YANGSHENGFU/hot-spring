package com.hotspr.ui.view;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.hotspr.R;

import java.util.ArrayList;


public class WardRoundTableView extends LinearLayout implements View.OnClickListener {

    private Context mContext ;
    private View mView ;
    private RelativeLayout allRoundLayout ;
    private RelativeLayout clearRoundLayout ;
    private RelativeLayout unClearRoundLayout ;
    private View allLine ;
    private View clearLine ;
    private View unClearLine ;

    private TextView oneText ;
    private TextView twoText ;
    private TextView threetext ;

    private ArrayList<View> viewLines ;

    private int index ;

    public WardRoundTableView(Context context) {
        super(context);
        init(context);
    }

    public WardRoundTableView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public WardRoundTableView(Context context,  AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        mContext = context ;
        viewLines = new ArrayList<>();
        mView = LayoutInflater.from(context).inflate(R.layout.view_ward_round_table_layout , this);

        allRoundLayout = mView.findViewById(R.id.all_round_layout);
        clearRoundLayout = mView.findViewById(R.id.clear_round_layout);
        unClearRoundLayout = mView.findViewById(R.id.unclear_round_layout);

        allLine = mView.findViewById(R.id.all_round_line);
        clearLine = mView.findViewById(R.id.clear_round_line);
        unClearLine = mView.findViewById(R.id.unclear_round_line);

        oneText = mView.findViewById(R.id.text_one);
        twoText = mView.findViewById(R.id.text_two);
        threetext = mView.findViewById(R.id.text_three);

        allRoundLayout.setOnClickListener(this);
        clearRoundLayout.setOnClickListener(this);
        unClearRoundLayout.setOnClickListener(this);

        viewLines.add(allLine);
        viewLines.add(clearLine);
        viewLines.add(unClearLine);
    }


    @Override
    public void onClick(View v) {
        int id = v.getId();
        if(id == R.id.all_round_layout){
            index = 0 ;
        } else if(id == R.id.clear_round_layout){
            index = 1;
        } else if(id == R.id.unclear_round_layout){
            index = 2 ;
        }
        setLineViewBackGorund(index);
        if(itemChoiceListeners != null ){
            itemChoiceListeners.itemChoice(index);
        }
    }

    private void setLineViewBackGorund(int index){
        if(viewLines == null || viewLines.size()<=0){
            return;
        }
        for(int i = 0 ; i < viewLines.size() ; i++ ){
            if(i == index ){
                viewLines.get(i).setBackgroundColor(mContext.getResources().getColor(R.color.color_D81B60));
            } else {
                viewLines.get(i).setBackgroundColor(mContext.getResources().getColor(R.color.color_ffffff));
            }
        }
    }


    public ItemChoiceListeners itemChoiceListeners;

    public interface ItemChoiceListeners{
        void itemChoice(int index);
    }

    public void setItemChoiceListeners(ItemChoiceListeners itemChoiceListeners){
        this.itemChoiceListeners = itemChoiceListeners ;
    }

    public void setTextConctent(String[] s){
        if(s!=null &&s.length>=3){
            oneText.setText(s[0]);
            twoText.setText(s[1]);
            threetext.setText(s[2]);
        }
    }

}
