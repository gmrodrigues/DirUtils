package com.gmrodrigues.dirutils;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class DirUtils
{
    private DirUtils(){}

    public static List<File> getNormalFilesTree(File dir)
    {
        List<File> normalFiles = new ArrayList<File>();
        if (dir != null) {
            for (File file : dir.listFiles()) {
                if (file.isFile() && !file.isHidden() && file.canRead()) {
                    normalFiles.add(file);
                }
                else if (file.isDirectory() && !file.isHidden()
                        && file.canRead()) {
                    for (File innerFile : DirUtils.getNormalFilesTree(file)) {
                        normalFiles.add(innerFile);
                    }
                }
            }
        }
        return normalFiles;
    }

    public static boolean isFileInsideDirectoryHierarchy(File file, File dir)
            throws IOException
    {
        if (dir == null || !dir.isDirectory()) {
            return false;
        }
        file = file.getCanonicalFile();
        dir = dir.getCanonicalFile();
        while (file.getParentFile() != null) {
            if (dir.equals(file.getParentFile())) {
                return true;
            }
            file = file.getParentFile();
            continue;
        }
        return false;
    }

    public static void assertFileIsInsideDirectoryHierarchy(File file, File dir)
            throws FileOutsideDirectoryHierarchyException, IOException
    {
        if (!isFileInsideDirectoryHierarchy(file, dir)) {
            throw new FileOutsideDirectoryHierarchyException(file, dir);
        }
        return;
    }

    public static String getRelativePath(File base, File path)
    {
        try {
            String relative = base.getCanonicalFile().toURI()
                    .relativize(path.getCanonicalFile().toURI()).getPath();
            return relative;
        }
        catch (IOException e) {
            return null;
        }
    }
}
