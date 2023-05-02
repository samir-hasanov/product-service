package com.alfa.net.productservice.exception.enums;

import com.alfa.net.productservice.exception.exceptions.ProductNotFoundException;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {
    OK(1000),
    ERROR(1001),
    SUCCESS(1002),
    PRODUCT_NOT_CREATED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULLY_CREATED(1501),
    PRODUCT_NOT_FOUND_EXCEPTION(1502);
    private final int value;
    FriendlyMessageCodes(int value) {
        this.value=value;
    }

    @Override
    public int getFriendlyMessageCodes() {
        return value;
    }
}
