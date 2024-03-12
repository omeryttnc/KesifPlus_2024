package pojos.handMade;

import com.fasterxml.jackson.annotation.JsonAnySetter;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.Map;
@JsonIgnoreProperties(ignoreUnknown = true)
public class Address {
    @JsonProperty("id")
    private int id;
    @JsonProperty("title")
    private String title;
    @JsonProperty("city")
    private String city;

    @JsonProperty("id")
    public int getId() {
        return id;
    }
    @JsonProperty("title")
    public String getTitle() {
        return title;
    }
    @JsonProperty("city")
    public String getCity() {
        return city;
    }
}
