package xyz.wystudio.qistudio.program.autobackup.backup.local;

import android.widget.Toast;

import com.rapid.api.Platform;
import com.rapid.api.project.Project;
import com.rapid.api.project.ProjectManager;

import java.io.File;

import xyz.wystudio.qistudio.program.autobackup.backup.local.config.LocalBackupConfig;
import xyz.wystudio.qistudio.program.autobackup.util.FileUtils;
import xyz.wystudio.qistudio.program.autobackup.util.LogUtils;
import xyz.wystudio.qistudio.program.autobackup.widget.dialog.backup.BackupProcessDialog;

public class LocalBackup {
    public static void backup(Project project, ProjectManager<Project> projectManager) {
        String projectName = project.getName();
        LogUtils.writeBackupLog("开始备份项目 - " + projectName);
        if (LocalBackupConfig.IsShowBackupLoadingDialog()) {
            runOnUI(new Runnable() {
                @Override
                public void run() {
                    BackupProcessDialog.show();
                }
            });
        }

        String backupFilePath = projectManager.backup(project);
        LogUtils.writeBackupLog("开始移动备份文件");
        copyAndReNameBackupFile(backupFilePath, projectName);
        LogUtils.writeBackupLog("移动文件成功");
        if (LocalBackupConfig.IsShowBackupLoadingDialog()) {
            runOnUI(new Runnable() {
                @Override
                public void run() {
                    BackupProcessDialog.dismiss();
                }
            });
        }
        if (LocalBackupConfig.IsShowCompleteTips()) {
            runOnUI(new Runnable() {
                @Override
                public void run() {
                    Toast.makeText(Platform.getActivity(), "自动备份成功", Toast.LENGTH_SHORT).show();
                }
            });
        }
        LogUtils.writeBackupLog("自动备份成功！");
    }

    private static void copyAndReNameBackupFile(String backupFilePath, String projectName) {
        String BackupPath = LocalBackupConfig.getBackupPath() + projectName + File.separator;
        if (!new File(BackupPath).exists()) {
            LogUtils.writeBackupLog("第一次备份");
            new File(BackupPath).mkdirs();
            FileUtils.renameFile(backupFilePath, BackupPath + projectName + "_1.tsp");
        } else {
            int fileCount = scanFilesByPath(BackupPath, projectName);
            LogUtils.writeBackupLog("已有备份文件：" + fileCount + " 最大备份文件限制：" + LocalBackupConfig.getBackupFileCount());
            if (fileCount >= LocalBackupConfig.getBackupFileCount()) {
                LogUtils.writeBackupLog("已达到最大文件数量，开始改名");
                if (fileCount > LocalBackupConfig.getBackupFileCount()) {
                    int i;
                    for (i = 1; i <= fileCount - LocalBackupConfig.getBackupFileCount() + 1; i++) {
                        FileUtils.deleteFile(new File(BackupPath + projectName + "_" + i + ".tsp"));
                    }

                    for (int j = i + 1; j <= fileCount; j++) {
                        FileUtils.renameTo(BackupPath + projectName + "_" + j + ".tsp", BackupPath + projectName + "_" + (j - i) + ".tsp");
                    }
                } else {
                    FileUtils.deleteFile(new File(BackupPath + projectName + "_1.tsp"));
                    for (int i = 2; i <= LocalBackupConfig.getBackupFileCount(); i++) {
                        FileUtils.renameTo(BackupPath + projectName + "_" + i + ".tsp", BackupPath + projectName + "_" + (i - 1) + ".tsp");
                    }
                }

                FileUtils.renameFile(backupFilePath, BackupPath + projectName + "_" + LocalBackupConfig.getBackupFileCount() + ".tsp");
            } else {
                LogUtils.writeBackupLog("未达到最大文件数量");
                FileUtils.renameFile(backupFilePath, BackupPath + projectName + "_" + (fileCount + 1) + ".tsp");
            }
        }
    }

    private static int scanFilesByPath(String path, String fileName) {
        int count = 0;
        File[] files = new File(path).listFiles();
        if (files != null) {
            for (File file : files) {
                if (file.getName().contains(fileName)) {
                    count++;
                }
            }
        }
        return count;
    }

    private static void runOnUI(Runnable runnable) {
        Platform.runUI(runnable);
    }

}
