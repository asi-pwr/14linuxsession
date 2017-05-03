package tk.julianjurec.linuxsession14.Sponsors;

import java.util.List;

import javax.inject.Inject;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.Model.SponsorsResponse;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsContract;
import tk.julianjurec.linuxsession14.Sponsors.SponsorsFragment;
import tk.julianjurec.linuxsession14.Base.BasePresenter;
import tk.julianjurec.linuxsession14.Utils.Api;

/**
 * Created by sp0rk on 22.03.17.
 */

public class SponsorsPresenter implements SponsorsContract.Presenter {
    private SponsorsFragment view;

    @Inject
    public SponsorsPresenter(SponsorsFragment view){
        this.view = view;
    }

    @Override
    public void start() {
        final String BASE_URL = "http://tramwaj.asi.wroclaw.pl:6937/";
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        Api apiService = retrofit.create(Api.class);
        Call<SponsorsResponse> call = apiService.getSponsors();
        call.enqueue(new Callback<SponsorsResponse>() {
            @Override
            public void onResponse(Call<SponsorsResponse> call, Response<SponsorsResponse> response) {
                for (Sponsor s : response.body().getSponsors()){
                    System.out.println(s.getName());
                }
            }

            @Override
            public void onFailure(Call<SponsorsResponse> call, Throwable t) {
                    t.printStackTrace();
            }
        });
    }
}
