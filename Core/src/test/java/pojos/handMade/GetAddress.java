package pojos.handMade;

import com.fasterxml.jackson.annotation.*;
import pojos.handMade.Address;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class GetAddress {
    @JsonProperty("success")
    private  boolean success;
    @JsonProperty("addresses")
    private List<Address> addresses;

    @JsonProperty("success")
    public boolean isSuccess() {
        return success;
    }
    @JsonProperty("addresses")
    public List<Address> getAddresses() {
        return addresses;
    }


}
