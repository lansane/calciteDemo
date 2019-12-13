package com.calcite.study;

import org.apache.calcite.schema.Schema;
import org.apache.calcite.schema.SchemaFactory;
import org.apache.calcite.schema.SchemaPlus;

import java.util.Map;

/**
 * Created by admin on 2019-11-24.
 */
public class CsvSchemaFactory implements SchemaFactory {
    @Override

    public Schema create(SchemaPlus schemaPlus, String s, Map<String, Object> operand) {
        return new CsvSchema(String.valueOf(operand.get("directory")));
    }
}
