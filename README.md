# calciteDemo
# 一、创建一个mode的json文件
```
{
  "version": "1.0",
  "defaultSchema": "SALES"
  "schemas": [
    {
      "name": "SALES",
      "type": "custom",
      "factory": "com.iflytek.edu.zx.CsvSchemaFactory",
      "operand": {
        "directory": "SALES.csv"
      }
    }
  ]
}

```
    描述数据库名称、文件目录、shcema创建
    ##基本概念
      schema   ==>  database
      defaultSchema  ==>登陆数据库时的默认数据库
      schema factory ==>创建schema的工厂类
      directory ==>数据存储
      

  
  # 二、代码    
  
## a、自定义SchemaFactory
```
public class CsvSchemaFactory implements SchemaFactory {
    @Override
    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> operand) {
        return new CsvSchema(String.valueOf(operand.get("directory")));
    }
}
```

## b、自定义Shcema
获取表的映射关系
```
public class CsvSchema extends AbstractSchema {
    private Map<String,Table> tableMap;
    private String dataFile;
    public CsvSchema(String dataFile){
        this.dataFile = dataFile;
    }
    @Override
    protected Map<String, Table> getTableMap() {
        URL url = Resources.getResource(dataFile);
        Source source = Sources.of(url);
        if (tableMap == null) {
            final ImmutableMap.Builder<String, Table> builder = ImmutableMap.builder();
            builder.put(this.dataFile.split("\\.")[0],new CsvTable(source));
            // 一个数据库有多个表名，这里初始化，大小写要注意了,TEST01是表名。
            tableMap = builder.build();
        }
        return tableMap;
    }
}
```

## c、自定义Table
     定义表的个字段的名称和类型
     表支持的操作
## d、自定义Enumerator
    如何遍历表对应的数据以满足查询语句
    
   
