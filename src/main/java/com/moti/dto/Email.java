package com.moti.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * @ClassName: Email
 * @Description: TODO
 * @author: 莫提
 * @date 2020/10/9 17:28
 * @Version: 1.0
 **/
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Email {
    private String to;
    private String content;
    private String title;
    private String url;
}
