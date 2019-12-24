package edu.lab.back.json;

import com.fasterxml.jackson.annotation.JsonProperty;
import edu.lab.back.db.entity.ProfileTypeEntity;
import lombok.Data;

@Data
public class ProfileTypeJson implements JsonPojo {

    @JsonProperty(value = "id")
    private Integer id;

    @JsonProperty(value = "name")
    private String name;

    public static ProfileTypeJson convert(final ProfileTypeEntity profileTypeEntity) {
        if (profileTypeEntity == null) {
            return null;
        }

        final ProfileTypeJson profileTypeJson = new ProfileTypeJson();
        profileTypeJson.setId(profileTypeEntity.getId());
        profileTypeJson.setName(profileTypeEntity.getName());

        return profileTypeJson;
    }

}
