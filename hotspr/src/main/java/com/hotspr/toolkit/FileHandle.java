package com.hotspr.toolkit;

import com.hotspr.ui.bean.User;
import com.modulebase.toolkit.FileUtils;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;


public class FileHandle {

    public static String User_Folder="/User";
    public static String User_File="user.txt";

    /**
     * 保存用户信息
     * @param user
     */
    public static void saveUser(User user){
        if(user == null){
            return;
        }
        ObjectOutputStream fos=null;
        try {
            File file=new File(FileUtils.SDCRADIR + FileUtils.ROOTDIR + User_Folder + User_File );
            fos=new ObjectOutputStream(new FileOutputStream(file));
            fos.writeObject(user);
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (fos!=null) {
                    fos.close();
                }
            } catch (IOException e) {
            }
        }
    }

    /**
     * 获取用户信息
     * @return
     */
    public static User getUser(){
        ObjectInputStream ois=null;
        User user = null ;
        try {
            //获取输入流
            ois = new ObjectInputStream(new FileInputStream(new File(FileUtils.SDCRADIR + FileUtils.ROOTDIR + User_Folder + User_File)));
            user = (User) ois.readObject();
        } catch (Exception e) {
            e.printStackTrace();
        }finally{
            try {
                if (ois!=null) {
                    ois.close();
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return user ;
    }
}
