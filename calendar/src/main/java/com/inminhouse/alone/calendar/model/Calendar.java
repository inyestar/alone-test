package com.inminhouse.alone.calendar.model;

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
@Table(name = "calendar")
@Getter
@Setter
@Builder
@AllArgsConstructor(access = AccessLevel.PACKAGE)
@NoArgsConstructor(access = AccessLevel.PACKAGE)
public class Calendar {

	@Id
	@Column(name = "cid", nullable = false)
	private String cid;

	@Column(name = "event", nullable = false)
	private String event;

	// TODO : uid fk 맵핑표현
	@Column(name = "uid", nullable = false)
	private String uid;

}
