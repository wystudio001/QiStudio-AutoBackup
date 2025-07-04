package xyz.wystudio.qistudio.program.autobackup.widget.dialog.add;

import android.app.AlertDialog;
import android.content.ClipboardManager;
import android.content.Context;

import android.content.DialogInterface;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Spinner;
import android.widget.RadioGroup;
import android.widget.RadioButton;
import android.widget.AdapterView;

public class AddPlanTimeDialog {
    private static volatile AddPlanTimeDialog instance;
    
    private Context context;
    private static AlertDialog dialog;
    private static AlertDialog.Builder builder;

    private AddPlanTimeDialog(Context context) {
        super();
        builder = new AlertDialog.Builder(context);
        this.context = context;
    }

    public static AddPlanTimeDialog getInstance(Context context) {
        if (instance == null) {
            synchronized (AddPlanTimeDialog.class) {
                if (instance == null) {
                    instance = new AddPlanTimeDialog(context);
                }
            }
        }
        return instance;
    }

    public void show() {
        builder.setTitle("测试");
        
        try {
        	//layout();
            layout2();
        } catch(Exception err) {
        	err.printStackTrace();
            Log.e("Dialog",err.toString());
            ClipboardManager clipboard = (ClipboardManager) context.getSystemService("clipboard");
		    clipboard.setText(err.toString());
        }
        
        builder.setPositiveButton("添加",new DialogInterface.OnClickListener(){
            @Override
			public void onClick(DialogInterface p1, int p2) {
				
			}
        });
        builder.setNegativeButton("取消",new DialogInterface.OnClickListener(){
            @Override
			public void onClick(DialogInterface p1, int p2) {
				
			}
        });
        
        if(dialog == null) {
			dialog = builder.create();
		}
		dialog.show();
    }
    
    public void dismiss(){
        dialog.dismiss();
        instance = null;
        dialog = null;
    }
    
    public void layout(){
         // 创建根布局，垂直排列 
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setLayoutParams(new ViewGroup.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        
        // 第一部分：选择项目（多选下拉列表）
        LinearLayout.LayoutParams textViewParams = new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        );
        textViewParams.setMargins(10, 30, 10, 10); // 设置左右上下边距 
        
        // 创建TextView“选择项目”
        TextView textViewProject = new TextView(context);
        textViewProject.setText("选择项目");
        textViewProject.setTextSize(15);
        textViewProject.setLayoutParams(textViewParams);
        
        // 创建Spinner 
        Spinner spinnerProject = new Spinner(context);
        spinnerProject.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        
        // 为Spinner设置适配器 
        String[] itemsProject = {"项目1", "项目2", "项目3"};
        ArrayAdapter<String> adapterProject = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, itemsProject);
        adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerProject.setAdapter(adapterProject);
        
        // 将TextView和Spinner添加到LinearLayout 
        LinearLayout layoutProject = new LinearLayout(context);
        layoutProject.setOrientation(LinearLayout.HORIZONTAL);
        layoutProject.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        layoutProject.addView(textViewProject);
        layoutProject.addView(spinnerProject);
        
        // 第二部分：选择时机（单选下拉列表）
        TextView textViewTiming = new TextView(context);
        textViewTiming.setText("选择时机");
        textViewTiming.setTextSize(15);
        textViewTiming.setLayoutParams(textViewParams);
        
        Spinner spinnerTiming = new Spinner(context);
        spinnerTiming.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        
        String[] itemsTiming = {"时机1", "时机2", "时机3"};
        ArrayAdapter<String> adapterTiming = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, itemsTiming);
        adapterTiming.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerTiming.setAdapter(adapterTiming);
        
        LinearLayout layoutTiming = new LinearLayout(context);
        layoutTiming.setOrientation(LinearLayout.HORIZONTAL);
        layoutTiming.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        layoutTiming.addView(textViewTiming);
        layoutTiming.addView(spinnerTiming);
        
        // 第三部分：备份方式 
        TextView textViewBackup = new TextView(context);
        textViewBackup.setText("备份方式");
        textViewBackup.setTextSize(15);
        textViewBackup.setLayoutParams(textViewParams);
        
        // 第四部分：单选按钮组 
        RadioGroup radioGroup = new RadioGroup(context);
        radioGroup.setLayoutParams(new LinearLayout.LayoutParams(
                ViewGroup.LayoutParams.MATCH_PARENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        radioGroup.setOrientation(RadioGroup.HORIZONTAL); // 水平排列 
        
        RadioButton radioButton1 = new RadioButton(context);
        radioButton1.setText("蓝奏云备份");
        radioButton1.setLayoutParams(new RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        radioButton1.setPadding(10, 30, 10, 10); // 设置内边距 
        
        RadioButton radioButton2 = new RadioButton(context);
        radioButton2.setText("本地备份");
        radioButton2.setLayoutParams(new RadioGroup.LayoutParams(
                ViewGroup.LayoutParams.WRAP_CONTENT,
                ViewGroup.LayoutParams.WRAP_CONTENT 
        ));
        radioButton2.setPadding(10, 30, 10, 10);
        
        radioGroup.addView(radioButton1);
        radioGroup.addView(radioButton2);
        
        // 将所有部分添加到根布局 
        rootLayout.addView(layoutProject);
        rootLayout.addView(layoutTiming);
        rootLayout.addView(textViewBackup);
        rootLayout.addView(radioGroup);
        
        // 设置自定义布局到AlertDialog.Builder 
        builder.setView(rootLayout);
    }
    
    public void layout2(){
        LinearLayout rootLayout = new LinearLayout(context);
        rootLayout.setOrientation(LinearLayout.VERTICAL);
        rootLayout.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));
        setViewHeight(rootLayout,-1);
        setViewWidth(rootLayout,-1);
        
        LinearLayout linearLayoutChooseProject = new LinearLayout(context);
        linearLayoutChooseProject.setOrientation(LinearLayout.HORIZONTAL);
        linearLayoutChooseProject.setLayoutParams(new ViewGroup.LayoutParams(-2,-2));
        setViewHeightByDP(linearLayoutChooseProject,50);
        setViewWidth(linearLayoutChooseProject,-1);
        
        TextView textViewChooseProject = new TextView(context);
        textViewChooseProject.setText("选择项目");
        textViewChooseProject.setGravity(17);
        textViewChooseProject.setTextSize(17);
        textViewChooseProject.setTextColor(0XFF000000);
        textViewChooseProject.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));
        setLinearLayoutViewQuan(textViewChooseProject,0.1f);
        setViewHeight(textViewChooseProject,-1);
        
        Spinner spinnerChooseProject = new Spinner(context);
        // 为Spinner设置适配器 
        String[] itemsProject = {"项目1", "项目2", "项目3"};
        ArrayAdapter<String> adapterProject = new ArrayAdapter<>(context, android.R.layout.simple_spinner_item, itemsProject);
        adapterProject.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinnerChooseProject.setAdapter(adapterProject);
        spinnerChooseProject.setLayoutParams(new LinearLayout.LayoutParams(-2,-2));
        setViewHeight(spinnerChooseProject,-1);
        setLinearLayoutViewQuan(spinnerChooseProject,0.5f);
        
        linearLayoutChooseProject.addView(textViewChooseProject);
        linearLayoutChooseProject.addView(spinnerChooseProject);
        
        rootLayout.addView(linearLayoutChooseProject);
        builder.setView(rootLayout);
        
    }
    
    public void setLinearLayoutViewQuan(View v,float key){
        LinearLayout.LayoutParams params = (LinearLayout.LayoutParams) v.getLayoutParams();
		params.weight = key;
		v.setLayoutParams(params);
    }
    
    public void setViewWidth(View view,int width){
        ViewGroup.LayoutParams params = view.getLayoutParams();
		if (params == null) {
			return;
		}
		params.width = width;
		view.setLayoutParams(params);
    }
    
    public void setViewHeight(View view,int height){
        ViewGroup.LayoutParams params = view.getLayoutParams();
		if (params == null) {
			return;
		}
		params.height = height;
		view.setLayoutParams(params);
    }
    
    public void setViewWidthByDP(View view,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        int width = (int) (dp * scale + 0.5f);
        setViewWidth(view,width);
    }

    public void setViewHeightByDP(View view,int dp){
        float scale = context.getResources().getDisplayMetrics().density;
        int height = (int) (dp * scale + 0.5f);
        setViewWidth(view,height);
    }
}
