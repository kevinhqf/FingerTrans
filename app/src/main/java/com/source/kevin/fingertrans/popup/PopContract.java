package com.source.kevin.fingertrans.popup;

import com.source.kevin.fingertrans.data.TransInfo;
import com.source.kevin.fingertrans.mvp.BasePresenter;
import com.source.kevin.fingertrans.mvp.BaseView;

/**
 * the popup contract class
 */
public class PopContract {

    interface View extends BaseView<Presenter>{
        void showPopView(TransInfo info);

    }
    interface Presenter extends BasePresenter{
        void translate(String query);
    }
}
