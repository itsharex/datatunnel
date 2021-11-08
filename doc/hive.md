### Reader

#### 参数说明

| 参数key         | 数据类型   | 是否必填  | 默认值    |描述                                  |
| :-----          | :-----   | :-----   | :------  | :------                             |
| tableName       | string   | √        |          |读取数据表的表名称（大小写不敏感）, 可以包含数据库名，如果没有数据库名，数据库默认为当前项目          |
| partitions      | string    |         |          |如果分区表必须指定具体分区，数组类型值，可以指定多个分区，partition="ds=20210816,ds=20210817", 多个值逗号分隔         |
| columns         | string    | √       | *        |读取hive源头表的列信息。例如现在有表 test，其字段为：id,name,age 如果你想依次读取 id,name,age 那么你应该配置为: "columns":"id,name,age" 或者配置为:"columns"="*" 这里 * 表示依次读取表的每个字段，但是我们不推荐你配置抽取字段为 * ，因为当你的表字段顺序调整、类型变更或者个数增减，你的任务就会存在源头表列和目的表列不能对齐的风险，会直接导致你的任务运行结果不正确甚至运行失败。如果你想依次读取 name,id 那么你应该配置为: "coulumns":"name,id" 如果你想在源头抽取的字段中添加常量字段(以适配目标表的字段顺序)，比如你想抽取的每一行数据值为 age 列对应的值,name列对应的值,常量日期值1988-08-08 08:08:08,id 列对应的值 那么你应该配置为:"columns":"age,name,'1988-08-08 08:08:08',id" 即常量列首尾用符号' 包住即可，我们内部实现上识别常量是通过检查你配置的每一个字段，如果发现有字段首尾都有'，则认为其是常量字段，其实际值为去除' 之后的值。            |


### Writer

#### 参数说明

| 参数key         | 数据类型   | 是否必填  | 默认值     |描述                                  |
| :-----          | :-----   | :-----   | :------   | :------                             |
| tableName       | string   | √        |           |读取数据表的表名称（大小写不敏感）, 可以包含数据库名，如果没有数据库名，数据库默认为当前项目          |
| partitions      | string   |          |           |如果分区表必须指定具体分区，数组类型值，可以指定多个分区，partition="ds=20210816,ds=20210817", 多个值逗号分隔         |
| columns         | string   | √        | *         |读取hive源头表的列信息。例如现在有表 test，其字段为：id,name,age 如果你想依次读取 id,name,age 那么你应该配置为: "columns":"id,name,age" 或者配置为:"columns"="*" 这里 * 表示依次读取表的每个字段，但是我们不推荐你配置抽取字段为 * ，因为当你的表字段顺序调整、类型变更或者个数增减，你的任务就会存在源头表列和目的表列不能对齐的风险，会直接导致你的任务运行结果不正确甚至运行失败。如果你想依次读取 name,id 那么你应该配置为: "coulumns":"name,id" 如果你想在源头抽取的字段中添加常量字段(以适配目标表的字段顺序)，比如你想抽取的每一行数据值为 age 列对应的值,name列对应的值,常量日期值1988-08-08 08:08:08,id 列对应的值 那么你应该配置为:"columns":"age,name,'1988-08-08 08:08:08',id" 即常量列首尾用符号' 包住即可，我们内部实现上识别常量是通过检查你配置的每一个字段，如果发现有字段首尾都有'，则认为其是常量字段，其实际值为去除' 之后的值。            |
| writeMode       | string   | √        | overwrite |可选值: append & overwrite |