# BreezeeBanner
#### 标准简化Banner
```
    <com.example.breezeebanner.BreezeeBanner
        android:id="@+id/breezeeBanner"
        android:layout_width="match_parent"
        android:layout_height="260dp">
     </com.example.breezeebanner.BreezeeBanner>
```

```
breezeeBanner= (BreezeeBanner) findViewById(R.id.breezeeBanner);
ArrayList<Object> imgPaths=new ArrayList<>();    
imgPaths.add("https://xxxxx1")
imgPaths.add("https://xxxxx2");
breezeeBanner.init(imgPaths);
```
