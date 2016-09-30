package cn.encore.imageloader;

import android.content.Context;

import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;
import com.bumptech.glide.Priority;
import com.bumptech.glide.RequestManager;
import com.bumptech.glide.load.data.DataFetcher;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.model.stream.StreamModelLoader;

import java.io.IOException;
import java.io.InputStream;

import cn.encore.framecommon.utils.AppUtils;
import cn.encore.framecommon.utils.SettingUtils;


/**
 * Created by：Encore
 * Created Time：16/7/1 16:04
 */
public class GlideImageLoaderStrategy implements BaseImageLoaderStrategy {
    @Override
    public void loadImage(Context ctx, ImageLoader img) {

        boolean flag = SettingUtils.getOnlyWifiLoadImg(ctx);
        //如果不是在wifi下加载图片，直接加载

        int strategy = img.getWifiStrategy();
        if (strategy == ImageLoaderManager.LOAD_STRATEGY_ONLY_WIFI || flag) {
            int netType = AppUtils.getNetWorkType(ctx);
            //如果是在wifi下才加载图片，并且当前网络是wifi,直接加载
            if (netType == AppUtils.NETWORKTYPE_WIFI) {
                loadNormal(ctx, img);
            } else {
                //如果是在wifi下才加载图片，并且当前网络不是wifi，加载缓存
                loadCache(ctx, img);
            }
        } else {
            //如果不是在wifi下才加载图片
            loadNormal(ctx, img);
        }

    }

    /**
     * load image with Glide
     */
    private void loadNormal(Context ctx, ImageLoader img) {
        loadNormal(img, Glide.with(ctx));
    }

    /**
     * load image with Glide
     */
    private void loadNormal(ImageLoader img, Object obj) {
        if (img == null) return;


        //处理加载图片
        Object load = null;
        if (img.getUrl() != null && !img.getUrl().equals("")) {
            load = img.getUrl();
        } else if (img.getResourceId() != -1) {
            load = img.getResourceId();
        } else if (img.getFile() != null && img.getFile().exists()) {
            load = img.getFile();
        }

        //获取实例
        DrawableTypeRequest dr = null;
        if (obj instanceof RequestManager) {
            dr = ((RequestManager) obj).load(load);
        } else if (obj instanceof RequestManager.ImageModelRequest) {
            dr = ((RequestManager.ImageModelRequest) obj).load(load);
            dr.diskCacheStrategy(DiskCacheStrategy.ALL);
        }
        if (dr == null) {
            return;
        }
        //设置占位图
        dr.placeholder(img.getDefaultImg());
        //设置重设图片大小
        if (img.getResizeWidth() != -1 && img.getResizeHeight() != -1) {
            dr.override(img.getResizeWidth(), img.getResizeHeight());
        }


        //加载imageView
        dr.into(img.getImgView());

    }


    /**
     * load cache image with Glide
     */
    private void loadCache(Context ctx, ImageLoader img) {
        RequestManager.ImageModelRequest imageModelRequest = Glide.with(ctx).using(new StreamModelLoader<String>() {
            @Override
            public DataFetcher<InputStream> getResourceFetcher(final String model, int i, int i1) {
                return new DataFetcher<InputStream>() {
                    @Override
                    public InputStream loadData(Priority priority) throws Exception {
                        throw new IOException();
                    }

                    @Override
                    public void cleanup() {

                    }

                    @Override
                    public String getId() {
                        return model;
                    }

                    @Override
                    public void cancel() {

                    }
                };
            }
        });

        loadNormal(img, imageModelRequest);
    }
}