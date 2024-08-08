package com.speech.up.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.oAuth.service.dto.SignUpRequestDto;
import com.speech.up.script.entity.ScriptEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.util.List;

@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Entity
@Table(name = "user")
@JsonNaming(value = PropertyNamingStrategies.SnakeCaseStrategy.class)
public class UserEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long userId;

    private String name;

    private String socialId;

    @Null
    private String password;

    private String email;

    @Null
    private String address;

    @Null
    private String level;

    private String authorization;

    @Null
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScriptEntity> scriptEntity;

    public UserEntity(Long userId, String name, String socialId, String password, String email, String address,
                      String level, String authorization) {
        this.userId = userId;
        this.name = name;
        this.socialId = socialId;
        this.password = password;
        this.email = email;
        this.address = address;
        this.level = level;
        this.authorization = authorization;
    }

    public UserEntity(SignUpRequestDto dto) {
        this.name = dto.getEmail();
        this.socialId = dto.getId();
    }

    public UserEntity(String socialId, String email, String  name, String authorization) {
        this.userId = 1L;
        this.socialId = socialId;
        this.email = email;
        this.name = name;
        this.authorization = authorization;
    }
}
