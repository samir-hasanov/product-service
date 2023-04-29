package com.alfa.net.productservice.exception.utils;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.IFriendlyMessageCode;
import lombok.experimental.UtilityClass;
import lombok.extern.slf4j.Slf4j;

import java.util.Locale;
import java.util.MissingResourceException;
import java.util.ResourceBundle;

@Slf4j
@UtilityClass
public class FriendlyMessageUtils {

    private static final String RESOURCE_BUNDLE_NAME="FriendlyMessage";
    private static final String SPECIAL_CHARACTERS="__";
    public static String getFriendlyMessage(Language language, IFriendlyMessageCode messageCode) {
        String messageKey = null;
        try {
            Locale locale = new Locale(language.name());
            ResourceBundle resourceBundle = ResourceBundle.getBundle(RESOURCE_BUNDLE_NAME, locale);
            messageKey = messageCode.getClass().getSimpleName() + SPECIAL_CHARACTERS + messageCode;
            return resourceBundle.getString(messageKey);

        } catch (MissingResourceException missingResourceException) {
            log.error("Friendly message not found for key {}", messageCode);
            return null;
        }


    }



}
