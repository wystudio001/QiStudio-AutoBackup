package xyz.wystudio.qistudio.program.autobackup.manager;

import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;
import com.rapid.api.component.widget.item.ITextSwitchItemView;
import com.rapid.api.framework.common.setting.AsSubSettingsInfo;
import com.rapid.api.framework.common.setting.BaseSettingsBuilder;

public class WSettingManager2 extends BaseSettingsBuilder {
    @Override
    public void build(SettingsPage settingsPage) {
        ISettingGroupView item1 = settingsPage.getOrAddSettingGroup("基础设置");
        item1.addSwitchItem("总开关", "", true, new ITextSwitchItemView.OnCheckedChangeListener() {
            @Override
            public void onChange(ITextSwitchItemView iTextSwitchItemView, boolean b) {

            }
        });

    }

    @Override
    public String getParentId() {
        return "ID_WAUTO_SETTINGS";
    }

    @Override
    public String getId() {
        return "ID_WAUTO_SETTINGS2";
    }

    @Override
    public AsSubSettingsInfo getAsSubSettingsInfo() {
        return new AsSubSettingsInfo("基础设置", "设置2", "配置相关设置");
    }

    @Override
    public void dispose() {

    }
}
