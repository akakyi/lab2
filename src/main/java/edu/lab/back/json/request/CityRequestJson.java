package edu.lab.back.json.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class CityRequestJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    public static CityRequestJson convert(final CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }

        final CityRequestJson cityJson = new CityRequestJson();
        cityJson.setId(cityEntity.getId());
        cityJson.setName(cityEntity.getName());

        return cityJson;
    }

}
