package com.taobao.ideabox.android.test;

import java.io.File;
import java.io.FileDescriptor;

/**
 * Created with IntelliJ IDEA.
 * User: Warren
 * Date: 12-12-9
 * Time: ÏÂÎç3:41
 */
public class FileTest {
    public static void main(String[] args) throws Exception{
        File file = new File("d:/tmp/img.png");
        if(file.exists()){
            System.out.println(file.getAbsolutePath());
            System.out.println(file.getPath());
            System.out.println(file.getName());
            System.out.println(file.getParent());
            System.out.println(file.getCanonicalPath());
            System.out.println(file.length());
        }
    }
}
