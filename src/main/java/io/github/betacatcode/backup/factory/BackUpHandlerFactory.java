package io.github.betacatcode.backup.factory;

import io.github.betacatcode.backup.entity.BackupDTO;
import io.github.betacatcode.backup.factory.impl.MySQLBackupHandler;
import io.github.betacatcode.backup.factory.impl.OracleBackupHandler;

import java.util.Objects;

public class BackUpHandlerFactory {
    public static BackupHandler getBackUpHandler(BackupDTO backupDTO){
        String type = backupDTO.getType();
        if(Objects.equals(type,"MySQL")) return new MySQLBackupHandler();
        if(Objects.equals(type,"Oracle")) return new OracleBackupHandler();
        throw new RuntimeException("不存在的Handler类型");
    }
}
