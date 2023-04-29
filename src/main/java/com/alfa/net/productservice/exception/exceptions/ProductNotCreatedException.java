package com.alfa.net.productservice.exception.exceptions;

import com.alfa.net.productservice.configuration.enums.Language;
import com.alfa.net.productservice.exception.enums.IFriendlyMessageCode;
import com.alfa.net.productservice.exception.utils.FriendlyMessageUtils;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Getter
public class ProductNotCreatedException extends RuntimeException{
    private final Language language;
    private final IFriendlyMessageCode friendlyMessageCode;

    public ProductNotCreatedException(Language language, IFriendlyMessageCode friendlyMessageCode,String message) {
       super(FriendlyMessageUtils.getFriendlyMessage(language,friendlyMessageCode));
        this.language = language;
        this.friendlyMessageCode = friendlyMessageCode;
    }
}
