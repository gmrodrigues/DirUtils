package com.gmrodrigues.dirurils.test;

import com.gmrodrigues.dirurils.DirUtils;
import com.gmrodrigues.dirurils.FileOutsideDirectoryHierarchyException;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

import java.io.File;
import java.io.IOException;
import java.util.List;

import static org.hamcrest.core.Is.is;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertThat;
import static org.junit.Assert.assertTrue;

public class DirUtilsTest
{
    @Rule
    public ExpectedException thrown = ExpectedException.none();

    @Test
    public void testGetRelativePath()
    {
        String relativePath = DirUtils.getRelativePath(new File("config"), new File("config/checkstyle/checkstyle.xml"));
        assertThat("Relative path must match", relativePath, is("checkstyle/checkstyle.xml"));
    }

    @Test
    public void testGetNormalFilesTree()
    {
        List<File> normFilesTree = DirUtils.getNormalFilesTree(new File("config"));
        File[] expected = new File[]{new File("config/checkstyle/checkstyle.xml")};
        assertArrayEquals("should return file tree", expected, normFilesTree.toArray());
    }

    @Test
    public void testIsFileInsideDirectoryHierarchy() throws IOException
    {
        boolean shouldBeTrue = DirUtils.isFileInsideDirectoryHierarchy(new File("config/checkstyle/checkstyle.xml"), new File("config"));
        assertTrue("File should be inside dir", shouldBeTrue);

        boolean shouldBeFalse = DirUtils.isFileInsideDirectoryHierarchy(new File("README.md"), new File("config"));
        assertFalse("File should not be inside dir", shouldBeFalse);
    }

    @Test
    public void testAssertFileIsInsideDirectoryHierarchy() throws IOException, FileOutsideDirectoryHierarchyException
    {
        thrown.expect(FileOutsideDirectoryHierarchyException.class);
        DirUtils.assertFileIsInsideDirectoryHierarchy(new File("README.md"), new File("config"));
    }
}
