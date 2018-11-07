package com.jeecms.config;

import org.springframework.cache.ehcache.EhCacheCacheManager;
import org.springframework.cache.ehcache.EhCacheFactoryBean;
import org.springframework.cache.ehcache.EhCacheManagerFactoryBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;

import net.sf.ehcache.CacheManager;

@Configuration
public class CacheConfig {

    @Bean
    public EhCacheManagerFactoryBean ehCacheManagerFactoryBean(){
        EhCacheManagerFactoryBean cacheManagerFactoryBean = new EhCacheManagerFactoryBean ();
        cacheManagerFactoryBean.setConfigLocation (new ClassPathResource("ehcache.xml"));
        cacheManagerFactoryBean.setShared (true);
        return cacheManagerFactoryBean;
    }
   
    @Bean(name = "cacheManager")
    public EhCacheCacheManager ehCacheCacheManager(EhCacheManagerFactoryBean bean){
        return new EhCacheCacheManager (bean.getObject ());
    }
	
    @Bean
	public EhCacheFactoryBean ehcache(CacheManager manager) {
		EhCacheFactoryBean bean=new EhCacheFactoryBean();
		bean.setCacheManager(manager);
		bean.setName("ehcache");
		return bean;
	}
    
    @Bean("bbsconfigCount")
	public EhCacheFactoryBean bbsconfigCache(CacheManager manager) {
		EhCacheFactoryBean bean=new EhCacheFactoryBean();
		bean.setCacheManager(manager);
		bean.setName("com.jeecms.cms.front.BbsConfigCount");
		return bean;
	}
    
    @Bean("session")
  	public EhCacheFactoryBean ehSessionCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.common.web.Session");
  		return bean;
  	}
    
    @Bean("bbsUserCache")
  	public EhCacheFactoryBean ehBbsUserCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.bbs.front.BbsUserCache");
  		return bean;
  	}
    
    @Bean("topicCount")
  	public EhCacheFactoryBean ehTopicCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.TopicCount");
  		return bean;
  	}
    
    @Bean("lastReply")
  	public EhCacheFactoryBean ehLastReplyCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.LastReply");
  		return bean;
  	}
    
    @Bean("topicDayCount")
  	public EhCacheFactoryBean ehTopicDayCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.TopicDayCount");
  		return bean;
  	}
    
    @Bean("topicWeekCount")
  	public EhCacheFactoryBean ehTopicWeekCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.TopicWeekCount");
  		return bean;
  	}
    
    @Bean("topicMonthCount")
  	public EhCacheFactoryBean ehTopicMonthCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.TopicMonthCount");
  		return bean;
  	}
    
    @Bean("OrderTemp")
  	public EhCacheFactoryBean ehOrderTempCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.cms.front.OrderTemp");
  		return bean;
  	}
    
    @Bean("forumTopicCount")
  	public EhCacheFactoryBean ehForumTopicCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.bbs.front.ForumTopicCount");
  		return bean;
  	}
    
    @Bean("forumPostCount")
  	public EhCacheFactoryBean ehForumPostCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.bbs.front.ForumPostCount");
  		return bean;
  	}
    
    @Bean("forumVisitCount")
  	public EhCacheFactoryBean ehForumVisitCountCache(CacheManager manager) {
  		EhCacheFactoryBean bean=new EhCacheFactoryBean();
  		bean.setCacheManager(manager);
  		bean.setName("com.jeecms.bbs.front.ForumVisitCount");
  		return bean;
  	}
}
