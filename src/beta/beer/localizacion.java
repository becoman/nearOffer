package beta.beer;

import android.content.Context;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.location.LocationProvider;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.util.Log;
import android.widget.Toast;

public class localizacion implements Runnable {

	private Context contexto;
	private Location currentLocation;
	MyLocationListener mLocationListener;
	LocationManager localizacionManager;
	private coordenada defaultGPS;
	
	
	public localizacion(Context contesto)
	{
		contexto=contesto;		
		defaultGPS = new coordenada(40.420341, -3.701625);
	}
	
	public void exec ()
	{
		Thread thread = new Thread(this);
		thread.start();
	}
	
	public void run()
	{
		localizacionManager= (LocationManager) contexto.getSystemService(contexto.LOCATION_SERVICE);
		boolean activado = localizacionManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
		if (activado)
		{
			Looper.prepare();
			MyLocationListener mLocationListener = new MyLocationListener();
			localizacionManager.requestLocationUpdates(LocationManager.GPS_PROVIDER, 0, 0, mLocationListener);
			Looper.loop(); 
			Looper.myLooper().quit(); 	
			
		}
		 
	}
	
	  private class MyLocationListener implements LocationListener 
	    {
	        
	        public void onLocationChanged(Location loc) {
	            if (loc != null) {
	                Toast.makeText( contexto , 
	                   " Funciona ", 
	                    Toast.LENGTH_LONG).show();
	                setCurrentLocation(loc);
	                handler.sendEmptyMessage(0);
	            }
	        }

	        public void onProviderDisabled(String provider) {
	            // TODO Auto-generated method stub
	        }

	        
	        public void onProviderEnabled(String provider) {
	            // TODO Auto-generated method stub
	        }

	        
	        public void onStatusChanged(String provider, int status, 
	            Bundle extras) {
	            // TODO Auto-generated method stub
	        }


	    } 

	  private Handler handler = new Handler() {
			@Override
			public void handleMessage(Message msg) {
				
				localizacionManager.removeUpdates(mLocationListener);
		    	if (currentLocation!=null) {
		    	//	outlat.setText("Latitude: " + currentLocation.getLatitude());
		    		//outlong.setText("Longitude: " + currentLocation.getLongitude());
		    		Log.v("GPS",currentLocation.getLatitude()+" - " +currentLocation.getLongitude() );
		    	}
			}
		};

		
		private void setCurrentLocation(Location loc) {
	    	currentLocation = loc;
	    }
}
