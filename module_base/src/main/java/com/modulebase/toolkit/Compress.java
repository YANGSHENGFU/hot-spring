package com.modulebase.toolkit;

import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.RectF;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.text.DecimalFormat;

public class Compress {

    /**
     * 压缩本地大于1M的图片
     * @param srcpath 本地路径，需包括图片名和后缀
     * @param width 压缩后的宽度，0代表不设置宽度限制
     * @param height 压缩后的高度，0代表不设置高度限制
     * @param savePath 压缩后图片保存的路径,包括图片名和后缀
     */
    public static void compress(String srcpath,int width,int height,String savePath) {

        Log.i("压缩：","压缩之前的大小"+formatFileSize(getFileSizes(new File(srcpath))));

        if (ifNeedCompress(formatFileSize(getFileSizes(new File(srcpath))))){
            int compressWidth;
            int compressHeight;

            Bitmap bitmap = BitmapFactory.decodeFile(srcpath);

            //获取压缩后的长和宽
            if (width != 0){
                compressWidth = width;
                if (height != 0){
                    compressHeight = height;
                }else{
                    compressHeight = (width * bitmap.getHeight()) / bitmap.getWidth();
                }
            }else{
                if (height != 0){
                    compressWidth = (height * bitmap.getWidth()) / bitmap.getHeight();
                    compressHeight = height;
                }else{
                    compressWidth = bitmap.getWidth();
                    compressHeight = bitmap.getHeight();
                }
            }

            Bitmap result = Bitmap.createBitmap(compressWidth, compressHeight, Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(result);
            RectF rectF = new RectF(0, 0, compressWidth, compressHeight);
            //将原图画在缩放之后的矩形上
            canvas.drawBitmap(bitmap, null, rectF, null);
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            result.compress(Bitmap.CompressFormat.JPEG, 100, bos);
            try {
                FileOutputStream fos = new FileOutputStream(new File(savePath));
                fos.write(bos.toByteArray());
                fos.flush();
                fos.close();
                Log.i("压缩：","压缩之后的大小"+formatFileSize(getFileSizes(new File(savePath))));
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }else{
            Log.i("压缩：","图片无需压缩。");
        }
    }


    /**
     * 获取文件大小
     * @param f
     * @return
     */
    public static long getFileSizes(File f)
    {
        long s = 0;
        if (f.exists())
        {
            FileInputStream fis = null;
            try {
                fis = new FileInputStream(f);
                s = fis.available();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        else
        {
            try {
                f.createNewFile();
            } catch (IOException e) {
                e.printStackTrace();
            }
            System.out.println("文件不存在");
        }
        return s;
    }

    /**
     * 将文件大小转换成字节
     * @param fSize
     * @return
     */
    public static String formatFileSize(long fSize) {
        DecimalFormat df = new DecimalFormat("#.00");
        String fileSizeString = "";
        if (fSize < 1024) {
            fileSizeString = df.format((double) fSize) + "B";
        } else if (fSize > 104875) {
            fileSizeString = df.format((double) fSize / 1024) + "K";
        } else if (fSize > 1073741824) {
            fileSizeString = df.format((double) fSize / 104875) + "M";
        } else {
            fileSizeString = df.format((double) fSize / 1073741824) + "G";
        }
        return fileSizeString;
    }

    public static boolean ifNeedCompress(String imgSizeUnit){

        int length = imgSizeUnit.length();
        float iimgSize = Float.parseFloat(imgSizeUnit.substring(0,length-1));
        String simgSize = imgSizeUnit.substring(0,length-1);
        String imgUnit = imgSizeUnit.substring(length-1);

        String limitSizeB = "";
        String limitSizeK = "1024K";
        String limitSizeM = "1M";
        String limitSizeG = "";

        if (imgUnit.equals("K")){
            if (simgSize.compareTo( limitSizeK.substring(0,limitSizeK.length()-1) ) > 0){
                return true;
            }
        }

        if (imgUnit.equals("M")){
            if (simgSize.compareTo( limitSizeM.substring(0,limitSizeM.length()-1) ) > 0){
                return true;
            }
        }

        return false;
    }
}
