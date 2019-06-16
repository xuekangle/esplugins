### elasticsearch的Java插件开发
#### 代码说明
    * 插件的入口类需要继承AbstractDoublieSearchScript抽象类，这个配置在property配置中设置，即classname。
    * 插件Plugin类需要继承Plugin抽象类，选择实现ActionPlugin，ScriptPlugin接口
    * 插件Factory类需要实现NativeScriptFactory接口类
#### 打包发布
    * 将打包成zip包，里面包括所有依赖的jar和配置
    * 使用时，将zip包解压到elasticsearch的plugins目录下
        
#### 注意：
    * es中的字段数据为float数组转成的base64数组。根据需要可以自定义数据纬度，本例子为256位长度。
    * 本插件适用于5.3.x版本，其他版本暂未测试
