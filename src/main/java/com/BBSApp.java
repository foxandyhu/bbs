package com;


import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.ComponentScan.Filter;
import org.springframework.context.annotation.FilterType;
import org.springframework.stereotype.Controller;

@SpringBootApplication
@ServletComponentScan
@ComponentScan(basePackages="com.jeecms",excludeFilters=@Filter(type=FilterType.ANNOTATION,value=Controller.class))
public class BBSApp {

	public static void main(String[] args) {
		SpringApplication.run(BBSApp.class, args);
	}
}
