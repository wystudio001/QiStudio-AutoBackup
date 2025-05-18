package xyz.wystudio.qistudio.program.autobackup.backup.local.config;

import com.rapid.api.Platform;

import xyz.wystudio.qistudio.program.autobackup.key.SettingsKey;

public class LocalBackupConfig {
    public static String getBackupPath() {
        return Platform.getSharedPreferences().getString(SettingsKey.CONFIG_BACKUPPATH, "/storage/emulated/0/Tiecode/backups/");
    }

    public static boolean IsShowBackupLoadingDialog() {
        return getSettings(SettingsKey.SWITCH_DIALOG);
    }

    public static boolean IsShowCompleteTips() {
        return getSettings(SettingsKey.SWITCH_TIPS);
    }

    private static boolean getSettings(String item) {
        return Platform.getSharedPreferences().getBoolean(item, true);
    }

    public static int getBackupFileCount() {
        return Integer.parseInt(Platform.getSharedPreferences().getString(SettingsKey.CONFIG_BACKUPFILECOUNT, "5"));
    }
}
