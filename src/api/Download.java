package api;

import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

import org.xml.sax.InputSource;

public class Download {

	public static InputSource getFromHTTPS(String adresse){
		
		//String result = "";
		URL url = null;
		
		System.out.println("downloading...");
		
		try{
			disableCertificateValidation();
			url = new URL(adresse.replace(" ", "%20"));
			HttpsURLConnection client = (HttpsURLConnection) url.openConnection();
			client.setRequestProperty("User-Agent", "Xenos");
			client.setRequestProperty("Accept-Encoding", "gzip, deflate");
			client.setRequestProperty("Accept", "application/xml");
			
			client.setRequestMethod("GET");
			
			//String line;
			//BufferedReader rd = new BufferedReader( new InputStreamReader(client.getInputStream()));
			
			return new InputSource(client.getURL().openStream());
			
			//while ((line = rd.readLine()) != null){
			//	result += line;
			//}
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		//System.out.println("downloaded");
	}
	public static InputSource getFromHTTP(String adresse){
		
		//String result = "";
		URL url = null;
		
		System.out.println("downloading...");
		
		try{
			url = new URL(adresse.replace(" ", "%20"));
			HttpURLConnection client = (HttpURLConnection) url.openConnection();
			client.setRequestProperty("User-Agent", "Xenos");
			client.setRequestProperty("Accept-Encoding", "gzip, deflate");
			client.setRequestProperty("Accept", "application/xml");
			
			client.setRequestMethod("GET");
			
			//String line;
			//BufferedReader rd = new BufferedReader( new InputStreamReader(client.getInputStream()));
			
			return new InputSource(client.getURL().openStream());
			
			//while ((line = rd.readLine()) != null){
			//	result += line;
			//}
		} catch (Exception e){
			e.printStackTrace();
			return null;
		}
		
		//System.out.println("downloaded");
	}
	
	private static void disableCertificateValidation() {
		  // Create a trust manager that does not validate certificate chains
		  TrustManager[] trustAllCerts = new TrustManager[] { 
		    new X509TrustManager() {
		      public X509Certificate[] getAcceptedIssuers() { 
		        return new X509Certificate[0]; 
		      }
		      public void checkClientTrusted(X509Certificate[] certs, String authType) {}
		      public void checkServerTrusted(X509Certificate[] certs, String authType) {}
		  }};

		  // Ignore differences between given hostname and certificate hostname
		  HostnameVerifier hv = new HostnameVerifier() {
		    public boolean verify(String hostname, SSLSession session) { return true; }
		  };

		  // Install the all-trusting trust manager
		  try {
		    SSLContext sc = SSLContext.getInstance("SSL");
		    sc.init(null, trustAllCerts, new SecureRandom());
		    HttpsURLConnection.setDefaultSSLSocketFactory(sc.getSocketFactory());
		    HttpsURLConnection.setDefaultHostnameVerifier(hv);
		  } catch (Exception e) {}
		}

}
