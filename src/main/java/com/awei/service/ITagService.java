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
public interface ITagService extends IService<Tag> {
    Tag saveTag(Tag tag);

    Tag getTag(Long tagid);

    Tag getTagByName(String name);

    Object listTag(Integer num);

    Tag updateTag(Long tagid, Tag tag);

    int deleteTag(Long tagid);

    List<Tag> listAllTag();

    List<Long> listTagById(String ids);

    Object listTagTop(Integer num);
}
