package com.demo.common;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * @author nieyawei
 * @version 1.0
 * @className: CommonException
 * @description:
 * @date 2019-06-12 21:27
 */

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CommonException extends RuntimeException {
    private String errCode;
    private String errInfo;
}
