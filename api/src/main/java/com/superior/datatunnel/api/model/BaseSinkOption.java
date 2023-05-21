package com.superior.datatunnel.api.model;

import com.google.common.collect.Maps;
import com.superior.datatunnel.api.DataSourceType;
import lombok.Data;

import java.util.Map;

@Data
public class BaseSinkOption implements DataTunnelSinkOption {

    private DataSourceType dataSourceType;

    private final Map<String, String> properties = Maps.newHashMap();
}