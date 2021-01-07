package com.ezboot.admin.common.config;

import com.ezboot.admin.common.AdminContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.AuditorAware;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;

import java.util.Optional;

/**
 * @author David hua
 * @date 2019-11-17 16:51:48
 * JPA Audit 在保存和更新数据的时候
 * 为createName/createTime/updateName/updateTime 赋值
 */
@EnableJpaAuditing
@Configuration
public class JPAAuditorConfig implements AuditorAware<String> {
    @Override
    public Optional<String> getCurrentAuditor() {
        return Optional.of(AdminContext.getCurrentAdminUsername());
    }
}
