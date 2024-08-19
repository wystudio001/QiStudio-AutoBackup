package xyz.wystudio.qistudio.program.autobackup.manager;

import android.content.Intent;

import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;
import com.rapid.api.framework.common.setting.AsSubSettingsInfo;
import com.rapid.api.framework.common.setting.BaseSettingsBuilder;

public class WSettingManager extends BaseSettingsBuilder {
	@Override
	public void onActivityResult(int arg0, int arg1, Intent arg2) {
	    // TODO: Implement this method
	}
    
    @Override
    public void build(SettingsPage page) {
        ISettingGroupView item1 = page.getOrAddSettingGroup("基础设置");
        item1.addSwitchItem("标题", "描述", "itemKey1", true);
    }
    
    @Override
    public AsSubSettingsInfo getAsSubSettingsInfo() {
        // TODO: Implement this method
        return new AsSubSettingsInfo("1","22","333");
    }
    
	@Override
	public String getId() {
	    // TODO: Implement this method
        return "setting-page";
	}
	
    @Override
    public String getParentId() {
        return ID_ROOT;
    }
    
    @Override
    public void dispose() {
        // TODO: Implement this method
    }
    
}