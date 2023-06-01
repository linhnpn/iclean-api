package com.iclean.icleanapi.constant;

import java.util.HashMap;
import java.util.Map;

public enum DefaultOrNot {
    DEFAULT(1),
    NOT_DEFAULT(0)
    ;
    private int value;
    private static Map map = new HashMap();

    private DefaultOrNot(int value) {
        this.value = value;
    }

    static {
        for (DefaultOrNot pageType : DefaultOrNot.values()) {
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
