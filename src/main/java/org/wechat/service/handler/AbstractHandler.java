package org.wechat.service.handler;


import org.springframework.stereotype.Service;


@Service
public abstract class AbstractHandler {

    public abstract String getMyContent();

}
