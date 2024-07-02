/**
*
* @author Bugra KAYACAN bugra.kayacan@ogr.sakarya.edu.tr
* @since 01.04.2024
* <p>
* Java Dosyalarinin Yorumlarinin Bulunmasi
* </p>
*/
package g221210389_commenthw;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class analyzeFile { 
	public static void analyzeJavaFile(File file) {
	    try {
	        BufferedReader reader = new BufferedReader(new FileReader(file));
	        String line;
	        boolean javadocModu = false;
	        boolean cokluSatirModu = false;
	        boolean haveClass = false;
	        int javaDocYorumSayisi = 0;
	        int yorumsatirSayisi = 0;
	        int kodSatir = 0;
	        int loc = 0;
	        int fonksiyonSayisi = 0;
	
	        while ((line = reader.readLine()) != null) {
	            line = line.trim();
	            
	            loc++;
	
	            if (javadocModu) {
	                if (line.contains("*/")) 
	                {
	                	javadocModu = false;
	                } 
	                else 
	                {
	                	javaDocYorumSayisi++;
	                    continue;
	                }
	            }
	            if (cokluSatirModu) {
	                if (line.contains("*/")) 
	                {
	                	cokluSatirModu = false;
	                } 
	                else 
	                {
	                    yorumsatirSayisi++;
	                    continue; 
	                }
	            }
	
	            if (line.startsWith("/**")) {
	                if (!line.contains("*/")) {
	                	javadocModu = true;
	                    continue;
	                }
	                else
	                {
	                	javaDocYorumSayisi++;
	                	continue;
	                }
	            }
	
	            if (line.startsWith("/*")) {
	                if (!line.contains("*/")) {
	                	cokluSatirModu = true;
	                    continue;
	                }
	                else
	                {
	                	yorumsatirSayisi++;
	                	continue;
	                }
	            }
	
	            if (line.contains("//")) {
	                if (!line.matches(".*\".*//.*\".*")) {
	                    String[] parts = line.split("//");
	                    String codePart = parts[0].trim(); 
	                    if (!codePart.isEmpty()) { 
	                        kodSatir++;
	                        if (line.contains("class"))
	                            haveClass = true;
	                        if(haveClass){
	                            String FUNCTION_REGEX =
	                                    "\\b(public|private|protected|static|final|native|synchronized|abstract|transient)\\s+(\\w+\\s+)?(\\w+\\s+)?(\\w+)\\s*\\([^\\)]*\\)\\s*";
	
	                            Pattern pattern = Pattern.compile(FUNCTION_REGEX);
	                            Matcher matcher = pattern.matcher(line);
	                            while (matcher.find()) {
	                            	fonksiyonSayisi++;
	                            }
	                        }
	                    }
	                    yorumsatirSayisi++; 
	                } 
	                else 
	                {
	                    kodSatir++;
	                }
	            } 
	            else if (!line.isEmpty() && !line.contains("*/")) 
	            {
	                kodSatir++;
	                if (line.contains("class"))
	                    haveClass = true;
	                if(haveClass)
	                {
	                    String FUNCTION_REGEX =
	                            "\\b(public|private|protected|static|final|native|synchronized|abstract|transient)\\s+(\\w+\\s+)?(\\w+\\s+)?(\\w+)\\s*\\([^\\)]*\\)\\s*";
	
	                    Pattern pattern = Pattern.compile(FUNCTION_REGEX);
	                    Matcher matcher = pattern.matcher(line);
	                    while (matcher.find()) 
	                    {
	                    	fonksiyonSayisi++;
	                    }
	                }
	            }
	
	        }
	        reader.close();
	        
	        if(haveClass)
	        {
	            double YG=((javaDocYorumSayisi + yorumsatirSayisi)*0.8)/(double)fonksiyonSayisi;
	            double YH= ((double) kodSatir/(double)fonksiyonSayisi)*0.3;
	            String format = String.format("Yorum Sapma Yüzdesi: %% %.2f " , (((100*YG)/YH)-100));
	            System.out.println("Sınıf: " + file.getName());
	            System.out.println("Javadoc Satır Sayısı: " + javaDocYorumSayisi);
	            System.out.println("Yorum Satır Sayısı: " + yorumsatirSayisi);
	            System.out.println("Kod Satır Sayısı: " + kodSatir);
	            System.out.println("LOC: " + loc);
	            System.out.println("Fonksiyon Sayısı: " + fonksiyonSayisi);
	            System.out.println(format);
	            System.out.println("-----------------------------------------");
	        }
	    } 
	    catch (IOException e) 
	    {
	        System.err.println("Dosya işlemleri sırasında bir hata oluştu: " + e.getMessage());
	    }
	}
}


