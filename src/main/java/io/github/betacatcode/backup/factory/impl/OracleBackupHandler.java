package io.github.betacatcode.backup.factory.impl;

import cn.hutool.core.date.DateUtil;
import io.github.betacatcode.backup.constant.Command;
import io.github.betacatcode.backup.entity.BackupDTO;
import io.github.betacatcode.backup.factory.BackupHandler;
import lombok.extern.slf4j.Slf4j;

import java.io.File;
import java.io.IOException;
import java.util.Date;

@Slf4j
public class OracleBackupHandler implements BackupHandler {
    @Override
    public void backup(BackupDTO backupDTO) {
        log.info("开始执行{}的定时任务..", backupDTO.toString());
        String dumpFile = DateUtil.format(new Date(), "yyyyMMdd_HHmmss") + "_" + backupDTO.getTableSpace()+ ".dump";
        String logFile = DateUtil.format(new Date(), "yyyyMMdd_HHmmss") + "_" + backupDTO.getTableSpace()+ ".log";
        String command = Command.ORACLE_COMMAND
                .replaceAll("#\\{username}", backupDTO.getUsername())
                .replaceAll("#\\{password}", backupDTO.getPassword())

                .replaceAll("#\\{db}", backupDTO.getDb())
                .replaceAll("#\\{location}", backupDTO.getLocation())
                .replaceAll("#\\{dump}", dumpFile)
                .replaceAll("#\\{log}", logFile)
                .replaceAll("#\\{tablespace}", backupDTO.getTableSpace());

        log.info(command);
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("备份结束..");
    }
}
