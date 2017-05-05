package tk.julianjurec.linuxsession14.Base;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.HandlerThread;
import android.os.Looper;
import android.preference.PreferenceManager;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.support.v7.app.AppCompatActivity;
import android.view.Window;
import android.view.WindowManager;

import com.mikhaellopez.circularfillableloaders.CircularFillableLoaders;
import com.orm.SchemaGenerator;
import com.orm.SugarContext;
import com.orm.SugarDb;

import javax.inject.Inject;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
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
    private static final int MIN_DURATION = 1000;
    private static final int END_DURATION = 500;
    private static final int MAX_PROGRESS = 160;

    @Inject
    Retrofit retrofit;
    private Api api;
    private SharedPreferences prefs;

    @BindView(R.id.splash_logo)
    CircularFillableLoaders logo;
    private int progress;
    private ValueAnimator loader;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);
        ButterKnife.bind(this);
        prefs = PreferenceManager.getDefaultSharedPreferences(this);

        ((MainApplication) getApplication()).getNetworkComponent().inject(this);
        api = retrofit.create(Api.class);


        Window window = getWindow();
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS);
        window.setStatusBarColor(ContextCompat.getColor(this, R.color.session_dark));

        startProgress();
        fetchLastUpdate();
    }

    private void startProgress() {
        loader = ValueAnimator.ofInt(0, MAX_PROGRESS / 2).setDuration(MIN_DURATION);
        loader.addUpdateListener(a -> {
            progress = (Integer) a.getAnimatedValue();
            logo.setProgress(progress);
        });
        loader.start();
    }

    private void stopProgress() {
        loader.end();
        loader = ValueAnimator.ofInt(progress, MAX_PROGRESS).setDuration(END_DURATION);
        loader.addUpdateListener(a -> logo.setProgress((Integer) a.getAnimatedValue()));
        loader.addListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animation) {
            }

            @Override
            public void onAnimationEnd(Animator animation) {
                continueToApplication();
            }

            @Override
            public void onAnimationCancel(Animator animation) {
            }

            @Override
            public void onAnimationRepeat(Animator animation) {
            }
        });
        loader.start();
    }


    private void fetchLastUpdate() {
        System.out.println("fetchlastupdate");
        api.getLastUpdate().enqueue(new Callback<Float>() {
            @Override
            public void onResponse(Call<Float> call, Response<Float> response) {
                long server = (long) (response.body() * 1000L);
                long local = prefs.getLong(LAST_UPDATE, -1L);
                if (server == local) {
                    stopProgress();
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

        new Thread(() -> {
            //drop db
            try {
                new Sponsor().save();
                new Speaker().save();
                new Lecture().save();
                Sponsor.deleteAll(Sponsor.class);
                Speaker.deleteAll(Speaker.class);
                Lecture.deleteAll(Lecture.class);
            } catch (Exception e) {e.printStackTrace();}
            //repopulate
            for (Sponsor sponsor : appDataResponse.getSponsors()) {
                sponsor.save();
            }
            for (Speaker speaker : appDataResponse.getSpeakers()) {
                speaker.save();
                System.out.println(speaker.getName() + " " + speaker.getId());
            }
            for (Lecture lecture : appDataResponse.getLectures()) {
                lecture.save();
                System.out.println(lecture.getTitle() + " " + lecture.getSpeakerId());
            }
            prefs.edit().putLong(LAST_UPDATE, updated).apply();
            runOnUiThread(()->stopProgress());
        }).start();
    }

    private void continueToApplication() {
        startActivity(new Intent(this, MainActivity.class));
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out);
    }
}
