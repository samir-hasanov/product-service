package com.alfa.net.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {
    ok(1000),
    ERROR(1001),
    SUCCESS(1002),
    RRODUCT_NOTCREATED_EXCEPTION(1500),
    PRODUCT_SUCCESSFULLY_CREATED(1501);
   private final int value;
    FriendlyMessageCodes(int value) {
        this.value=value;
    }

    @Override
    public int getFriendlyMessageCodes() {
        return value;
    }
}
