package tk.julianjurec.linuxsession14.Base;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import com.orm.SchemaGenerator;
import com.orm.SugarApp;
import com.orm.SugarContext;
import com.orm.SugarDb;

import javax.inject.Inject;

import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import tk.julianjurec.linuxsession14.Agenda.AgendaFragment;
import tk.julianjurec.linuxsession14.Model.AppDataResponse;
import tk.julianjurec.linuxsession14.Model.Lecture;
import tk.julianjurec.linuxsession14.Model.Speaker;
import tk.julianjurec.linuxsession14.Model.Sponsor;
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
        System.out.println("fetchlastupdate");
        api.getLastUpdate().enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                long server = (long)(response.body()*1000L);
                long local = prefs.getLong(LAST_UPDATE, -1L);
                if (server == local) {
                    continueToApplication();
                } else {
                    fetchAppData(server);
                }
            }

            @Override
            public void onFailure(Call<Float> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void fetchAppData(long updated) {
        System.out.println("fetchappdata");
        api.getAppData().enqueue(new Callback<AppDataResponse>() {
            @Override
            public void onResponse(Call<AppDataResponse> call, Response<AppDataResponse> response) {
                saveAppData(response.body(), updated);
            }

            @Override
            public void onFailure(Call<AppDataResponse> call, Throwable t) {
                t.printStackTrace();
            }
        });
    }

    private void saveAppData(AppDataResponse appDataResponse, long updated) {
        System.out.println("saveappdata");
        //drop db
        SugarContext.terminate();
        SchemaGenerator schemaGenerator = new SchemaGenerator(getApplicationContext());
        schemaGenerator.deleteTables(new SugarDb(getApplicationContext()).getDB());
        SugarContext.init(getApplicationContext());
        schemaGenerator.createDatabase(new SugarDb(getApplicationContext()).getDB());
        //repopulate
        for (Sponsor sponsor : appDataResponse.getSponsors()) {
            sponsor.save();
        }
        for (Speaker speaker : appDataResponse.getSpeakers()) {
            speaker.save();
        }
        for (Lecture lecture : appDataResponse.getLectures()) {
            lecture.save();
        }
        prefs.edit().putLong(LAST_UPDATE, updated).apply();
        continueToApplication();
    }

    private void continueToApplication() {
        startActivity(new Intent(this, MainActivity.class));
    }
}
