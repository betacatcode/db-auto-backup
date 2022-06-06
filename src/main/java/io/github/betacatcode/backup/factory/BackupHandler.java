package io.github.betacatcode.backup.factory;

import io.github.betacatcode.backup.entity.BackupDTO;

public interface BackupHandler {
    void backup(BackupDTO backupDTO);
}
