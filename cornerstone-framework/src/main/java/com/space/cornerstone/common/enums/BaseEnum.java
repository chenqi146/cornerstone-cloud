package com.space.cornerstone.common.enums;

/**
 * @author chen qi
 * @version 1.0.0
 * @InterfaceName BaseEnum.java
 * @Description TODO
 * @createTime 2021年05月18日 08:38:00
 */
public interface BaseEnum {

    /**
     * @title getCode
     * @description
     * @author chen qi
     * @updateTime 2021-05-18 8:41
     * @return: java.lang.Integer
     * @throws
     */
    Integer getCode();

    /**
     * @title getMsg
     * @description  获取枚举 描述
     * @author chen qi
     * @updateTime 2021-05-18 8:42
     * @return: java.lang.String
     * @throws
     */
    String getMsg();


    /**
     * 通过枚举类型和code值获取对应的枚举类型
     * @param enumType
     * @param code
     * @param <T>
     * @return
     */
    static <T extends BaseEnum> T valueOf(Class<? extends BaseEnum> enumType, Integer code) {
        if (enumType == null || code == null) {
            return null;
        }
        T[] enumConstants = (T[]) enumType.getEnumConstants();
        if (enumConstants == null) {
            return null;
        }
        for (T enumConstant : enumConstants) {
            int enumCode = enumConstant.getCode();
            if (code.equals(enumCode)) {
                return enumConstant;
            }
        }
        return null;
    }
}
