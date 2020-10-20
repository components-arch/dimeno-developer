# dimeno-developer
> 通用开发者选项

[![Platform](https://img.shields.io/badge/Platform-Android-00CC00.svg?style=flat)](https://www.android.com)
[![](https://jitpack.io/v/dimeno-tech/dimeno-developer.svg)](https://jitpack.io/#dimeno-tech/dimeno-developer)

### 依赖导入

项目根目录

``` gradle
allprojects {
	repositories {
		...
		maven { url 'https://jitpack.io' }
	}
}
```

模块目录

``` gradle
dependencies {
	implementation 'com.github.dimeno-tech:dimeno-developer:0.0.5'
}
```

### 动态切换api环境
**Router.with(this).toPath("/developer");**
![](https://tva1.sinaimg.cn/large/007S8ZIlgy1gjvjlqyq42j30u01uodha.jpg)

切换完成后，请在 **/login** 路由所属页面处理环境切换逻辑

``` java
public class MainActivity extends ToolbarActivity {

    @Override
    protected void onResume() {
        super.onResume();
        bind();
    }

    private void bind() {
        SharedPreferences preferences = getSharedPreferences("developer", Context.MODE_PRIVATE);
        ((TextView) findViewById(R.id.textView)).setText(String.format("当前环境：%s", preferences.getBoolean("env", true) ? "测试" : "正式"));
    }
}
```