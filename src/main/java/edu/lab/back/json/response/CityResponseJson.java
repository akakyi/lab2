package edu.lab.back.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class CityResponseJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    //TODO в рамках лабы норм, но лучше бы тут лежали просто айдишники
    @JsonProperty(value = "schools")
    private List<SchoolResponseJson> schools;


    public static CityResponseJson convert(@NonNull final CityEntity cityEntity) {
        final CityResponseJson cityResponseJson = new CityResponseJson();
        cityResponseJson.setId(cityEntity.getId());
        cityResponseJson.setName(cityEntity.getName());

        if (cityEntity.getSchools() != null) {
            final List<SchoolResponseJson> schools = cityEntity.getSchools()
                .stream()
                .map(SchoolResponseJson::convert)
                .collect(Collectors.toList());

            cityResponseJson.setSchools(schools);
        }

        return cityResponseJson;
    }

}
