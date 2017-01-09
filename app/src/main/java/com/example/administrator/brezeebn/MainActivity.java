package com.example.administrator.brezeebn;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.breezeebanner.BreezeeBanner;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private BreezeeBanner breezeeBanner;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        breezeeBanner= (BreezeeBanner) findViewById(R.id.breezeeBanner);
        ArrayList<Object> imgPaths=new ArrayList<>();
        imgPaths.add("http://b258.photo.store.qq.com/psb?/V10y9Ohx2Vjfdp/Bxj4Q7yc8u9jhVnbuTZA8UpvyrUSkB7Y8Dqz3vWGPuc!/b/dAIBAAAAAAAA&bo=kgOAArAESAMDCME!&rf=viewer_4");
        imgPaths.add("http://a1.qpic.cn/psb?/V10y9Ohx0SausD/jXEYIHd5GeIMv4ZcoVM3ZSBh65S*Buz4XXK4K1hPYQY!/b/dCABAAAAAAAA&ek=1&kp=1&pt=0&bo=0ALmAQAAAAAFFwE!&tm=1483970400&sce=60-3-3&rf=viewer_4");
        imgPaths.add("http://img.pconline.com.cn/images/upload/upc/tx/wallpaper/1212/14/c1/16601624_1355469191491.jpg");
        breezeeBanner.init(imgPaths);
    }

}
