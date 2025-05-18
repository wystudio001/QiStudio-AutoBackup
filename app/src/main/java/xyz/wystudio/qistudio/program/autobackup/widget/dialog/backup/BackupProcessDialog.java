package xyz.wystudio.qistudio.program.autobackup.widget.dialog.backup;

import com.qiplat.open.ui.widget.dialog.QiLoadingDialog;
import com.rapid.api.Platform;


public class BackupProcessDialog {
    private static QiLoadingDialog dialog;

    public static void show() {
        dialog = new QiLoadingDialog(Platform.getActivity());
        dialog.setTipText("正在进行自动备份中....");
        dialog.show();
    }

    public static void dismiss() {
        if (dialog != null) {
            dialog.dismiss();
        }
    }
}
