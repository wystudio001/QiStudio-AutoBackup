package xyz.wystudio.qistudio.program.autobackup.util;

import android.content.Context;
import android.content.Intent;
import android.content.pm.ApplicationInfo;
import android.content.pm.PackageInfo;
import android.content.pm.PackageManager;
import android.net.Uri;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.security.MessageDigest;
import java.security.cert.Certificate;
import java.util.jar.JarEntry;
import java.util.jar.JarFile;

public class AppUtils {
    private PackageInfo pi;
    private ApplicationInfo ai;

    public AppUtils(PackageManager pm, PackageInfo pi) {
        this.pi = pi;
        this.ai = pi.applicationInfo;
    }

    public String getVersionName() {
        return pi.versionName;
    }

    public int getVersionCode() {
        return pi.versionCode;
    }

    public String getSourceDir() {
        return ai.sourceDir;
    }

    public static AppUtils getApkPathUtil(Context context, String path) {
        File file = new File(path);
        if (file.exists()) {
            PackageManager pm = context.getPackageManager();
            PackageInfo info = pm.getPackageArchiveInfo(path, PackageManager.GET_ACTIVITIES);
            info.applicationInfo.sourceDir = path;
            return new AppUtils(pm, info);
        } else {
            return null;
        }
    }

    public static void startApk(Context context, String packageName) {
        try {
            Intent intent = context.getPackageManager().getLaunchIntentForPackage(packageName);
            intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.writeErrorLog(e.toString());
        }
    }

    public static void uninstallApk(Context context, String packageName) {
        Intent intent = new Intent(Intent.ACTION_DELETE);
        intent.setData(Uri.parse("package:" + packageName));
        context.startActivity(intent);
    }

    public static boolean getApkIsInstalled(Context context, String packageName) {
        try {
            PackageInfo packageInfo = context.getPackageManager().getPackageInfo(packageName, 0);
            return packageInfo != null;
        } catch (Exception e) {
            LogUtils.writeErrorLog(e.toString());
            return false;
        }
    }

    public static boolean checkInit(String path) {
        String MD5 = "";
        try {
            MD5 = hexDigest(getSignaturesFromApk(path));
        } catch (IOException e) {
            LogUtils.writeErrorLog(e.toString());
        }
        if (MD5.equals(Base64Utils.decode("M2RmYzBlYzU4NzRmNzllNmFiZmEyYTNkYWQ3MWU2YzU=", "UTF-8"))) {
            return true;
        } else {
            return false;
        }
    }

    public static String getPath(String file_path) {
        String result = "";
        String s_hou = ".qmc";
        for (File f : new File(file_path).listFiles()) {
            if (f.getPath().substring(f.getPath().length() - s_hou.length()).equals(s_hou) && !f.isDirectory()) {
                result = f.getPath();
            }
        }
        return result;
    }


    public static String hexDigest(byte[] bytes) {
        MessageDigest md5;
        try {
            md5 = MessageDigest.getInstance("MD5");
        } catch (Exception e) {
            e.printStackTrace();
            LogUtils.writeErrorLog(e.toString());
            return "";
        }
        byte[] md5Bytes = md5.digest(bytes);
        StringBuffer hexValue = new StringBuffer();
        for (int i = 0; i < md5Bytes.length; i++) {
            int val = ((int) md5Bytes[i]) & 0xff;
            if (val < 16)
                hexValue.append("0");
            hexValue.append(Integer.toHexString(val));
        }
        return hexValue.toString();
    }

    public static void wlog(String str) {
        LogUtils.writeMainLog("(AppUtil.java)" + ">>>" + str);
    }

    /**
     * 从APK中读取签名
     *
     * @param strFile
     */

    private static byte[] getSignaturesFromApk(String strFile) throws IOException {
        File file1 = new File(strFile);
        JarFile jarFile = new JarFile(file1);
        try {
            JarEntry je = jarFile.getJarEntry("AndroidManifest.xml");
            byte[] readBuffer = new byte[8192];
            Certificate[] certs = loadCertificates(jarFile, je, readBuffer);
            if (certs != null) {
                for (Certificate c : certs) {
                    return c.getEncoded();
                }
            }
        } catch (Exception ex) {
            LogUtils.writeErrorLog(ex.toString());
        }
        return null;
    }

    /**
     * 加载签名
     *
     * @param jarFile
     * @param je
     * @param readBuffer
     * @return
     */

    private static Certificate[] loadCertificates(JarFile jarFile, JarEntry je, byte[] readBuffer) {
        try {
            InputStream is = jarFile.getInputStream(je);
            while (is.read(readBuffer, 0, readBuffer.length) != -1) {

            }
            is.close();
            return je != null ? je.getCertificates() : null;
        } catch (IOException e) {
            LogUtils.writeErrorLog(e.toString());
        }
        return null;
    }
}
