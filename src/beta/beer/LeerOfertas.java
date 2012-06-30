package beta.beer;

import java.util.ArrayList;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

public class LeerOfertas {
	
	private String file;
	private String content = "";
	private List<Oferta> listaOfertas;	
	private double lat;
	private double lng;
	private JSONObject objJson;	

	public void parseFile() throws JSONException{
		
		LeerYunait getJson = new LeerYunait();
		getJson.setLat(lat);
		getJson.setLat(lng);		
		listaOfertas=new ArrayList<Oferta>(); 
		
		JSONArray data = getJson.getOfferts();
		
		int i;		
		
		for( i = 1; i < 4; i++ ){
			
			JSONObject item =  (JSONObject) data.get(i);			
			
			Oferta ofer = new Oferta();
			
			ofer.setTitle( item.getString("title") );
			ofer.setDescription( item.getString("description") );
			
			ofer.setUrl( item.getString("url") );
			ofer.setLatitude( item.getDouble("latitude") );
			ofer.setLongitude( item.getDouble("latitude") );
			ofer.setImage( item.getString("image") );	
			listaOfertas.add(ofer);
			
		}	
		
	}
	
	public void setLat(double lat) {
		this.lat = lat;
	}

	public void setLng(double lng) {
		this.lng = lng;
	}
	
	public List<Oferta> getListaOfertas() {
		return listaOfertas;
	}
	
	public JSONObject getObjJson() {
		return objJson;
	}

	public void setObjJson(JSONObject objJson) {
		this.objJson = objJson;
	}
	
}
