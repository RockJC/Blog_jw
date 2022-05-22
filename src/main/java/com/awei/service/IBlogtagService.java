package com.awei.service;

import com.awei.entity.Blogtag;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-03
 */
public interface IBlogtagService extends IService<Blogtag> {
    int insertListTags(Long blogid, List<Long> tagIds);
    int updateListByBlogId(Long blogid,List<Long> tagIds);
}
