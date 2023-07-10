package com.iclean.icleanapi.constant;

import java.util.HashMap;
import java.util.Map;

public enum StatusProductOrder {

        CART(0),
        WAITING_TO_SHIP(1),
        WAITING_TO_SEND(2),
        SHIPPED(3),
        CANCEL(4),
        REFUND(5);
        ;

        private int value;
        private static Map map = new HashMap();

        private StatusProductOrder(int value) {
            this.value = value;
        }

        static {
            for (StatusProductOrder pageType : StatusProductOrder.values()) {
                map.put(pageType.value, pageType);
            }
        }

        public static com.iclean.icleanapi.constant.StatusOrder valueOf(int pageType) {
            return (com.iclean.icleanapi.constant.StatusOrder) map.get(pageType);
        }

        public int getValue() {
            return value;
        }

}
