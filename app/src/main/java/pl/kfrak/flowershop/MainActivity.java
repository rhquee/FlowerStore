package pl.kfrak.flowershop;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.view.ActionMode;
import android.util.Log;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.Toast;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.jakewharton.retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.annotations.NonNull;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;
import pl.kfrak.flowershop.adapters.CoolListAdapter;
import pl.kfrak.flowershop.models.Flower;
import pl.kfrak.flowershop.service.FlowerApi;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    public static final String BASE_URL = "http://services.hanselandpetal.com";
    private Retrofit retrofit;
    private FlowerApi flowerApi;
    private List<Flower> flowersList;
    private CoolListAdapter coolListAdapter;



    @BindView(R.id.main_activity_listView)
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        //prepareRetrofit();
        prepareAdapter();

        ///GSON
        Gson gson = new GsonBuilder()
                .setDateFormat("yyyy-MM-dd'T'HH:mm:ssZ")
                .setLenient()
                .create();

        retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create(gson))
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .build();

        ///


//        retrofit = new Retrofit.Builder()
//                .baseUrl(BASE_URL)
//                .build();
        flowerApi = retrofit.create(FlowerApi.class);

/*        flowerApi.getAllFlowers().enqueue(new Callback<List<Flower>>() {
            @Override
            public void onResponse(Call<List<Flower>> call, Response<List<Flower>> response) {
                showToast("response");
            }

            @Override
            public void onFailure(Call<List<Flower>> call, Throwable t) {
                showToast("fail");
            }
        });*/

        getFlowerRxJava();
    }

    private void prepareAdapter() {
        flowersList = new ArrayList<>(); //flowerList przed podaniem go do adaptera
        coolListAdapter = new CoolListAdapter(this, flowersList);
        listView.setAdapter(coolListAdapter);
    }

    private void getFlowerRxJava() {
        flowerApi.getAllFlowersRxJava()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<Flower>>() {
                    @Override
                    public void onSubscribe(@NonNull Disposable d) {

                    }

                    @Override
                    public void onNext(@NonNull List<Flower> flowers) {
                        //MainActivity.this.flowersList = flowers;
                        flowersList.addAll(flowers);
                        Log.d("RxJava", "New flowers" + flowers.size());
                        showToast("done!");
                        coolListAdapter.notifyDataSetChanged(); //odswiezanie
                    }

                    @Override
                    public void onError(@NonNull Throwable e) {

                    }

                    @Override
                    public void onComplete() {
                        Log.d("RxJava", "done");
                        showToast("done!!");
                    }
                });
    }


    private void showToast(String message) {
        Toast.makeText(MainActivity.this, message, Toast.LENGTH_SHORT).show();
    }


}
