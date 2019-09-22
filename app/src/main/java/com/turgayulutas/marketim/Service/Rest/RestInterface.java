package com.turgayulutas.marketim.Service.Rest;

import com.turgayulutas.marketim.Service.Models.MyOrdersDatum;

import java.util.List;

import io.reactivex.Observable;
import retrofit2.http.GET;

/**
 * Created by turgayulutas on 22/09/2019.
 */

public interface RestInterface {
    @GET("/")
    Observable<List<MyOrdersDatum>> getMyOrders();
}
