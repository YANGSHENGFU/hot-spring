package com.modulebase.toolkit;

import android.content.Context;
import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Base64;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

public class FileUtils {

    public static String SDCRADIR = Environment.getExternalStorageDirectory().getAbsolutePath() ;
    public static String ROOTDIR = "/HotSpring" ;
    public static String LOG = "/log";
    public static String CACHEDIR = SDCRADIR+ROOTDIR+LOG+"/cach/" ;
    public static String XLOGDIR  = SDCRADIR+ROOTDIR+LOG+"/xlog/" ;
    public static String CRASHDIR = SDCRADIR+ROOTDIR+LOG+"/crash/" ;
    public static String PHOTODIR = SDCRADIR+ROOTDIR+"/photo/" ;



    public static String getCacheDir( Context context ){
        if( context != null ){
            return context.getCacheDir().getAbsolutePath();
        }else{
            return "" ;
        }
    }

    public static String replaceString( String url){
        url = url.replace("\\","")
                .replace("/" , "a")
                .replace("?" , "a" )
                .replace(":", "a")
                .replace("-", "a")
                .replace("_" , "a");
        return url ;
    }

    /**
     * 创建文件夹
     */
    public static void  caretCachAndXlogExists(){
        File file = new File(SDCRADIR ,CACHEDIR);
        if(!file.exists()){
            file.mkdirs();
        }

        file = new File(SDCRADIR ,XLOGDIR);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    public static void  caretAudoiExists(){
        File file = new File(SDCRADIR ,CACHEDIR);
        if(!file.exists()){
            file.mkdirs();
        }
    }

    /**
     *  把图片文件转换成Base64
     * @param
     * @param imgPath   图片的路径
     * @return
     */
    public static String imgToBase64(String imgPath) {
        Bitmap bitmap = null;
        if (imgPath !=null && imgPath.length() > 0) {
            bitmap = readBitmap(imgPath);
        }
        if(bitmap == null){
            return null;
        }
        ByteArrayOutputStream out = null;
        try {
            out = new ByteArrayOutputStream();
            bitmap.compress(Bitmap.CompressFormat.JPEG, 100, out);
            out.flush();
            out.close();
            byte[] imgBytes = out.toByteArray();
            //System.out.println("img = "+ new String(imgBytes)) ;
            return Base64.encodeToString(imgBytes, Base64.DEFAULT);
            //return new String(Base64.encode(imgBytes, Base64.DEFAULT)) ;
        } catch (Exception e) {
            return null;
        } finally {
            try {
                out.flush();
                out.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 根据文件的名字读取生成Bitmap
     * @param imgPath
     * @return
     */
    public static Bitmap readBitmap(String imgPath) {
        try {
            return BitmapFactory.decodeFile(imgPath);
        } catch (Exception e) {
            return null;
        }
    }

    /**
     *  Base64文件转换成Bitmap
     * @param base64Data
     * @param imgName
     * @param imgFormat 图片格式
     */
    public static void base64ToBitmap(String base64Data,String imgName,String imgFormat) {
        byte[] bytes = Base64.decode(base64Data, Base64.DEFAULT);
        Bitmap bitmap = BitmapFactory.decodeByteArray(bytes, 0, bytes.length);

        File myCaptureFile = new File("/sdcard/", imgName);
        FileOutputStream fos = null;
        try {
            fos = new FileOutputStream(myCaptureFile);
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        boolean isTu = bitmap.compress(Bitmap.CompressFormat.JPEG, 100, fos);
        if (isTu) {
            try {
                fos.flush();
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 读取文件
     * @param context
     * @param path
     * @return
     * @throws IOException
     */
    public static byte[] readFile(Context context,String path) throws IOException{
        if (path == null ) {
            return null;
        }
        File file = new File(path);
        if (!file.exists()) {
            return null;
        }
        byte[] buffer = null;
        FileInputStream fis = new FileInputStream(file);
        int lng = fis.available();
        buffer = new byte[lng];
        fis.read(buffer);
        fis.close();
        return buffer;
    }

    /**
     * 从assets文件夹中读取文件
     * @param context
     * @param file
     * @return
     */
    public static StringBuffer readTextFileFromAssets(Context context,String file){
        AssetManager am = context.getAssets();
        StringBuffer sb = new StringBuffer();
        try {
            InputStream is = am.open(file);
            byte[] buffer = new byte[256];
            int readbytes = -1;
            byte[] buf = new byte[is.available()];
            int hadWrite = 0;
            while ((readbytes = is.read(buffer)) > 0) {
                System.arraycopy(buffer, 0, buf, hadWrite, readbytes);
                hadWrite += readbytes;
            }
            sb.append(new String(buf, "utf-8"));
            buffer = null;
            buf = null;
            is.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return sb;
    }

    /**
     * 从SD卡读取文件
     * @param path
     * @return
     */
    public static StringBuffer readTextFileFromPath(String path){
        StringBuffer sb = new StringBuffer();
        try {
            File file = new File(path);
            FileInputStream is = new FileInputStream(file);
            byte[] buffer = new byte[256];
            int readbytes = -1;
            byte[] buf = new byte[is.available()];
            int hadWrite = 0;
            while ((readbytes = is.read(buffer)) > 0) {
                System.arraycopy(buffer, 0, buf, hadWrite, readbytes);
                hadWrite += readbytes;
            };
            sb.append(new String(buf, "utf-8"));
            buffer = null;
            buf = null;
            is.close();
        } catch (IOException e) {

        }
        return sb;
    }




}
