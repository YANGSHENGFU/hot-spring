package com.hotspr.business.api;

import com.hotspr.ui.bean.Round;

import java.util.ArrayList;
import java.util.Map;

public class CleanRoundAPI {

    public interface View {

        void upDatd(int mode, ArrayList<Round> rounds, int pageNumber);
        /**
         *更新单条数据
         */
        void upDataInfo(int i, Round round);
    }

    public interface Pressente {

        int LOAD_MODLE_REFRASH = 1;
        int LOAD_MODLE_MORE = 2;
        int LOAD_MODLE_SEARCH = 3;

        void loadData(int lodelModel, int page, Map<String, String> params);

    }
}
