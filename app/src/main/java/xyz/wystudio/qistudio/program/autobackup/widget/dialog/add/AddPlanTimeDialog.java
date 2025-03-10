package xyz.wystudio.qistudio.program.autobackup.widget.dialog.add;

import android.content.Context;

import xyz.wystudio.qistudio.program.autobackup.widget.dialog.WBaseBottomDialog;

public class AddPlanTimeDialog extends WBaseBottomDialog {
    private static volatile AddPlanTimeDialog instance;

    private AddPlanTimeDialog(Context context) {
        super(context);
    }

    public static AddPlanTimeDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (AddPlanTimeDialog.class) {
                if (instance == null) {
                    instance = new AddPlanTimeDialog(context);
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
