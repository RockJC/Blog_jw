package com.awei.mapper;

import com.awei.entity.Type;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Mapper
public interface TypeMapper extends BaseMapper<Type> {
    Page<Type> TypeListIndex(@Param("page") Page<Type> page);
}
