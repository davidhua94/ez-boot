package ${basePackage}.entity;

import com.david.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;

/**
* generate by code generator
*/
@Data
@Entity(name = "${tableName}")
@EqualsAndHashCode(callSuper = false)
public class ${entityName} extends UpdatedEntity {

    <#list field as entityFieldList>
        @Column(name = "${field.columnName}")
        private ${field.fieldType} ${field.fieldName};
    </#list>

}
