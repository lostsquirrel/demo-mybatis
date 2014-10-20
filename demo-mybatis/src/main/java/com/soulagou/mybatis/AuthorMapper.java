package com.soulagou.mybatis;

import java.util.List;

import org.apache.ibatis.annotations.Select;

public interface AuthorMapper {

	Author selectAuthor(long i);
	
	@Select("SELECT * FROM author")
	List<Author> findAll();

	void insertAuthor(Author author);

	void updateAuthor(Author au);

}
