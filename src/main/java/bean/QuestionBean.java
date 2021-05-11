package bean;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown=true)
public class QuestionBean {
    @JsonProperty("type")
    private String type;
    @JsonProperty("label")
    private String label;
    @JsonProperty("required")
    private boolean required;
    @JsonProperty("access")
    private boolean personal;
    @JsonProperty("values")
    private List<OptionBean> options;

    public QuestionBean() {
    }
}
