package com.awei.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.List;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_blogtag")
public class Blogtag implements Serializable {
    /**
     * 对应关系-博客ID
     */
    @TableField(value = "ref_blog_id")
    private Long refBlogId;

    /**
     * 对应关系-标签ID
     */
    @TableField(value = "ref_tag_id")
    private Integer refTagId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    @TableField(exist = false)
    private Tag tag;

    public Blogtag(Long blog_id, Integer tag_id) {
        this.refBlogId = blog_id;
        this.refTagId = tag_id;
    }
}
