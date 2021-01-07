package ${basePackage}.service;

import com.david.core.base.PageResult;
import com.david.core.base.service.BaseService;
import ${basePackage}.dto.${entityName}DTO;
import ${basePackage}.dto.${entityName}ListQueryDTO;
import ${basePackage}.entity.${entityName};

/**
* generate by code generator
*/
public interface ${entityName}Service extends BaseService<${entityName}> {

    void save(${entityName}DTO role);

    void update(${entityName}DTO role);

    PageResult<${entityName}> pageList(${entityName}ListQueryDTO pageQuery);
}
