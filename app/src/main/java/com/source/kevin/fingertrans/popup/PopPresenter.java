package com.source.kevin.fingertrans.popup;

import com.source.kevin.fingertrans.data.TransInfo;
import com.source.kevin.fingertrans.data.webdata.TransResult;
import com.source.kevin.fingertrans.utils.HttpMethods;

import java.util.List;

import rx.Subscriber;

/**
 * the pop presenter
 */
public class PopPresenter implements PopContract.Presenter {

    private PopContract.View mView;

    public PopPresenter(PopContract.View view) {
        this.mView = view;

        mView.setPresenter(this);
    }

    @Override
    public void translate(String query) {
        Subscriber<TransResult> subscriber = new Subscriber<TransResult>() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(TransResult transResult) {
                mView.showPopView(processResult(transResult));
            }
        };

        HttpMethods.getInstance().translate(subscriber, query);
    }

    /**
     *  process the web data
     * @param result the raw data
     * @return the popView content data
     */
    private TransInfo processResult(TransResult result) {
        String explain = null;
        if (result.getBasic() != null) {
            List<String> explains = result.getBasic().getExplains();
            explain = "";
            for (int i = 0; i < explains.size(); ++i) {
                explain += explains.get(i);
                if (i + 1 < explains.size())
                    explain += "\n";
            }
        }
        return new TransInfo(result.getQuery(), result.getTranslation().get(0), explain);
    }

    @Override
    public void start() {

    }
}
