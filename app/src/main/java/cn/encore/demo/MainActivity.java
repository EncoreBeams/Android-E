package cn.encore.demo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;

import cn.encore.imageloader.ImageLoader;
import cn.encore.imageloader.ImageLoaderManager;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        ImageView imageView = (ImageView) findViewById(R.id.imgTest);

        ImageLoader imageLoader = new ImageLoader.Builder()
                .load("http://cdn.sspai.com/attachment/thumbnail/2016/06/28/98a9788ea7e0f8e1c8eefb2d87393c9151d8b_mw_800_wm_1_wmp_3.jpg")
                .imgView(imageView)
                .build();

        ImageLoaderManager.getInstance().loadImage(this,imageLoader);
    }
}
