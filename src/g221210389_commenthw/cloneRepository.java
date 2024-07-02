/**
*
* @author Bugra KAYACAN bugra.kayacan@ogr.sakarya.edu.tr
* @since 01.04.2024
* <p>
* Klonlama islemi
* </p>
*/
package g221210389_commenthw;

import java.io.File;
import java.io.IOException;

public class cloneRepository {
    public static void cloneRepoURL(String repositoryUrl) {
        try {
            String folderName = repositoryUrl.substring(repositoryUrl.lastIndexOf("/") + 1).replace(".git", "");
            String filePath = System.getProperty("user.dir") + "/" + folderName;
            ProcessBuilder processBuilder = new ProcessBuilder();
            processBuilder.command("git", "clone", repositoryUrl);
            Process process = processBuilder.start();

            int exitCode = process.waitFor();
            if (exitCode == 0) 
            {
                System.out.println("Repository cloned successfully.");
                findJavaFiles.findJavaFilesRecursive(new File(filePath));
            } 
            else 
            {
            	findJavaFiles.findJavaFilesRecursive(new File(filePath));
            }
        } 
        catch (IOException | InterruptedException e) 
        {
            e.printStackTrace();
        }
    }

}
