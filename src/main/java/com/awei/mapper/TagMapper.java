package com.awei.mapper;

import com.awei.entity.Tag;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Mapper
public interface TagMapper extends BaseMapper<Tag> {
    Page<Tag> TagListIndex(@Param("page") Page<Tag> page);
}
