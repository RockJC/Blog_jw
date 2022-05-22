package com.awei.mapper;

import com.awei.entity.Message;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
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
public interface MessageMapper extends BaseMapper<Message> {

}
