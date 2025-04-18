package engine.model;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonPropertyOrder;

import java.util.List;

@JsonPropertyOrder(
        {"title", "text", "options"}
)
public class Quiz {
    private String title;
    @JsonProperty("text")
    private String question;
    private List<String> options;

    public Quiz() {
        this.title = "The Java Logo";
        this.question = "What is depicted on the Java logo?";
        this.options = List.of("Robot","Tea leaf","Cup of coffee","Bug");
    }

    public String getTitle() {
        return title;
    }

    public String getQuestion() {
        return question;
    }

    public List<String> getOptions() {
        return options;
    }
}
