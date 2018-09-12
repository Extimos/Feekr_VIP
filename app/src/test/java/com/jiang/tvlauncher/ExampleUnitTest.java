package com.jiang.tvlauncher;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public void main() {
        String s = "http://owod107fe.bkt.clouddn.com/tv_video_3.0.0.1050_android_15000.apk";

        System.out.println(s.substring(s.indexOf("tv_video")));
    }
}