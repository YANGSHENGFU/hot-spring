package com.hotspr.business.api;

import com.hotspr.ui.bean.Round;

import java.util.ArrayList;
import java.util.Map;

public interface BaggageRegistrationAPI extends BaseBusinessAPI{
    public interface View {

        void upDatd(int mode, ArrayList<Round> rounds, int pageNumber);
    }

    public interface Pressente {

        void loadData(int lodelModel, int page, Map<String, String> params);

    }
}
