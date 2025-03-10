package xyz.wystudio.qistudio.program.autobackup.widget.dialog.setting;

import android.content.Context;

import xyz.wystudio.qistudio.program.autobackup.widget.dialog.WBaseBottomDialog;

public class SettingPageQADialog extends WBaseBottomDialog {
    private static volatile SettingPageQADialog instance;

    private SettingPageQADialog(Context context) {
        super(context);
    }

    public static SettingPageQADialog getInstance(Context context) {
        if (instance == null) {
            synchronized (SettingPageQADialog.class) {
                if (instance == null) {
                    instance = new SettingPageQADialog(context);
                }
            }
        }
        return instance;
    }

    public void show() {
        setContent("content");
        super.show();
    }
}
