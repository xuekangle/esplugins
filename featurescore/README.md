* 环境依赖:
    * elasticsearch版本 >= 5.x
    * pom中对不同elasticsearch版本的依赖，最终插件只能应用在对应版本的elasticsearch上,版本要一致。

* 编译安装：
    * 编译：mvn clean package -DskipTests
    * 安装方式(两种)：
        * 1. 直接将target/release/es-plugins-featurescore-${version}.zip文件解压到${elasticsearch-home}/plugins/featurescore下面
        * 2. ${elasticsearch-home}/bin/elasticsearch-plugin install file:///path/to plugin.zip(官方不再支持)

* 插件测试：
    * 首先测试插件是否安装成功 'bin/elasticsearch-plugin list'
    * 插件调用方式

```json
    curl -XGET localhost:9200/<index>/<type>/_search -d '{
        "query":{
            "function_score":{
                "query":{
                    "match_all":{}
                },
                "script_score":{
                    "script":{
                        "inline":"featurescore",,
                        "lang":"native",
                        "params":{
                            "feature":"feature base64编码",
                        }
                    }
                }
            }
        },
        "sort":{
            "_score":{
                "order":"asc"
            }
        }
    }'
```

* 注意：
    * 本例子有两种计算方式，计算欧式距离或余弦距离。根据计算方式的不通，查询语句应做生序或降序排序。
