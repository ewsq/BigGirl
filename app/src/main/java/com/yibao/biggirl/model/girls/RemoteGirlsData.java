package com.yibao.biggirl.model.girls;

import com.yibao.biggirl.MyApplication;
import com.yibao.biggirl.network.RetrofitHelper;
import com.yibao.biggirl.service.MeizituService;
import com.yibao.biggirl.util.Constants;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.schedulers.Schedulers;

/**
 * Author：Sid
 * Des：${TODO}
 * Time:2017/4/26 00:52
 */
public class RemoteGirlsData
        implements GrilsDataSource {


    private int totalPages = 1;

    @Override
    public void getGirls(String dataType, int size, int page, LoadGDataCallback callback) {
        List<String> urlList = new ArrayList<>();
        RetrofitHelper.getGankApi(Constants.GANK_API)
                .getGril(dataType, size, page)
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Observer<GirlsBean>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                    }

                    @Override
                    public void onNext(GirlsBean girlsBean) {
                        List<ResultsBean> results = girlsBean.getResults();


                        for (int i = 0; i < results.size(); i++) {
                            urlList.add(results.get(i)
                                    .getUrl());
                        }
                        callback.onLoadDatas(urlList);
                    }

                    @Override
                    public void onError(Throwable e) {
                        callback.onDataNotAvailable();
                    }

                    @Override
                    public void onComplete() {
                    }
                });
    }

    @Override
    public void getMeizitu(String type, int page, int codeId, LoadGMeizituCallback callback) {
        if (codeId == 0) {

            String url = Constants.MEIZITU_API + type + "/page/" + page;
            final String fakeRefer = url + "/";
            final String realUrl = "http://api.caoliyu.cn/meizitu.php?url=%s&refer=%s";// 然后用自己的服务器进行转发
            Observable.just(Constants.MEIZITU_API).subscribeOn(Schedulers.io()).map(s -> {

                List<Girl> girls = new ArrayList<>();
                try {
                    Document doc = Jsoup.connect(url).timeout(10000).get();
                    Element total = doc.select("div.postlist").first();
                    Elements items = total.select("li");
                    for (Element element : items) {
                        Girl girl = new Girl(String.format(realUrl, element.select("img").first().attr("data-original"), fakeRefer));
                        girl.setLink(element.select("a[href]").attr("href"));
                        girls.add(girl);
                    }
                } catch (IOException e) {
                    e.printStackTrace();
                }

                return girls;
            }).observeOn(AndroidSchedulers.mainThread()).subscribe(callback::onLoadDatas);
        }
    }

    @Override
    public void getMeiziList(String url) {
        Observable.just(url).subscribeOn(Schedulers.io()).map(s -> {
            List<Girl> girls = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(url).timeout(10000).get();
                Element total = doc.select("div.pagenavi").first();
                Elements spans = total.select("span");
                for (Element str : spans) {
                    int page;
                    try {
                        page = Integer.parseInt(str.text());
                        if (page >= totalPages)
                            totalPages = page;
                    } catch (Exception e) {

                    }

                }
            } catch (Exception e) {
                e.printStackTrace();
            }

            return girls;
        }).observeOn(AndroidSchedulers.mainThread()).subscribe(girlList -> {
            for (int i = 0; i < totalPages; i++) {
                getMeizis(url, i);
            }
        });
    }

    private void getMeizis(String baseUrl, int page) {
        String url = baseUrl + "/" + page;
        final String fakeRefer = baseUrl + "/";
        final String realUrl = "http://api.caoliyu.cn/meizitu.php?url=%s&refer=%s";
        Observable.just(url).subscribeOn(Schedulers.io()).map(s -> {
            List<Girl> girls = new ArrayList<>();
            try {
                Document doc = Jsoup.connect(s).timeout(10000).get();
                Element total = doc.select("div.main-image").first();
                String attr = total.select("img").first().attr("src");
                girls.add(new Girl(String.format(realUrl, attr, fakeRefer)));
            } catch (IOException e) {
                e.printStackTrace();
            }
            return girls;

        }).observeOn(AndroidSchedulers.mainThread()).subscribe(girlList -> MeizituService.start(MyApplication.getIntstance(), baseUrl, girlList));


    }


}
