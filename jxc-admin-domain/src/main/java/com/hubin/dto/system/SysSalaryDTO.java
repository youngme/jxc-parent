package com.hubin.dto.system;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;

/**
 * <br>
 *
 * @author hubin
 * @title:
 * @description:
 * @date: 2019/4/9 15:42
 */
@Getter
@Setter
@Accessors(chain = true)
public class SysSalaryDTO{

    Date createTime;
}
