# SiriView
SiriView for Android

###效果图
![自定义View1](http://img.blog.csdn.net/20160616230719521)
![自定义View2](http://img.blog.csdn.net/20160616230739130)

###使用方法
在xml中，
```java
<com.tao.view.SiriView
        android:id="@+id/siriView"
        android:layout_width="match_parent"
        android:layout_height="100dp"
        android:layout_centerInParent="true"/>
```
在Activity.java中，
```java
SiriView siriView = (SiriView) findViewById(R.id.siriView);
// 停止波浪曲线
siriView.stop();
// 设置曲线高度，height的取值是0f~1f
siriView.setWaveHeight(0.5f);
// 设置曲线的粗细，width的取值大于0f
siriView.setWaveWidth(5f);
// 设置曲线颜色
siriView.setWaveColor(Color.rgb(39, 188, 136));
// 设置曲线在X轴上的偏移量，默认值为0f
siriView.setWaveOffsetX(0f);
// 设置曲线的数量，默认是4
siriView.setWaveAmount(4);
// 设置曲线的速度，默认是0.1f
siriView.setWaveSpeed(0.1f);
```
