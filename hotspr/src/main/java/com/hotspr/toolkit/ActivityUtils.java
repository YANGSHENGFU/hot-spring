package com.hotspr.toolkit;

import android.support.annotation.NonNull;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;

import java.util.HashMap;
import java.util.Map;

public class ActivityUtils {
    private static Map<String, Fragment> sFragmentStacks = new HashMap<>();

    public static void addFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId) {
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.add(frameId, fragment);
        transaction.commit();
    }

    public static void addFragmentUseStack(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId, String tag) {
        if (!fragment.isAdded()) {
            FragmentTransaction transaction = fragmentManager.beginTransaction();
            transaction.add(frameId, fragment);
            transaction.addToBackStack(tag);
            transaction.commit();
        }
    }

    public static void replaceFragmentToActivity(@NonNull FragmentManager fragmentManager, @NonNull Fragment fragment, int frameId){
        FragmentTransaction transaction = fragmentManager.beginTransaction();
        transaction.replace(frameId, fragment);
        transaction.commitAllowingStateLoss();
    }

    public static Map<String, Fragment> getFragmentStacks() {
        return sFragmentStacks;
    }
}
