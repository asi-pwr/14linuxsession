
package tk.julianjurec.linuxsession14.Model;

import java.io.Serializable;
import java.util.List;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;

public class SpeakersResponse implements Serializable
{

    @SerializedName("speakers")
    @Expose
    private List<Speaker> speakers = null;

    public SpeakersResponse() {
    }

    public SpeakersResponse(List<Speaker> speakers) {
        super();
        this.speakers = speakers;
    }

    public List<Speaker> getSpeakers() {
        return speakers;
    }

    public void setSpeakers(List<Speaker> speakers) {
        this.speakers = speakers;
    }

}