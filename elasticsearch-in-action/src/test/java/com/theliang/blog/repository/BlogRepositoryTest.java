package com.theliang.blog.repository;

import com.theliang.blog.domain.Blog;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.core.ElasticsearchTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
public class BlogRepositoryTest {
    @Autowired
    private BlogRepository blogRepository;
    @Autowired
    private ElasticsearchTemplate elasticsearchTemplate;

    @Before
    @Test
    public void initBlogRepositoryData() {
        //清除所有数据
        blogRepository.deleteAll();

        blogRepository.save(new Blog("登鹳雀楼", "王之换的登鹳雀楼", "白日依山尽，黄河入海流。欲穷千里目,更上一层楼。"));
        blogRepository.save(new Blog("相思", "王维的相思", "红豆生南国,春来发几枝。愿君多采撷,此物最相思。"));
        blogRepository.save(new Blog("静夜思", "李白的静夜思", "床前明月光,疑是地上霜。举头望明月,低头思故乡"));
        Blog blog = new Blog("静夜思11", "李白的静夜思", "床前明月光,疑是地上霜。举头望明月,低头思故乡");
        blog.setId("1");
        blogRepository.save(blog);

    }

    @Test
    public void testA() {
        elasticsearchTemplate.createIndex(Blog.class);
    }

    @Test
    public void testfindDistinctBlogByTitleContainingOrSummaryContainingOrContentContaining() {
        Pageable pageable = PageRequest.of(0, 20);
        String title = "思";
        String summary = "相思";
        String content = "相思";
        Page<Blog> page = blogRepository.findDistinctBlogByTitleContainingOrSummaryContainingOrContentContaining(title, summary, content, pageable);
        Assertions.assertThat(page.getTotalElements()).isEqualTo(2);
        for (Blog blog : page.getContent()) {
            System.out.println(blog);
        }
        System.out.println("------end");
    }
}
