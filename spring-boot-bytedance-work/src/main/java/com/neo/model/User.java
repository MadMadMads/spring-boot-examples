package com.neo.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;

/**
 * @description:
 * @author: Luozhi
 * @create: 2021-02-19 20:45
 **/
@Data
public class User {

    private int id;

    private String name;

    @JSONField
    private String Remarks;
}
