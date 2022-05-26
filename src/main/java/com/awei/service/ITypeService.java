package com.awei.service;

import com.awei.entity.Type;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
public interface ITypeService{
    Type saveType(Type type);

    Type getType(Long id);

    Type getTypeByName(String Name);

    Object listType(Integer num);

    Type updateType(Long id,Type type);

    int deleteType(Long id);

    List<Type> listAllType();

    Object listTypeTop(Integer num);

    Map<Integer,Type> getTypeSizeById(Long Typeid);
}
