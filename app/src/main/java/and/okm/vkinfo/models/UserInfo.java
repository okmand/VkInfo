package and.okm.vkinfo.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserInfo {

    @JsonProperty("id")
    private Long id = -1L;

    @JsonProperty("first_name")
    private String firstName = "";

    @JsonProperty("last_name")
    private String lastName = "";

    @JsonProperty("can_access_closed")
    private Boolean canAccessClosed = true;

    @JsonProperty("photo_max_orig")
    private String photoMaxOrig = "";

    @JsonProperty("is_closed")
    private Boolean isClosed = false;

    public Long getId() {
        return id;
    }
    public void setId(Long id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }
    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }
    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public Boolean getCanAccessClosed() {
        return canAccessClosed;
    }
    public void setCanAccessClosed(Boolean canAccessClosed) {
        this.canAccessClosed = canAccessClosed;
    }

    public String getPhotoMaxOrig() {
        return photoMaxOrig;
    }
    public void setPhotoMaxOrig(String photoMaxOrig) {
        this.photoMaxOrig = photoMaxOrig;
    }

    public Boolean getClosed() {
        return isClosed;
    }
    public void setClosed(Boolean closed) {
        isClosed = closed;
    }
}
