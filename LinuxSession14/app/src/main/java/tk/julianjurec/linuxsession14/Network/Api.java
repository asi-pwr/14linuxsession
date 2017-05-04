package tk.julianjurec.linuxsession14.Network;

import retrofit2.Call;
import retrofit2.http.GET;
import tk.julianjurec.linuxsession14.Model.AppDataResponse;
import tk.julianjurec.linuxsession14.Model.LecturesResponse;
import tk.julianjurec.linuxsession14.Model.SpeakersResponse;
import tk.julianjurec.linuxsession14.Model.SponsorsResponse;

/**
 * Created by sp0rk on 03.05.17.
 */

public interface Api {

    @GET("sponsors")
    Call<SponsorsResponse> getSponsors();

    @GET("lectures")
    Call<LecturesResponse> getLectures();

    @GET("speakers")
    Call<SpeakersResponse> getSpeakers();

    @GET("last_updated")
    Call<Float> getLastUpdate();

    @GET("app_data")
    Call<AppDataResponse> getAppData();
}
