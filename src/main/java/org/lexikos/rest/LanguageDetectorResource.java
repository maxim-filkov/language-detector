package org.lexikos.rest;

import org.hibernate.validator.constraints.NotEmpty;
import org.lexikos.constant.Language;
import org.lexikos.converter.LanguageEditor;
import org.lexikos.service.LocaleDetectorService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.validation.constraints.Size;
import java.io.IOException;
import java.util.List;

@RestController
public class LanguageDetectorResource {

   @Resource
   private LocaleDetectorService localeDetectorService;

   @RequestMapping(path = "/v0/language/", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
   public Language detect(
      @RequestParam @NotEmpty @Size(max = 255) final String phrase,
      @RequestParam(required = false) final List<Language> hint) throws IOException {

      return localeDetectorService.detectLocale(phrase, hint);
   }

   @InitBinder
   public void initBinder(WebDataBinder binder) {
      binder.registerCustomEditor(Language.class, new LanguageEditor());
   }

}
