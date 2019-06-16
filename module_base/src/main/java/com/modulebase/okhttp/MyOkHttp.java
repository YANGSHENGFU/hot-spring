package com.modulebase.okhttp;

import android.content.Context;
import android.os.Handler;
import android.util.Log;
import android.widget.Toast;

import com.google.gson.Gson;
import com.modulebase.toolkit.MD5;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.Headers;
import okhttp3.Interceptor;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * 封装好的MyOkhttp
 * Created by tsy on 16/8/15.
 */
public class MyOkHttp {

    private String TAG = "MyOkHttp";
    private OkHttpClient client ;
    private static MyOkHttp instance ;
    public MyOkHttp() {
        client = new OkHttpClient();
    }

    /**
     * 获取句柄
     * @return
     */
    public static MyOkHttp get() {
        if(instance == null) {
            instance = new MyOkHttp() ;
        }
        return instance ;
    }

    /**
     * post 请求
     * @param url url
     * @param params 参数
     * @param responseHandler 回调
     */
    public void post(final String url , final Map<String, String> params, final IResponseHandler responseHandler) {
        post(null, url, params, responseHandler);
    }

    /**
     * post 请求
     * @param context 发起请求的context
     * @param url url
     * @param params 参数
     * @param responseHandler 回调
     */
    public void post( Context context , final String url , final Map<String, String> params , final IResponseHandler responseHandler ) {
        //post builder 参数
        FormBody.Builder builder = new FormBody.Builder();
        StringBuffer link = new StringBuffer();
        link.append(url);
        if(params != null && params.size() > 0) {
            try{
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.add(entry.getKey() , entry.getValue());
                    link.append(entry.getKey());
                    link.append("=");
                    link.append(entry.getValue());
                    link.append("&");
                }
            }catch (Exception e){
                Log.d("post_url" ,  "解析链接异常" ) ;
                Toast.makeText( context , "缺少必要的字段" , Toast.LENGTH_SHORT).show();
            }
        }
        try{
            Log.d("post_url" ,  link.toString().substring(0 , link.toString().length()-1) ) ;
        }catch (Exception e){
            Log.d("post_url" ,  "链接拼接异常" ) ;
        }
        Request request;
        //发起request
        if(context == null) {
            request = new Request.Builder().url(url).post(builder.build()).build();
        } else {
            request = new Request.Builder().url(url).post(builder.build()).tag(context).build();
        }
        client.newCall(request).enqueue(new MyCallback(new Handler(), responseHandler));
    }

    /**
     * post 求情 需要签名
     * @param context
     * @param url
     * @param params
     * @param responseHandler
     */
    public void postSign( Context context , final String url , final Map<String, String> params , final IResponseHandler responseHandler ) {
        //post builder 参数
        FormBody.Builder builder = new FormBody.Builder();
        StringBuffer link = new StringBuffer();
        StringBuffer sb = new StringBuffer();    //存放签名的
        link.append(url);
        if(params != null && params.size() > 0) {
            try{
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.add(entry.getKey() , entry.getValue());
                    link.append(entry.getKey());
                    link.append("=");
                    link.append(entry.getValue());
                    link.append("&");
                    sb.append(entry.getKey());
                    sb.append("=");
                    sb.append(entry.getValue());
                    sb.append("&");
                }
            }catch (Exception e){
                Log.d("post_url" ,  "解析链接异常" ) ;
                Toast.makeText( context , "缺少必要的字段" , Toast.LENGTH_SHORT).show();
            }
        }
        try{
            //获取字段的签名
            String s = sb.subSequence( 0 , sb.toString().length()-1 ).toString();
            Log.d("s" ,  s ) ;
            String sing = MD5.encode(s.getBytes() ) ;  // 不包含image的签名
            builder.add("sign" ,  sing );
            link.append("sign=") ;
            link.append(sing) ;
            Log.d("post_url" ,  link.toString() ) ;
        }catch (Exception e){
            Log.d("post_url" ,  "链接拼接异常" ) ;
        }
        Request request;
        //发起request
        if(context == null) {
            request = new Request.Builder().url(url).post(builder.build()).build();
        } else {
            request = new Request.Builder().url(url).post(builder.build()).tag(context).build();
        }
        client.newCall(request).enqueue(new MyCallback(new Handler(), responseHandler));
    }

    /**
     * post 请求
     * @param context 发起请求的context
     * @param url url
     * @param params 参数
     * @param responseHandler 回调
     */
    public void postImage( Context context , final String url , final Map<String , String> params , final IResponseHandler responseHandler ) {
        //post builder 参数
        FormBody.Builder builder = new FormBody.Builder() ;
        StringBuffer link = new StringBuffer() ;  // 存放全部链接的
        StringBuffer sb = new StringBuffer() ;    // 存放签名的
        link.append(url) ;
        if(params != null && params.size() > 0) {
            try{
                for (Map.Entry<String, String> entry : params.entrySet()) {
                    builder.add( entry.getKey() , entry.getValue());
                    link.append(entry.getKey());
                    link.append("=") ;
                    link.append(entry.getValue()) ;
                    link.append("&") ;
                    if( !entry.getKey().equals("img") && !entry.getKey().equals("imgtem")){
                        sb.append(entry.getKey()) ;
                        sb.append("=") ;
                        sb.append(entry.getValue()) ;
                        sb.append("&") ;
                    }
                }
            }catch (Exception e){
                Log.d(TAG ,  "解析链接异常" ) ;
                Toast.makeText( context , "缺少必要的字段" , Toast.LENGTH_SHORT).show();
            }
        }
        try{
            String sing = MD5.encode( sb.subSequence( 0 , sb.length() -1 ).toString().getBytes() ) ;  //不包含image的签名
            builder.add("sign" ,  sing ) ;
            link.append("sign=") ;
            link.append(sing) ;
            Log.d(TAG , "postImage = " + link.toString() ) ;
        }catch (Exception e){
            Log.d( TAG ,  "链接拼接异常" ) ;
        }
        Request request ;
        //发起request
        if( context == null ) {
            request = new Request.Builder().url(url).post(builder.build()).build();
        } else {
            request = new Request.Builder().url(url).post(builder.build()).tag(context).build();
        }
        client.newCall(request).enqueue(new MyCallback(new Handler(), responseHandler));
    }

    /**
     * get 请求
     * @param url url
     * @param params 参数
     * @param responseHandler 回调
     */
    public void get(final String url, final Map<String, String> params, final IResponseHandler responseHandler) {
        get(null, url, params, responseHandler);
    }

    /**
     * get 请求
     * @param context 发起请求的context
     * @param url url
     * @param params 参数
     * @param responseHandler 回调
     */
    public void get( Context context , final String url , final Map<String , String> params , final IResponseHandler responseHandler ) {
        //拼接url
        String get      = url ;
        String para_url = ""  ;
        String get_url  = ""  ;
        if( params != null && params.size() > 0 ) {
            int i = 0 ;
            try{
                for (Map.Entry< String , String > entry : params.entrySet()) {
                    if( i++ == 0 ) {
                        para_url = entry.getKey() + "=" + entry.getValue() ;
                    } else {
                        para_url = para_url + "&" + entry.getKey() + "=" + entry.getValue() ;
                    }
                }
            }catch (Exception e){
                Log.d("get_url" ,  "解析链接异常 ," ) ;
                Toast.makeText( context , "缺少必要的字段" , Toast.LENGTH_SHORT).show();
            }
        }
        try{
            String sing = MD5.encode( para_url.getBytes() ) ;  //获取参数字段的MD5值
            get_url = get + para_url +"&sign=" + sing ;        //最后的链接
            Log.d("get_url" , get_url ) ;
        }catch (Exception e){
            Log.d("get_url" ,  "链接拼接异常" ) ;
        }
        Request request ;
        //发起request
        if(context == null) {
            request = new Request.Builder().url(get_url).build();
        } else {
            request = new Request.Builder().url(get_url).tag(context).build();
        }
        //Toast.makeText(context , get_url  ,Toast.LENGTH_LONG).show();
        client.newCall(request).enqueue(new MyCallback(new Handler(), responseHandler));
    }

    /**
     * 上传文件
     * @param url url
     * @param files 上传的文件files
     * @param responseHandler 回调
     */
    public void upload(String url, Map<String, File> files, final IResponseHandler responseHandler) {
        upload(null, url, null, files, responseHandler);
    }

    /**
     * 上传文件
     * @param url url
     * @param params 参数
     * @param files 上传的文件files
     * @param responseHandler 回调
     */
    public void upload(String url , Map<String, String> params, Map<String , File> files , final IResponseHandler responseHandler) {
        upload(null, url , params , files , responseHandler );
    }

    /**
     * 上传文件
     * @param context 发起请求的context
     * @param url url
     * @param files 上传的文件files
     * @param responseHandler 回调
     */
    public void upload(Context context, String url, Map<String, File> files, final IResponseHandler responseHandler) {
        upload(context, url, null, files, responseHandler);
    }

    /**
     * 上传文件
     * @param context 发起请求的context
     * @param url url
     * @param params 参数
     * @param files 上传的文件files
     * @param responseHandler 回调
     */
    public void upload(Context context, String url, Map<String, String> params, Map<String, File> files, final IResponseHandler responseHandler) {
        MultipartBody.Builder multipartBuilder = new MultipartBody.Builder().setType(MultipartBody.FORM);

        //添加参数
        if (params != null && !params.isEmpty()) {
            for (String key : params.keySet()) {
                multipartBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\""),
                        RequestBody.create(null, params.get(key)));
            }
        }

        //添加上传文件
        if (files != null && !files.isEmpty()) {
            RequestBody fileBody;
            for (String key : files.keySet()) {
                File file = files.get(key);
                String fileName = file.getName();
                fileBody = RequestBody.create(MediaType.parse(guessMimeType(fileName)), file);
                multipartBuilder.addPart(Headers.of("Content-Disposition", "form-data; name=\"" + key + "\"; filename=\"" + fileName + "\""), fileBody);
            }
        }

        Request request;
        if(context == null) {
            request = new Request.Builder().url(url).post(new ProgressRequestBody(multipartBuilder.build(),responseHandler)).build();
        } else {
            request = new Request.Builder().url(url).post(new ProgressRequestBody(multipartBuilder.build(),responseHandler)).tag(context).build();
        }
        client.newCall(request).enqueue(new MyCallback(new Handler(), responseHandler));
    }

    /**
     * 下载文件
     * @param url 下载地址
     * @param filedir 下载目的目录
     * @param filename 下载目的文件名
     * @param downloadResponseHandler 下载回调
     */
    public void download( String url , String filedir , String filename , final DownloadResponseHandler downloadResponseHandler) {
        download( null , url , filedir , filename , downloadResponseHandler );
    }

    /**
     * 下载文件
     * @param context 发起请求的context
     * @param url 下载地址
     * @param filedir 下载目的目录
     * @param filename 下载目的文件名
     * @param downloadResponseHandler 下载回调
     */
    public void download( Context context , String url , String filedir , String filename , final DownloadResponseHandler downloadResponseHandler) {
        Request request ;
        if(context == null) {
            request = new Request.Builder().url(url).build() ;
        } else {
            request = new Request.Builder().url(url).tag(context).build();
        }
        client.newBuilder().addNetworkInterceptor(new Interceptor() {      //设置拦截器
                    @Override
                    public Response intercept(Chain chain) throws IOException {
                        Response originalResponse = chain.proceed(chain.request());
                        return originalResponse.newBuilder().body(new ResponseProgressBody(originalResponse.body(), downloadResponseHandler)).build();
                    }
                }).build().newCall(request).enqueue( new MyDownloadCallback(new Handler(), downloadResponseHandler, filedir, filename));
    }

    /**
     * 取消当前context的所有请求
     * @param context
     */
    public void cancel(Context context) {
        if(client != null) {
            for(Call call : client.dispatcher().queuedCalls()) {
                if(call.request().tag().equals(context))
                    call.cancel();
            }
            for(Call call : client.dispatcher().runningCalls()) {
                if(call.request().tag().equals(context))
                    call.cancel();
            }
        }
    }

    //下载回调
    private class MyDownloadCallback implements Callback {
        private Handler mHandler;
        private DownloadResponseHandler mDownloadResponseHandler;
        private String mFileDir;
        private String mFilename;

        public MyDownloadCallback(Handler handler , DownloadResponseHandler downloadResponseHandler, String filedir, String filename) {
            mHandler = handler ;
            mDownloadResponseHandler = downloadResponseHandler;
            mFileDir = filedir ;
            mFilename = filename ;
        }

        @Override
        public void onFailure(Call call , final IOException e) {
            LogUtils.e("onFailure", e);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mDownloadResponseHandler.onFailure(e.toString());
                }
            });
        }

        @Override
        public void onResponse(Call call , final Response response) throws IOException {
            if(response.isSuccessful()) {
                File file = null;
                try {
                    file = saveFile( response , mFileDir , mFilename , mDownloadResponseHandler );
                } catch (final IOException e) {
                    LogUtils.e("onResponse saveFile fail", e) ;
                    mHandler.post( new Runnable() {
                        @Override
                        public void run() {
                            mDownloadResponseHandler.onFailure("onResponse saveFile fail." + e.toString());
                        }
                    });
                }
                final File finalFile = file;
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mDownloadResponseHandler.onFinish(finalFile);
                    }
                });
            } else {
                LogUtils.e("onResponse fail status=" + response.code());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mDownloadResponseHandler.onFailure("fail status=" + response.code());
                    }
                });
            }
        }
    }

    /**
     *  callback
     */
    private class MyCallback implements Callback {
        private Handler mHandler;
        private IResponseHandler mResponseHandler;
        public MyCallback(Handler handler, IResponseHandler responseHandler) {
            mHandler = handler;
            mResponseHandler = responseHandler;
        }
        @Override
        public void onFailure(Call call, final IOException e) {
            LogUtils.e("onFailure", e);
            mHandler.post(new Runnable() {
                @Override
                public void run() {
                    mResponseHandler.onFailure( 0 , e.toString()) ;
                }
            });
        }
        @Override
        public void onResponse( Call call , final Response response ) throws IOException {
            if(response.isSuccessful()) {
                final String response_body = response.body().string();
                if( mResponseHandler instanceof JsonResponseHandler ) {       //json回调
//                    try {
//                        final JSONObject jsonBody = new JSONObject(response_body);
                        mHandler.post( new Runnable() {
                            @Override
                            public void run() {
                                ((JsonResponseHandler)mResponseHandler).onSuccess(response.code(), response_body);
                            }
                        });
//                    }
//                    catch (JSONException e) {
//                        LogUtils.e("onResponse fail parse jsonobject, body=" + response_body);
//                        mHandler.post(new Runnable() {
//                            @Override
//                            public void run() {
//                                mResponseHandler.onFailure(response.code(), "fail parse jsonobject, body=" + response_body);
//                            }
//                        });
//                    }
                } else if(mResponseHandler instanceof GsonResponseHandler) {    //gson回调
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            try {
                                Gson gson = new Gson();
                                ((GsonResponseHandler)mResponseHandler).onSuccess(response.code(),
                                        gson.fromJson(response_body, ((GsonResponseHandler)mResponseHandler).getType()));
                            } catch (Exception e) {
                                LogUtils.e("onResponse fail parse gson, body=" + response_body, e);
                                mResponseHandler.onFailure(response.code(), "fail parse gson, body=" + response_body);
                            }
                        }
                    });
                } else if(mResponseHandler instanceof RawResponseHandler) {     //raw字符串回调
                    mHandler.post(new Runnable() {
                        @Override
                        public void run() {
                            ((RawResponseHandler)mResponseHandler).onSuccess(response.code(), response_body);
                        }
                    });
                }
            } else {
                LogUtils.e("onResponse fail status=" + response.code());
                mHandler.post(new Runnable() {
                    @Override
                    public void run() {
                        mResponseHandler.onFailure(0, "fail status=" + response.code());
                    }
                });
            }
        }
    }

    /**
     *  保存文件
     * filedir 文件目录
     * filename 文件的名字
     */
    private File saveFile( Response response , String filedir , String filename , DownloadResponseHandler downloadResponseHandler ) throws IOException {
        InputStream is = null ;
        byte[] buf = new byte[1024] ;
        int len ;
        long total ;
        long currentProgress = 0 ;
        FileOutputStream fos = null ;
        File file = null ;
        try {
            total = response.body().contentLength() ;
            is = response.body().byteStream() ;
            File dir = new File(filedir) ;
            if (!dir.exists()) {
                dir.mkdirs() ;
            }
            file = new File(dir , filename ) ;  // 文件目录 文件名
            if(file.exists()){   // 文件存在
                file.delete() ;  // 先删除
            }
            fos = new FileOutputStream(file) ;
            while ((len = is.read(buf)) != -1) {
                fos.write(buf , 0 , len ) ;
                currentProgress = currentProgress + len ;
                Log.d("PINYUN" , "len = "+ String.valueOf(len)) ;
                Log.d("PINYUN" , "currentProgress = " + String.valueOf(currentProgress)) ;
                downloadResponseHandler.onProgress( currentProgress ,total ) ; //进度回调
            }
            fos.flush() ; // 一定要刷新一下 才能完成保存到文件中
        } finally {
            try {
                if (is != null) {
                    is.close() ; // 关闭流
                }
            } catch (IOException e) {
            }
            try {
                if (fos != null){
                    fos.close();  //关闭流
                }
            } catch (IOException e) {
            }
            return file ;
        }
    }

    //获取mime type
    private String guessMimeType(String path) {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(path);
        if (contentTypeFor == null) {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}

