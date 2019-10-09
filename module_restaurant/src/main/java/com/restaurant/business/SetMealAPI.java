package com.restaurant.business;

import java.util.ArrayList;
import java.util.Map;

public interface SetMealAPI {
    int LOAD_MODLE_REFRASH = 1;
    int LOAD_MODLE_MORE = 2;
    int LOAD_MODLE_SEARCH = 3;
    interface View<T> {
        void upDatd2(int mode, ArrayList<T> tables, int pageNumber);
    }

//    interface Pressente {
//        void loadData(int lodelModel, int page, Map<String, String> params);
//    }
}
