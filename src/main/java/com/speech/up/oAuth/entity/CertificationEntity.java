package com.speech.up.oAuth.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@AllArgsConstructor
@NoArgsConstructor
@Entity(name = "certification")
@Table(name = "certification")
public class CertificationEntity {
	@Id
	private String socialId;
	private String email;
	private String certificationNumber;
}
