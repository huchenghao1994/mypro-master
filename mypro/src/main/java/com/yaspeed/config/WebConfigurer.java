package com.yaspeed.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import com.yaspeed.core.context.SpringContextHolder;
import com.yaspeed.web.cache.PermissionCacheProvider;
import com.yaspeed.web.interceptor.SessionUserInterceptor;
import org.apache.ibatis.plugin.Interceptor;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.templateresolver.ITemplateResolver;
import javax.sql.DataSource;
import java.util.Properties;

/**
 * Created by Administrator on 2019/3/15.
 */
@Configuration
@EnableTransactionManagement
@MapperScan(basePackages = "com.yaspeed.web.dao")
public class WebConfigurer implements WebMvcConfigurer{
    @Bean
    public SessionUserInterceptor sessionUserInterceptor(){
        return new SessionUserInterceptor();
    }

    @Bean
    public PermissionCacheProvider permissionCache(){
        return new PermissionCacheProvider();
    }

    @Bean
    public SpringContextHolder springContextHolder(){
        return new SpringContextHolder();
    }

    /**
     * 数据库连接dataSource
     * @return
     */
    @Bean
    public DataSource dataSource() {
        DruidDataSource dataSource = new DruidDataSource();
        dataSource.setDbType("com.alibaba.druid.pool.DruidDataSource");
        dataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");
        dataSource.setUrl("jdbc:mysql://106.15.36.5:3306/rds?characterEncoding=utf-8");
        dataSource.setUsername("root");
        dataSource.setPassword("rt123tr");
        dataSource.setTestOnBorrow(true);
        dataSource.setTestWhileIdle(true);
        return dataSource;
    }

    /**
     * 分页pageHelper
     * @return
     */
    @Bean
    public PageHelper pageHelper(){
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect","mysql");
        properties.setProperty("reasonable","true");
        properties.setProperty("supportMethodsArguments","true");
        properties.setProperty("params","count=countSql");
        pageHelper.setProperties(properties);
        Interceptor interceptor = new PageInterceptor();
        interceptor.setProperties(properties);
        new SqlSessionFactoryBean().setPlugins(new Interceptor[]{interceptor});
        return pageHelper;
    }

    @Bean
    public ITemplateResolver resourceViewResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setPrefix("classpath:/static/tpl/");
        templateResolver.setSuffix(".html");
        templateResolver.setCacheable(false);
        return templateResolver;
    }

    /**
     * 事物管理，开启事务@EnableTransactionManagement
     * @return
     */
    @Bean
    public DataSourceTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    @Bean
    public SqlSessionFactoryBean sqlSessionFactory() throws Exception {
        SqlSessionFactoryBean sessionFactory = new SqlSessionFactoryBean();
        sessionFactory.setDataSource(dataSource());
        //sessionFactory.setTypeAliasesPackage("com.yaspeed.pojo");
        sessionFactory.setMapperLocations(new PathMatchingResourcePatternResolver().getResources("classpath:com/yaspeed/web/mapper/*Mapper.xml"));
        return sessionFactory;
    }

    /**
     * 登陆信息拦截器
     * @param registry
     */
    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(sessionUserInterceptor()).addPathPatterns("/**");
    }
}
