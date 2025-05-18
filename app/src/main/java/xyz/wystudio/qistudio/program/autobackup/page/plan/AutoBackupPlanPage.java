package xyz.wystudio.qistudio.program.autobackup.page.plan;

import android.view.View;
import android.widget.AdapterView;
import android.widget.Toast;

import com.qiplat.open.ui.widget.dialog.QiBottomListDialog;
import com.qiplat.open.ui.widget.dialog.QiBottomTextInputDialog;
import com.rapid.android.ui.widget.setting.TextArrowItemView;
import com.rapid.api.component.page.setting.SettingsPage;
import com.rapid.api.component.widget.item.ISettingGroupView;

import xyz.wystudio.qistudio.program.autobackup.key.SettingsKey;
import xyz.wystudio.qistudio.program.autobackup.page.WBasePage;

public class AutoBackupPlanPage extends WBasePage {

    String TAG = "自动备份策略";
    String BackupPath;
    ISettingGroupView planGroupView;

    @Override
    public void build(SettingsPage settingsPage) {
        BackupPath = getSharedPreferences().getString(SettingsKey.CONFIG_BACKUPPATH, "/storage/emulated/0/Tiecode/backups/");
        ISettingGroupView item1 = settingsPage.getOrAddSettingGroup("设置");
        item1.addSwitchItem("备份进度对话框", "备份时是否显示进度对话框", SettingsKey.SWITCH_DIALOG, getSettingsItem(SettingsKey.SWITCH_DIALOG));

        TextArrowItemView pathItemView = new TextArrowItemView(getActivity());
        pathItemView.setTitle("备份路径");
        pathItemView.setDescription(BackupPath);
        pathItemView.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QiBottomTextInputDialog dialog = new QiBottomTextInputDialog(getActivity());
                dialog.setTitle("请输入备份路径，以/结尾");
                dialog.setText(BackupPath);
                dialog.setPositiveButton("确定", new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        getSharedPreferences().putString(SettingsKey.CONFIG_BACKUPPATH, dialog.getText());
                        pathItemView.setDescription(dialog.getText());
                        BackupPath = dialog.getText();
                        Toast.makeText(getActivity(), "保存成功！", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
        item1.addArrowItem(pathItemView);

        TextArrowItemView fileCountItemView = new TextArrowItemView(getActivity());
        fileCountItemView.setTitle("保留备份文件数量");
        fileCountItemView.setDescription("");
        fileCountItemView.setDefaultText(getSharedPreferences().getString(SettingsKey.CONFIG_BACKUPFILECOUNT, "5"));
        fileCountItemView.setClick(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                QiBottomListDialog dialog = new QiBottomListDialog(getActivity());
                dialog.setContentItems(new String[]{"2", "3", "4", "5", "6", "7", "8", "9", "10"}, new AdapterView.OnItemClickListener() {
                    @Override
                    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                        getSharedPreferences().putString(SettingsKey.CONFIG_BACKUPFILECOUNT, String.valueOf(position + 2));
                        fileCountItemView.setDefaultText(String.valueOf(position + 2));
                        dialog.dismiss();
                    }
                });
                dialog.setTitle("选择备份文件数量");
                dialog.show();
            }
        });
        item1.addArrowItem(fileCountItemView);

        planGroupView = settingsPage.getOrAddSettingGroup("备份时机");
        addPlanItem("打开项目时", "当前项目");
        //addPlanItem("打开结绳时", "指定xxx项目");
        //addPlanItem("运行项目时", "当前项目");
        //addPlanItem("打开项目时", "当前项目");
        //addPlanItem("打包项目时", "指定 xxx,xx 2个项目");
        planGroupView.addArrowItem(">>>添加时机<<<", ">>>点我添加<<<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //AddPlanTimeDialog.getInstance(getActivity()).show();
                Toast.makeText(getActivity(), "beta版本目前只有打开项目时机", Toast.LENGTH_SHORT).show();
            }
        });

        ISettingGroupView item3 = settingsPage.getOrAddSettingGroup("教程");
        item3.addArrowItem("时机添加教程", ">>>必看<<<", new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                //PlanPageTimeHelpDialog.getInstance(getActivity()).show();
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

    private boolean getSettingsItem(String name) {
        return getSharedPreferences().getBoolean(name, true);
    }
}
