# ATools
一些简简单单的工具类

Step 1. Add the JitPack repository to your build file
---
Add it in your root build.gradle at the end of repositories:

```java
  allprojects {
        repositories {
            ...
            maven { url "https://jitpack.io" }
        }
    }
```

Step 2. Add the dependency
---
```java
  dependencies {
        implementation 'com.github.EllforS:ATools:v3.0.1'
    }
```

Base
===
* ExBaseRcvAdapter
```java
    添加点击事件
```

Utils
===
* ExAndroidUtil (UI工具类)
* ExBitmapCompressUtil (图片压缩工具类)
```java
    压缩图片(需要压缩的图片地址  getCompressImage(String pic_path))
    清除压缩图片  clearCompressImages()
```
* ExBitmapSaveUtils (保存图片到本地图库)
```java
    保存图片到SD卡 并通知图库更新   saveImageToGallery(Context context, Bitmap bmp, String savePath)

    图片的类都需要添加权限
    <uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
    <uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
```
* ExCheckApkExist (根据包名检测手机是否安装某个APK)
```java
    检测手机是否安装某个程序  checkApkExist(Context context, String packageName)
    微信  WEIXIN_PACKAGE
    QQ   QQ_PACKAGE
```
* ExDataCleanManager(计算和清空缓存大小)---好像并没有什么卵用
```java
    获取应用缓存大小   getTotalCacheSize(Context context)
    清空应用缓存       clearAllCache(Context context)
```
* ExDateUtil (时间工具类)
* ExDensityUtils(dp px 转换)
```java
    dp转px  dp2px(Context context, float dpVal)
    sp转px  sp2px(Context context, float spVal)
    px转dp  px2dp(Context context, float pxVal)
    px转sp  px2sp(Context context, float pxVal)
```
* ExStausBarUtils(设置沉浸式状态栏)
* ExLoggerUtil(Logger 工具类) 具体查看orhanobut大神的 [Logger](https://github.com/orhanobut/logger)
* ExImageLoader(图片加载类)

View
===
* CircleImageView(自定义圆形图片控件)
* ExGridView (自适应ScrollView的GridView)
* ExListView (自适应ScrollView的ListView)
* ExRadioGroup (可换行的RadioGroup)
```java
	设置RadioButton的margin方法 setChildMargin(int l, int t, int r, int b);
```
* ExRecyclerView(自适应ScrollView的RecyclerView)
* ExScrollView(解决5.0以上RecyclerView滑动卡顿的ScrollView)
* ExStaggeredGridLayoutManager(不规则排列（类似于瀑布流）的布局管理器)
* FullyGridLayoutManager(嵌套在ScrollView中的GridLayoutManager)
* FullyLinearLayoutManager(嵌套在ScrollView中的LinearLayoutManager)
* LoadingRecyclerView(上拉加载RecyclerView)
```java
	init(SwipeRefreshLayout s, Adapter a) 传入SwipeRefreshLayout，和用到的Adapter
	setOnLoadingListener()加载监听
	isLoadingEnd()加载结束
```
* DividerGridItemDecoration(GridLayoutManager分割线)
* DividerItemDecoration(LinearLayoutManager分割线)
```java
    改变分割线样式：
        在Style中  <item name="android:listDivider">@drawable/divider_bg</item>
        新建divider_bg绘制
```
