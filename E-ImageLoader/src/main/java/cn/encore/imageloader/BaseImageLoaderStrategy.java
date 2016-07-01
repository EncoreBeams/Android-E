package cn.encore.imageloader;


import android.content.Context;

/**
 * Created by：Encore
 * Created Time：16/7/1 16:04
 */
public interface BaseImageLoaderStrategy {
    void loadImage(Context ctx, ImageLoader img);
}