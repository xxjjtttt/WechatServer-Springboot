package org.wechat.configuration;


import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.wechat.component.WechatServerInfo;

@EnableConfigurationProperties(WechatServerInfo.class)
public class AutoConfiguration {
    private WechatServerInfo wechatServerInfo;

    private AutoConfiguration(WechatServerInfo wechatServerInfo) {
        this.wechatServerInfo = wechatServerInfo;
    }

    @Bean
    public static AutoConfiguration createAutoConfiguration(WechatServerInfo wechatServerInfo) {
        return new AutoConfiguration(wechatServerInfo);
    }

}
