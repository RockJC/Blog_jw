package com.awei.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import com.github.dreamyoung.mprelation.JoinColumn;
import com.github.dreamyoung.mprelation.OneToMany;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.validator.constraints.NotBlank;

/**
 * <p>
 * 
 * </p>
 *
 * @author jiangwei
 * @since 2022-05-01
 */
@Data
@EqualsAndHashCode(callSuper = false)
@TableName("t_type")
public class Type implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "type_id", type = IdType.AUTO)
    private Long typeid;

    @TableField(value = "type_name")
    @NotBlank(message = "分类名称不能为空")
    private String typename;

    @TableField(exist = false)
    private List<Blog> blogs;

    @TableField(exist = false)
    private Integer size;
}
