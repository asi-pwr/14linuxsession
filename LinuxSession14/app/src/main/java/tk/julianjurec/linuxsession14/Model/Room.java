package tk.julianjurec.linuxsession14.Model;

import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

import java.io.Serializable;

public class Room extends SugarRecord implements Serializable {

    @SerializedName("uId")
    @Expose
    private Integer uId;
    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("name")
    @Expose
    private String name;

    public Room() {
    }

    public Room(Integer uId, String description, String name) {
        this.uId = uId;
        this.description = description;
        this.name = name;
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
