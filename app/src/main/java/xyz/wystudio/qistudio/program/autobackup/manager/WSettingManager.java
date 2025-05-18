package xyz.wystudio.qistudio.program.autobackup.manager;

import android.content.Intent;
import android.view.View;

import com.rapid.api.Platform;
import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;
import com.rapid.api.framework.common.setting.AsSubSettingsInfo;
import com.rapid.api.framework.common.setting.BaseSettingsBuilder;

import xyz.wystudio.qistudio.program.autobackup.key.SettingsKey;
import xyz.wystudio.qistudio.program.autobackup.widget.dialog.setting.SettingPageHelpDialog;

public class WSettingManager extends BaseSettingsBuilder {
    @Override
    public void build(SettingsPage page) {
        page.getOrAddSettingGroup("当前为beta版本，仅提供本地备份");
        ISettingGroupView item1 = page.getOrAddSettingGroup("基础设置");
        item1.addSwitchItem("总开关", "设置是否开启插件", SettingsKey.SWITCH_ALL, getSettingsItem(SettingsKey.SWITCH_ALL));
        item1.addSwitchItem("自动备份", "是否开启自动备份功能，不影响手动", SettingsKey.SWITCH_AUTO, getSettingsItem(SettingsKey.SWITCH_AUTO));
        item1.addSwitchItem("弹出提示", "备份完成后是否弹出提示", SettingsKey.SWITCH_TIPS, getSettingsItem(SettingsKey.SWITCH_TIPS));


        ISettingGroupView item2 = page.getOrAddSettingGroup("策略设置");

        ISettingGroupView item3 = page.getOrAddSettingGroup("使用教程");
        item3.addArrowItem("用前必看", ">>>>必看<<<<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SettingPageHelpDialog.getInstance(getActivity()).show();
            }
        });
        item3.addArrowItem("常见问题", ">解决大多数问题<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //SettingPageQADialog.getInstance(getActivity()).show();
            }
        });

        ISettingGroupView item4 = page.getOrAddSettingGroup("关于");
        item4.addArrowItem("作者", "QQ:1519258319", "WYstudio", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Platform.getSharedPreferences().getBoolean("key", true);
            }
        });
        item4.addArrowItem("版本", "beta-0.1", "检查更新", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });


    }

    @Override
    public void onActivityResult(int arg0, int arg1, Intent arg2) {

    }
    
    @Override
    public AsSubSettingsInfo getAsSubSettingsInfo() {
        return new AsSubSettingsInfo("插件设置", "自动备份插件", "配置相关设置");
    }
    
	@Override
	public String getId() {
        return "ID_WAUTO_SETTINGS";
	}
	
    @Override
    public String getParentId() {
        return ID_ROOT;
    }
    
    @Override
    public void dispose() {

    }

    private boolean getSettingsItem(String name) {
        return getSharedPreferences().getBoolean(name, true);
    }
    
}