package edu.lab.back.db.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import java.util.List;

@Entity
@Table(name = "school")
//@Data
@Getter
@Setter
@NoArgsConstructor
public class SchoolEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "school_generator")
    @SequenceGenerator(name = "school_generator", sequenceName = "school_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "city_id")
    private CityEntity city;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "school")
    private List<ProfileEntity> profiles;

//    public SchoolEntity conveert(@NonNull final SchoolRequestJson requestJson) {
//        final SchoolEntity entity = new SchoolEntity();
//        entity.setId(requestJson.getId());
//        entity.setName(requestJson.getName());
//    }

    @Override
    public String toString() {
        String profilesStr = "";
        if (this.profiles != null) {
            profilesStr = this.profiles
                .stream()
                .map(p -> p.getId().toString())
                .reduce((s, p) -> s + ", " + p)
                .orElse("");
        }

        return "SchoolEntity{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", city_name=" + this.city.getName() +
            ", profiles_ids=" + profilesStr +
            '}';
    }
}
