package edu.lab.back.db.entity;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

@Entity
@Table(name = ProfileTypeEntity.TABLE_NAME)
@Data
@NoArgsConstructor
public class ProfileTypeEntity {

    public static final String TABLE_NAME = "profile_type";

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "profile_type_generator")
    @SequenceGenerator(name = "profile_type_generator", sequenceName = "profile_type_id_sequence", allocationSize = 1)
    private Integer id;

    @Column(name = "name")
    private String name;

}
