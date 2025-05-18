package xyz.wystudio.qistudio.program.autobackup.widget.dialog.setting;

import android.content.Context;

import xyz.wystudio.qistudio.program.autobackup.widget.dialog.WBaseBottomDialog;

public class SettingPageHelpDialog extends WBaseBottomDialog {
    private static volatile SettingPageHelpDialog instance;

    private SettingPageHelpDialog(Context context) {
        super(context);
    }

    public static SettingPageHelpDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (SettingPageHelpDialog.class) {
                if (instance == null) {
                    instance = new SettingPageHelpDialog(context);
                }
            }
        }
        return instance;
    }

    public void show() {
        setContent("当前版本为beta版本，仅提供本地备份");
        super.show();
    }
}
