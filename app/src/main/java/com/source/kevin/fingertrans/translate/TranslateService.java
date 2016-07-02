package com.source.kevin.fingertrans.translate;

import com.source.kevin.fingertrans.data.webdata.TransResult;

import retrofit2.http.GET;
import retrofit2.http.Query;
import rx.Observable;

/**
 * the translate service
 */
public interface TranslateService {

    @GET("openapi.do")
    Observable<TransResult> translate(@Query("keyfrom") String keyfrom,
                                      @Query("key") String key,
                                      @Query("type") String type,
                                      @Query("doctype") String doctype,
                                      @Query("version") String version,
                                      @Query("q") String query);
}
