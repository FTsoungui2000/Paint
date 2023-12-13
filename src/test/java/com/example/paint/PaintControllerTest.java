package com.example.paint;

import org.junit.Assert;
import org.junit.jupiter.api.Test;

class PaintControllerTest {

    PaintController pc = new PaintController();

    @Test
    void widthSize() {
        String[] ContSize = pc.size;
        String[] allowedSize = {"1", "2", "4", "6"};
        Assert.assertEquals(allowedSize, ContSize);
    }
}