package org.lexikos.service;

import static java.util.Collections.emptyList;
import static java.util.stream.Collectors.toList;

import com.optimaize.langdetect.LanguageDetector;
import com.optimaize.langdetect.LanguageDetectorBuilder;
import com.optimaize.langdetect.i18n.LdLocale;
import com.optimaize.langdetect.ngram.NgramExtractors;
import com.optimaize.langdetect.profiles.LanguageProfile;
import com.optimaize.langdetect.profiles.LanguageProfileReader;
import com.optimaize.langdetect.text.CommonTextObjectFactories;
import com.optimaize.langdetect.text.TextObject;
import com.optimaize.langdetect.text.TextObjectFactory;
import org.lexikos.constant.Language;
import org.springframework.stereotype.Service;

import java.io.IOException;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class LocaleDetectorService {

   public Language detectLocale(String phrase, List<Language> hint) throws IOException {
      if (phrase.isEmpty()) {
         return Language.UNKNOWN;
      }

      List<String> locales = Optional.ofNullable(hint).orElse(emptyList()).stream()
         .filter(Objects::nonNull).map(Language::toString).collect(toList());

      TextObjectFactory textObjectFactory = CommonTextObjectFactories.forDetectingShortCleanText();
      TextObject textObject = textObjectFactory.forText(phrase);

      List<LanguageProfile> languageProfiles = locales.isEmpty()
         ? new LanguageProfileReader().readAllBuiltIn()
         : new LanguageProfileReader().read(locales);
      LanguageDetector languageDetector = LanguageDetectorBuilder.create(NgramExtractors.standard())
         .withProfiles(languageProfiles).build();
      Optional<LdLocale> localeOptional = Optional.ofNullable(languageDetector.detect(textObject).orNull());
      return localeOptional.isPresent() ? Language.fromString(localeOptional.get().getLanguage()) : Language.UNKNOWN;
   }

}
