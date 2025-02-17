package com.superior.datatunnel.examples.oracle

import com.superior.datatunnel.core.DataTunnelExtensions
import org.apache.spark.sql.SparkSession

object OracleUpsertTest {

    @JvmStatic
    fun main(args: Array<String>) {
        System.setProperty("HADOOP_USER_NAME", "hdfs");

        val spark = SparkSession
            .builder()
            .master("local")
            .enableHiveSupport()
            .appName("Datatunnel spark example")
            .config("spark.sql.extensions", DataTunnelExtensions::class.java.name)
            .getOrCreate()

        val sql1 = """
            DATATUNNEL SOURCE("postgresql") OPTIONS (
              username = "postgres",
              password = "postgres2023",
              host = '172.18.1.56',
              port = 5432,
              databaseName = 'postgres',
              schemaName = 'public',
              tableName = 'pg_orders',
              columns = ["*"],
              condition = "id < 300000"
              )
            SINK("oracle") OPTIONS (
              username = "system",
              password = "system",
              jdbcUrl = 'jdbc:oracle:thin:@172.18.1.51:1523:XE',
              schemaName = 'FLINKUSER',
              tableName = 'PG_ORDERS',
              truncate = true,
              columns = ["*"],
              writeMode = 'upsert'
            )
        """.trimIndent()

        spark.sql(sql1)
    }
}