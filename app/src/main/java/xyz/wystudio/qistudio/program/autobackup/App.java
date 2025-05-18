package xyz.wystudio.qistudio.program.autobackup;

import android.content.res.AssetManager;

import com.rapid.api.Platform;
import com.rapid.api.ext.file.PublicPath;
import com.rapid.framework.program.android.DvmPluginDescriptor;
import com.rapid.framework.program.android.app.AndroidPlugin;

import xyz.wystudio.qistudio.program.autobackup.util.LogUtils;


public class App extends AndroidPlugin {

    public static AssetManager assetManager;

    @Override
    public void onInit(DvmPluginDescriptor descriptor) {
        super.onInit(descriptor);
        assetManager = descriptor.getAssetManager();
        LogUtils.init();
        LogUtils.writeMainLog("插件被启动");

        /*
        Platform.getService(ServiceKeys.SETTINGS).add(new AutoBackupPlanPage());
        Platform.getService(ServiceKeys.SETTINGS).add(new CloudBackupPlanPage());
        Platform.getService(ServiceKeys.SETTINGS).add(new ThanksPage());
        */
    }

    @Override
    public void onInstall() {
        /*
        Platform.getActivity().runOnUiThread(new Runnable() {
            @Override
            public void run() {
                Toast.makeText(getApplicationContext(),"备份插件安装成功",Toast.LENGTH_LONG).show();
            }
        });
         */
    }

    @Override
    public void onUninstall() {

    }


    public static String getPluginPath() {
        return Platform.getSystemPath().getPublicPath(PublicPath.PROGRAMS) + "/xyz.wystudio.qistudio.program.autobackup/";
    }
}
