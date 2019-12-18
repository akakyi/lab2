package edu.lab.back.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.CityEntity;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;

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
//    @JsonProperty(value = "schools")
//    private List<SchoolResponseJson> schools;
    @JsonProperty(value = "schools_ids")
    private List<Long> schoolsIds;


    public static CityResponseJson convert(final CityEntity cityEntity) {
        if (cityEntity == null) {
            return null;
        }

        final CityResponseJson cityResponseJson = new CityResponseJson();
        cityResponseJson.setId(cityEntity.getId());
        cityResponseJson.setName(cityEntity.getName());

        if (cityEntity.getSchools() != null) {
            final List<Long> schoolsIds = cityEntity.getSchools()
                .stream()
                .map(SchoolEntity::getId)
                .collect(Collectors.toList());

            cityResponseJson.setSchoolsIds(schoolsIds);
        }

        return cityResponseJson;
    }

}
