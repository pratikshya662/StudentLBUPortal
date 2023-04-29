package com.pratikshya.StudentPortal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.MessageSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.support.ReloadableResourceBundleMessageSource;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;
import org.springframework.web.servlet.LocaleResolver;
import org.springframework.web.servlet.i18n.SessionLocaleResolver;
import java.util.Locale;

    @Configuration
    class assorted {
        private static final Logger log = LoggerFactory.getLogger(assorted.class);

        assorted() {
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
    }



