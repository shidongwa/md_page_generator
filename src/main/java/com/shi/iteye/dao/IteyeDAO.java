package com.shi.iteye.dao;

import java.util.List;

import com.shi.iteye.model.Blog;


public interface IteyeDAO {
	int insertBlog(String author, String title, String content);
	
	List<Blog> selectBlog(int start, int size);
	
	int countBlog();
}
