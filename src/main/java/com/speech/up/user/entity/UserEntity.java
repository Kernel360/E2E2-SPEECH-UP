package com.speech.up.user.entity;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;

import jakarta.persistence.*;
import jakarta.validation.constraints.Null;
import lombok.*;

import java.time.LocalDateTime;
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
    @Column(name = "user_id")
    private Long userId;

    private String name;

    @Column(name = "social_id")
    private String socialId;

    private String email;

    private String level; // => ENUM 값으로 대체 필요

    private String authorization; // => ENUM 값으로 대체 필요

    private String providerType;

    private LocalDateTime lastAccessedAt;

    private boolean isUse;

    @Null
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<ScriptEntity> scriptEntity;





    private UserEntity(String socialId, String email, String level,
        String name, String authorization, String providerType) {
        this.socialId = socialId;
        this.email = email;
        this.level = level;
        this.name = name;
        this.authorization = authorization;
        this.providerType = providerType;
        this.lastAccessedAt = LocalDateTime.now();
        this.isUse = true;
    }

    private UserEntity(UserEntity user) {
        this.userId = user.getUserId();
        this.name = user.getName();
        this.socialId = user.getSocialId();
        this.email = user.getEmail();
        this.level = user.getLevel();
        this.providerType = user.getProviderType();
        this.authorization = user.getAuthorization();
        this.lastAccessedAt = LocalDateTime.now();
        this.isUse = true;
    }

    public static UserEntity providerOf(String socialId, String email, String level,
        String name, String authorization, String providerType){
        return new UserEntity(socialId, email, level, name, authorization, providerType);
    }

    public static UserEntity updateUserAccess(UserEntity user){
        return new UserEntity(user);
    }
}
