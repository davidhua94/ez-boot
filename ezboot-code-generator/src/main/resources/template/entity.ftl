package ${basePackage}.${modulePackage}.entity;

import com.david.core.base.entity.UpdatedEntity;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * generate by code generator
 */
@Data
@Entity
@Table(name = "${tableName}")
@EqualsAndHashCode(callSuper = false)
public class ${entityName} extends ${parentEntityClass} {

    <#list entityFieldList as field>
        @Column(name = "${field.columnName}")
        private ${field.fieldType} ${field.fieldName};

    </#list>

}
