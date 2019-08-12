package com.restaurant.business;

import java.util.ArrayList;
import java.util.Map;

public interface OrderResultAPI  {
    public static int LOAD_MODLE_REFRASH = 1;
    public static int LOAD_MODLE_MORE = 2;
    public static int LOAD_MODLE_SEARCH = 3;
    public static interface View<T> {
        void upDatd(int mode, ArrayList<T> tables, int pageNumber);
    }

    public interface Pressente {
        void loadData(int lodelModel, int page, Map<String, String> params);
    }
}
