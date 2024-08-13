package com.speech.up.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.List;

@ToString
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    @Column(name = "social_id")
    private String socialId;

    private String email;

    private String level; // => ENUM 값으로 대체 필요

    private String authorization; // => ENUM 값으로 대체 필요

    private String providerType;

    @Null
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScriptEntity> scriptEntity;

    public UserEntity(Long userId, String name, String socialId, String email,
                      String level, String authorization) {
        this.userId = userId;
        this.name = name;
        this.socialId = socialId;
        this.email = email;
        this.level = level;
        this.authorization = authorization;
    }


    public UserEntity(String socialId, String email, String level, String name, String authorization) {
        this.socialId = socialId;
        this.email = email;
        this.level = level;
        this.name = name;
        this.authorization = authorization;
    }

    public UserEntity(String socialId, String email, String level,
        String name, String authorization, String providerType) {
        this.socialId = socialId;
        this.email = email;
        this.level = level;
        this.name = name;
        this.authorization = authorization;
        this.providerType = providerType;
    }
}
