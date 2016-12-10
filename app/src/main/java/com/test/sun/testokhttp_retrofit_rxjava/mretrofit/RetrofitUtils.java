package com.test.sun.testokhttp_retrofit_rxjava.mretrofit;

/**
 * Created by ZS27 on 2016/12/10.
 */

public class RetrofitUtils {

    public interface Callback {
        void onFailure();

        void onSuccess();
    }

    public static class HyperTextObject {
        /*constants*/

        /*callback*/
        public interface Callback extends RetrofitUtils.Callback {
            @Override
            void onFailure();

            @Override
            void onSuccess();
        }

        /*method*/
        public static void doGet(String url, String txt, Callback callback) {

        }

        public static void doPost(String url, String txt, Callback callback) {

        }
    }

    public static class FileUploadObject {
        public interface Callback extends RetrofitUtils.Callback {
            @Override
            void onFailure();

            @Override
            void onSuccess();
        }

        public static void singleFile(String url, String fileAbsPath, Callback callback) {

        }

        public static void multiFiles(String url, String fileAbsPath, Callback callback) {

        }
    }

    public static class FileDownloadObject {
        public interface Callback extends RetrofitUtils.Callback {
            @Override
            void onFailure();

            @Override
            void onSuccess();
        }

        public static void singleFile(String url, String fileAbsPath, Callback callback) {

        }

        public static void multiFiles(String url, String fileAbsPath, Callback callback) {

        }
    }
}
