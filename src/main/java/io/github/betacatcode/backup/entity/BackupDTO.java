package io.github.betacatcode.backup.entity;

import lombok.Data;

@Data
public class BackupDTO {
    private String type;
    private String ip;
    private String db;
    private String username;
    private String password;
    private String tableSpace;
    private String location;
    private String cron;
    private String enable;
    private String runAtStartUp;
}
