package xyz.wystudio.qistudio.program.autobackup.page.plan;

import com.rapid.api.component.page.setting.SettingsPage;

import xyz.wystudio.qistudio.program.autobackup.page.WBasePage;

public class CloudBackupPlanPage extends WBasePage {

    String TAG = "云备份策略";

    @Override
    public void build(SettingsPage settingsPage) {
        super.build(settingsPage);
    }

    @Override
    public String getId() {
        return "ID_WAUTO_SETTINGS_ClOUDPLAN";
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
