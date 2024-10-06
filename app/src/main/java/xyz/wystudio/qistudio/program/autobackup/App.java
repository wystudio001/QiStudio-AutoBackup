package xyz.wystudio.qistudio.program.autobackup;

import android.content.res.AssetManager;

import com.rapid.api.Platform;
import com.rapid.api.framework.ServiceKeys;
import com.rapid.framework.program.android.DvmPluginDescriptor;
import com.rapid.framework.program.android.app.AndroidPlugin;

import xyz.wystudio.qistudio.program.autobackup.manager.WSettingManager2;


public class App extends AndroidPlugin {

    public static AssetManager assetManager;

    @Override
    public void onInit(DvmPluginDescriptor descriptor) {
        super.onInit(descriptor);
        assetManager = descriptor.getAssetManager();

        Platform.getService(ServiceKeys.SETTINGS).add(new WSettingManager2());
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
        return Platform.getSystemPath().getLoggingPath() + "/xyz.wystudio.qistudio.program.autobackup/";
    }
}
