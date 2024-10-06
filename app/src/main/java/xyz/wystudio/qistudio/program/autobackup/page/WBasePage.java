package xyz.wystudio.qistudio.program.autobackup.page;


import android.os.Bundle;
import android.widget.LinearLayout;

import com.rapid.android.ui.app.RapidPluginPage;
import com.rapid.android.ui.widget.setting.SettingGroupView;

public class WBasePage extends RapidPluginPage {

    LinearLayout layout;
    SettingGroupView settingGroupView;

    @Override
    public void onCreate(Bundle bundle) {
        layout = new LinearLayout(this);
        setContentView(layout);

        settingGroupView = new SettingGroupView(this);
        //SettingGroupView view = new SettingGroupView(Platform.getApplicationContext());
        layout.addView(settingGroupView);
    }

    public SettingGroupView getSettingGroupView(String title) {
        settingGroupView.setTitle(title);
        return settingGroupView;
    }
}
