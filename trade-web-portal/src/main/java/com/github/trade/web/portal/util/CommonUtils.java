package com.github.trade.web.portal.util;

import java.math.BigDecimal;

public class CommonUtils {

    /**
     * cent to cny
     */
    public static String changeCentToCNY(int price){
        return BigDecimal.valueOf(Long.valueOf(price)).divide(new BigDecimal(100)).toString();
    }
}
