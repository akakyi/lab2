package edu.lab.back.json.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ProfileEntity;
import edu.lab.back.db.entity.ProfileTypeEntity;
import edu.lab.back.json.JsonPojo;
import edu.lab.back.json.ProfileTypeJson;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class ProfileResponseJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Long id;

    @JsonProperty(value = "name")
    private String name;

    @JsonProperty(value = "age")
    private Integer age;

    @JsonProperty(value = "profile_type")
    private ProfileTypeJson profileType;

    @JsonProperty(value = "class_level")
    private String classLevel;

    @JsonProperty(value = "school_id")
    private Long schoolId;

    public static ProfileResponseJson convert(final ProfileEntity profileEntity) {
        if (profileEntity == null) {
            return null;
        }

        final ProfileResponseJson result = new ProfileResponseJson();
        result.setAge(profileEntity.getAge());
        result.setClassLevel(profileEntity.getClassLevel());
        result.setId(profileEntity.getId());
        result.setName(profileEntity.getName());

        final ProfileTypeEntity profileType = profileEntity.getProfileType();
        final ProfileTypeJson type = ProfileTypeJson.convert(profileType);
        if (profileType != null) {
            result.setProfileType(type);
        }

        final Long schoolId = profileEntity.getSchool().getId();
        result.setSchoolId(schoolId);

        return result;
    }

}
