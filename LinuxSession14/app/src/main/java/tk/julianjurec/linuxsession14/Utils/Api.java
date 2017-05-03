package tk.julianjurec.linuxsession14.Utils;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.http.GET;
import retrofit2.http.Path;
import tk.julianjurec.linuxsession14.Model.Sponsor;
import tk.julianjurec.linuxsession14.Model.SponsorsResponse;

/**
 * Created by sp0rk on 03.05.17.
 */

public interface Api {

    @GET("sponsors")
    Call<SponsorsResponse> getSponsors();

}
