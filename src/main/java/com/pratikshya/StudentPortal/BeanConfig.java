package com.pratikshya.StudentPortal;

import com.pratikshya.StudentPortal.Repo.CourseRepo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;


    @Configuration
    public class BeanConfig {
        private static final Logger log = LoggerFactory.getLogger(BeanConfig.class);

        BeanConfig() {
        }
        @Bean
        public LocalValidatorFactoryBean getValidator () {
            LocalValidatorFactoryBean bean = new LocalValidatorFactoryBean();
            bean.setValidationMessageSource(this.messageSource());
            return bean;
        }

        @Bean
        public MessageSource messageSource () {
            ReloadableResourceBundleMessageSource messageSource = new ReloadableResourceBundleMessageSource();
            messageSource.setBasename("classpath:messages");
            messageSource.setDefaultEncoding("UTF-8");
            return messageSource;
        }

        @Bean
        public LocaleResolver localeResolver () {
            SessionLocaleResolver sessionLocaleResolver = new SessionLocaleResolver();
            sessionLocaleResolver.setDefaultLocale(Locale.UK);
            return sessionLocaleResolver;
        }
        @Bean
        public PasswordEncoder passwordEncoder() {
            return new BCryptPasswordEncoder();
        }

        //pass out the subject in courses. and commenting it out after save
        @Bean
        CommandLineRunner initDatabase(CourseRepo courseRepo) {
            return args -> {

//                courseRepo.save(new Course(1, "IT Technical Sales person", "15", (long)10000L));
//                courseRepo.save(new Course(2, "Digital Marketer", "18", (long) 12000L));
//                courseRepo.save(new Course(3, "IT solutions", "18", (long)12000L));
//                courseRepo.save(new Course(4, "Metal Fabricator", "42", (long)24000L));
//                courseRepo.save(new Course(5, "Beauty Theraphist ", "15", (long) 9500L));
//                courseRepo.save(new Course(6, "Animal Care and Welfare Assitant", "15", (long) 8000L));
//                courseRepo.save(new Course(7, "Auto Care Technician", "30", (long) 20500L));
//                courseRepo.save(new Course(8, "Adult Care Worker level 2", "15", (long) 10000L));
           };
         };
    }


