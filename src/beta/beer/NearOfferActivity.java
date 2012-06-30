package beta.beer;

import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

import org.json.JSONException;
import org.openintents.intents.AbstractWikitudeARIntent;
import org.openintents.intents.WikitudeARIntent;
import org.openintents.intents.WikitudePOI;

import android.app.Activity;
import android.content.ActivityNotFoundException;
import android.content.res.Resources;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;

public class NearOfferActivity extends Activity {
    /** Called when the activity is first created. */
	private List<Oferta> ofertas;  
	private WikitudeARIntent intent;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        localizacion locate=new localizacion(getBaseContext());
        coordenada coordenadaLocal=locate.exec();
        LeerOfertas lector=new LeerOfertas();
        lector.setLng(coordenadaLocal.getX());
        lector.setLat(coordenadaLocal.getY());
        try{
        lector.parseFile();
        ofertas=lector.getListaOfertas();
        }catch( JSONException e )
        {
        	Log.v("ERROR", "JSON");
        }
        launchARView();
    }
    
    public void launchARView() {  
    	
    	 intent = new WikitudeARIntent(this.getApplication(), null, null, true); 
    	/* Oferta ofertado=new Oferta();
    	 ofertado.setTitle("Inicio Orgullo Gay");
    	 ofertado.setDescription("Inicio de la cabalgata del Orgullo Gay");
    	 ofertado.setLongitude(40.419149);
    	 ofertado.setLatitude(-3.692887);
    	 ofertado.setUrl("http://blogbar.es");
    	 ofertas.add(ofertado);
    	 
    	 Oferta ofertado2=new Oferta();
    	 ofertado2.setTitle("Plaza España");
    	 ofertado2.setDescription("Fin de la cabalgata del Orgullo Gay");
    	 ofertado2.setLongitude(40.42338);
    	 ofertado2.setLatitude(-3.711061);
    	 ofertado2.setUrl("http://roboces.es");
    	 ofertas.add(ofertado2);*/
    	
    	 for ( Oferta oferta1:ofertas)
    	 {
    		 addPoint(oferta1);
    	 }
    	 
    	 
    	/*while( ofertas.iterator().hasNext()  ) {
    		
    		Oferta oferta= ofertas.iterator().next();
    		if(oferta==null)
    		{
    			break;
    		}
    		addPoint(oferta);
    	}*/
    	try {  
    		  
    		intent.startIntent(this);  
    		  
    		} catch (ActivityNotFoundException e) {  
    		  
    		AbstractWikitudeARIntent.handleWikitudeNotFound(this);  
    		  
    		}  
    }

	private void addPoint(Oferta oferta) {
		// TODO Auto-generated method stub
		
		Resources res = getResources();  
		  
		WikitudePOI poi = new WikitudePOI(oferta.getLongitude(), oferta.getLatitude());  
		  
		poi.setLink(oferta.getUrl());  
		poi.setName(oferta.getTitle());
		poi.setDescription(oferta.getDescription());
		poi.setLink(oferta.getUrl()) ; 
		poi.setIconresource(getResources().getResourceName(R.drawable.ic_launcher));
		//leerImage(oferta.getFavicon()));  
		  
		intent.addPOI(poi);  
		
	}
	
	private Bitmap leerImage(String url)
	{
		
		 Log.v("URL", url);
	        URL  myFileUrl = null;
	        try {
	            myFileUrl = new URL (url);
	        } catch (MalformedURLException  e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	        }
	        try {
	            HttpURLConnection  conn = (HttpURLConnection ) myFileUrl
	                    .openConnection();
	            conn.setDoInput(true);
	            conn.connect();
	            int length = conn.getContentLength();
	            @SuppressWarnings("unused")
	            int[] bitmapData = new int[length];
	            @SuppressWarnings("unused")
	            byte[] bitmapData2 = new byte[length];
	            InputStream  is = conn.getInputStream();

	            Bitmap bmImg = BitmapFactory.decodeStream(is);
	            return bmImg;

	        } catch (IOException  e) {
	            // TODO Auto-generated catch block
	            e.printStackTrace();
	            return null;
	        }
	}
}