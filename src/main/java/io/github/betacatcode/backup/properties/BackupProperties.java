package io.github.betacatcode.backup.properties;

import io.github.betacatcode.backup.entity.BackupDTO;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.util.List;

@Data
@Component
@ConfigurationProperties(prefix = "backup")
public class BackupProperties {
    private String mysqlBinPath;
    private List<BackupDTO> taskList;
}
