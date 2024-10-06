package xyz.wystudio.qistudio.program.autobackup.util;

import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;

import xyz.wystudio.qistudio.program.autobackup.App;

public class LogUtils {
    private static File file_log_backup;
    private static File file_log_error;
    private static File file_log_main;

    public static void init() {
        file_log_backup = new File(App.getPluginPath() + "log_backup.txt");
        file_log_error = new File(App.getPluginPath() + "log_error.txt");
        file_log_main = new File(App.getPluginPath() + "log_main.txt");
        deleteMainLog();
        deleteBackupLog();
        deleteErrorLog();
    }

    public static void writeBackupLog(String log) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date_now = "[" + formatter.format(date) + "]";
        String log_y = date_now + " " + log + '\n';
        if (!file_log_backup.exists()) {
            FileUtils.createFile(file_log_backup);
            try {
                FileUtils.addString(file_log_backup, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.addString(file_log_backup, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeMainLog(String log) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date_now = "[" + formatter.format(date) + "]";
        String log_y = date_now + " " + log + '\n';
        if (!file_log_main.exists()) {
            FileUtils.createFile(file_log_main);
            try {
                FileUtils.addString(file_log_main, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.addString(file_log_main, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void writeErrorLog(String log) {
        Date date = new Date();
        SimpleDateFormat formatter = new SimpleDateFormat("yyy-MM-dd HH:mm:ss");
        String date_now = "[" + formatter.format(date) + "]";
        String log_y = date_now + " " + log + '\n';
        if (!file_log_error.exists()) {
            FileUtils.createFile(file_log_error);
            try {
                FileUtils.addString(file_log_error, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        } else {
            try {
                FileUtils.addString(file_log_error, log_y);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void deleteErrorLog() {
        if (file_log_error.exists()) {
            FileUtils.deleteFile(file_log_error);
        }
    }

    public static void deleteBackupLog() {
        if (file_log_backup.exists()) {
            FileUtils.deleteFile(file_log_backup);
        }
    }

    public static void deleteMainLog() {
        if (file_log_main.exists()) {
            FileUtils.deleteFile(file_log_main);
        }
    }
}