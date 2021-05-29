package com.space.cornerstone.framework.core.domain.entity;

import com.baomidou.mybatisplus.annotation.TableLogic;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author chen qi
 * @version 1.0.0
 * @ClassName LogicDeleteEntity.java
 * @Description LogicDeleteEntity
 * @createTime 2021年05月21日 23:21:00
 */
@Data
@Accessors(chain = true)
public class LogicDeleteEntity extends ActiveEntity {
    private static final long serialVersionUID = -5760089580957524273L;

    /**
     * 逻辑删除字段 true-删除
     */
    @TableLogic
    private Boolean deleteFlag;
}
