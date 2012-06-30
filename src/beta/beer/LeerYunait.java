package beta.beer;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URISyntaxException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONTokener;


public class LeerYunait {

	final String KEY = "484f3ccc716c2d25069b416ce0cabedd6fc54572";
	private double lat = 40.407742;
	private double lng = -3.703211;
	
	public double getLat() {
		return lat;
	}

	public void setLat(double lat) {
		this.lat = lat;
	}

	public double getLng() {
		return lng;
	}

	public void setLng(double lng) {	
		this.lng = lng;
	}

	public String getUrlStr()
	{
		
		String strUrl = "http://www.yunait.com/rest/deals/in?";
		strUrl += "format=json";
		strUrl += "&lat=" +  ( (Object) lat).toString();
		strUrl += "&lng=" +  ( (Object) lng).toString();
		strUrl += "&national=0";
		strUrl += "&key="+ KEY;
		
		return strUrl;
	}
	
	public  JSONArray getOfferts()  {
			
		HttpClient client = new DefaultHttpClient();
		HttpGet request = new HttpGet();

		
		try {
//			URI url = new URI("http://www.yunait.com/rest/deals/in?format=json&lat=40.407742&lng=-3.703211&national=0&key="+KEY);

			URI url = new URI(getUrlStr());
			request.setURI(url);
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
		
		HttpResponse response = null;
		
		try {
			response = client.execute(request);
		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		BufferedReader reader = null;
		try {
			reader = new BufferedReader(new InputStreamReader(response.getEntity().getContent(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IllegalStateException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		StringBuilder builder = new StringBuilder();
		
		try {
			for (String line = null; (line = reader.readLine()) != null;) {
			    builder.append(line).append("\n");
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		JSONArray finalResult = null;
		
		JSONTokener tokener = new JSONTokener(builder.toString());
		
		try {
			JSONObject result = new JSONObject(tokener);
			finalResult = result.getJSONArray("data");
			
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}		

		
		
		return finalResult;
	}
	
}
