package com.hotspr.business.api;


import java.util.ArrayList;
import java.util.Map;

public interface BaggageEnquiryAPI extends BaseBusinessAPI{
    public interface View<T> {

        void upDatd(int mode, ArrayList<T> rounds, int pageNumber);
    }

    public interface Pressente {

        void loadData(int lodelModel, int page, Map<String, String> params);

    }
}
