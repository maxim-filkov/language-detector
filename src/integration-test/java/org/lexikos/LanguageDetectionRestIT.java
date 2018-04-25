package org.lexikos;

import org.json.JSONException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.skyscreamer.jsonassert.JSONAssert;
import org.skyscreamer.jsonassert.JSONCompareMode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = LanguageDetectorApplication.class, webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LanguageDetectionRestIT {

   @Autowired
   private TestRestTemplate restTemplate;

   @LocalServerPort
   private int port;

   @Test
   public void testUnknownLanguageIsRetrievedWhenPhraseIsEmpty() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
         + "/v0/language/?phrase=&hint=ru&hint=en", HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals(" {\"language\":\"unknown\"}", response.getBody(), false);
   }

   @Test
   public void testStatusIsBadRequestWhenPhraseIsNotPresent() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
         + "/v0/language/?hint=ru&hint=en", HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals("{\"status\":400,\"error\":\"Bad Request\",\"message\":\"Required String parameter 'phrase' is not present\"}",
         response.getBody(), JSONCompareMode.LENIENT);
   }

   @Test
   public void testEnglishLanguageIsRetrievedWhenPhraseIsInEnglishAndLanguageIsUpperCaseWholeWord() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
            + "/v0/language/?phrase=english&hint=RUSSIAN&hint=ENGLISH",
         HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals(" {\"language\":\"en\"}", response.getBody(), false);
   }

   @Test
   public void testEnglishLanguageIsRetrievedWhenPhraseIsInEnglishAndLanguageIsUpperCaseAbbreviated() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
            + "/v0/language/?phrase=english&hint=RU&hint=EN",
         HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals(" {\"language\":\"en\"}", response.getBody(), false);
   }

   @Test
   public void testEnglishLanguageIsRetrievedWhenPhraseIsInEnglishAndLanguageIsLowerCaseWholeWord() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
            + "/v0/language/?phrase=english&hint=russian&hint=english",
         HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals(" {\"language\":\"en\"}", response.getBody(), false);
   }

   @Test
   public void testEnglishLanguageIsRetrievedWhenPhraseIsInEnglishAndLanguageIsLowerCaseAbbreviated() throws JSONException {
      HttpEntity<String> entity = new HttpEntity<>(null, null);

      ResponseEntity<String> response = restTemplate.exchange("http://localhost:" + port
            + "/v0/language/?phrase=english&hint=ru&hint=en",
         HttpMethod.GET, entity, String.class);

      JSONAssert.assertEquals(" {\"language\":\"en\"}", response.getBody(), false);
   }

}