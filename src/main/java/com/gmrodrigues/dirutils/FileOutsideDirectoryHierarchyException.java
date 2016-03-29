package com.gmrodrigues.dirutils;

import java.io.File;

public class FileOutsideDirectoryHierarchyException extends Exception
{
    public FileOutsideDirectoryHierarchyException(File file, File dir)
    {
        super("File <" + file.getAbsolutePath() + "> outside directory <" + dir.getAbsolutePath() + ">");
    }
}
