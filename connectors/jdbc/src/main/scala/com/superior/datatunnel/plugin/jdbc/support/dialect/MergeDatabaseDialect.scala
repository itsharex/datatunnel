package com.superior.datatunnel.plugin.jdbc.support.dialect

import org.apache.commons.lang3.StringUtils
import org.apache.spark.sql.jdbc.JdbcDialect
import org.apache.spark.sql.types.StructType

import java.sql.Connection

class MergeDatabaseDialect(conn: Connection, jdbcDialect: JdbcDialect, dataSourceType: String)
  extends DefaultDatabaseDialect(conn, jdbcDialect, dataSourceType) {

  override def getUpsertStatement(
      destTableName: String,
      rddSchema: StructType,
      tableSchema: Option[StructType],
      keyColumns: Array[String]): String = {

    if (keyColumns.length == 0) {
      throw new IllegalArgumentException("not primary key, not support upsert")
    }

    val columns = getColumns(rddSchema, tableSchema)
    val builder = new StringBuffer()
    var sql = s"MERGE INTO $destTableName dist \nUSING (\n    SELECT "
    builder.append(sql);
    sql = columns.map(col => s"? AS $col").mkString(",")
    builder.append(sql);

    if (StringUtils.equalsIgnoreCase("oracle", dataSourceType)) {
      builder.append("\n    FROM DUAL")
    } else if (StringUtils.equalsIgnoreCase("db2", dataSourceType)) {
      builder.append("\n    FROM sysibm.sysdummy1")
    }

    builder.append("\n) src\n")
    builder.append("on (")
    sql = keyColumns.map(key => s"src.${key} = dist.${key}").mkString(" AND ")
    builder.append(sql);
    builder.append(")\n")

    builder.append("WHEN MATCHED THEN\n    UPDATE SET ")
    sql = columns.filter(!keyColumns.contains(_))
      .map(key => s"dist.${key} = src.${key}").mkString(", ")
    builder.append(sql);
    builder.append("\nWHEN NOT MATCHED THEN\n")
    builder.append(s"    insert(${columns.mkString(",")})\n")
    sql = columns.map(col => s"src.${col}").mkString(",")
    builder.append(s"    VALUES ($sql)")
    builder.toString
  }
}