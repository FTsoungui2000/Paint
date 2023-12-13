package com.example.paint;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class FileTest {

    File file = new File();

    @Test
    void startTabNum() {
        int fileNum = file.tabNum;
        int rightNum = 2;
        Assert.assertEquals(rightNum, fileNum);
    }

    @Test
    void delayNum() {
        int fileDNum = file.delay;
        int delayNum = 30000;
        Assert.assertEquals(delayNum, fileDNum);
    }
}