package xyz.wystudio.qistudio.program.autobackup.plan.impl;

import com.rapid.api.project.Project;
import com.rapid.api.project.ProjectManager;

import xyz.wystudio.qistudio.program.autobackup.backup.local.LocalBackup;
import xyz.wystudio.qistudio.program.autobackup.plan.PlanTime;

public class PlanTimeImpl {
    private static PlanTimeImpl instance;

    public static PlanTimeImpl getInstance() {
        if (instance == null) {
            instance = new PlanTimeImpl();
        }
        return instance;
    }

    public void handleEvent(String planTime, Project project, ProjectManager<Project> projectManager) {
        switch (planTime) {
            case PlanTime.PROJECT_OPEN:
                handleProjectOpen(project, projectManager);
            case PlanTime.PROJECT_CLOSE:
                handleProjectClose(project, projectManager);
        }
    }

    private void handleProjectOpen(Project project, ProjectManager<Project> projectManager) {
        LocalBackup.backup(project, projectManager);
    }

    private void handleProjectClose(Project project, ProjectManager<Project> projectManager) {

    }
}
