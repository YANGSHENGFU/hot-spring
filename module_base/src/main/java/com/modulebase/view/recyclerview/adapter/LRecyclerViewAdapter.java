package com.modulebase.view.recyclerview.adapter;

import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.View;
import android.view.ViewGroup;

import com.modulebase.view.recyclerview.recinterface.IRefreshHeader;
import com.modulebase.view.recyclerview.recinterface.OnItemClickListener;
import com.modulebase.view.recyclerview.recinterface.OnItemLongClickListener;

import java.util.ArrayList;
import java.util.List;


public class LRecyclerViewAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final int TYPE_REFRESH_HEADER = 10000;
    private static final int TYPE_NORMAL = 0;
    private static final int TYPE_FOOTER_VIEW = 10001;
    private static final int HEADER_INIT_INDEX = 10002;
    private static List<Integer> mHeaderTypes = new ArrayList<>();

    private IRefreshHeader mRefreshHeader;                    //刷新

    private OnItemClickListener mOnItemClickListener;         //点击
    private OnItemLongClickListener mOnItemLongClickListener; //长安

    /**
     * RecyclerView使用的，真正的Adapter
     */
    private RecyclerView.Adapter mInnerAdapter;

    private ArrayList<View> mHeaderViews = new ArrayList<>();  //存放头View
    private ArrayList<View> mFooterViews = new ArrayList<>();  //存放尾View

    private SpanSizeLookup mSpanSizeLookup;


    public LRecyclerViewAdapter(RecyclerView.Adapter innerAdapter) {
        this.mInnerAdapter = innerAdapter;
    }

    public void setRefreshHeader(IRefreshHeader refreshHeader){
        mRefreshHeader = refreshHeader;
    }

    public RecyclerView.Adapter getInnerAdapter() {
        return mInnerAdapter;
    }

    /**
     * 添加头
     * @param view
     */
    public void addHeaderView(View view) {
        if (view == null) {
            throw new RuntimeException("header is null");
        }
        mHeaderTypes.add(HEADER_INIT_INDEX + mHeaderViews.size());
        mHeaderViews.add(view);
    }

    /**
     * 添加尾
     * @param view
     */
    public void addFooterView(View view) {
        if (view == null) {
            throw new RuntimeException("footer is null");
        }
        removeFooterView();
        mFooterViews.add(view);
    }

    /**
     * 根据header的ViewType判断是哪个header
     * @param itemType
     * @return
     */
    private View getHeaderViewByType(int itemType) {
        if(!isHeaderType(itemType)) {
            return null;
        }
        return mHeaderViews.get(itemType - HEADER_INIT_INDEX);
    }

    /**
     * 判断一个type是否为HeaderType
     * @param itemViewType
     * @return
     */
    private boolean isHeaderType(int itemViewType) {
        return  mHeaderViews.size() > 0 &&  mHeaderTypes.contains(itemViewType);
    }

    /**
     * 返回第一个FootView
     * @return
     */
    public View getFooterView() {
        return  getFooterViewsCount()>0 ? mFooterViews.get(0) : null;
    }

    /**
     * 返回第一个HeaderView
     * @return
     */
    public View getHeaderView() {
        return  getHeaderViewsCount()>0 ? mHeaderViews.get(0) : null;
    }

    /**
     * 获取所有的头View
     * @return
     */
    public ArrayList<View> getHeaderViews() {
        return mHeaderViews;
    }

    /**
     * 移除头view
     */
    public void removeHeaderView() {
        if (getHeaderViewsCount() > 0) {
            View headerView = getHeaderView();
            mHeaderViews.remove(headerView);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 移除尾view
     */
    public void removeFooterView() {
        if (getFooterViewsCount() > 0) {
            View footerView = getFooterView();
            mFooterViews.remove(footerView);
            this.notifyDataSetChanged();
        }
    }

    /**
     * 获取所有的头view的数目
     * @return
     */
    public int getHeaderViewsCount() {
        return mHeaderViews.size();
    }

    /**
     * 获取所有的尾view的数目
     * @return
     */
    public int getFooterViewsCount() {
        return mFooterViews.size();
    }

    /**
     *
     * @param position
     * @return
     */
    public boolean isHeader(int position) {
        return position >= 1 && position < mHeaderViews.size() + 1;
    }

    /**
     *
     * @param position
     * @return
     */
    public boolean isRefreshHeader(int position) {
        return position == 0;
    }

    /**
     *
     * @param position
     * @return
     */
    public boolean isFooter(int position) {
        int lastPosition = getItemCount() - getFooterViewsCount();
        return getFooterViewsCount() > 0 && position >= lastPosition;
    }

    /**
     *
     * @param parent
     * @param viewType
     * @return
     */
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        if (viewType == TYPE_REFRESH_HEADER ) {                    //先返回头
            return new ViewHolder(mRefreshHeader.getHeaderView());
        } else if (isHeaderType(viewType)) {
            return new ViewHolder(getHeaderViewByType(viewType));
        } else if (viewType == TYPE_FOOTER_VIEW) {                 //返回尾
            return new ViewHolder(mFooterViews.get(0));
        }
        return mInnerAdapter.onCreateViewHolder(parent, viewType);    //调用传进来的 Adpater的onCreateViewHolder的方法
    }

    /**
     *
     * @param holder
     * @param position
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder, int position) {
        if (isHeader(position) || isRefreshHeader(position)) {
            return;
        }
        final int adjPosition = position - (getHeaderViewsCount() + 1);
        int adapterCount;
        if (mInnerAdapter != null) {
            adapterCount = mInnerAdapter.getItemCount();     //调用传进来的Adpter 的getItemCount（）
            if (adjPosition < adapterCount) {
                mInnerAdapter.onBindViewHolder(holder, adjPosition);    //调用传进来的Adpter 的onBindViewHolder（）
                if (mOnItemClickListener != null) {
                    holder.itemView.setOnClickListener(new View.OnClickListener()  {
                        @Override
                        public void onClick(View v) {
                            mOnItemClickListener.onItemClick(holder.itemView, adjPosition);
                        }
                    });
                }

                if (mOnItemLongClickListener != null) {
                    holder.itemView.setOnLongClickListener(new View.OnLongClickListener() {
                        @Override
                        public boolean onLongClick(View v) {
                            mOnItemLongClickListener.onItemLongClick(holder.itemView, adjPosition);
                            return true;
                        }
                    });
                }
            }
        }
    }

    /**
     *
     * @param holder
     * @param position
     * @param payloads
     */
    @Override
    public void onBindViewHolder(final RecyclerView.ViewHolder holder , int position , List<Object> payloads) {
        if (payloads.isEmpty()) {
            onBindViewHolder(holder ,position);   //调用传进来的Adpter 的onBindViewHolder（）
        } else {
            if (isHeader(position) || isRefreshHeader(position)) {
                return;
            }
            final int adjPosition = position - (getHeaderViewsCount() + 1);
            int adapterCount;
            if (mInnerAdapter != null) {
                adapterCount = mInnerAdapter.getItemCount();     //调用传进来的Adpter 的getItemCount（）
                if (adjPosition < adapterCount) {
                    mInnerAdapter.onBindViewHolder(holder, adjPosition, payloads);  //调用传进来的Adpter 的onBindViewHolder（）
                }
            }
        }
    }

    /**
     *
     * @return
     */
    @Override
    public int getItemCount() {
        if (mInnerAdapter != null) {
            return getHeaderViewsCount() + getFooterViewsCount() + mInnerAdapter.getItemCount() + 1;
        } else {
            return getHeaderViewsCount() + getFooterViewsCount() + 1;
        }
    }

    /**
     *
     * @param position
     * @return
     */
    @Override
    public int getItemViewType(int position) {
        int adjPosition = position - (getHeaderViewsCount() + 1);
        if (isRefreshHeader(position)) {
            return TYPE_REFRESH_HEADER;
        }
        if (isHeader(position)) {
            position = position - 1;
            return mHeaderTypes.get(position);
        }
        if (isFooter(position)) {
            return TYPE_FOOTER_VIEW;
        }
        int adapterCount;
        if (mInnerAdapter != null) {
            adapterCount = mInnerAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mInnerAdapter.getItemViewType(adjPosition);
            }
        }
        return TYPE_NORMAL;
    }

    /**
     * @param position
     * @return
     */
    @Override
    public long getItemId(int position) {
        if (mInnerAdapter != null && position >= getHeaderViewsCount()) {
            int adjPosition = position - getHeaderViewsCount();
            //判断是否setHasStableIds(true);
            if(hasStableIds()) {
                adjPosition-- ;
            }
            int adapterCount = mInnerAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return mInnerAdapter.getItemId(adjPosition);
            }
        }
        return -1;
    }

    /**
     *
     * @param recyclerView
     */
    @Override
    public void onAttachedToRecyclerView(RecyclerView recyclerView) {
        super.onAttachedToRecyclerView(recyclerView);
        RecyclerView.LayoutManager manager = recyclerView.getLayoutManager();
        if (manager instanceof GridLayoutManager) {
            final GridLayoutManager gridManager = ((GridLayoutManager) manager);
            gridManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                @Override
                public int getSpanSize(int position) {
                    if (mSpanSizeLookup == null) {
                        return (isHeader(position) || isFooter(position) || isRefreshHeader(position))
                                ? gridManager.getSpanCount() : 1;
                    } else {
                        return (isHeader(position) || isFooter(position) || isRefreshHeader(position))
                                ? gridManager.getSpanCount() : mSpanSizeLookup.getSpanSize(gridManager,  ( position - (getHeaderViewsCount() + 1) ));
                    }
                }
            });
        }
        mInnerAdapter.onAttachedToRecyclerView(recyclerView);
    }

    @Override
    public void onDetachedFromRecyclerView(RecyclerView recyclerView) {
        mInnerAdapter.onDetachedFromRecyclerView(recyclerView);
    }

    @Override
    public void onViewAttachedToWindow(RecyclerView.ViewHolder holder) {
        super.onViewAttachedToWindow(holder);
        ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
        if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
            if(isHeader(holder.getLayoutPosition()) ||isRefreshHeader(holder.getLayoutPosition()) || isFooter(holder.getLayoutPosition())) {
                StaggeredGridLayoutManager.LayoutParams p = (StaggeredGridLayoutManager.LayoutParams) lp;
                p.setFullSpan(true);
            }
        }
        mInnerAdapter.onViewAttachedToWindow(holder);
    }

    @Override
    public void onViewDetachedFromWindow(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewDetachedFromWindow(holder);
    }

    @Override
    public void onViewRecycled(RecyclerView.ViewHolder holder) {
        mInnerAdapter.onViewRecycled(holder);
    }


    public static class ViewHolder extends RecyclerView.ViewHolder {
        public ViewHolder(View itemView) {
            super(itemView);
        }
    }

    /**
     *
     * @param isCallback whether position is from callback interface
     * @param position
     * @return
     */
    public int getAdapterPosition(boolean isCallback, int position) {
        if(isCallback) {
            int adjPosition = position - (getHeaderViewsCount() + 1);
            int adapterCount = mInnerAdapter.getItemCount();
            if (adjPosition < adapterCount) {
                return adjPosition;
            }
        }else {
            return  (position + getHeaderViewsCount()) + 1;
        }

        return -1;
    }

    /**
     *
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener) {
        this.mOnItemClickListener = itemClickListener;
    }

    /**
     *
     * @param itemLongClickListener
     */
    public void setOnItemLongClickListener(OnItemLongClickListener itemLongClickListener) {
        this.mOnItemLongClickListener = itemLongClickListener;
    }

    /**
     *
     */
    public interface SpanSizeLookup {
        int getSpanSize(GridLayoutManager gridLayoutManager, int position);
    }

    /**
     * @param spanSizeLookup
     * only used to GridLayoutManager
     */
    public void setSpanSizeLookup(SpanSizeLookup spanSizeLookup) {
        this.mSpanSizeLookup = spanSizeLookup;
    }

}