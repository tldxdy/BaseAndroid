# Add project specific ProGuard rules here.
# You can control the set of applied configuration files using the
# proguardFiles setting in build.gradle.
#
# For more details, see
#   http://developer.android.com/guide/developing/tools/proguard.html

# If your project uses WebView with JS, uncomment the following
# and specify the fully qualified class name to the JavaScript interface
# class:
#-keepclassmembers class fqcn.of.javascript.interface.for.webview {
#   public *;
#}

# Uncomment this to preserve the line number information for
# debugging stack traces.
#-keepattributes SourceFile,LineNumberTable

# If you keep the line number information, uncomment this to
# hide the original source file name.
#-renamesourcefileattribute SourceFile
#############################
#
#       基本的指令
#
##########################
#-ignorewarnings
-ignorewarnings

-dontwarn org.apache.http.**

# 压缩质量  0~7
-optimizationpasses 5

#不采用大小写命名的混淆
-dontusemixedcaseclassnames

#不跳过类库例的非公共类
-dontskipnonpubliclibraryclasses

#混淆时是否记录日志（混淆后生产映射文件 map 类名 -> 转化后类名的映射
-verbose

#不跳过类库中的非公共方法
-dontskipnonpubliclibraryclassmembers

# 不做预校验
-dontpreverify

# 不混淆注解和内部类
-keepattributes *Annotation*,InnerClasses

#保持泛型
-keepattributes Signature

#保持源文件以及行号，友盟统计需要加入此行
-keepattributes SourceFile,LineNumberTable

# 混淆算法
-optimizations !code/simplification/cast,!field/*,!class/merging/*

#-ignorewarnings -keep class * { public private *; }


#############################################
#
# Android开发中一些需要保留的公共部分
#
#############################################

 ## 注册等需要类名，所以不能被混淆
-keep public class * extends android.app.Activity
-keep public class * extends android.app.Appliction
-keep public class * extends android.app.Service
-keep public class * extends android.content.BroadcastReceiver
-keep public class * extends android.content.ContentProvider
-keep public class * extends android.app.backup.BackupAgentHelper
-keep public class * extends android.preference.Preference
-keep public class * extends android.view.View
-keep public class com.android.vending.licensing.ILicensingService

## Androidx的不能混淆
-keep class com.google.android.material.** {*;}
-keep class androidx.** {*;}
-keep public class * extends androidx.**
-keep interface androidx.** {*;}
-dontwarn com.google.android.material.**
-dontnote com.google.android.material.**
-dontwarn androidx.**

## v4,v7,v13包
-keep class android.support.** {*;}

# 默认proguard-android文件已混淆，此处重复
-keep class **.R$* {*;}

# 默认proguard-android文件已混淆，此处重复
-keepclasseswithmembernames class * {
    native <methods>;
}
# 默认proguard-android文件已混淆，此处重复
-keepclassmembers class * extends android.app.Activity{
    public void *(android.view.View);
}
# 默认proguard-android文件已混淆，此处重复
-keepclassmembers enum * {
    public static **[] values();
    public static ** valueOf(java.lang.String);
}
#默认proguard-android文件已混淆，此处重复  添加View的构造方法不能被混淆
-keep public class * extends android.view.View{
    *** get*();
    void set*(***);
    public <init>(android.content.Context);
    public <init>(android.content.Context, android.util.AttributeSet);
    public <init>(android.content.Context, android.util.AttributeSet, int);
}

# 序列化
-keep class * implements android.os.Parcelable {
  public static final android.os.Parcelable$Creator *;
}

# 同上
-keepclassmembers class * implements java.io.Serializable {
    static final long serialVersionUID;
    private static final java.io.ObjectStreamField[] serialPersistentFields;
    private void writeObject(java.io.ObjectOutputStream);
    private void readObject(java.io.ObjectInputStream);
    java.lang.Object writeReplace();
    java.lang.Object readResolve();
}

#触摸事件
-keepclassmembers class * {
    void *(**On*Event);
}

#webView处理，项目中没有使用到webView忽略即可
-keepclassmembers class fqcn.of.javascript.interface.for.webview {
   public *;
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.WebView, java.lang.String, android.graphics.Bitmap);
    public boolean *(android.webkit.WebView, java.lang.String);
}
-keepclassmembers class * extends android.webkit.webViewClient {
    public void *(android.webkit.webView, jav.lang.String);
}