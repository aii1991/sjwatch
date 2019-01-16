package com.boiledcoffee.sjwatch.test;

import com.boiledcoffee.sjwatch.util.UploadUtils;
import org.json.JSONException;
import org.junit.Test;

public class GenerateTokenTest {
    @Test
    public void generate() throws JSONException {
        System.out.println(UploadUtils.generateOssPolicyAndToken("...","....",24));
    }
}
