package com.awei.mapper;

import com.awei.entity.User;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.apache.ibatis.annotations.Mapper;

/**
 * <p>
 *  Mapper 接口
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Mapper
public interface UserMapper extends BaseMapper<User> {


}
