package pl.kfrak.flowershop.service;

import java.util.List;

import io.reactivex.Observable;
import pl.kfrak.flowershop.models.Flower;
import retrofit2.Call;
import retrofit2.http.GET;

/**
 * Created by RENT on 2017-07-12.
 */

public interface FlowerApi {

    @GET("/feeds/flowers.json")
    Call<List<Flower>> getAllFlowers();

    @GET("/feeds/flowers.json")
    Observable<List<Flower>> getAllFlowersRxJava();

}
