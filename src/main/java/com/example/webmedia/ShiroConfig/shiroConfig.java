package com.example.webmedia.ShiroConfig;

import org.apache.shiro.authc.credential.HashedCredentialsMatcher;
import org.apache.shiro.cache.ehcache.EhCacheManager;
import org.apache.shiro.spring.LifecycleBeanPostProcessor;
import org.apache.shiro.spring.security.interceptor.AuthorizationAttributeSourceAdvisor;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.aop.framework.autoproxy.DefaultAdvisorAutoProxyCreator;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.web.servlet.FilterRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;
import org.springframework.web.filter.DelegatingFilterProxy;

import java.util.LinkedHashMap;
import java.util.Map;

@Configuration
public class shiroConfig {



    //加密规则
    @Bean
    public HashedCredentialsMatcher hashedCredentialsMatcher(){
        HashedCredentialsMatcher hashedCredentialsMatcher = new HashedCredentialsMatcher();
        hashedCredentialsMatcher.setHashAlgorithmName("MD5");
        hashedCredentialsMatcher.setHashIterations(1024);

       // hashedCredentialsMatcher.setStoredCredentialsHexEncoded(false);
        return hashedCredentialsMatcher;
    }


    @Bean(name = "lifecycleBeanPostProcessor")
    public LifecycleBeanPostProcessor lifecycleBeanPostProcessor() {
        return new LifecycleBeanPostProcessor();
    }



    //自定义Realm
    @Bean(name = "shiroRealm")
    @DependsOn("lifecycleBeanPostProcessor")
    public MyRealm shiroRealm(HashedCredentialsMatcher hashedCredentialsMatcher) {
        MyRealm realm = new MyRealm();
        realm.setCredentialsMatcher(hashedCredentialsMatcher);
        return realm;
    }


    //eh缓存
    @Bean(name = "ehCacheManager")
    @DependsOn("lifecycleBeanPostProcessor")
    public EhCacheManager ehCacheManager(){
        EhCacheManager ehCacheManager = new EhCacheManager();
        return ehCacheManager;
    }


    //安全管理器
    @Bean(name = "securityManager")
    public DefaultWebSecurityManager securityManager(){
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setCacheManager(ehCacheManager());//用户授权/认证信息Cache, 采用EhCache 缓存
        securityManager.setRealm(shiroRealm(hashedCredentialsMatcher()));
        return securityManager;
    }

    //shiro过滤器
    @Bean(name = "shiroFilter")
    public ShiroFilterFactoryBean shiroFilterFactoryBean(DefaultWebSecurityManager securityManager){
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager);

        System.out.println(">>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>>");

        // 过滤链定义，从上向下顺序执行，一般将 /**放在最为下边
        Map<String, String> filterChainDefinitionManager = new LinkedHashMap<String, String>();
        // 配置退出过滤器,其中的具体的退出代码Shiro已经替我们实现了
     //   filterChainDefinitionManager.put("/user/logout", "logout");
        // authc:所有url都必须认证通过才可以访问; anon:所有url都都可以匿名访问
//        filterChainDefinitionManager.put("/user/**", "authc,roles[user]");
//        filterChainDefinitionManager.put("/admin/**", "authc,roles[admin]");
     //   filterChainDefinitionManager.put("/admin", "authc,roles[admin]");
      //  filterChainDefinitionManager.put("/user", "authc,roles[user]");
//        filterChainDefinitionManager.put("/index", "anon");
//        filterChainDefinitionManager.put("/ajaxLogin", "anon");
//        filterChainDefinitionManager.put("/statistic/**",  "anon");
        filterChainDefinitionManager.put("/**",  "anon");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filterChainDefinitionManager);

        // 如果不设置默认会自动寻找Web工程根目录下的"/login.jsp"页面
        shiroFilterFactoryBean.setLoginUrl("/");
        // 登录成功后要跳转的链接
//        shiroFilterFactoryBean.setSuccessUrl("/success");
        // 未授权界面
        shiroFilterFactoryBean.setUnauthorizedUrl("/403.html");

        return shiroFilterFactoryBean;
    }

    @Bean
    @ConditionalOnMissingBean
    public DefaultAdvisorAutoProxyCreator defaultAdvisorAutoProxyCreator() {
        DefaultAdvisorAutoProxyCreator daap = new DefaultAdvisorAutoProxyCreator();
        daap.setProxyTargetClass(true);
        return daap;
    }

    @Bean
    public AuthorizationAttributeSourceAdvisor authorizationAttributeSourceAdvisor(DefaultWebSecurityManager securityManager) {
        AuthorizationAttributeSourceAdvisor aasa = new AuthorizationAttributeSourceAdvisor();
        aasa.setSecurityManager(securityManager);
        return aasa;
    }
    @Bean
    public FilterRegistrationBean delegatingFilterProxy(){
        FilterRegistrationBean filterRegistrationBean = new FilterRegistrationBean();
        DelegatingFilterProxy proxy = new DelegatingFilterProxy();
        proxy.setTargetFilterLifecycle(true);
        proxy.setTargetBeanName("shiroFilter");
        filterRegistrationBean.setFilter(proxy);
        return filterRegistrationBean;
    }



}
