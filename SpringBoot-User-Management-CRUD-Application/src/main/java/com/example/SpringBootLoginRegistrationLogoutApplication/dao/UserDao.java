package com.example.SpringBootLoginRegistrationLogoutApplication.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import com.example.SpringBootLoginRegistrationLogoutApplication.model.UserModel;

@Repository
public interface UserDao extends JpaRepository<UserModel, Long>{

	@Transactional
	@Modifying
	@Query("update UserModel t set t.name=:name, t.email=:email, t.country=:country, t.age=:age where t.id=:id")
	void updateUser(String name, String email, String country, int age, long id);

	@Query("from UserModel where id=:id")
	UserModel findByid(long id);
	
}
