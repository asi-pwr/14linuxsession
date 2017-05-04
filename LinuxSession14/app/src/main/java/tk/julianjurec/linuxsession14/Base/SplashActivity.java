package tk.julianjurec.linuxsession14.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Model.AppDataResponse;
import tk.julianjurec.linuxsession14.Network.Api;
import tk.julianjurec.linuxsession14.R;

/**
 * Created by sp0rk on 04.05.17.
 */

public class SplashActivity extends AppCompatActivity {
    private static final String LAST_UPDATE = "last_update";

    @Inject
    Retrofit retrofit;
    private Api api;
    private SharedPreferences prefs;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        ((MainApplication)getApplication()).getNetworkComponent().inject(this);
        api = retrofit.create(Api.class);

        startProgress();
        fetchLastUpdate();
    }

    private void startProgress() {

    }

    private void fetchLastUpdate() {
        api.getLastUpdate().enqueue(new Callback<Long>() {
            @Override
            public void onResponse(Call<Long> call, Response<Long> response) {
                long server = response.body();
                long local = prefs.getLong(LAST_UPDATE, -1);
                if (server == local) {
                    continueToApplication();
                } else {
                    prefs.edit().putLong(LAST_UPDATE, server).apply();
                    fetchAppData();
                }
            }

            @Override
            public void onFailure(Call<Long> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fetchAppData() {
        api.getAppData().enqueue(new Callback<AppDataResponse>() {
            @Override
            public void onResponse(Call<AppDataResponse> call, Response<AppDataResponse> response) {
                saveAppData(response.body());
            }

            @Override
            public void onFailure(Call<AppDataResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void saveAppData(AppDataResponse appDataResponse) {

    }

    private void continueToApplication() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
