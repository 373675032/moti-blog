package com.moti.entity;

import lombok.*;

import java.io.Serializable;

/**
 * @ClassName: ArticleDateArchive
 * @Description: 文章日期归档实体类
 * @author: xw
 * @date 2020/3/1 17:04
 * @Version: 1.0
 **/
@Builder
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class ArticleDateArchive implements Serializable {
    /**
     * 日期：只取年月
     */
    private String date;
    /**
     * 文章数量
     */
    private int count;
}
