package com.restaurant.business;

import java.util.ArrayList;
import java.util.Map;

public interface TableNumberAPI {
    int LOAD_MODLE_REFRASH = 1;
    int LOAD_MODLE_MORE = 2;
    int LOAD_MODLE_SEARCH = 3;
    interface View<T> {
        void upDatd(int mode, ArrayList<T> tables, int pageNumber);
        void openTabelResult(T tabel );
    }

    interface Pressente {
        void loadData(int lodelModel, int page, Map<String, String> params);
    }
}
