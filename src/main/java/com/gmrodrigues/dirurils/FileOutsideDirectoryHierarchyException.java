package com.gmrodrigues.dirurils;

import java.io.File;

public class FileOutsideDirectoryHierarchyException extends Exception
{
    public FileOutsideDirectoryHierarchyException(File file, File dir)
    {
        super("File <" + file.getAbsolutePath() + "> outside directory <" + dir.getAbsolutePath() + ">");
    }
}
