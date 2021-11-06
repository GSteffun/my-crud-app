package org.steffun.config;


import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ViewResolverRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.steffun.dao.UserDao;
import org.steffun.dao.UserDaoImpl;
import org.steffun.service.UserService;
import org.steffun.service.UserServiceImpl;
import org.thymeleaf.spring5.SpringTemplateEngine;
import org.thymeleaf.spring5.templateresolver.SpringResourceTemplateResolver;
import org.thymeleaf.spring5.view.ThymeleafViewResolver;

import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import java.util.Properties;

@Configuration
@EnableWebMvc
@EnableTransactionManagement
@PropertySource("classpath:db.properties")
@ComponentScan(value = "org.steffun")
public class WebConfig implements WebMvcConfigurer {

    private final Environment env;
    private final ApplicationContext applicationContext;

    public WebConfig(Environment env, ApplicationContext applicationContext) {
        this.env = env;
        this.applicationContext = applicationContext;
    }

//    @Bean
//    public DataSource getDataSource() {
//        DriverManagerDataSource dataSource = new DriverManagerDataSource();
//        dataSource.setDriverClassName(env.getProperty("db.driver"));
//        dataSource.setUrl(env.getProperty("db.url"));
//        dataSource.setUsername(env.getProperty("db.username"));
//        dataSource.setPassword(env.getProperty("db.password"));
//        return dataSource;
//    }

    @Bean
    public EntityManagerFactory getEntityManagerFactory() {
        Properties properties = new Properties();
        properties.setProperty("connection.driver_class", env.getProperty("db.driver"));
        properties.setProperty("hibernate.connection.url", env.getProperty("db.url"));
        properties.setProperty("hibernate.connection.username", env.getProperty("db.username"));
        properties.setProperty("hibernate.connection.password", env.getProperty("db.password"));
        properties.setProperty("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
        properties.setProperty("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
        properties.setProperty("hibernate.dialect", env.getProperty("hibernate.dialect"));

        EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory("org.steffun", properties);

        return entityManagerFactory;
//        LocalSessionFactoryBean factoryBean = new LocalSessionFactoryBean();
//        factoryBean.setDataSource(getDataSource());
//
//        Properties properties = new Properties();
//        properties.put("hibernate.show_sql", env.getProperty("hibernate.show_sql"));
//        properties.put("hibernate.hbm2ddl.auto", env.getProperty("hibernate.hbm2ddl.auto"));
//        properties.put("hibernate.dialect", env.getProperty("hibernate.dialect"));
//
//        factoryBean.setHibernateProperties(properties);
//        factoryBean.setAnnotatedClasses(User.class);
//        return factoryBean;
    }

    @Bean
    public JpaTransactionManager getTransactionManager() {
        JpaTransactionManager transactionManager = new JpaTransactionManager();
        transactionManager.setEntityManagerFactory(getEntityManagerFactory());
        return transactionManager;
    }

    @Bean
    public SpringResourceTemplateResolver templateResolver() {
        SpringResourceTemplateResolver templateResolver = new SpringResourceTemplateResolver();
        templateResolver.setApplicationContext(applicationContext);
        templateResolver.setPrefix("/WEB-INF/pages/");
        templateResolver.setSuffix(".html");
        templateResolver.setCharacterEncoding("UTF-8");
        return templateResolver;
    }

    @Bean
    public SpringTemplateEngine templateEngine() {
        SpringTemplateEngine templateEngine = new SpringTemplateEngine();
        templateEngine.setTemplateResolver(templateResolver());
        templateEngine.setEnableSpringELCompiler(true);
        return templateEngine;
    }

    @Bean
    public UserDao userDao() {
        return new UserDaoImpl(getEntityManagerFactory().getObject());
    }

    @Bean
    public UserService userService() {
        return new UserServiceImpl(userDao());
    }

    @Override
    public void configureViewResolvers(ViewResolverRegistry registry) {
        ThymeleafViewResolver resolver = new ThymeleafViewResolver();
        resolver.setTemplateEngine(templateEngine());
        registry.viewResolver(resolver);
        resolver.setCharacterEncoding("UTF-8");
        resolver.setContentType("text/html;charset=UTF-8");
    }

}
