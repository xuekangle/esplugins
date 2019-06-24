### elasticsearch的Java插件开发
#### 代码说明
    * 插件的入口类需要继承AbstractDoublieSearchScript抽象类，这个配置在property配置中设置，即classname。
    * 插件Plugin类需要继承Plugin抽象类，选择实现ActionPlugin，ScriptPlugin接口
    * 插件Factory类需要实现NativeScriptFactory接口类
#### 打包发布
    * 使用assembly，将打包成zip包，里面包括所有依赖的jar和配置
    * 使用时，将zip包解压到elasticsearch的plugins目录下
        
#### 注意：
    * es中的字段数据feature为float数组转成的base64数组。根据需要可以自定义数据纬度，本项目纬度必须大8位。
    * 本插件适用于5.3.x版本，其他版本暂未测试。
    * 测试中，对两个地图经纬度做了欧式距离计算，可以应用于app中附近人的搜索。地图经纬度float数组需要补充到了8位做计算。也可不补充长度，对比分计算做修改，适用于自己项目。
