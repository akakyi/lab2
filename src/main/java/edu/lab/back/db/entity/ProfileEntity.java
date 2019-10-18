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
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = "profile")
//@Data
@Getter
@Setter
@NoArgsConstructor
public class ProfileEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_generator")
    @SequenceGenerator(name = "profile_generator", sequenceName = "profile_id_sequence", allocationSize = 1)
    private Long id;

    @Column(name = "name")
    private String name;

    @Column(name = "age")
    private Integer age;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "type_id")
    private ProfileTypeEntity profileType;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "school_id")
    private SchoolEntity school;

    @Column(name = "class_level")
    private String classLevel;

    @Override
    public String toString() {
        final String progileTypeStr = this.profileType == null ? "" : this.profileType.getName();
        final String schoolStr = this.school.getName() == null ? "" : this.school.getName();

        return "ProfileEntity{" +
            "id=" + this.id +
            ", name='" + this.name + '\'' +
            ", age=" + this.age +
            ", profileType=" + progileTypeStr +
            ", school=" + schoolStr +
            ", classLevel='" + this.classLevel + '\'' +
            '}';
    }
}
