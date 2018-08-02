package com.meituan.tianlong.yuekaomoni.network;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

public abstract class OkCallBack implements Callback{
   /**
        * 成功
        * @param json
        */
       public abstract void success(String json);

       /**
        * 失败
        * @param json
        */
       public abstract void failed(String json);

       /**
        * 响应失败
        * 这个方法在网络请求线程执行，意味无法更新UI
        *
        * @param call
        * @param e
        */
       @Override
       public void onFailure(Call call, final IOException e) {

           Ok.getHandler().post(new Runnable() {
               @Override
               public void run() {
   //                这就是主线程
                   failed(e.getMessage());
               }
           });

       }

       /**
        * 响应成功
        * 这个方法在网络请求线程执行，意味无法更新UI
        *
        * @param call
        * @param response
        * @throws IOException
        */
       @Override
       public void onResponse(Call call, Response response) throws IOException {
           /**
            * 网络请求的数据必须有一个变量接受，body方法只执行一次。
            */
           final String json = response.body().string();

           Ok.getHandler().post(new Runnable() {
               @Override
               public void run() {
                   //成功返回数据
                   success(json);
               }
           });

       }
}
