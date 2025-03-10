package xyz.wystudio.qistudio.program.autobackup.page.plan;

import android.view.View;

import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;

import xyz.wystudio.qistudio.program.autobackup.key.SettingsKey;
import xyz.wystudio.qistudio.program.autobackup.page.WBasePage;
import xyz.wystudio.qistudio.program.autobackup.widget.dialog.add.AddPlanTimeDialog;
import xyz.wystudio.qistudio.program.autobackup.widget.dialog.plan.PlanPageTimeHelpDialog;

public class AutoBackupPlanPage extends WBasePage {

    String TAG = "自动备份策略";
    ISettingGroupView planGroupView;

    @Override
    public void build(SettingsPage settingsPage) {
        ISettingGroupView item1 = settingsPage.getOrAddSettingGroup("对话框");
        item1.addSwitchItem("备份进度对话框", "备份时是否显示进度对话框", SettingsKey.SWITCH_DIALOG, true);

        planGroupView = settingsPage.getOrAddSettingGroup("备份时机");
        addPlanItem("打开项目时", "当前项目");
        addPlanItem("打开结绳时", "指定xxx项目");
        addPlanItem("运行项目时", "当前项目");
        addPlanItem("打开项目时", "当前项目");
        addPlanItem("打包项目时", "指定 xxx,xx 2个项目");
        planGroupView.addArrowItem(">>>添加时机<<<", ">>>点我添加<<<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                AddPlanTimeDialog.getInstance(getActivity()).show();
            }
        });

        ISettingGroupView item3 = settingsPage.getOrAddSettingGroup("教程");
        item3.addArrowItem("时机添加教程", ">>>必看<<<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PlanPageTimeHelpDialog.getInstance(getActivity()).show();
            }
        });

    }

    public void addPlanItem(String time, String programs) {
        planGroupView.addArrowItem(time, "项目：" + programs, "修改", new View.OnClickListener() {
            @Override
            public void onClick(View view) {

            }
        });
    }

    @Override
    public String getId() {
        return "ID_WAUTO_SETTINGS_PLAN";
    }

    @Override
    public String getTAG() {
        return TAG;
    }
}
