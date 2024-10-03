package xyz.wystudio.qistudio.program.autobackup;

import android.widget.Toast;

import com.rapid.framework.program.android.DvmPluginDescriptor;
import com.rapid.framework.program.android.app.AndroidPlugin;


public class App extends AndroidPlugin {
    
    @Override
    public void onInit(DvmPluginDescriptor descriptor) {
        super.onInit(descriptor);
    }

    @Override
    public void onInstall() {
        Toast.makeText(getApplicationContext(),"备份插件安装成功",Toast.LENGTH_LONG).show();
    }

    @Override
    public void onUninstall() {

    }
}
