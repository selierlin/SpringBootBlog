package com.waylau.spring.boot.blog.repository;

import com.waylau.spring.boot.blog.domain.Authority;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Authority 仓库.
 */
public interface AuthorityRepository extends JpaRepository<Authority, Long> {
}
