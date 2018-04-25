package org.lexikos.constant;

import static java.util.Arrays.stream;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@JsonFormat(shape = JsonFormat.Shape.OBJECT)
public enum Language {

   AFRIKAANS("af"),
   ARAGONESE("an"),
   ARABIC("ar"),
   ASTURIAN("ast"),
   BELARUSIAN("be"),
   BRETON("br"),
   CATALAN("ca"),
   BULGARIAN("bg"),
   BENGALI("bn"),
   CZECH("cs"),
   WELSH("cy"),
   DANISH("da"),
   GERMAN("de"),
   GREEK("el"),
   ENGLISH("en"),
   SPANISH("es"),
   ESTONIAN("et"),
   BASQUE("eu"),
   PERSIAN("fa"),
   FINNISH("fi"),
   FRENCH("fr"),
   IRISH("ga"),
   GALICIAN("gl"),
   GUJARATI("gu"),
   HEBREW("he"),
   HINDI("hi"),
   CROATIAN("hr"),
   HAITIAN("ht"),
   HUNGARIAN("hu"),
   INDONESIAN("id"),
   ICELANDIC("is"),
   ITALIAN("it"),
   JAPANESE("ja"),
   KHMER("km"),
   KANNADA("kn"),
   KOREAN("ko"),
   LITHUANIAN("lt"),
   LATVIAN("lv"),
   MACEDONIAN("mk"),
   MALAYALAM("ml"),
   MARATHI("mr"),
   MALAY("ms"),
   MALTESE("mt"),
   NEPALI("ne"),
   DUTCH("nl"),
   NORWEGIAN("no"),
   OCCITAN("oc"),
   PUNJABI("pa"),
   POLISH("pl"),
   PORTUGUESE("pt"),
   ROMANIAN("ro"),
   RUSSIAN("ru"),
   SLOVAK("sk"),
   SLOVENE("sl"),
   SOMALI("so"),
   ALBANIAN("sq"),
   SERBIAN("sr"),
   SWEDISH("sv"),
   SWAHILI("sw"),
   TAMIL("ta"),
   TELUGU("te"),
   THAI("th"),
   TAGALOG("tl"),
   TURKISH("tr"),
   UKRAINIAN("uk"),
   URDU("ur"),
   VIETNAMESE("vi"),
   WALLOON("wa"),
   YIDDISH("yi"),
   CHINESE_SIMPLIFIED("zh-cn"),
   CHINESE_TRADITIONAL("zh-tw"),
   UNKNOWN("unknown");

   @JsonProperty("language")
   public final String value;

   public static Language fromString(String value) {
      return stream(Language.values()).filter(lang -> lang.value.equals(value)).findFirst().orElse(UNKNOWN);
   }

   @Override
   public String toString() {
      return this.value;
   }

}
