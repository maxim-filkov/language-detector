package org.lexikos.config;

import org.lexikos.constant.Language;
import org.lexikos.converter.LanguageEditor;
import org.springframework.beans.factory.config.CustomEditorConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Profile;

import java.beans.PropertyEditor;
import java.util.HashMap;
import java.util.Map;

@ComponentScan
@Configuration
@Profile({ "prod" })
public class ServiceConfig {

   @Bean
   public CustomEditorConfigurer customEditorConfigurer() {
      CustomEditorConfigurer customEditorConfigurer = new CustomEditorConfigurer();
      Map<Class<?>, Class<? extends PropertyEditor>> editors = new HashMap();
      editors.put(Language.class, LanguageEditor.class);
      customEditorConfigurer.setCustomEditors(editors);
      return customEditorConfigurer;
   }

}