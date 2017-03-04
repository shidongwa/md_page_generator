1. 下载H2 Database: http://www.h2database.com/html/download.html
2. 创建表， sql: src/main/resources/db/createDB.sql
3. 从idea中运行IteyeMain程序从iteye blog网页抓取自己的blog列表到H2 Database
4. 从idea中运行Generator程序中H2 Database中生成md文件(应用velocity/gitlab_blog.vm)