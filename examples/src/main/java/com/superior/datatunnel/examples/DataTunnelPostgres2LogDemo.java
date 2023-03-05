package com.superior.datatunnel.examples;

import com.superior.datatunnel.core.DataTunnelExtensions;
import org.apache.spark.sql.SparkSession;

import java.sql.SQLException;

public class DataTunnelPostgres2LogDemo {

    public static void main(String[] args) throws SQLException {
        SparkSession spark = SparkSession
                .builder()
                //.enableHiveSupport()
                .master("local")
                .appName("Iceberg spark example")
                .config("spark.sql.parquet.compression.codec", "zstd")
                .config("spark.sql.sources.partitionOverwriteMode", "dynamic")
                .config("spark.sql.extensions", DataTunnelExtensions.class.getName())
                .getOrCreate();

        String sql = "datatunnel SOURCE('gauss') OPTIONS(\n" +
                "    username='postgres',\n" +
                "    password='postgres2023',\n" +
                "    host='172.18.1.56',\n" +
                "    port=15432,\n" +
                "    databaseName='postgres'," +
                "    schema='public'," +
                "    tableName='ORDERS', columns=['*'])\n" +
                "    SINK('log') OPTIONS(numRows = 10)";

        spark.sql(sql);
    }
}
