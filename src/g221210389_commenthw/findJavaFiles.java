/**
*
* @author Bugra KAYACAN bugra.kayacan@ogr.sakarya.edu.tr
* @since 01.04.2024
* <p>
* Klasorleri gezerek Java dosyalarinin bulunmasi
* </p>
*/
package g221210389_commenthw;

import java.io.File;
import java.util.Arrays;
import java.util.Comparator;

public class findJavaFiles {
    
	public static void findJavaFilesRecursive(File directory) {
        File[] files = directory.listFiles();
        if (files != null) {
            Arrays.sort(files, Comparator.comparing(File::getName));
            for (File file : files) 
            {
                if (file.isDirectory()) 
                {
                    findJavaFilesRecursive(file);
                } 
                else if (file.getName().endsWith(".java")) 
                {
                    analyzeFile.analyzeJavaFile(file);
                }
            }
        }
    }

}
