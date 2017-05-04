package tk.julianjurec.linuxsession14.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

import java.util.List;

/**
 * Created by sp0rk on 03.05.17.
 */

public class SponsorsResponse {

    @SerializedName("sponsors")
    @Expose
    private List<Sponsor> sponsors = null;

    public SponsorsResponse() {
        //No args constructor for use in serialization
    }

    public SponsorsResponse(List<Sponsor> sponsors) {
        super();
        this.sponsors = sponsors;
    }

    public List<Sponsor> getSponsors() {
        return sponsors;
    }
}
