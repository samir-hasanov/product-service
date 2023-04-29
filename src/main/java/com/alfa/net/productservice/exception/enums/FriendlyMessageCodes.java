package com.alfa.net.productservice.exception.enums;

public enum FriendlyMessageCodes implements IFriendlyMessageCode {
    ok(1000);
   private final int value;
    FriendlyMessageCodes(int value) {
        this.value=value;
    }

    @Override
    public int getFriendlyMessageCodes() {
        return value;
    }
}
