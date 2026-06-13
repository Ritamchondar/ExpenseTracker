package com.ritamrupayan.expenseTracker.repository;

import java.util.Optional;
import java.util.UUID;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ritamrupayan.expenseTracker.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, UUID> {
	Optional<User> findByUserMail(String userMail);
	boolean existsByUserMail(String userMail);
	User findByUserMailAndUserPassword(String userMail, String userPassword);
}
