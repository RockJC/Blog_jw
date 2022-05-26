package com.awei.service;

import com.awei.entity.Tag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
public interface ITagService{
    Tag saveTag(Tag tag);

    Tag getTag(Integer tagid);

    Tag getTagByName(String name);

    Object listTag(Integer num);

    Tag updateTag(Integer tagid, Tag tag);

    int deleteTag(Integer tagid);

    List<Tag> listAllTag();

    List<Integer> listTagById(String ids);

    Object listTagTop(Integer num);
}
