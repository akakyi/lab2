package edu.lab.back.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileRequestJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "profile_type_id")
    private Integer profileTypeId;

    @JsonProperty(value = "class_level")
    private String classLevel;

    @JsonProperty(value = "school_id")
    private Long schoolId;

}
