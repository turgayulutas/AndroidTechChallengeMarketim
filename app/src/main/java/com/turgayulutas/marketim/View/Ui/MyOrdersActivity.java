package com.turgayulutas.marketim.View.Ui;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Toast;

import com.turgayulutas.marketim.Service.Rest.ApiClient;
import com.turgayulutas.marketim.Service.Models.MyOrdersDatum;
import com.turgayulutas.marketim.Service.Rest.RestInterface;
import com.turgayulutas.marketim.View.Adapter.MyOrdersAdapter;
import com.turgayulutas.marketim.R;
import com.turgayulutas.marketim.Utils.Preferences;

import java.util.List;

import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Created by turgayulutas on 22/09/2019.
 */

public class MyOrdersActivity extends AppCompatActivity {
    RestInterface mRestInterface;
    private RecyclerView mRec_myOrderList;
    public MyOrdersAdapter mOrderAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_orders);

        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        mRec_myOrderList = findViewById(R.id.rec_myOrdersList);
        mRec_myOrderList.setLayoutManager(new LinearLayoutManager(this));

        /*
         * * Api'den siparişlerim datasının çekilmesi
         * */
        getMyOrders();

    }

    private void getMyOrders() {
        mRestInterface = ApiClient.getClient().create(RestInterface.class);
        mRestInterface.getMyOrders()
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<List<MyOrdersDatum>>() {
                    @Override
                    public void onSubscribe(Disposable d) {

                    }

                    @Override
                    public void onNext(List<MyOrdersDatum> myOrdersDatum) {
                        mOrderAdapter = new MyOrdersAdapter(myOrdersDatum, getApplication());
                    }

                    @Override
                    public void onError(Throwable e) {
                        Toast.makeText(MyOrdersActivity.this, getResources().getString(R.string.an_error_occured), Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onComplete() {
                        findViewById(R.id.ly_loadingOrders).setVisibility(View.GONE);
                        mRec_myOrderList.setAdapter(mOrderAdapter);
                    }
                });
    }

    public void logOut(View view) {
        /*
         * Çıkış sırasında dialog onay alınması onay durumunda; "Beni hatırla" fonksiyonun devre dışı kalması için Preferences datasının temizlenmesi
         */

        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        DialogInterface.OnClickListener dialogClickListener = (dialog, which) -> {
            switch (which) {
                case DialogInterface.BUTTON_POSITIVE:
                    Preferences.clearData(this);
                    startActivity(new Intent(this, LoginActivity.class).addFlags(Intent.FLAG_ACTIVITY_NEW_TASK));
                    finish();
                    break;

                case DialogInterface.BUTTON_NEGATIVE:
                    dialog.cancel();
                    break;
            }
        };

        builder.setMessage(getResources().getString(R.string.log_out_message))
                .setPositiveButton(getResources().getString(R.string.yes_i_do), dialogClickListener)
                .setNegativeButton(getResources().getString(R.string.cancel), dialogClickListener).show();
    }
}