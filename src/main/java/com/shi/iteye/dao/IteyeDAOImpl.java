package com.shi.iteye.dao;

import com.shi.iteye.model.Blog;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public class IteyeDAOImpl implements IteyeDAO {
	@Autowired
    private DataSource dataSource;

    public DataSource getDataSource() { 
        return dataSource; 
    } 

    public void setDataSource(DataSource dataSource) { 
        this.dataSource = dataSource; 
    } 
    
    public JdbcTemplate getJdbcTemplate(){ 
        return new JdbcTemplate(getDataSource()); 
    } 

	public int insertBlog(String author, String title, String content) {
		return getJdbcTemplate().update("insert into iteye_blog"
				+ " (author,title,content,created_at,updated_at) values(?,?,?,now(),now())",
				author, title, content);
	}

	public List<Blog> selectBlog(int start, int size) {
		List<Blog> blogs = getJdbcTemplate().query(
				"select title,content from iteye_blog "
						+ "limit " + start +"," + size,
				new RowMapper<Blog>() {
					@Override
					public Blog mapRow(ResultSet rs, int rowNum)
							throws SQLException {
						Blog blog = new Blog();
						blog.setTitle(rs.getString("title"));
						blog.setContent(rs.getString("content"));
						return blog;
					}
				});
		return blogs;
	}

	public int countBlog() {
		int count = getJdbcTemplate().queryForObject("select count(1) from iteye_blog", Integer.class);
		return count;
	}
}
