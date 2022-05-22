package com.awei.service.impl;

import com.awei.NotFoundException;
import com.awei.entity.Blog;
import com.awei.entity.Type;
import com.awei.mapper.BlogMapper;
import com.awei.mapper.TypeMapper;
import com.awei.service.ITypeService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>
 * 服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Transactional
@Service
public class TypeServiceImpl extends ServiceImpl<TypeMapper, Type> implements ITypeService {
    @Autowired
    private TypeMapper typeMapper;
    @Autowired
    private BlogMapper blogMapper;

    @Override
    public Type saveType(Type type) {
        int i = typeMapper.insert(type);
        if (i != 1) {
            return null;
        } else {
            return type;
        }
    }

    @Override
    public Type getType(Long id) {
        return typeMapper.selectById(id);
    }

    @Override
    public Type getTypeByName(String Name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("type_name",Name);
        return typeMapper.selectOne(queryWrapper);
    }

    @Override
    public Object listType(Integer num) {
        Page<Type> page = new Page<>(num,5);
        IPage<Type> typeIPage = typeMapper.selectPage(page,null);
//        List<Type> typeList = typeIPage.getRecords();
        return typeIPage;
    }


    @Override
    public Type updateType(Long id,Type type) {
        Type t = typeMapper.selectById(id);
        if (t == null){
            throw new NotFoundException("不存在该类型");
        }
        BeanUtils.copyProperties(type,t);
        if (typeMapper.updateById(t) < 1){
            return null;
        }
        return t;
    }

    @Override
    public int deleteType(Long id) {
        int i = typeMapper.deleteById(id);
        return i;
    }

    @Override
    public List<Type> listAllType() {
        return typeMapper.selectList(null);
    }

    @Override
    public Object listTypeTop(Integer num) {
        Page<Type> typePage = new Page<>(1,num);

        return typeMapper.TypeListIndex(typePage);
    }

    @Override
    public Map<Integer,Type> getTypeSizeById(Long Typeid) {
        QueryWrapper<Blog> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("type_id",Typeid);
        Map<Integer,Type> map = new HashMap<>();
        map.put(blogMapper.selectCount(queryWrapper),typeMapper.selectById(Typeid));
        return map;
    }
}
