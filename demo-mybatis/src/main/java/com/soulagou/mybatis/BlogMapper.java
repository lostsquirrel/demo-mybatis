package com.soulagou.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface BlogMapper {

	Blog selectBlog(long i);
	
	@Select("SELECT * FROM Blog")
	List<Blog> findAll();

	void insertBlog(Blog blog);

	List<Blog> findActiveBlogWithTitleLike(BlogQuery q);

	List<Blog> findByState(int i);

	List<Blog> findByTitle(String title);

	List<Blog> selectBlogIn(List<Long> ids);

}
