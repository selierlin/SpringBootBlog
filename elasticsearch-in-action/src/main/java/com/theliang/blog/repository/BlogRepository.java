package com.theliang.blog.repository;

import com.theliang.blog.domain.Blog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

public interface BlogRepository extends ElasticsearchRepository<Blog, String> {
    /**
     * 分页查询博客（去重）
     */
    Page<Blog> findDistinctBlogByTitleContainingOrSummaryContainingOrContentContaining(String title, String summary, String content, Pageable pageable);

    /**
     * 模糊查询
     */
    Page<Blog> findByTitleLikeOrSummaryLikeOrContentLike(String title, String summary, String content, Pageable pageable);

    Page<Blog> findByContentLike(String content, Pageable pageable);


}
