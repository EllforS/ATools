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
	        compile 'com.github.EllforS:ATools:v2.1.1'
	}
```

Base
===
* ExBaseRcvAdapter
```java
	构造函数传入isHasHeader,isHasFooter
	isEnd(),setEnd(boolean end) 方法控制是否全部加载完成 setEnd方法自带notifyDataSetChanged() 
	如果有FooterView 则list size + 1
```
	
Utils
===
* ExAppUtils (UI工具类)
* ExBitmapCompressUtils (图片压缩工具类)
```java
	压缩图片（需要压缩的图片地址，保存的地址   compressImage(String filePath,String savePath) 	）
	删除压缩的图片（压缩图片的保存地址）      deleteCacheImg(String savePath) 		
```
* ExBitmapSaveUtils (保存图片到本地图库)
```java
	保存图片到SD卡 并通知图库更新   saveImageToGallery(Context context, Bitmap bmp, String savePath) 
	 
	 图片的类都需要添加权限
 	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 	<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
```
* ExCheckTextUtil (正则表达式工具类)
```java
	检查是否为Email   isEmail(String strEmail) 		
	检查是否为手机号   isPhoneNumberValid(String phoneNumber) 	
	检查是否为手机号   isQQ(String str)
```
* ExDataCleanManager(计算和清空缓存大小)---好像并没有什么卵用
```java
	获取应用缓存大小   getTotalCacheSize(Context context)
	清空应用缓存	     clearAllCache(Context context)
```
* ExNotificationUtils (通知栏工具类)
```java
	需要添加
	<uses-permission android:name="android.permission.VIBRATE"/>
	
	发起普通通知(context,intent,大图标,小图标,标题,内容,震动时间,通知ID)   showNormalNotifi()
	取消通知(通知ID)   cancelNotification(int id)
	发起自定义通知(context,构造的View,intent,震动时间,通知ID)   showCustomNotifi()
```
* ExTimeUtil(时间工具类)
```java
	将字符串转位日期类型{yyyy-MM-dd HH:mm:ss}   toDate(String sdate)
	将字符串转位日期类型_2{yyyy-MM-dd}   toDate_2(String sdate)
	以友好的方式显示时间   friendly_time(String sdate)
	字符串日期转换成中文格式日期   dateToCnDate(String date)
	格式化时间   formatDate(Date date)
	获取当前时间   getNowTime()
```
* ExVersionUtils(获取App版本数据)
```java
	获取当前版本名   getAppVersionName(Context context)
	获取当前版本号   getAppVersionCode(Context context)
```
* L(Logger 工具类) 具体查看orhanobut大神的 [Logger](https://github.com/orhanobut/logger)

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
