package beta.beer;

import java.io.BufferedReader;

import java.io.InputStream;
import java.io.InputStreamReader;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.util.Log;


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
	
	public  JSONArray getOfferts() throws JSONException  {
			
		JSONObject obj = this.getJSONfromURL( this.getUrlStr() );

		JSONArray arr = obj.getJSONArray("data");
		
		return arr;
	}
	
	public JSONObject getJSONfromURL(String url){
	     InputStream is = null;
	     String result = "";
	     JSONObject json = null;
	      try{
	         HttpClient httpclient = new DefaultHttpClient();
	         HttpGet httppost = new HttpGet(url);
	         HttpResponse response = httpclient.execute(httppost);
	         HttpEntity entity = response.getEntity();
	         is = entity.getContent();
	     }catch(Exception e){
	    	 
	    	 Log.v("error", e.toString());
	     }

	      try{
	         BufferedReader reader = new BufferedReader(new InputStreamReader(is,"iso-8859-1"),8);
	         StringBuilder sb = new StringBuilder();
	         String line = null;
	         while ((line = reader.readLine()) != null) {
	             sb.append(line + "\n");
	         }
	         is.close();
	         result=sb.toString();
	     } catch(Exception e){
	    	 Log.v("error", e.toString());
	     }

	     try{
	         json = new JSONObject(result);
	     }catch(JSONException e){
	    	 Log.v("error", e.toString());
	     }

	      return json;
	 }
	
}
