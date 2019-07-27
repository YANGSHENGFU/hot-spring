package com.restaurant.business;

import java.util.ArrayList;
import java.util.Map;

public interface OrderAPI {
    int LOAD_MODLE_REFRASH = 1;
    int LOAD_MODLE_MORE = 2;
    int LOAD_MODLE_SEARCH = 3;
    public interface View<T> {
        void upDatd( T tables);
    }

    public interface Pressente {
        void loadData( Map<String, String> params);
    }
}
