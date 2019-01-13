package com.boiledcoffee.sjwatch.test;

import com.boiledcoffee.sjwatch.util.UploadUtils;
import org.json.JSONException;
import org.junit.Test;

public class GenerateTokenTest {
    @Test
    public void generate() throws JSONException {
        UploadUtils.generateOssPolicyAndToken("6bpjaMgBu6ChmdHzr88nG0VYIHMtQgM8iMPJ0fJZ",24);
    }
}
