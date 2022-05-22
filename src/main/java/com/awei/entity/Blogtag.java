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

    private static final long serialVersionUID = 1L;

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    private Long blog_id;

    private Long tag_id;

    @TableField(exist = false)
    private Tag tag;

    public Blogtag(Long id, Long blog_id, Long tag_id) {
        this.id = id;
        this.blog_id = blog_id;
        this.tag_id = tag_id;
    }
}
