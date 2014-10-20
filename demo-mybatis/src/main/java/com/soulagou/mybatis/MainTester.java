package com.soulagou.mybatis;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.ibatis.io.Resources;
import org.apache.ibatis.session.SqlSession;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.session.SqlSessionFactoryBuilder;
import org.junit.Before;
import org.junit.Test;

public class MainTester {

	SqlSessionFactory sqlSessionFactory = null;

	SqlSession session = null;

	BlogMapper blogMapper = null;

	AuthorMapper authorMapper = null;

	@Before
	public void init() {
		String resource = "mybatis-config.xml";
		InputStream inputStream;
		try {
			inputStream = Resources.getResourceAsStream(resource);
			sqlSessionFactory = new SqlSessionFactoryBuilder()
					.build(inputStream);
			session = sqlSessionFactory.openSession();
			blogMapper = session.getMapper(BlogMapper.class);
			authorMapper = session.getMapper(AuthorMapper.class);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Test
	public void addAuthor() {
		try {
			Author au = new Author();
			au.setName("张三2");
			au.setBrief("张家老三2");
			saveAuthor(au);
			session.commit();
		} finally {
			session.close();
		}
	}

	@Test
	public void addAuthor_2() {
		try {
			String ns = "顺风, 苗炜, 李光斗, 罗援, 徐焰, 徐静波, 北美崔哥, 巨春雷";
			for (String name : ns.split(", ")) {
				Author au = new Author();
				au.setName(name);
				// au.setBrief("张家老三2");
				saveAuthor(au);
			}
			session.commit();
		} finally {
			session.close();
		}
	}

	private void saveAuthor(Author au) {
		authorMapper.insertAuthor(au);
	}

	@Test
	public void getAuthorAll() {
		try {
			List<Author> aus = authorMapper.findAll();
			System.out.print(aus);
		} finally {
			session.close();
		}
	}

	@Test
	public void updateAuthor() {
		Author au = this.getAuthor(2);
		System.out.println(au);
		authorMapper.updateAuthor(au);
		Author aux = this.getAuthor(2);
		System.out.println(aux);
		System.out.println(au.getVersion().compareTo(aux.getVersion()));
	}
	@Test
	public void addBlog_1() {
		try {
			Blog blog = new Blog();
			blog.setTitle("title");
			blog.setContent("ccccccccccc");
			Author au = authorMapper.selectAuthor(2L);
			blog.setAuthor(au);
			blogMapper.insertBlog(blog);
			session.commit();
		} finally {
			session.close();
		}

	}

	@Test
	public void addBlog_2() {
		try {
			String bs = "我坚信苏格兰不会独立 当地人淡定, 欧洲人什么时候有了国家意识, 警方抓王全安嫖娼的凶猛利器, 对日采取鸵鸟政策将致民族危亡, 日侵华战犯归国曾唱《东方红》, 探秘中俄各占一半的黑瞎子岛, 中国人惊人一致的奋斗目标, 姚晨团队散播歪曲言论 爆料可信吗";
			long oid = 4L;
			for (String title : bs.split(", ")) {

				Blog blog = new Blog();
				blog.setTitle(title);
				// blog.setContent();
				Author au = authorMapper.selectAuthor(oid);
				blog.setAuthor(au);
				blogMapper.insertBlog(blog);
				oid++;
			}
			session.commit();
		} finally {
			session.close();
		}

	}

	@Test
	public void getBlog_1() {
		try {
			Blog blog = session.selectOne(
					"com.soulagou.mybatis.BlogMapper.selectBlog", 1L);
			System.out.print(blog);
		} finally {
			session.close();
		}

	}

	private Author getAuthor(long id) {
		Author au = authorMapper.selectAuthor(id);
		return au;
	}

	@Test
	public void testGetAuthor() {
		try {
			Author au = this.getAuthor(1L);
			System.out.print(au);
		} finally {
			session.close();
		}
	}

	@Test
	public void getBlog_2() {
		try {
			Blog blog = blogMapper.selectBlog(1L);
			System.out.print(blog);
		} finally {
			session.close();
		}
	}

	@Test
	public void getBlog_3() {
		try {
			List<Blog> blogs = blogMapper.findAll();
			System.out.print(blogs);
		} finally {
			session.close();
		}
	}

	@Test
	public void getBlog_4() {
		try {
			String title = "%t%";
			List<Blog> blogs = null;
			Map<String, Object> params = new HashMap<String, Object>();
			params.put("title", "t%");
			BlogQuery q = new BlogQuery();
			q.setTitle(title);
			blogMapper.findActiveBlogWithTitleLike(q);
			// blogs = blogMapper.findByState(0);
			// blogs = blogMapper.findByTitle(title);
			System.out.print(blogs);
		} finally {
			session.close();
		}
	}

	@Test
	public void getBlog_5() {
		try {
			List<Long> ids = Arrays.asList(1L);
			List<Blog> blogs = blogMapper.selectBlogIn(ids);
			System.out.print(blogs);
		} finally {
			session.close();
		}
	}
}
