package com.awei.mapper;

import com.awei.entity.Blog;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
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
public interface BlogMapper extends BaseMapper<Blog> {
    Page<Blog> BlogListPage(@Param("page") Page<Blog> page, @Param("ew") QueryWrapper<Blog> queryWrapper);
    Blog BlogUpdateDetail(@Param("id") Long id);
    Page<Blog> BlogListIndex(@Param("page") Page<Blog> page,@Param("ew") QueryWrapper<Blog> queryWrapper);
    Page<Blog> BlogListIndexByTime(@Param("page") Page<Blog> page);
}
