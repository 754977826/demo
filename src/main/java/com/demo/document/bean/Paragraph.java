package com.demo.document.bean;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nieyawei
 * @version 1.0
 * @className: Paragraph
 * @description:
 * @date 2019-06-13 20:52
 */
@Getter
@Setter
@NoArgsConstructor
public class Paragraph {
    private String text;
    private int fontSize;
    private String color;
    private String fontName;
}
