package io.github.betacatcode.backup.config;

import io.github.betacatcode.backup.entity.BackupDTO;
import io.github.betacatcode.backup.properties.BackupProperties;
import io.github.betacatcode.backup.thread.BackupThread;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.SchedulingConfigurer;
import org.springframework.scheduling.config.CronTask;
import org.springframework.scheduling.config.ScheduledTaskRegistrar;

import java.util.List;
import java.util.Objects;

@Slf4j
@Configuration
@EnableScheduling
public class AutoBackupTaskConfig implements SchedulingConfigurer {
    @Autowired
    BackupProperties backupProperties;
    
    @Override
    public void configureTasks(ScheduledTaskRegistrar taskRegistrar) {
        List<BackupDTO> backupTaskList = backupProperties.getTaskList();
        for (BackupDTO backupDTO : backupTaskList) {
            //是否启用
            if(Objects.equals(backupDTO.getEnable(),"Y")){
                String cron = backupDTO.getCron();
                BackupThread backupThread = new BackupThread(backupDTO);
                //是否程序启动时运行
                if(Objects.equals(backupDTO.getRunAtStartUp(),"Y")){
                    log.info("{}启动时运行..",backupDTO.toString());
                    backupThread.run();
                }
                //注册定时任务
                CronTask cronTask=new CronTask(backupThread,cron);
                taskRegistrar.addCronTask(cronTask);
            }
        }
    }
}
