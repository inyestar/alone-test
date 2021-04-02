package com.inminhouse.alone.user.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.inminhouse.alone.user.model.User;

@Repository // 이 인터페이스를 레포지토리로 취급하고 동적 프록시를 생성해야 한다고 스프링에 노티
public interface UserRepository extends CrudRepository<User, String> {

}
