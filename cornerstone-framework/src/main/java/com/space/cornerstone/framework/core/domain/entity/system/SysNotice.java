package com.space.cornerstone.framework.core.domain.entity.system;

import java.util.Date;

import com.space.cornerstone.framework.core.domain.entity.ActiveEntity;
import lombok.Data;

@Data
public class SysNotice extends ActiveEntity {
    private static final long serialVersionUID = -5130631718401778598L;
    /**
     * 公告ID
     */
    private Long id;

    /**
     * 公告标题
     */
    private String title;

    /**
     * 公告类型（1通知 2公告）
     */
    private Integer type;

    /**
     * 公告内容
     */
    private String content;

    /**
     * 备注
     */
    private String remark;
}
