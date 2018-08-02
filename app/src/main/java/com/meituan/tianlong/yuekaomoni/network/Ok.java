package com.meituan.tianlong.yuekaomoni.network;

import android.os.Handler;

import java.util.Map;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;

public class Ok {
        private static Ok ok;
            private static OkHttpClient okHttpClient;
            private static Handler handler;


            public static OkHttpClient getOkHttpClient() {
                return okHttpClient;
            }

            public static Handler getHandler() {
                return handler;
            }

            private Ok(){
                okHttpClient = new OkHttpClient
                        //TODO 拦截器，打印日志
                        .Builder()
                        .build();

                handler = new Handler();
            }
            /**
             * 获取单例（双重检测）
             *
             * @return
             */
            public static Ok getOk() {
                if (ok  == null){//第一重检测
                    synchronized (Ok.class){//加同步锁
                        if (ok==null){//第二重检测
                            ok = new Ok();
                        }
                    }
                }
                return ok;
            }


            /**
             * GET方法
             */
            public void doGet(String url, Callback callback){
                //组装请求
                Request request = new Request.Builder()
                        .url(url)
                        .build();

                //初始化调用类
                Call call = okHttpClient.newCall(request);

                //加入请求队列，回调结果
                call.enqueue(callback);

            }

            public void post(String url, Map<String, String> map, Callback callback){
                /**
                 * Body实体，传入参数
                 */
                FormBody.Builder builder =  new FormBody.Builder();
                for (String key: map.keySet()) {
                    builder.add(key,map.get(key));
                }

                Request request = new Request.Builder()
                        .url(url)
                        .post(builder.build())
                        .build();

                Call call = getOkHttpClient().newCall(request);

                call.enqueue(callback);
            }
}
