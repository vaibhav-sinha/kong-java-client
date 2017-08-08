package com.github.vaibhavsinha.kong.internal.admin;

import com.github.vaibhavsinha.kong.model.admin.sni.Sni;
import com.github.vaibhavsinha.kong.model.admin.sni.SniList;
import retrofit2.Call;
import retrofit2.http.*;

/**
 * Created by vaibhav on 12/06/17.
 */
public interface RetrofitSniService {

    @POST("snis/")
    Call<Sni> createSni(@Body Sni request);

    @GET("snis/{name}")
    Call<Sni> getSni(@Path("name") String name);

    @PATCH("snis/{name}")
    Call<Sni> updateSni(@Path("name") String name, @Body Sni request);

    @PUT("snis/")
    Call<Sni> createOrUpdateSni(@Body Sni request);

    @DELETE("snis/{name}")
    Call<Void> deleteSni(@Path("name") String name);

    @GET("snis/")
    Call<SniList> listSnis();

}
