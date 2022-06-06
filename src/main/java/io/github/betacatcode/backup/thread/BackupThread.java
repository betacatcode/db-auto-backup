package io.github.betacatcode.backup.thread;


import io.github.betacatcode.backup.entity.BackupDTO;
import io.github.betacatcode.backup.factory.BackUpHandlerFactory;
import io.github.betacatcode.backup.factory.BackupHandler;

public class BackupThread implements Runnable{

    BackupDTO backupDTO;

    public BackupThread() {
    }

    public BackupThread(BackupDTO backupDTO) {
        this.backupDTO = backupDTO;
    }

    @Override
    public void run() {
        BackupHandler backUpHandler = BackUpHandlerFactory.getBackUpHandler(backupDTO);
        backUpHandler.backup(backupDTO);
    }
}
