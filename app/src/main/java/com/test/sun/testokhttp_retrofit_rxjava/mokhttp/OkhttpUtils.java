package com.test.sun.testokhttp_retrofit_rxjava.mokhttp;

import android.util.Log;

import com.test.sun.testokhttp_retrofit_rxjava.mretrofit.RetrofitUtils;

import java.io.File;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

import okhttp3.Call;
import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

/**
 * Created by ZS27 on 2016/12/10.
 */

public class OkhttpUtils {

    public interface Callback {
        void onFailure();

        void onSuccess();
    }

    public static class HyperTextObject {
        /*constants*/
        public static final int FAIL_REQUEST_ = 0x10;
        public static final int FAIL_RESPONSE_ERROR_ = 0x12;

        public static final int SUCCESS_REQUEST_1 = 0x1;//more status
        public static final int SUCCESS_REQUEST_2 = 0x2;//more status
        public static final int SUCCESS_REQUEST_3 = 0x3;//more status

        /*callback*/
        public interface Callback extends RetrofitUtils.Callback {
            @Override
            void onFailure();

            @Override
            void onSuccess();
        }

        /*method*/
        public static void doGet(String url, String txt, RetrofitUtils.HyperTextObject.Callback callback) {

        }

        public static void doPost(String url, String txt, RetrofitUtils.HyperTextObject.Callback callback) {

        }
    }

    public static class FileUploadObject {
        /*constants*/
        public static final int FAIL_REQUEST_ = 0x10;
        public static final int FAIL_RESPONSE_ERROR_ = 0x12;
        public static final int SUCCESS_REQUEST_ = 0x1;//more status

        /*callback*/
        public interface Callback extends RetrofitUtils.Callback {
            @Override
            void onFailure();

            @Override
            void onSuccess();
        }

        /*method*/
        public static void singleFile(String url, String fileAbsPath, final OkhttpUtils.FileUploadObject.Callback callback) {
            File file = new File(fileAbsPath);
            if (!file.exists()) {
                callback.onFailure();
                return;
            }
            /*form表单*/
            RequestBody fileBody = RequestBody.create(MediaType.parse("application/octet-stream"), file);
            RequestBody requestBody = new MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("info", file.getName(), fileBody)
                    .build();
            /*请求参数*/
            Request request = new Request.Builder()
                    .url(url)
                    .post(requestBody)
                    .build();
            /*设置client*/
            OkHttpClient.Builder builder = new OkHttpClient.Builder();
            OkHttpClient okHttpClient = builder
                    .connectTimeout(10, TimeUnit.SECONDS)
                    .writeTimeout(15, TimeUnit.SECONDS)
                    .build();
            /*发送请求*/
            Call call = okHttpClient.newCall(request);
            call.enqueue(new okhttp3.Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    Log.i("info", "UploadUtil:onFailure----------------------");
                    callback.onFailure();
                }

                @Override
                public void onResponse(Call call, Response response) {
                    Log.i("info", "UploadUtil:onResponse----------------------");
                    if (response.isSuccessful()) {
                        try {
                            String string = response.body().string();
                            callback.onSuccess();
                        } catch (IOException e) {
                            callback.onFailure();
                        }
                    } else {
                        callback.onSuccess();
                    }
                }
            });
        }

        public static void multiFiles(String url, String fileAbsPath, Callback callback) {

        }
    }

    public static class FileDownloadObject {
        public static void singleFile(String url, String fileAbsPath, Callback callback) {

        }

        public static void multiFiles(String url, String fileAbsPath, Callback callback) {

        }
    }
}
