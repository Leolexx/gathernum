package com.diff.check.gathernum;

import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

/**
 * Конфигуратор MVC контроллера
 *
 * @author Lev
 * @version 1.0
 */
@Configuration
public class MvcConfig implements WebMvcConfigurer {

    /**
     * Добавление контроллеров обработки статичных страниц
     */
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/home").setViewName("home");
        registry.addViewController("/").setViewName("home");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/**").setViewName("home");
    }

}