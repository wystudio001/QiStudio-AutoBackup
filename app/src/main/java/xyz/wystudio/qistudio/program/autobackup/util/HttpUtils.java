package xyz.wystudio.qistudio.program.autobackup.util;

import java.io.IOException;

import cn.zhxu.okhttps.HTTP;
import cn.zhxu.okhttps.HttpTask;
import cn.zhxu.okhttps.OkHttps;

public class HttpUtils {
    public void init() {
        HTTP http = HTTP.builder()
                .exceptionListener((HttpTask<?> task, IOException error) -> {
                    // 所有异步请求（包括 WebSocket）发生异常都会走这里

                    // 返回 true 表示继续执行 task 的 OnException 回调，
                    // 返回 false 则表示不再执行，即 阻断
                    return true;
                })
                .build();
    }

    public String get(String url) {
        return OkHttps.sync(url)
                .get()
                .getBody()
                .toString();
    }
}
