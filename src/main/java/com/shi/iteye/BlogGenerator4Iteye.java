package com.shi.iteye;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.util.Date;
import java.util.List;
import java.util.Properties;

import javax.annotation.PostConstruct;

import org.apache.velocity.Template;
import org.apache.velocity.VelocityContext;
import org.apache.velocity.app.Velocity;
import org.springframework.beans.factory.annotation.Autowired;

import com.shi.iteye.dao.IteyeDAO;
import com.shi.iteye.model.Blog;

public class BlogGenerator4Iteye {
	private final static int PAGE_SIZE = 5;
	@Autowired
	private IteyeDAO iteyeDao;

	@PostConstruct
	public static void init() {
		try {
			Properties prop = new Properties();
			// 指定日志文件存放位置
			prop.put("runtime.log", "src/log/velocity.log");
			// 指定模板文件存放位置
			prop.put("file.resource.loader.path", "src/main/resources/velocity/");
			// 指定输入编码格式
			prop.put("input.encoding", "UTF-8");
			// 指定输出编码格式
			prop.put("output.encoding", "UTF-8");
			// 修改获取迭代索引的名称，默认为velocityCount
			prop.put("directive.foreach.counter.name", "index");
			// 修改迭代索引的初始值，默认为1
			prop.put("directive.foreach.counter.initial.value", "0");
			Velocity.init(prop);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public void generateBlog(String path) {
		java.text.DateFormat format1 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd");

		String date = format1.format(new Date());

		int count = iteyeDao.countBlog();
//		int count = 1;

		int page = count / PAGE_SIZE;
		for (int i = 0; i < page; i++) {
			List<Blog> blogs = iteyeDao.selectBlog(i * PAGE_SIZE, PAGE_SIZE);
			createBlogFile(path, date, "iteye", i * PAGE_SIZE, blogs);
		}

		if (count > page * PAGE_SIZE) {
			List<Blog> blogs = iteyeDao.selectBlog(page * PAGE_SIZE, count
					- page * PAGE_SIZE);

			createBlogFile(path, date, "iteye", page * PAGE_SIZE, blogs);
		}
	}

	private void createBlogFile(String path, String date, String source,
			int fromSize, List<Blog> blogs) {
		java.text.DateFormat format2 = new java.text.SimpleDateFormat(
				"yyyy-MM-dd HH:mm:ss");
		
		for (int i = 0; i < blogs.size(); i++) {
			String filePath = path + File.separator + date + "-" + source + "-"
					+ (fromSize + i) + ".md";
			try {
				FileOutputStream fos = new FileOutputStream(filePath);
				BufferedWriter writer = new BufferedWriter(
						new OutputStreamWriter(fos, "UTF-8"));
				Template template = Velocity.getTemplate("gitlab_blog.vm");

				VelocityContext context = new VelocityContext();
				context.put("title", blogs.get(i).getTitle());
				context.put("content", blogs.get(i).getContent());
				context.put("source", source);
				context.put("date", format2.format(new Date()));
				template.merge(context, writer);
				writer.close();
			} catch (Exception e) {
				e.printStackTrace();
			}
		}
	}

	public IteyeDAO getIteyeDao() {
		return iteyeDao;
	}

	public void setIteyeDao(IteyeDAO iteyeDao) {
		this.iteyeDao = iteyeDao;
	}
}
