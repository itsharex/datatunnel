package com.dataworker.datax.sftp;

import com.dataworker.datax.api.DataxWriter;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;
import org.apache.spark.sql.SparkSession;

import java.util.Map;

/**
 * @author melin 2021/7/27 11:06 上午
 */
public class SftpWriter implements DataxWriter {

    @Override
    public void validateOptions(Map<String, String> options) {

    }

    @Override
    public void write(SparkSession sparkSession, Dataset<Row> dataset, Map<String, String> options) {

    }
}