<?xml version="1.0" encoding="UTF-8"?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">
<mapper namespace="com.hubin.factor.finance.AuthItemInfoFactor">
    <resultMap id="BaseResultMap" type="com.hubin.domain.finance.AuthItemInfo">
        <id column="id" jdbcType="BIGINT" property="id" />
        <result column="item_name" jdbcType="VARCHAR" property="itemName" />
        <result column="company_name" jdbcType="TINYINT" property="companyName" />
        <result column="earning" jdbcType="DECIMAL" property="earning" />
        <result column="expense" jdbcType="DECIMAL" property="expense" />
        <result column="create_time" jdbcType="TIMESTAMP" property="createTime" />
        <result column="status" jdbcType="TINYINT" property="status" />
    </resultMap>
    <sql id="Base_Column_List">
       id,item_name,company_name,earning,expense,status,create_time
    </sql>

</mapper>