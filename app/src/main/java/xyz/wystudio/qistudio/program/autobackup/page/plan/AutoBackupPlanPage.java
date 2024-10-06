package xyz.wystudio.qistudio.program.autobackup.page.plan;

import android.os.Bundle;

import com.rapid.android.ui.widget.setting.SettingGroupView;

import xyz.wystudio.qistudio.program.autobackup.page.WBasePage;

public class AutoBackupPlanPage extends WBasePage {
    @Override
    public void onCreate(Bundle bundle) {
        super.onCreate(bundle);
        //setTitle("自动备份策略");
        SettingGroupView item1 = getSettingGroupView("基础设置");
        item1.addSwitchItem("测试", "", true);
    }
}
