package xyz.wystudio.qistudio.program.autobackup.activity;

import android.app.Activity;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import java.io.File;

import xyz.wystudio.qistudio.program.autobackup.R;
import xyz.wystudio.qistudio.program.autobackup.util.AppUtils;
import xyz.wystudio.qistudio.program.autobackup.util.FileUtils;

public class MainActivity extends Activity {

    Button btn_updata;
    Button btn_install;
    Button btn_start;
    Button btn_uninstall;
    TextView text_this;
    TextView text_have;

    private AppUtils appUtil;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("MainActivity", "open app!");

        try {
            appUtil = new AppUtils(getPackageManager(), getPackageManager().getPackageInfo(getPackageName(), 0));
        } catch (PackageManager.NameNotFoundException e) {
            e.printStackTrace();
        }
        wRequestPermission();

        btn_updata = findViewById(R.id.button_updata);
        btn_install = findViewById(R.id.button_install);
        btn_start = findViewById(R.id.button_start);
        btn_uninstall = findViewById(R.id.button_uninstall_this);
        text_this = findViewById(R.id.textview_tip_bottom);
        text_have = findViewById(R.id.textview_bottom_2);

        text_this.setText("当前程序所带版本：" + appUtil.getVersionName());

        btn_updata.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String path = "/sdcard/MT2/apks/备份插件_" + appUtil.getVersionName() + ".rmx";
                FileUtils.deleteFile(new File(path));

                if (FileUtils.copy(new File(appUtil.getSourceDir()), new File(path))) {
                    tips("文件移动成功！");
                } else {
                    tips("文件移动失败！");
                }
            }
        });
    }

    public void tips(String str) {
        Toast.makeText(this, str, Toast.LENGTH_SHORT).show();
    }

    public void wRequestPermission() {
        try {
            PackageManager mPackageMgr = this.getPackageManager();
            PackageInfo pack = mPackageMgr.getPackageInfo(this.getPackageName(), PackageManager.GET_PERMISSIONS);
            String[] permissions = pack.requestedPermissions;
            this.requestPermissions(permissions, 1);
        } catch (Exception e) {
            e.printStackTrace();
            Log.e("Request", e.toString());
        }
    }
}
