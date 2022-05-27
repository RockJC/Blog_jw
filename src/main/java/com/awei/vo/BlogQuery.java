package com.awei.vo;

import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @author jiangwei
 * @create 2022-05-03 15:21
 */
@Data
@NoArgsConstructor
public class BlogQuery {
    private String blogTitle;
    private Integer typeId;
    private Boolean blogRecommend;
}
