package pojos.handMade;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import pojos.handMade.Address;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class GetAddress {
    @JsonProperty("success")
    private  boolean success;
    @JsonProperty("address")
    private List<Address> addresses;

    @JsonIgnore
    private Map<String,Object> additionalProperties= new HashMap<>();

    @JsonProperty("isSuccess")
    public boolean isSuccess() {
        return success;
    }
    @JsonProperty("getAddresses")
    public List<Address> getAddresses() {
        return addresses;
    }

    @JsonAnySetter
    public void setAdditionalProperties(Map<String, Object> additionalProperties) {
        this.additionalProperties = additionalProperties;
    }
}
