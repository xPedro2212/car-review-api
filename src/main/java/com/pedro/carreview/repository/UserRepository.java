package com.pedro.carreview.repository;

import com.pedro.carreview.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository  extends JpaRepository<User, Long> {
}
