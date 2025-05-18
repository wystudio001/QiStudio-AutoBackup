package xyz.wystudio.qistudio.program.autobackup.plan.impl;

public class CloudPlanImpl {
    private static CloudPlanImpl instance;

    public static CloudPlanImpl getInstance() {
        if (instance == null) {
            instance = new CloudPlanImpl();
        }
        return instance;
    }
}
