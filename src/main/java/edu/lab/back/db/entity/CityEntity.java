package edu.lab.back.db.entity;

import edu.lab.back.json.request.CityRequestJson;
import edu.lab.back.json.response.CityResponseJson;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.NonNull;
import lombok.Setter;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "city")
@Getter
@Setter
@NoArgsConstructor
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "city_generator")
    @SequenceGenerator(name = "city_generator", sequenceName = "city_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "city", cascade = CascadeType.REMOVE)
    private List<SchoolEntity> schools;


    @Override
    public String toString() {
        String schoolsString = "";
        if (this.schools != null) {
            schoolsString = this.schools.stream()
                .map(s -> s.getId().toString())
                .reduce((r, s) -> r + ", " + s)
                .orElse("");
        }

        return "CityEntity{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", schools_ids=" + schoolsString +
            '}';
    }

    public static CityEntity convert(@NonNull final CityResponseJson cityResponseJson) {
        final CityEntity entity = new CityEntity();
        entity.setName(cityResponseJson.getName());
        entity.setId(cityResponseJson.getId());
        //TODO schools

        return entity;
    }

    public static CityEntity convert(@NonNull final CityRequestJson cityJson) {
        final CityEntity entity = new CityEntity();
        entity.setName(cityJson.getName());
        entity.setId(cityJson.getId());

        return entity;
    }
}
