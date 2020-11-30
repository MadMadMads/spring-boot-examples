package com.neo.configuration;

import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * @author: Luo
 * @description:
 * @time: 2020/9/15 15:11
 * Modified By:
 */

@ConfigurationProperties("example.service")
public class ExampleServiceProperties {
    private String prefix;

    public String getPrefix() {
        return prefix;
    }

    public void setPrefix(String prefix) {
        this.prefix = prefix;
    }

    public String getSuffix() {
        return suffix;
    }

    public void setSuffix(String suffix) {
        this.suffix = suffix;
    }

    private String suffix;
}
