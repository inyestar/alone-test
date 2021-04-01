package com.inminhouse.alone.user.model;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Builder
@Getter
@Setter
public class User {

	private String uid;

	private String email;

	private String pw;

	private String name;

}
