
package tk.julianjurec.linuxsession14.Model;

import java.io.Serializable;
import com.google.gson.annotations.Expose;
import com.google.gson.annotations.SerializedName;
import com.orm.SugarRecord;

public class Speaker extends SugarRecord<Speaker> implements Serializable
{

    @SerializedName("description")
    @Expose
    private String description;
    @SerializedName("id")
    @Expose
    private Integer uId;
    @SerializedName("img_url")
    @Expose
    private String imgUrl;
    @SerializedName("name")
    @Expose
    private String name;

    public Speaker() {
    }

    public Speaker(String description, Integer uId, String imgUrl, String name) {
        super();
        this.description = description;
        this.uId = uId;
        this.imgUrl = imgUrl;
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getUId() {
        return uId;
    }

    public void setUID(Integer uId) {
        this.uId = uId;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}