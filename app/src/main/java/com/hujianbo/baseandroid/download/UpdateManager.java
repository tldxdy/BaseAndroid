package com.hujianbo.baseandroid.download;

import java.io.File;
import java.io.IOException;

import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Function;
import io.reactivex.schedulers.Schedulers;
import okhttp3.ResponseBody;
import okio.BufferedSink;
import okio.BufferedSource;
import okio.Okio;

public class UpdateManager {

    /**
     * 是否需要更新,需要则下载
     *
     * @param url     新版本地址
     * @param apkPath 本地apk保存路径
     * @param cd      订阅关系集合，在数据传输完毕时解除订阅
     */
    public static void download(final String url, final String apkPath, final CompositeDisposable cd, final DowloadListener listener) {
        NetWork.getInstance().down(url, listener)
                .map(new Function<ResponseBody, BufferedSource>() {
                    @Override
                    public BufferedSource apply(ResponseBody responseBody) throws Exception {
                        return responseBody.source();
                    }
                })
                .subscribeOn(Schedulers.io())
                .observeOn(Schedulers.io())
                .subscribe(new Observer<BufferedSource>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        cd.add(d);
                    }

                    @Override
                    public void onNext(BufferedSource bufferedSource) {
                        File file = new File(apkPath);
                        if (!file.exists()) {
                            try {
                                file.createNewFile();
                            } catch (IOException e) {
                                e.printStackTrace();
                                listener.onfail();
                            }
                        }
                        try {
                            writeFile(bufferedSource, new File(apkPath));
                        } catch (IOException e) {
                            e.printStackTrace();
                            listener.onfail();
                        }
                    }

                    @Override
                    public void onError(Throwable e) {
                        unSubscribe(cd);
                        listener.onfail();
                    }

                    @Override
                    public void onComplete() {
                        //安装apk
//                        Intent intent = new Intent(Intent.ACTION_VIEW);
//                        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
//                        intent.setDataAndType(Uri.fromFile(new File(apkPath)), "application/vnd.android.package-archive");
//                        context.startActivity(intent);
                        if (listener != null) {
                            listener.complete(apkPath);
                        }
                        unSubscribe(cd);
                    }
                });
    }


    /**
     * 写入文件
     */
    private static void writeFile(BufferedSource source, File file) throws IOException {
        if (!file.getParentFile().exists())
            file.getParentFile().mkdirs();

        if (file.exists())
            file.delete();

        BufferedSink bufferedSink = Okio.buffer(Okio.sink(file));
        bufferedSink.writeAll(source);

        bufferedSink.close();
        source.close();
    }

    /**
     * 解除订阅
     *
     * @param cd 订阅关系集合
     */
    private static void unSubscribe(CompositeDisposable cd) {
        if (cd != null && !cd.isDisposed())
            cd.dispose();
    }


}
