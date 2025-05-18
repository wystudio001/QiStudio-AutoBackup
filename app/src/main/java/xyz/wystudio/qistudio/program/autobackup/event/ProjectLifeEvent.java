package xyz.wystudio.qistudio.program.autobackup.event;

import com.rapid.api.Platform;
import com.rapid.api.framework.project.primary.ProjectLifecycleEvent;
import com.rapid.api.project.Project;
import com.rapid.api.project.ProjectManager;

import xyz.wystudio.qistudio.program.autobackup.key.SettingsKey;
import xyz.wystudio.qistudio.program.autobackup.plan.PlanTime;
import xyz.wystudio.qistudio.program.autobackup.plan.impl.PlanTimeImpl;
import xyz.wystudio.qistudio.program.autobackup.util.LogUtils;

public class ProjectLifeEvent implements ProjectLifecycleEvent {
    @Override
    public void beforeLoadingProject(Project project, ProjectManager<?> projectManager) {

    }

    @Override
    public void afterLoadingProject(Project project, ProjectManager<?> projectManager) {
        if (Platform.getSharedPreferences().getBoolean(SettingsKey.SWITCH_ALL, true) && Platform.getSharedPreferences().getBoolean(SettingsKey.SWITCH_AUTO, true)) {
            LogUtils.writeMainLog(project.getName() + " 项目被打开");
            PlanTimeImpl.getInstance().handleEvent(PlanTime.PROJECT_OPEN, project, (ProjectManager<Project>) projectManager);
        }
    }

    @Override
    public void onCloseProject(Project project, ProjectManager<?> projectManager) {
        if (Platform.getSharedPreferences().getBoolean(SettingsKey.SWITCH_ALL, true) && Platform.getSharedPreferences().getBoolean(SettingsKey.SWITCH_AUTO, true)) {
            LogUtils.writeMainLog(project.getName() + " 项目被关闭");
            PlanTimeImpl.getInstance().handleEvent(PlanTime.PROJECT_CLOSE, project, (ProjectManager<Project>) projectManager);
        }
    }
}
