package com.awei.service.impl;

import com.awei.NotFoundException;
import com.awei.entity.Tag;
import com.awei.mapper.TagMapper;
import com.awei.service.ITagService;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Transactional
@Service
public class TagServiceImpl implements ITagService {
    @Autowired
    private TagMapper tagMapper;

    @Override
    public Tag saveTag(Tag tag) {
        int i = tagMapper.insert(tag);
        if (i < 1) {
            return null;
        } else {
            return tag;
        }
    }

    @Override
    public Tag getTag(Integer tagid) {
        return tagMapper.selectById(tagid);
    }

    @Override
    public Tag getTagByName(String name) {
        QueryWrapper queryWrapper = new QueryWrapper();
        queryWrapper.eq("tag_name",name);
        return tagMapper.selectOne(queryWrapper);
    }

    @Override
    public Object listTag(Integer num) {
        Page<Tag> page = new Page<>(num,5);
        IPage<Tag> tagIPage = tagMapper.selectPage(page,null);
        return tagIPage;
    }

    @Override
    public Tag updateTag(Integer tagid, Tag tag) {
        Tag t = tagMapper.selectById(tagid);
        if (t == null){
            throw new NotFoundException("不存在该标签");
        }
        BeanUtils.copyProperties(tag,t);
        if (tagMapper.updateById(t)<1)
        {return null;}
        return t;
    }

    @Override
    public int deleteTag(Integer tagid) {
        int i = tagMapper.deleteById(tagid);
        return i;
    }

    @Override
    public List<Tag> listAllTag() {
        return tagMapper.selectList(null);
    }

    @Override
    public List<Integer> listTagById(String ids) {
        List<Integer> longlist = convertList(ids);
//        return tagMapper.selectBatchIds(longlist);
        return longlist;
    }

    @Override
    public Object listTagTop(Integer num) {
        Page<Tag> page = new Page<>(1,num);
        return tagMapper.TagListIndex(page);
    }

    private List<Integer> convertList(String ids){
        System.out.println(ids);
        List<Integer> list = new ArrayList<>();
        if (StringUtils.hasText(ids)){
            String[] idarray = ids.split(",");
            for (int i=0;i<idarray.length;i++){
                list.add(Integer.parseInt(idarray[i]));
            }
        }
        return list;
    }
}
