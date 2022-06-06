package io.github.betacatcode.backup.constant;

public class Command {
    public static final String MYSQL_COMMAND = "mysqldump -h#{ip} -u#{username} -p#{password} --databases #{db}  -r #{file}";
    public static final String ORACLE_COMMAND = "expdp #{username}/#{password}@#{db} directory=#{location} dumpfile=#{dump} tablespaces=#{tablespace} logfile=#{log}";
}
