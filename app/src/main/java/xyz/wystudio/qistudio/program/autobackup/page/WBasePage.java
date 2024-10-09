package xyz.wystudio.qistudio.program.autobackup.page;

import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.framework.common.setting.AsSubSettingsInfo;
import com.rapid.api.framework.common.setting.BaseSettingsBuilder;

public class WBasePage extends BaseSettingsBuilder {
    @Override
    public void build(SettingsPage settingsPage) {

    }

    @Override
    public String getParentId() {
        return "ID_WAUTO_SETTINGS";
    }

    @Override
    public String getId() {
        return null;
    }

    @Override
    public AsSubSettingsInfo getAsSubSettingsInfo() {
        return new AsSubSettingsInfo("策略设置", getTAG(), "配置相关设置");
    }

    @Override
    public void dispose() {

    }

    public String getTAG() {
        return "";
    }
}
