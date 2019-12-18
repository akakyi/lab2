package edu.lab.back.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class SchoolRequestJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "city_id")
    private Long cityId;

    public static SchoolRequestJson convert(final SchoolEntity schoolEntity) {
        if (schoolEntity == null) {
            return null;
        }

        SchoolRequestJson result = new SchoolRequestJson();
        result.setId(schoolEntity.getId());
        result.setName(schoolEntity.getName());

        return result;
    }

}
