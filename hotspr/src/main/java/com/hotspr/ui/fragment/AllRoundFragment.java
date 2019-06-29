package com.hotspr.ui.fragment;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.FileProvider;
import android.support.v7.widget.GridLayoutManager;
import android.text.TextUtils;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.Toast;

import com.hotspr.HttpConfig;
import com.hotspr.R;
import com.hotspr.business.api.WardRoundPressenterAPI;
import com.hotspr.business.presenter.WardRoundPressenter;
import com.hotspr.ui.activity.WardRoundActivity;
import com.hotspr.ui.adapter.RoundAdapter;
import com.hotspr.ui.bean.Round;
import com.hotspr.ui.view.SearchView;
import com.modulebase.toolkit.Compress;
import com.modulebase.toolkit.FileUtils;
import com.modulebase.toolkit.NetworkUtils;
import com.modulebase.ui.dialog.LoadDialog;
import com.modulebase.ui.fragment.BaseFragment;
import com.modulebase.view.recyclerview.adapter.LRecyclerViewAdapter;
import com.modulebase.view.recyclerview.recinterface.OnLoadMoreListener;
import com.modulebase.view.recyclerview.recinterface.OnRefreshListener;
import com.modulebase.view.recyclerview.view.LRecyclerView;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static android.app.Activity.RESULT_OK;

public class AllRoundFragment extends BaseFragment implements WardRoundPressenterAPI.View, RoundAdapter.CheckLisnter, SearchView.SearchLisnter {

    private String TAG = "AllRoundFragment";
    public static int REQUEST_CAMERA = 7;
    private static int REQUEST_CODE = 1001 ;

    private View mView;
    private Context mContext;
    private SearchView mSearchView;
    private LRecyclerView mLRecyclerView;

    private RoundAdapter mAdapter;
    private LRecyclerViewAdapter mLRecyclerViewAdapter;
    private WardRoundPressenter mPressenter;

    private int page = 1;
    private int TOLTE_PAGE_NUMBER;
    private File mFile;
    private String mLookID;
    private ImageView v_iv_goods;
    private LoadDialog mLoadDialog ;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        if (mView == null) {
            mContext = getContext();
            mView = LayoutInflater.from(mContext).inflate(R.layout.fragment_all_round_layout, null);
            findView(mView);
            initLRecyclerView();
            initData();
            initDilaog();
        }
        ViewGroup parent = (ViewGroup) mView.getParent();
        if (parent != null) {
            parent.removeView(mView);
        }
        return mView;
    }

    private void findView(View view) {
        mSearchView = view.findViewById(R.id.searchView);
        mLRecyclerView = view.findViewById(R.id.lrecyclerview);
        mSearchView.setSearchLisnter(this);
    }

    private void initDilaog(){
        mLoadDialog = new LoadDialog(mContext);
        mLoadDialog.setCanceledOnTouchOutside(true);
        mLoadDialog.setCancelable(true);
    }

    private void initLRecyclerView() {

        mLRecyclerView.setLayoutManager(new GridLayoutManager(mContext, 3)); // 不设会不显示
        mAdapter = new RoundAdapter(mContext);
        mAdapter.setCheckLisnter(this);
        mLRecyclerViewAdapter = new LRecyclerViewAdapter(mAdapter);
        mLRecyclerView.setAdapter(mLRecyclerViewAdapter);
        mLRecyclerView.setHeaderViewColor(R.color.colorAccent, R.color.color_5e5656, android.R.color.white); //设置头部加载颜色
        mLRecyclerView.setFooterViewColor(R.color.colorAccent, R.color.color_5e5656, android.R.color.white); //设置底部加载颜色
        mLRecyclerView.setFooterViewHint("努力加载中", "已全部加载完", "网络不给力啊，点击再试一次吧");
        mLRecyclerView.setHasFixedSize(true);                    //这是item的固定大小
        // 刷新
        mLRecyclerView.setOnRefreshListener(new OnRefreshListener() {
            @Override
            public void onRefresh() {
                if (!NetworkUtils.isNetworkConnected(mContext)) {
                    mLRecyclerView.refreshComplete(0);  //不调用这句就表示没有刷新成功(不会回上去)
                    mAdapter.notifyDataSetChanged();
                    Toast.makeText(mContext, "当前网络不可用,请检查网络设置", Toast.LENGTH_SHORT).show();
                    return;
                }
                mAdapter.upData(new ArrayList<Round>()); // 清空数据
                page = 1;
                load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page );
            }
        });
        // 加载更多
        mLRecyclerView.setOnLoadMoreListener(new OnLoadMoreListener() {
            @Override
            public void onLoadMore() {
                if (page < TOLTE_PAGE_NUMBER) {
                    load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_MORE , ++page );
                } else {
                    mLRecyclerView.setNoMore(true); //没有更多了
                }
            }
        });
    }


    private void initData() {
        mPressenter = new WardRoundPressenter(mContext, this);
        mPressenter.isRsh = true;
        load(mSearchView.getFloor() , mSearchView.getRoomType() , mSearchView.getRoomNumber() , WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page );
    }

    /**
     * 数据加载成功
     *
     * @param rounds
     */
    @Override
    public void upDatd(int mode , ArrayList<Round> rounds , int pageNumber) {
        if (rounds != null) {
            mLRecyclerView.refreshComplete(rounds.size());  // 不调用这句方法就表示没有刷新成功
            if (mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH ||
                    mode == WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH) {
                mAdapter.upData(rounds);
            } else {
                mAdapter.addData(rounds);
            }
        }else{
            mLRecyclerView.refreshComplete(0);  // 不调用这句方法就表示没有刷新成功
        }
        if (pageNumber >= 0) {
            TOLTE_PAGE_NUMBER = pageNumber;
        }
        if(mLoadDialog!=null && mLoadDialog.isShowing()){
            mLoadDialog.dismiss();
        }
    }

    /**
     * 重新请求
     */
    public void reRequest() {
        page = 1 ;
        mPressenter.loadData(WardRoundPressenterAPI.Pressente.LOAD_MODLE_REFRASH , page , null);
    }

    /**
     * 查房
     * @param round
     */
    @Override
    public void check(int i , Round round) {
        Bundle bundle = new Bundle();
        Intent intent = new Intent(mContext , WardRoundActivity.class);
        bundle.putParcelable(WardRoundActivity.round_key , round);
        bundle.putInt(WardRoundActivity.code_key , REQUEST_CODE);
        bundle.putInt(WardRoundActivity.index_key,i);
        intent.putExtras(bundle);
        startActivityForResult(intent , REQUEST_CODE);
    }


    /**
     * 上传图片结果
     *
     * @param isOK
     */
    @Override
    public void uploadPhoto(boolean isOK) {
        if (isOK) {
            mFile.delete(); // 上传成功后，删除照片
        } else {
            // 上传加载框消失
        }
    }

    /**
     * 上传图片按钮
     *
     * @param
     */
    @Override
    public void check(String lookID, ImageView iv_goods) {
        mLookID = lookID;
        v_iv_goods = iv_goods;
        Log.d(TAG, "check mLookID = " + mLookID);
        if (ActivityCompat.checkSelfPermission(mContext, Manifest.permission.CAMERA) != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(mContext, "请打开相机权限", Toast.LENGTH_SHORT).show();
        } else {
            Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
            mFile = new File(FileUtils.PHOTODIR + mLookID + ".jpg");
            mFile.getParentFile().mkdirs();
            //判断版本
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {   //如果在Android7.0以上,使用FileProvider获取Uri
                Uri contentUri = FileProvider.getUriForFile(mContext, "ywq.com.fileprovider", mFile);
                intent.setFlags(Intent.FLAG_GRANT_WRITE_URI_PERMISSION);
                intent.putExtra(MediaStore.EXTRA_OUTPUT, contentUri);
            } else {    //否则使用Uri.fromFile(file)方法获取Uri
                intent.putExtra(MediaStore.EXTRA_OUTPUT, Uri.fromFile(mFile));
            }
            startActivityForResult(intent, REQUEST_CAMERA);
        }
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        Log.d(TAG, "into onActivityResult");

        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == REQUEST_CAMERA && resultCode == RESULT_OK) {
            //先压缩图片，自动判断是否要压缩
            Compress.compress(mFile.getAbsolutePath(), 500, 0, mFile.getAbsolutePath());
            // 显示上传加载框
            mPressenter.uploadPhoto(mLookID, FileUtils.imgToBase64(mFile.getAbsolutePath()));
            Bitmap bitmap = getLoacalBitmap(mFile.getAbsolutePath());//显示本地照片
            v_iv_goods.setImageBitmap(bitmap);
        } else if(requestCode == REQUEST_CODE && resultCode == RESULT_OK){
            if(data!=null){
                Bundle bundle = data.getExtras() ;
                if(bundle!=null){
                    Round round = bundle.getParcelable(WardRoundActivity.resrt_round_key);
                    int index = bundle.getInt(WardRoundActivity.resrt_index_key);
                    if(round!=null){
                        mAdapter.getData().remove(index);
                        mAdapter.getData().add(index , round);
                        mAdapter.notifyItemChanged(index);
                    }
                }
            }
        }
    }

    /**
     * 加载本地图片
     *
     * @param url
     * @return
     */
    public Bitmap getLoacalBitmap(String url) {
        try {
            FileInputStream fis = new FileInputStream(url);
            return BitmapFactory.decodeStream(fis);  ///把流转化为Bitmap图片
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return null;
        }
    }

    public void check_out(String lookID, String memo) {
        //mPressenter.check_out(lookID,memo);
    }

    @Override
    public void onPause() {
        super.onPause();
    }

    /**
     * 搜索
     * @param floor
     * @param roomType
     * @param roomNumber
     */
    @Override
    public void search(String floor , String roomType , String roomNumber) {
        mAdapter.upData(new ArrayList<Round>()); // 清空数据
        mLoadDialog.show();
        page = 1  ;
        load(floor , roomType , roomNumber ,WardRoundPressenterAPI.Pressente.LOAD_MODLE_SEARCH , page );
    }


    /**
     * 加载数据
     * @param floor
     * @param roomType
     * @param roomNumber
     * @param lodelModel
     * @param page
     */
    private void load(String floor , String roomType , String roomNumber , int lodelModel , int page){
        if (!TextUtils.isEmpty(floor) || !TextUtils.isEmpty(roomType) || !TextUtils.isEmpty(roomNumber)) {
            Map<String, String> searchParamsMap = new HashMap<>();
            if (!TextUtils.isEmpty(floor)) {
                searchParamsMap.put(HttpConfig.Field.floor, floor);
            }
            if (!TextUtils.isEmpty(roomType)) {
                searchParamsMap.put(HttpConfig.Field.type_class, roomType);
            }
            if (!TextUtils.isEmpty(roomNumber)) {
                searchParamsMap.put(HttpConfig.Field.room, roomNumber);
            }
            mPressenter.loadData(lodelModel, page, searchParamsMap);
        } else {
            mPressenter.loadData(lodelModel, page , null);
        }
    }


}
