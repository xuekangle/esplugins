* 开发elasticsearch Java插件
    * Java代码（低于5.x版本和低版本的接口不兼容）
        * 插件入口类，需要继承AbstractDoublieSearchScript抽象类，这个配置在property配置中，classname。
        * 插件Factory类，实现NativeScriptFactory接口类
        * 插件Plugin类，继承Plugin抽象类，选择实现ActionPlugin，ScriptPlugin接口
    * 打包发布
        * 会打包成zip包，里面包括所有依赖的jar和配置（maven通过assembly插件）
        * 使用时，将zip包解压到elasticsearch的plugins目录下,包含jar和配置文件
        
* 注意：
    * 如果feature设置为keyword类型，插件中通过doc() api从内存读取。
    * 如果feature设置为binary类型，插件中也是通过doc() api从内存中读取。
    * feature为float数组转成的base64数组。根据需要可以自定义数据纬度，本例子为256位长度。