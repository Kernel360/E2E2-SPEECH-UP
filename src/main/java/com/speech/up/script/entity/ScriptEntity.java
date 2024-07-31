package com.speech.up.script.entity;

import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.databind.PropertyNamingStrategies;
import com.fasterxml.jackson.databind.annotation.JsonNaming;
import com.speech.up.user.entity.UserEntity;
import jakarta.persistence.*;
import lombok.*;
import java.sql.Timestamp;

@ToString
@Builder
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "script")
@JsonNaming(PropertyNamingStrategies.SnakeCaseStrategy.class)
public class ScriptEntity {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long scriptId;

	private String content;

	private Timestamp createdAt;

	private Timestamp modifiedAt;

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id", nullable = false)
	@JsonBackReference
	private UserEntity user;

}
