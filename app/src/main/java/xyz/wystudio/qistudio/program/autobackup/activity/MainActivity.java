package xyz.wystudio.qistudio.program.autobackup.activity;

import android.app.Activity;
import android.os.Bundle;
import android.util.Log;

import xyz.wystudio.qistudio.program.autobackup.R;

public class MainActivity extends Activity {
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.i("App", "MainActivity被启动！");
    }
}
