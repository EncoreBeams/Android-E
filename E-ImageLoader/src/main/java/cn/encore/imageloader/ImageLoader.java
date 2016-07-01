package cn.encore.imageloader;

import android.widget.ImageView;

import java.io.File;

/**
 * Created by：Encore
 * Created Time：16/7/1 16:04
 */
public class ImageLoader {
    //加载模式
    private int type;
    //图片url 地址
    private String url;
    //加载本地文件
    private File file;
    //本地资源图片路径
    private int resourceId = -1;
    //加载默认图
    private int defaultImg = -1;
    //错误图
    private int errorImg = -1;
    //imagewifi
    private ImageView imgView;
    //加载图片策略
    private int wifiStrategy;
    //设置宽度
    private int resizeWidth = -1;
    //设置高度
    private int resizeHeight = -1;



    private ImageLoader(Builder builder) {
        this.type = builder.type;
        this.url = builder.url;
        this.defaultImg = builder.defaultImg;
        this.imgView = builder.imgView;
        this.wifiStrategy = builder.wifiStrategy;
        this.file = builder.file;
        this.resourceId = builder.resourceId;
        this.errorImg = builder.errorImg;
        this.resizeWidth = builder.resizeWidth;
        this.resizeHeight = builder.resizeHeight;
    }
    public int getType() {
        return type;
    }

    public String getUrl() {
        return url;
    }

    public int getDefaultImg() {
        return defaultImg;
    }

    public void setDefaultImg(int defaultImg) {
        this.defaultImg = defaultImg;
    }

    public ImageView getImgView() {
        return imgView;
    }

    public int getWifiStrategy() {
        return wifiStrategy;
    }

    public File getFile() {
        return file;
    }

    public int getResourceId() {
        return resourceId;
    }

    public int getErrorImg() {
        return errorImg;
    }

    public int getResizeWidth() {
        return resizeWidth;
    }

    public int getResizeHeight() {
        return resizeHeight;
    }

    public static class Builder {
        //加载模式
        private int type;
        //图片url 地址
        private String url;
        //加载本地文件
        private File file;
        //本地资源图片路径
        private int resourceId;
        //加载默认图
        private int defaultImg;
        //错误图
        private int errorImg;
        //imagewifi
        private ImageView imgView;
        //加载图片策略
        private int wifiStrategy;
        //设置宽度
        private int resizeWidth = -1;
        //设置高度
        private int resizeHeight = -1;
        //是否关闭动画
        private boolean dontAnimate;

        public Builder() {
            this.type = ImageLoaderManager.PIC_SMALL;
            this.url = "";
            this.defaultImg = android.R.drawable.sym_def_app_icon;
            this.imgView = null;
            this.wifiStrategy = ImageLoaderManager.LOAD_STRATEGY_NORMAL;
        }

        /**
         * 加载图片模式
         * @param type
         * @return
         */
        public Builder type(int type) {
            this.type = type;
            return this;
        }

        /**
         * 图片地址
         * @param url
         * @return
         */
        public Builder load(String url) {
            this.url = url;
            return this;
        }

        /**
         * 图片本地路径
         * @param file
         * @return
         */
        public Builder load(File file) {
            this.file = file;
            return this;
        }

        /**
         * 图片本地资源
         * @param resourceId
         * @return
         */
        public Builder load(int resourceId) {
            this.resourceId = resourceId;
            return this;
        }

        /**
         * 默认图片
         * @param defaultImg
         * @return
         */
        public Builder defaultImg(int defaultImg) {
            this.defaultImg = defaultImg;
            return this;
        }

        /**
         * 重设宽高
         * @param width
         * @param height
         * @return
         */
        public Builder resize(int width,int height){
            this.resizeWidth = width;
            this.resizeHeight = height;
            return this;
        }

        public Builder imgView(ImageView imgView) {
            this.imgView = imgView;
            return this;
        }

        public Builder strategy(int strategy) {
            this.wifiStrategy = strategy;
            return this;
        }


        public ImageLoader build() {
            return new ImageLoader(this);
        }

    }
}
