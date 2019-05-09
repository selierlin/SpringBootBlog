package com.theliang.blog.repository;

import com.theliang.blog.domain.User;
import org.springframework.data.repository.CrudRepository;

/**
 * 用户仓库.
 */
public interface UserRepository extends CrudRepository<User, Long> {
}