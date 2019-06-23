package com.hotspr.business.api;

import com.hotspr.ui.bean.Round;

import java.util.ArrayList;
import java.util.Map;


public interface WardRoundPressenterAPI {

    interface View {

        void upDatd(int mode, ArrayList<Round> rounds, int pageNumber);

        void uploadPhoto(boolean isOK);

    }

    interface Pressente {

        int LOAD_MODLE_REFRASH = 1;
        int LOAD_MODLE_MORE = 2;
        int LOAD_MODLE_SEARCH = 3;

        void loadData(int lodelModel, int page, Map<String, String> params);

    }

}
