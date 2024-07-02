/**
*
* @author Bugra KAYACAN bugra.kayacan@ogr.sakarya.edu.tr
* @since 01.04.2024
* <p>
* URL girdisi alinmasi
* </p>
*/
package g221210389_commenthw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class Main {

	public static void main(String[] args) {
		
        BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));

        try {
            System.out.print("Lütfen GitHub URL Girin: ");
            String repositoryUrl = reader.readLine(); 
            System.out.println("");
            cloneRepository.cloneRepoURL(repositoryUrl);
        } catch (IOException e) {
            System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
        } finally {
            try {
                reader.close();
            } catch (IOException e) {
                System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
            }
        }

	}

}
