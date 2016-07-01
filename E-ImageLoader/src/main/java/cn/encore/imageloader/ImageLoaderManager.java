package cn.encore.imageloader;

import android.content.Context;

/**
 * Created by：Encore
 * Created Time：16/7/1 16:04
 * 图片加载库封装，通过策略模式方便后需要替换第三方库封装
 */
public class ImageLoaderManager {
    //大图
    public static final int PIC_LARGE = 0;
    //中等图
    public static final int PIC_MEDIUM = 1;
    //小图
    public static final int PIC_SMALL = 2;

    //所有网络下都加载图片
    public static final int LOAD_STRATEGY_NORMAL = 0;
    //仅wifi下加载图片
    public static final int LOAD_STRATEGY_ONLY_WIFI = 1;

    private static ImageLoaderManager mInstance;
    //图片加载
    private BaseImageLoaderStrategy mStrategy;

    private ImageLoaderManager(){
        mStrategy =new GlideImageLoaderStrategy();
    }

    //single instance
    public static ImageLoaderManager getInstance(){
        if(mInstance ==null){
            synchronized (ImageLoaderManager.class){
                if(mInstance == null){
                    mInstance = new ImageLoaderManager();
                    return mInstance;
                }
            }
        }
        return mInstance;
    }

    /**
     * 加载图片
     * @param context
     * @param img
     */
    public void loadImage(Context context, ImageLoader img){
        mStrategy.loadImage(context.getApplicationContext(),img);
    }

    /**
     * 设置图片加载策略
     * @param strategy
     */
    public void setLoadImgStrategy(BaseImageLoaderStrategy strategy){
        mStrategy =strategy;
    }
}
