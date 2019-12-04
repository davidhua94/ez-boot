# Service Module
    此模块以jar的形式被Api、Admin-API依赖, 所有的数据库操作以及业务逻辑都在这里面。

目录结构
    --core 核心类, 与业务逻辑无关
    --System 模块
        -- 系统操作日志
        -- 用户、权限
        -- 数据字典功能
        。。。
    --基于Quartz实现可配置的定时任务 
        
    
    例   如：
        可以参考以下结构, 按需添加
        
    system
        --timetask
            --constant      存放该模块相关常量
            --entity        对于数据库实体
            --enums         所需要的枚举
            --exception     该模块异常
            --form          新增/更新表单封装、列表查询条件封装
            --repository    数据访问
            --service       业务逻辑
            --task          定时任务
            --thread        所需要的线程类
            --vo            列表显示对象封装
            --dto           数据传输对象,严格意义上vo和form都属于dto
        --admin
        --role 
        --log
        --dictionary       