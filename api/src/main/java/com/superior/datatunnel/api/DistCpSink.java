package com.superior.datatunnel.api;

import com.gitee.melin.bee.core.extension.SPI;
import com.superior.datatunnel.api.model.DistCpBaseSinkOption;
import org.apache.spark.sql.Dataset;
import org.apache.spark.sql.Row;

import java.io.IOException;
import java.io.Serializable;
import java.util.Collections;
import java.util.Set;

/**
 * @author melin 2021/7/27 10:47 上午
 */
@SPI
public interface DistCpSink extends Serializable {

    void sink(Dataset<Row> dataset, DistCpContext context) throws IOException;

    Class<? extends DistCpBaseSinkOption> getOptionClass();

    default Set<String> optionalOptions() {
        return Collections.emptySet();
    }
}
