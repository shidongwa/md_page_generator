package com.shi.iteye;

import java.io.BufferedReader;
import java.io.InputStreamReader;

import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;

import com.shi.iteye.crawler.IteyeBlogCrawler;

public class IteyeMain {

	@SuppressWarnings("resource")
	public static void main(String[] args) throws Exception {
		// TODO Auto-generated method stub
		ApplicationContext context = new ClassPathXmlApplicationContext(
				new String[] {"/iteye-context.xml"});


        
        IteyeBlogCrawler crawler = (IteyeBlogCrawler) context.getBean("iteyeCrawl");
//        crawler.clearAllSeeds();
//        String url = "http://echohfut.iteye.com/?page=4";
//        String url = "http://echohfut.iteye.com";
/*        crawler.addSeed(new CrawlDatum(url)
		.setKey(url)
		.meta("method", "GET"));
        url = "http://echohfut.iteye.com/?page=5";
        crawler.addSeed(new CrawlDatum(url)
		.setKey(url)
		.meta("method", "GET"));
        System.out.println(url);*/
//        crawler.addSeed("http://echohfut.iteye.com");
        for(int i=1; i<2; i++){ //17
        	String url2 = "http://echohfut.iteye.com/?page=" + i;
        	System.out.println(url2);
            crawler.addSeed(new CrawlDatum(url2)
    		.setKey(url2)
    		.meta("method", "GET"));        	
        }
        crawler.addRegex("http://echohfut.iteye.com/\\?page=[0-9]*");
        crawler.addRegex("http://echohfut.iteye.com/blog/[0-9]*");
                
        crawler.setThreads(1);
        crawler.setMaxExecuteCount(1);
        crawler.setExecuteInterval(5000);
        crawler.start(2);
		
		new BufferedReader(new InputStreamReader(System.in)).readLine(); 
	}

}
