package com.source.kevin.fingertrans.utils;

import com.source.kevin.fingertrans.data.webdata.TransResult;
import com.source.kevin.fingertrans.translate.TranslateService;

import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import retrofit2.Retrofit;
import retrofit2.adapter.rxjava.RxJavaCallAdapterFactory;
import retrofit2.converter.gson.GsonConverterFactory;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;

/**
 * the network utils class
 */
public class HttpMethods {
    private static final String BASE_URL = "http://fanyi.youdao.com/";
    private static final String KEY = "1372247841";
    private static final String KEY_FROM = "fingertrans";

    private static final int DEFAULT_TIMEOUT = 5;

    private Retrofit retrofit;
    private TranslateService translateService;


    private HttpMethods(){
        OkHttpClient.Builder builder=new OkHttpClient.Builder();
        builder.connectTimeout(DEFAULT_TIMEOUT, TimeUnit.SECONDS);

        retrofit = new Retrofit.Builder()
                .client(builder.build())
                .addConverterFactory(GsonConverterFactory.create())
                .addCallAdapterFactory(RxJavaCallAdapterFactory.create())
                .baseUrl(BASE_URL)
                .build();
        translateService = retrofit.create(TranslateService.class);
    }


    /**
     * get the translation from the internet
     * @param subscriber the subscriber
     * @param query the word to translate
     */
    public void translate(Subscriber<TransResult> subscriber,String query){
        translateService.translate(KEY_FROM,KEY,"data","json","1.1",query)
                .subscribeOn(Schedulers.io())
                .unsubscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber);

    }

    public static HttpMethods getInstance()
    {
        return SingletonHolder.instance;
    }


    private static class SingletonHolder{
        private static final HttpMethods instance = new HttpMethods();
    }
}
