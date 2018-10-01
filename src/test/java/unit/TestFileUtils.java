package unit;

import java.io.File;

public class TestFileUtils {

    public static File getTestFile(String path) {
        ClassLoader classLoader = TestFileUtils.class.getClassLoader();
        return new File(classLoader.getResource(path).getFile());
    }

}
