package com.inminhouse.alone.user.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Table(name = "user")
@Builder
@Getter
@Setter
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class User {

	@Id
	@Column(name = "uid", nullable = false)
	private String uid;

	@Column(name = "email", nullable = false)
	private String email;

	@Column(name = "pw", nullable = false)
	private String pw;

	@Column(name = "name", nullable = false)
	private String name;

}
