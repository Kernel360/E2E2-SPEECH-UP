package com.speech.up.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;
import jakarta.persistence.*;
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

    private String password;

    private String token;

    private String address;

    private String rank;

    private String authorization;

    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScriptEntity> scriptEntity;

    public UserEntity(Long userId, String name, String socialId, String password, String token, String address,
                      String rank, String authorization) {
        this.userId = userId;
        this.name = name;
        this.socialId = socialId;
        this.password = password;
        this.token = token;
        this.address = address;
        this.rank = rank;
        this.authorization = authorization;
    }

}
