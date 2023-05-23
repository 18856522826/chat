package com.cyl.ctrbt.openai.api;

import com.cyl.ctrbt.openai.entity.billing.CreditGrantsResponse;
import com.cyl.ctrbt.openai.entity.chat.ChatCompletion;
import com.cyl.ctrbt.openai.entity.chat.ChatCompletionResponse;
import io.reactivex.Single;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;


/**
 *
 */
public interface Api {

    String DEFAULT_API_HOST = "http://45.32.78.170/api/";
//    String DEFAULT_API_HOST="https://open.aiproxy.xyz/";

    /**
     * chat
     */
    @POST("v1/chat/completions")
    Single<ChatCompletionResponse> chatCompletion(@Body ChatCompletion chatCompletion);


    /**
     * 余额查询
     */
    @GET("dashboard/billing/credit_grants")
    Single<CreditGrantsResponse> creditGrants();


}
