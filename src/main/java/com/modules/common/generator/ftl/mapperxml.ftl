<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN"
        "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="${package_name}.dao.${table_name}Dao">
    <resultMap id="BaseResultMap" type="${package_name}.entity.${table_name}" >
        <#if model_columns?exists && model_columns?size gt 0>
            <#list model_columns as fieldItem>
                <result column="${fieldItem.columnName}" property="${fieldItem.changeColumnName}" jdbcType="${fieldItem.jdbcType}" />
            </#list>
        </#if>
    </resultMap>

    <sql id="Base_Column_List">
        <#if model_columns?exists && model_columns?size gt 0>
            <#list model_columns as fieldItem >
                ${fieldItem.columnName}<#if fieldItem_has_next>,</#if>
            </#list>
        </#if>
    </sql>
    

    <select id="next" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${table_name_small}
        WHERE is_deleted=0 and ${primary_key} &lt; ${a_tag_start}${primary_key},jdbcType=${primary_key_JDBCType}${a_tag_end}
        ORDER BY ID DESC
        limit 1
    </select>
    
    <select id="prev" resultMap="BaseResultMap">
        SELECT <include refid="Base_Column_List" />
        FROM ${table_name_small}
        WHERE is_deleted=0 and ${primary_key} &gt; ${a_tag_start}${primary_key},jdbcType=${primary_key_JDBCType}${a_tag_end}
        ORDER BY ID ASC
        limit 1
    </select>
</mapper>