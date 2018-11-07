package com.jeecms.config;

import java.awt.Color;
import java.awt.Font;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.octo.captcha.component.image.backgroundgenerator.UniColorBackgroundGenerator;
import com.octo.captcha.component.image.color.SingleColorGenerator;
import com.octo.captcha.component.image.fontgenerator.RandomFontGenerator;
import com.octo.captcha.component.image.textpaster.DecoratedRandomTextPaster;
import com.octo.captcha.component.image.textpaster.textdecorator.BaffleTextDecorator;
import com.octo.captcha.component.image.textpaster.textdecorator.TextDecorator;
import com.octo.captcha.component.image.wordtoimage.ComposedWordToImage;
import com.octo.captcha.component.word.wordgenerator.RandomWordGenerator;
import com.octo.captcha.engine.GenericCaptchaEngine;
import com.octo.captcha.image.gimpy.GimpyFactory;
import com.octo.captcha.service.multitype.GenericManageableCaptchaService;

@Configuration
public class CaptchaConfig {

	@Bean
	public GenericManageableCaptchaService captchaService(GenericCaptchaEngine captchaEngine) {
		GenericManageableCaptchaService service=new GenericManageableCaptchaService(captchaEngine, 180, 100000, 75000);
		return service;
	}
	
	@Bean
	public GenericCaptchaEngine imageEngine(GimpyFactory captchaFactory) {
		GimpyFactory factorys[]= {captchaFactory};
		GenericCaptchaEngine engine=new GenericCaptchaEngine(factorys);
		return engine;
	}
	
	@Bean
	public GimpyFactory captchaFactory(RandomWordGenerator wordgen,ComposedWordToImage wordtoimage) {
		GimpyFactory factory=new GimpyFactory(wordgen, wordtoimage);
		return factory;
	}
	
	@Bean
	public RandomWordGenerator wordgen() {
		RandomWordGenerator tor=new RandomWordGenerator("0123456789abcdefghijklmnopqrstuvwxyz");
		return tor;
	}
	
	@Bean
	public ComposedWordToImage wordtoimage(RandomFontGenerator fontGenRandom,UniColorBackgroundGenerator background,DecoratedRandomTextPaster textPaster) {
		ComposedWordToImage image=new ComposedWordToImage(fontGenRandom, background, textPaster);
		return image;
	}
	
	@Bean
	public DecoratedRandomTextPaster decoratedPaster(SingleColorGenerator colorGen,BaffleTextDecorator baffleDecorator) {
		TextDecorator tors[]= {baffleDecorator};
		DecoratedRandomTextPaster paster=new DecoratedRandomTextPaster(4, 4, colorGen, tors);
		return paster;
	}
	
	@Bean
	public RandomFontGenerator fontGenRandom() {
		Font fonts[]= {new Font("Arial",0,32)};
		RandomFontGenerator tor=new RandomFontGenerator(26,34,fonts);
		return tor;
	}
	
	@Bean
	public UniColorBackgroundGenerator backGenUni() {
		UniColorBackgroundGenerator tor=new UniColorBackgroundGenerator(150,60);
		return tor;
	}
	
	@Bean
	public SingleColorGenerator colorGen() {
		SingleColorGenerator tor=new SingleColorGenerator(new Color(50,50,50));
		return tor;
	}
	
	@Bean
	public BaffleTextDecorator baffleDecorator() {
		BaffleTextDecorator tor=new BaffleTextDecorator(1,new Color(255,255,255));
		return tor;
	}
	
}
