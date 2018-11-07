package com.jeecms.config;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;
import org.springframework.scheduling.quartz.CronTriggerFactoryBean;
import org.springframework.scheduling.quartz.MethodInvokingJobDetailFactoryBean;
import org.springframework.scheduling.quartz.SchedulerFactoryBean;

import com.jeecms.bbs.action.ConfigJobAct;
import com.jeecms.bbs.schedule.BbsJob;
import com.jeecms.core.bbcode.BbcodeHandler;

@Configuration
public class SchedulConfig {

	@Bean
	public SchedulerFactoryBean schedulerFactoryBean(@Qualifier("configTrigger") CronTriggerFactoryBean configTrigger) {
		SchedulerFactoryBean bean=new SchedulerFactoryBean();
		bean.setTriggers(configTrigger.getObject());
		return bean;
	}
	
	@Bean("configTrigger")
	public CronTriggerFactoryBean configTrigger(@Qualifier("configJob") MethodInvokingJobDetailFactoryBean configJob) {
		CronTriggerFactoryBean factory=new CronTriggerFactoryBean();
		factory.setCronExpression("0 0 0 * * ?");
		factory.setJobDetail(configJob.getObject());
		return factory;
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean configJob(ConfigJobAct configJobAct) {
		MethodInvokingJobDetailFactoryBean factory=new MethodInvokingJobDetailFactoryBean();
		factory.setTargetObject(configJobAct);
		factory.setTargetMethod("dayClear");
		return factory;
	}
	
	@Bean
	public BbcodeHandler bbcodeHandler() {
		BbcodeHandler handler=new BbcodeHandler();
		handler.setConfigLocation(new ClassPathResource("bb_config.xml"));
		return handler;
	}
	
	@Bean
	public ThreadPoolTaskExecutor executor() {
		ThreadPoolTaskExecutor executor=new ThreadPoolTaskExecutor();
		executor.setCorePoolSize(10);
		executor.setMaxPoolSize(100);
		executor.setQueueCapacity(500);
		return executor;
	}
	
	@Bean
	public MethodInvokingJobDetailFactoryBean bbsJobDetail(BbsJob bbsJob) {
		MethodInvokingJobDetailFactoryBean factory=new MethodInvokingJobDetailFactoryBean();
		factory.setTargetMethod("execute");
		factory.setTargetObject(bbsJob);
		return factory;
	}
	
	
	@Bean("bbsTrigger")
	public CronTriggerFactoryBean bbsTrigger(@Qualifier("bbsJobDetail")MethodInvokingJobDetailFactoryBean bbsJobDetail) {
		CronTriggerFactoryBean factory=new CronTriggerFactoryBean();
		factory.setCronExpression("0 0 0 * * ?");
		factory.setJobDetail(bbsJobDetail.getObject());
		return factory;
	}
	
	@Bean("taskTrigger")
	public CronTriggerFactoryBean taskTrigger(@Qualifier("bbsJobDetail") MethodInvokingJobDetailFactoryBean bbsJobDetail) {
		CronTriggerFactoryBean factory=new CronTriggerFactoryBean();
		factory.setStartDelay(10000);
		factory.setCronExpression("0 0/1 0 * * ?");
		factory.setJobDetail(bbsJobDetail.getObject());
		return factory;
	}
	
	@Bean
	public SchedulerFactoryBean bbsSchedulerFactoryBean(@Qualifier("bbsTrigger") CronTriggerFactoryBean bbsTrigger,ThreadPoolTaskExecutor executor) {
		SchedulerFactoryBean factory=new SchedulerFactoryBean();
		factory.setTriggers(bbsTrigger.getObject());
		factory.setTaskExecutor(executor);
		return factory;
	}
}
