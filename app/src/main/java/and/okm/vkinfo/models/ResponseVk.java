package and.okm.vkinfo.models;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;
import org.codehaus.jackson.annotate.JsonProperty;

import java.util.ArrayList;
import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class ResponseVk {

    @JsonProperty("response")
    private List<UserInfo> usersInfo = new ArrayList<>();

    public List<UserInfo> getUsersInfo() {
        return usersInfo;
    }
    public void setUsersInfo(List<UserInfo> usersInfo) {
        this.usersInfo = usersInfo;
    }

}
