package api;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.URL;
import java.security.SecureRandom;
import java.security.cert.X509Certificate;
import java.util.zip.DeflaterInputStream;
import java.util.zip.GZIPInputStream;

import javax.net.ssl.HostnameVerifier;
import javax.net.ssl.HttpsURLConnection;
import javax.net.ssl.SSLContext;
import javax.net.ssl.SSLSession;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;

public class Download {

	public static InputStream getFromHTTPS(String adresse){
		
		URL url = null;
		
		System.out.println("downloading from: "+adresse);
		
		try{
			url = new URL(adresse);
			disableCertificateValidation();
			HttpsURLConnection client = (HttpsURLConnection) url.openConnection();
			client.setRequestMethod("GET");
			client.setRequestProperty("User-Agent", "Xenos");
			client.setRequestProperty("Accept-Encoding", "gzip, deflate");
			client.setRequestProperty("Connection", "close");
			//client.setRequestProperty("Accept", "application/xml");

			InputStream instream = client.getInputStream();
			if (client.getContentEncoding() != null && client.getContentEncoding().equalsIgnoreCase("gzip")) {
				System.out.println("Found Gzip Stream");
			    instream = new GZIPInputStream(instream);
			} else if (client.getContentEncoding() != null && client.getContentEncoding().equalsIgnoreCase("deflate")){
				System.out.println("Found Deflate Stream");
			    instream = new DeflaterInputStream(instream);
			}
			
			
			
			return instream;

		} catch (Exception e){
			e.printStackTrace();  //TODO: ignore 500 errors
			return null;
		}
	}
	public static InputStream getFromHTTP(String adresse){
		
		URL url = null;
		
		System.out.println("downloading from: "+adresse);
		
		try{
			url = new URL(adresse);
			HttpURLConnection client = (HttpURLConnection) url.openConnection();
			client.setRequestMethod("GET");
			client.setRequestProperty("User-Agent", "Xenos");
			client.setRequestProperty("Accept-Encoding", "gzip, deflate");
			client.setRequestProperty("Connection", "close");
			//client.setRequestProperty("Accept", "application/xml");

			InputStream instream = client.getInputStream();
			if (client.getContentEncoding() != null && client.getContentEncoding().equalsIgnoreCase("gzip")) {
				System.out.println("Found Gzip Stream");
			    instream = new GZIPInputStream(instream);
			} else if (client.getContentEncoding() != null && client.getContentEncoding().equalsIgnoreCase("deflate")){
				System.out.println("Found Deflate Stream");
			    instream = new DeflaterInputStream(instream);
			}
			
			return instream;

		} catch (IOException e){
			e.printStackTrace();
			return null;
		}
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
