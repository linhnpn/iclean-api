package com.iclean.icleanapi.constant;

import java.util.HashMap;
import java.util.Map;

public enum StatusOrder {
    IN_PROCESS(0),
    UNDONE(1),
    DONE(2),
    UPCOMING(3),
    CANCEL(4)
    ;

    private int value;
    private static Map map = new HashMap();

    private StatusOrder(int value) {
        this.value = value;
    }

    static {
        for (StatusOrder pageType : StatusOrder.values()) {
            map.put(pageType.value, pageType);
        }
    }

    public static StatusOrder valueOf(int pageType) {
        return (StatusOrder) map.get(pageType);
    }

    public int getValue() {
        return value;
    }
}
