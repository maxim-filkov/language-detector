package org.lexikos.converter;

import org.lexikos.constant.Language;

import java.beans.PropertyEditorSupport;

public class LanguageEditor extends PropertyEditorSupport {

   @Override
   public void setAsText(String text) throws IllegalArgumentException {
      if (Language.fromString(text.toLowerCase()) != Language.UNKNOWN) {
         setValue(Language.fromString(text.toLowerCase()));
      } else {
         setValue(Language.valueOf(text.toUpperCase()));
      }
   }
}