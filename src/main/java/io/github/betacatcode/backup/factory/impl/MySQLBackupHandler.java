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
public class MySQLBackupHandler implements BackupHandler {

    @Override
    public void backup(BackupDTO backupDTO) {
        log.info("开始执行{}的定时任务..", backupDTO.toString());
        File dir = new File(backupDTO.getLocation());
        if (!dir.exists()) {
            log.info("目录不存在，创建目录..");
            dir.mkdirs();
        }

        String file = backupDTO.getLocation() + File.separator + DateUtil.format(new Date(), "yyyyMMdd_HHmmss") + "_" + backupDTO.getDb() + ".sql";
        file = file.replaceAll("\\\\", "\\\\\\\\");
        String command = Command.MYSQL_COMMAND
                .replaceAll("#\\{ip}", backupDTO.getIp())
                .replaceAll("#\\{username}", backupDTO.getUsername())

                .replaceAll("#\\{password}", backupDTO.getPassword())
                .replaceAll("#\\{db}", backupDTO.getDb())
                .replaceAll("#\\{file}", file);
        log.info(command);
        try {
            Runtime.getRuntime().exec(command);
        } catch (IOException e) {
            e.printStackTrace();
        }
        log.info("备份结束..");

    }
}
