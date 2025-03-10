package xyz.wystudio.qistudio.program.autobackup.widget.dialog.plan;

import android.content.Context;

import xyz.wystudio.qistudio.program.autobackup.widget.dialog.WBaseBottomDialog;

public class PlanPageTimeHelpDialog extends WBaseBottomDialog {
    private static volatile PlanPageTimeHelpDialog instance;

    private PlanPageTimeHelpDialog(Context context) {
        super(context);
    }

    public static PlanPageTimeHelpDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (PlanPageTimeHelpDialog.class) {
                if (instance == null) {
                    instance = new PlanPageTimeHelpDialog(context);
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
