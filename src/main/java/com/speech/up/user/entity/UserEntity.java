package com.speech.up.user.entity;

import java.time.LocalDateTime;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.script.entity.ScriptEntity;
import com.speech.up.user.service.dto.UserUpdateDto;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Null;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

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

	private String level;

	private String authorization;

	private String providerType;

	private LocalDateTime lastAccessedAt;

	private boolean isUse;

	@Null
	@OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
	@JsonManagedReference
	@ToString.Exclude
	private List<ScriptEntity> scriptEntity;

	public void setScriptEntity(
		@Null List<ScriptEntity> scriptEntity) {
		this.scriptEntity = scriptEntity;
	}

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

	private UserEntity(UserUpdateDto.Request userUpdateDto) {
		this.userId = userUpdateDto.getUserId();
		this.name = userUpdateDto.getName();
		this.socialId = userUpdateDto.getSocialId();
		this.email = userUpdateDto.getEmail();
		this.level = userUpdateDto.getLevel();
		this.providerType = userUpdateDto.getProviderType();
		this.authorization = userUpdateDto.getAuthorization();
		this.lastAccessedAt = userUpdateDto.getLastAccessedAt();
		this.isUse = userUpdateDto.isUse();
	}

	public static UserEntity providerOf(String socialId, String email, String level,
		String name, String authorization, String providerType) {
		return new UserEntity(socialId, email, level, name, authorization, providerType);
	}

	public static UserEntity updateUserAccess(UserEntity user) {
		return new UserEntity(user);
	}

	public static UserEntity update(UserUpdateDto.Request userUpdateDto) {
		return new UserEntity(userUpdateDto);
	}
}
