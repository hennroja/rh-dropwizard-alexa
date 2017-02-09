package de.hennroja.alexaskill;

import io.dropwizard.Configuration;
import com.fasterxml.jackson.annotation.JsonProperty;
import javax.validation.Valid;
import javax.validation.constraints.NotNull;

/**
 * Created by hennroja on 20/03/16.
 */
public class AlexaConfiguration extends Configuration {

    @Valid
    @NotNull
    @JsonProperty
    private String skillname;

    @JsonProperty("skillname")
    public String getSkillname() {
        return skillname;
    }

    @JsonProperty("skillname")
    public void setSkillname(String skillname) {
        this.skillname = skillname;
    }

    public static final String SKILLNAME = "My Skill";
}