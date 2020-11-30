package com.neo.Service.Imp;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/15 15:10
 * Modified By:
 */
public class ExampleService {
    private String prefix;
    private String suffix;

    public ExampleService(String prefix, String suffix) {
        this.prefix = prefix;
        this.suffix = suffix;
    }
    public String wrap(String word) {
        return prefix + word + suffix;
    }
}
