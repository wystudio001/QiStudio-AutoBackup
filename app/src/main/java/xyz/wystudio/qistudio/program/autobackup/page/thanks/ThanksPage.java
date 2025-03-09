package xyz.wystudio.qistudio.program.autobackup.page.thanks;

import android.view.View;

import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;
import com.rapid.api.framework.common.setting.AsSubSettingsInfo;

import xyz.wystudio.qistudio.program.autobackup.page.WBasePage;

public class ThanksPage extends WBasePage {
    String TAG = "特别鸣谢";
    ISettingGroupView thanksListItem;

    @Override
    public void build(SettingsPage settingsPage) {
        thanksListItem = settingsPage.getOrAddSettingGroup("鸣谢列表");

        addThanksListItem("移动IPE联合成员组", "感谢成员组开发的平台", "");
        addThanksListItem("Scave", "在插件开发中提供帮助", "");
        addThanksListItem("OkHttp", "", "");
        addThanksListItem("OkHttps", "//github.com/troyzhxu/okhttps", "troyzhxu");
        addThanksListItem("hutool", "//github.com/dromara/hutool", "dromara");
    }

    public void addThanksListItem(String name, String message, String author) {
        thanksListItem.addArrowItem(name, message, author, new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public String getId() {
        return "ID_WAUTO_SETTING_PAGE_THANKS";
    }

    @Override
    public String getParentId() {
        return "ID_WAUTO_SETTINGS";
    }

    @Override
    public AsSubSettingsInfo getAsSubSettingsInfo() {
        return new AsSubSettingsInfo("关于", getTAG(), "查看特别鸣谢列表");
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
