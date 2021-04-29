package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown=true)
public class OptionBean {
    @JsonProperty("value")
    private String value;

    public OptionBean() {
    }
}
