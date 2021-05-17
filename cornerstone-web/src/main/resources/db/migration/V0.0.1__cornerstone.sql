CREATE TABLE IF NOT EXISTS SYS_CONFIG
(
    ID          BIGINT AUTO_INCREMENT COMMENT '参数主键'
        PRIMARY KEY,
    NAME        VARCHAR(100) DEFAULT '' NULL COMMENT '参数名称',
    CODE        VARCHAR(100) DEFAULT '' NULL COMMENT '参数键名',
    VALUE       VARCHAR(500) DEFAULT '' NULL COMMENT '参数键值',
    INTERNAL    TINYINT(1)   DEFAULT 0  NULL COMMENT '系统内置（1是 0否）',
    CREATE_BY   VARCHAR(64)  DEFAULT '' NULL COMMENT '创建者',
    CREATE_TIME DATETIME                NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64)  DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME                NULL COMMENT '更新时间',
    REMARK      VARCHAR(500)            NULL COMMENT '备注'
)
    COMMENT '参数配置表';

CREATE TABLE IF NOT EXISTS SYS_DEPT
(
    ID          BIGINT AUTO_INCREMENT COMMENT '部门id'
        PRIMARY KEY,
    PARENT_ID   BIGINT      DEFAULT 0  NULL COMMENT '父部门id',
    DEPT_NAME   VARCHAR(30) DEFAULT '' NULL COMMENT '部门名称',
    SORT        INT(4)      DEFAULT 0  NULL COMMENT '显示顺序',
    LEADER      VARCHAR(20)            NULL COMMENT '负责人',
    ACTIVE      TINYINT(1)  DEFAULT 1  NULL COMMENT '状态（1正常 0停用）',
    DELETE_FLAG TINYINT(1)  DEFAULT 0  NULL COMMENT '删除标志（0代表存在 1代表删除）',
    CREATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    CREATE_TIME DATETIME               NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME               NULL COMMENT '更新时间'
)
    COMMENT '部门表';

CREATE TABLE IF NOT EXISTS SYS_DICT
(
    CODE        VARCHAR(100)            NOT NULL COMMENT '字典编码',
    SORT        INT(4)       DEFAULT 0  NULL COMMENT '字典排序',
    NAME        VARCHAR(100) DEFAULT '' NULL COMMENT '字典名称',
    VALUE       VARCHAR(100) DEFAULT '' NOT NULL COMMENT '字典键值',
    CSS_CLASS   VARCHAR(100)            NULL COMMENT '样式属性（其他样式扩展）',
    LIST_CLASS  VARCHAR(100)            NULL COMMENT '表格回显样式',
    IS_DEFAULT  TINYINT(1)   DEFAULT 0  NULL COMMENT '是否默认（1是 0否）',
    ACTIVE      TINYINT(1)   DEFAULT 1  NULL COMMENT '状态（1正常 0停用）',
    CREATE_BY   VARCHAR(64)  DEFAULT '' NULL COMMENT '创建者',
    CREATE_TIME DATETIME                NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64)  DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME                NULL COMMENT '更新时间',
    REMARK      VARCHAR(500)            NULL COMMENT '备注',
    PRIMARY KEY (CODE, VALUE)
)
    COMMENT '字典数据表';

CREATE TABLE IF NOT EXISTS SYS_LOGIN_LOG
(
    ID                BIGINT(18) AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    REQUEST_ID        VARCHAR(32)                          NULL COMMENT '请求ID',
    USERNAME          VARCHAR(32)                          NULL COMMENT '用户名称',
    IP                VARCHAR(15)                          NULL COMMENT 'IP',
    AREA              VARCHAR(45)                          NULL COMMENT '区域',
    OPERATOR          VARCHAR(6)                           NULL COMMENT '运营商',
    TOKEN             VARCHAR(32)                          NULL COMMENT 'tokenMd5值',
    TYPE              INT                                  NULL COMMENT '1:登录，2：登出',
    SUCCESS           TINYINT(1) DEFAULT 0                 NOT NULL COMMENT '是否成功 true:成功/false:失败',
    CODE              INT                                  NULL COMMENT '响应码',
    EXCEPTION_MESSAGE VARCHAR(300)                         NULL COMMENT '失败消息记录',
    USER_AGENT        VARCHAR(300)                         NULL COMMENT '浏览器名称',
    BROWSER_NAME      VARCHAR(100)                         NULL COMMENT '浏览器名称',
    BROWSER_VERSION   VARCHAR(100)                         NULL COMMENT '浏览器版本',
    ENGINE_NAME       VARCHAR(100)                         NULL COMMENT '浏览器引擎名称',
    ENGINE_VERSION    VARCHAR(100)                         NULL COMMENT '浏览器引擎版本',
    OS_NAME           VARCHAR(100)                         NULL COMMENT '系统名称',
    PLATFORM_NAME     VARCHAR(100)                         NULL COMMENT '平台名称',
    MOBILE            TINYINT(1)                           NULL COMMENT '是否是手机,0:否,1:是',
    DEVICE_NAME       VARCHAR(100)                         NULL COMMENT '移动端设备名称',
    DEVICE_MODEL      VARCHAR(100)                         NULL COMMENT '移动端设备型号',
    REMARK            VARCHAR(200)                         NULL COMMENT '备注',
    CREATE_TIME       DATETIME   DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATE_TIME       DATETIME                             NULL COMMENT '修改时间'
)
    COMMENT '系统登录日志';

CREATE TABLE IF NOT EXISTS SYS_MENU
(
    ID          BIGINT AUTO_INCREMENT COMMENT '菜单ID'
        PRIMARY KEY,
    NAME        VARCHAR(50)              NOT NULL COMMENT '菜单名称',
    PARENT_ID   BIGINT       DEFAULT 0   NULL COMMENT '父菜单ID',
    CODE        VARCHAR(100)             NULL COMMENT '权限标识',
    SORT        INT(4)       DEFAULT 0   NULL COMMENT '显示顺序',
    URL         VARCHAR(200) DEFAULT ''  NULL COMMENT '路由地址',
    COMPONENT   VARCHAR(255)             NULL COMMENT '组件路径',
    LEVEL       INT          DEFAULT 0   NULL COMMENT '层级，1：第一级，2：第二级，N：第N级',
    MENU_TYPE   CHAR         DEFAULT ''  NULL COMMENT '菜单类型（M目录 C菜单 F按钮）',
    ACTIVE      TINYINT(1)   DEFAULT 1   NULL COMMENT '菜单状态（1正常 0停用）',
    ICON        VARCHAR(100) DEFAULT '#' NULL COMMENT '菜单图标',
    CREATE_BY   VARCHAR(64)  DEFAULT ''  NULL COMMENT '创建者',
    CREATE_TIME DATETIME                 NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64)  DEFAULT ''  NULL COMMENT '更新者',
    UPDATE_TIME DATETIME                 NULL COMMENT '更新时间',
    REMARK      VARCHAR(500) DEFAULT ''  NULL COMMENT '备注'
)
    COMMENT '菜单权限表';

CREATE TABLE IF NOT EXISTS SYS_NOTICE
(
    ID          BIGINT AUTO_INCREMENT COMMENT '公告ID'
        PRIMARY KEY,
    TITLE       VARCHAR(50)            NOT NULL COMMENT '公告标题',
    TYPE        INT(4)                 NOT NULL COMMENT '公告类型（1通知 2公告）',
    CONTENT     LONGBLOB               NULL COMMENT '公告内容',
    ACTIVE      TINYINT(1)  DEFAULT 1  NULL COMMENT '公告状态（1正常 0关闭）',
    CREATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    CREATE_TIME DATETIME               NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME               NULL COMMENT '更新时间',
    REMARK      VARCHAR(255)           NULL COMMENT '备注'
)
    COMMENT '通知公告表';

CREATE TABLE IF NOT EXISTS SYS_OPERATION_LOG
(
    ID                BIGINT AUTO_INCREMENT COMMENT '主键'
        PRIMARY KEY,
    REQUEST_ID        VARCHAR(32)                        NULL COMMENT '请求ID',
    USER_ID           BIGINT(18)                         NULL COMMENT '用户ID',
    USER_NAME         VARCHAR(32)                        NULL COMMENT '用户名称',
    NAME              VARCHAR(200)                       NULL COMMENT '日志名称',
    IP                VARCHAR(15)                        NULL COMMENT 'IP',
    AREA              VARCHAR(45)                        NULL COMMENT '区域',
    OPERATOR          VARCHAR(6)                         NULL COMMENT '运营商',
    PATH              VARCHAR(500)                       NULL COMMENT '全路径',
    MODULE            VARCHAR(100)                       NULL COMMENT '模块名称',
    CLASS_NAME        VARCHAR(100)                       NULL COMMENT '类名',
    METHOD_NAME       VARCHAR(100)                       NULL COMMENT '方法名称',
    REQUEST_METHOD    VARCHAR(10)                        NULL COMMENT '请求方式，GET/POST',
    CONTENT_TYPE      VARCHAR(100)                       NULL COMMENT '内容类型',
    REQUEST_BODY      TINYINT(1)                         NULL COMMENT '是否是JSON请求映射参数',
    PARAM             TEXT                               NULL COMMENT '请求参数',
    TOKEN             VARCHAR(32)                        NULL COMMENT 'tokenMd5值',
    TYPE              INT                                NULL COMMENT '0:其它,1:新增,2:修改,3:删除,4:详情查询,5:所有列表,6:分页列表,7:其它查询,8:上传文件',
    SUCCESS           TINYINT(1)                         NULL COMMENT '0:失败,1:成功',
    CODE              INT                                NULL COMMENT '响应结果状态码',
    MESSAGE           VARCHAR(100)                       NULL COMMENT '响应结果消息',
    EXCEPTION_NAME    VARCHAR(200)                       NULL COMMENT '异常类名称',
    EXCEPTION_MESSAGE VARCHAR(300)                       NULL COMMENT '异常信息',
    BROWSER_NAME      VARCHAR(100)                       NULL COMMENT '浏览器名称',
    BROWSER_VERSION   VARCHAR(100)                       NULL COMMENT '浏览器版本',
    ENGINE_NAME       VARCHAR(100)                       NULL COMMENT '浏览器引擎名称',
    ENGINE_VERSION    VARCHAR(100)                       NULL COMMENT '浏览器引擎版本',
    OS_NAME           VARCHAR(100)                       NULL COMMENT '系统名称',
    PLATFORM_NAME     VARCHAR(100)                       NULL COMMENT '平台名称',
    MOBILE            TINYINT(1)                         NULL COMMENT '是否是手机,0:否,1:是',
    DEVICE_NAME       VARCHAR(100)                       NULL COMMENT '移动端设备名称',
    DEVICE_MODEL      VARCHAR(100)                       NULL COMMENT '移动端设备型号',
    REMARK            VARCHAR(200)                       NULL COMMENT '备注',
    CREATE_TIME       DATETIME DEFAULT CURRENT_TIMESTAMP NULL COMMENT '创建时间',
    UPDATE_TIME       DATETIME                           NULL COMMENT '修改时间'
)
    COMMENT '系统操作日志';

CREATE TABLE IF NOT EXISTS SYS_ROLE
(
    ID          BIGINT AUTO_INCREMENT COMMENT '角色ID'
        PRIMARY KEY,
    NAME        VARCHAR(30)            NOT NULL COMMENT '角色名称',
    CODE        VARCHAR(100)           NOT NULL COMMENT '角色权限字符串',
    SORT        INT(4)                 NOT NULL COMMENT '显示顺序',
    ACTIVE      TINYINT(1)  DEFAULT 1  NOT NULL COMMENT '角色状态（1正常 0停用）',
    DELETE_FLAG TINYINT(1)  DEFAULT 0  NULL COMMENT '删除标志（0代表存在 1代表删除）',
    CREATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '创建者',
    CREATE_TIME DATETIME               NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64) DEFAULT '' NULL COMMENT '更新者',
    UPDATE_TIME DATETIME               NULL COMMENT '更新时间',
    REMARK      VARCHAR(500)           NULL COMMENT '备注'
)
    COMMENT '角色信息表';

CREATE TABLE IF NOT EXISTS SYS_ROLE_DEPT
(
    ROLE_ID BIGINT NOT NULL COMMENT '角色ID',
    DEPT_ID BIGINT NOT NULL COMMENT '部门ID',
    PRIMARY KEY (ROLE_ID, DEPT_ID)
)
    COMMENT '角色和部门关联表';

CREATE TABLE IF NOT EXISTS SYS_ROLE_MENU
(
    ROLE_ID BIGINT NOT NULL COMMENT '角色ID',
    MENU_ID BIGINT NOT NULL COMMENT '菜单ID',
    PRIMARY KEY (ROLE_ID, MENU_ID)
)
    COMMENT '角色和菜单关联表';

CREATE TABLE IF NOT EXISTS SYS_USER
(
    ID          BIGINT AUTO_INCREMENT COMMENT '用户ID'
        PRIMARY KEY,
    DEPT_ID     BIGINT                       NULL COMMENT '部门ID',
    USER_NAME   VARCHAR(30)                  NOT NULL COMMENT '用户账号',
    NICK_NAME   VARCHAR(30)                  NOT NULL COMMENT '用户昵称',
    TYPE        VARCHAR(10)  DEFAULT 'ADMIN' NULL COMMENT '用户类型（ADMIN系统用户）',
    EMAIL       VARCHAR(50)  DEFAULT ''      NULL COMMENT '用户邮箱',
    PHONE       VARCHAR(11)  DEFAULT ''      NULL COMMENT '手机号码',
    SEX         INT          DEFAULT 0       NULL COMMENT '用户性别（0男 1女 2未知）',
    IMAGE       VARCHAR(100) DEFAULT ''      NULL COMMENT '头像地址',
    PASSWORD    VARCHAR(100) DEFAULT ''      NOT NULL COMMENT '密码',
    ACTIVE      TINYINT(1)   DEFAULT 1       NOT NULL COMMENT '帐号状态（1正常 0停用）',
    DELETE_FLAG TINYINT(1)   DEFAULT 0       NOT NULL COMMENT '删除标志（0代表存在 1代表删除）',
    LOGIN_IP    VARCHAR(128) DEFAULT ''      NULL COMMENT '最后登录IP',
    LOGIN_DATE  DATETIME                     NULL COMMENT '最后登录时间',
    VERSION     INT          DEFAULT 0       NOT NULL COMMENT '版本',
    CREATE_BY   VARCHAR(64)  DEFAULT ''      NULL COMMENT '创建者',
    CREATE_TIME DATETIME                     NULL COMMENT '创建时间',
    UPDATE_BY   VARCHAR(64)  DEFAULT ''      NULL COMMENT '更新者',
    UPDATE_TIME DATETIME                     NULL COMMENT '更新时间',
    REMARK      VARCHAR(500)                 NULL COMMENT '备注'
)
    COMMENT '用户信息表';

CREATE TABLE IF NOT EXISTS SYS_USER_ROLE
(
    USER_ID BIGINT NOT NULL COMMENT '用户ID',
    ROLE_ID BIGINT NOT NULL COMMENT '角色ID',
    PRIMARY KEY (USER_ID, ROLE_ID)
)
    COMMENT '用户和角色关联表';

