package com.neo.model;

import java.io.Serializable;

/**
 * @author: Luo
 * @description:
 * @time: 2020/11/3 10:54
 * Modified By:
 */
public class FileTest implements Serializable {
    private static final long serialVersionUID = 1L;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public FileTest(){}

    public FileTest(String id, Long time) {
        this.id = id;
        this.time = time;
    }

    String id;
    Long time;

    public Long getTime() {
        return time;
    }

    public void setTime(Long time) {
        this.time = time;
    }

}
