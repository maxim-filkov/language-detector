package org.lexikos;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.lexikos.constant.Language;
import org.lexikos.service.LocaleDetectorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.TestPropertySource;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = LanguageDetectorApplication.class)
@TestPropertySource(locations = "classpath:application-test.properties")
public class ShortTextLanguageDetectionTest {

   @Autowired
   LocaleDetectorService localeDetectorService;

   @Test
   public void testDetectionForShortCleanTextWorksProperlyForEnglish() throws IOException {
      List<Language> languages = Arrays.asList(Language.ENGLISH, Language.RUSSIAN);
      Language language = localeDetectorService.detectLocale("english", languages);
      Assert.assertEquals(Language.ENGLISH, language);
   }

   @Test
   public void testDetectionForShortCleanTextWorksProperlyForRussian() throws IOException {
      List<Language> languages = Arrays.asList(Language.ENGLISH, Language.RUSSIAN);
      Language language = localeDetectorService.detectLocale("русский", languages);
      Assert.assertEquals(Language.RUSSIAN, language);
   }

   @Test
   public void testDetectionForShortCleanTextWorksProperlyForMixedText() throws IOException {
      List<Language> languages = Arrays.asList(Language.ENGLISH, Language.RUSSIAN);
      Language language = localeDetectorService.detectLocale("русский english", languages);
      Assert.assertEquals(Language.RUSSIAN, language);
   }

   @Test
   public void testDetectionForShortCleanTextReturnsUnknownIfGivenPhraseIsEmpty() throws IOException {
      List<Language> languages = Arrays.asList(Language.ENGLISH, Language.RUSSIAN);
      Language language = localeDetectorService.detectLocale("", languages);
      Assert.assertEquals(Language.UNKNOWN, language);
   }

   @Test
   public void testDetectionForShortCleanTextReturnsUnknownIfGivenHintLanguagesAreEmpty() throws IOException {
      List<Language> languages = Collections.emptyList();
      Language language = localeDetectorService.detectLocale("english", languages);
      Assert.assertEquals(Language.UNKNOWN, language);
   }

   @Test
   public void testDetectionForShortCleanTextReturnsUnknownIfGivenHintLanguagesDoNotMatch() throws IOException {
      List<Language> languages = Arrays.asList(Language.FRENCH, Language.GERMAN);
      Language language = localeDetectorService.detectLocale("русский", languages);
      Assert.assertEquals(Language.UNKNOWN, language);
   }

   @Test
   public void testDetectionForShortCleanTextWorksWhenSpecialCharacters() throws IOException {
      List<Language> languages = Arrays.asList(Language.ENGLISH, Language.RUSSIAN);
      Language language = localeDetectorService.detectLocale("english !!@#$%^&*()_+", languages);
      Assert.assertEquals(Language.ENGLISH, language);
   }

}
