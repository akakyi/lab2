package edu.lab.back.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.SchoolEntity;
import edu.lab.back.json.JsonPojo;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

import java.util.List;
import java.util.stream.Collectors;

@Data
@NoArgsConstructor
public class SchoolResponseJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    //TODO в рамках лабы норм, но лучше бы тут лежали просто айдишники
    @JsonProperty(value = "profiles")
    private List<ProfileResponseJson> profiles;

    public static SchoolResponseJson convert(@NonNull final SchoolEntity schoolEntity) {
        SchoolResponseJson result = new SchoolResponseJson();
        result.setId(schoolEntity.getId());
        result.setName(schoolEntity.getName());

        if (schoolEntity.getProfiles() != null) {
            final List<ProfileResponseJson> profilesJson = schoolEntity.getProfiles()
                .stream()
                .map(ProfileResponseJson::convert)
                .collect(Collectors.toList());

            result.setProfiles(profilesJson);
        }

        return result;
    }

}
