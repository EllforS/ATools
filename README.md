# ATools
Rapid development of framework

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
	        compile 'com.github.EllforS:ATools:v2.1.0'
	}
```

Utils
===
ExAppUtils 		UI工具类
---
ExBitmapCompressUtils 	图片压缩工具类
---·	
```java
	compressImage(String filePath,String savePath) 压缩图片（需要压缩的图片地址，保存的地址）
	deleteCacheImg(String savePath) 删除压缩的图片（压缩图片的保存地址）
```
ExBitmapSaveUtils	保存图片到本地图库
---
```java
	saveImageToGallery(Context context, Bitmap bmp, String savePath) 保存图片到SD卡 并通知图库更新
	 图片的类都需要添加权限
 	<uses-permission android:name="android.permission.READ_EXTERNAL_STORAGE" />
 	<uses-permission android:name="android.permission.WRITE_EXTERNAL_STORAGE" />
 	<uses-permission android:name="android.permission.MOUNT_UNMOUNT_FILESYSTEMS"/>
```
