package id.falasyam.blog;

import android.content.Context; 
import android.graphics.Bitmap; 
import android.net.ConnectivityManager; 
import android.net.NetworkInfo; 
import android.os.Bundle; 
import android.support.design.widget.CoordinatorLayout; 
import android.support.design.widget.FloatingActionButton; 
import android.support.design.widget.Snackbar; 
import android.support.v4.widget.SwipeRefreshLayout; 
import android.support.v7.app.AppCompatActivity; 
import android.support.v7.widget.Toolbar; 
import android.view.View;
import android.view.Menu; 
import android.view.MenuItem; 
import android.webkit.WebSettings; 
import android.webkit.WebView; 
import android.webkit.WebViewClient; 
import android.view.KeyEvent;
import android.webkit.WebChromeClient;
import android.webkit.WebView;
import android.webkit.WebViewClient;

public class MainActivity extends AppCompatActivity implements SwipeRefreshLayout.OnRefreshListener{
    private SwipeRefreshLayout swipeRefreshLayout;     
	private WebView mWebView;    
	private CoordinatorLayout coordinatorLayout;     
	@Override     
	protected void onCreate(Bundle savedInstanceState) {        
	super.onCreate(savedInstanceState);        
	setContentView(R.layout.main);         
	mWebView = (WebView) findViewById(R.id.activity_main_webview);   
	swipeRefreshLayout = (SwipeRefreshLayout) findViewById(R.id.swiperefresh);         
	swipeRefreshLayout.setOnRefreshListener(MainActivity.this);  
	swipeRefreshLayout.setColorSchemeResources(R.color.colorKesatu,R.color.colorKedua);
		coordinatorLayout = (CoordinatorLayout) findViewById(R.id.container); 
		mWebView.setWebViewClient(new WebViewClient() {           
	@Override             
	public void onPageStarted(WebView view, String url, Bitmap favicon)
				{                 super.onPageStarted(view, url, favicon);               
				swipeRefreshLayout.setRefreshing(true);             return ;
				}
				public void 
				onPageFinished(WebView view, String url) {                 
					swipeRefreshLayout.setRefreshing(false);             return ;
				}         });
        WebSettings webSettings = mWebView.getSettings();         
		webSettings.setJavaScriptEnabled(true);        
		webSettings.setAllowFileAccess(true);        
		webSettings.setAppCacheEnabled(true);         
		loadWebsite();     return ;
	}   
	private void loadWebsite() {         
	ConnectivityManager cm = (ConnectivityManager) getApplication().getSystemService(Context.CONNECTIVITY_SERVICE);      
	NetworkInfo netInfo = cm.getActiveNetworkInfo();     
    if (netInfo != null && netInfo.isConnectedOrConnecting()) {        
	mWebView.loadUrl("http://fala-syam.blogspot.com");      }
	else {            
	   Snackbar snackbar = Snackbar.make(coordinatorLayout, "Cek Jaringan Internet Anda!", Snackbar.LENGTH_LONG);           
	   snackbar.show();            
	   swipeRefreshLayout.setRefreshing(false);         
	   }    
		return ;
	}
    @Override     
	public void onRefresh() {        
		mWebView.reload();     return ;
	}
    @Override     
	public boolean onCreateOptionsMenu(Menu menu) { 
	
	
	// Inflate the menu; this adds items to the action bar if it is present.         
	getMenuInflater().inflate(R.menu.menu_main, menu);         return true;     }
    @Override    
	public boolean onOptionsItemSelected(MenuItem item) {         
	// Handle action bar item clicks here. The action bar will        
	// automatically handle clicks on the Home/Up button, so long        
	// as you specify a parent activity in AndroidManifest.xml.         
	int id = item.getItemId();
        //noinspection SimplifiableIfStatement         
		if (id == R.id.action_settings) 
			{            
		return true;         
		}
        return super.onOptionsItemSelected(item);
		}
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event)
	{
		if(event.getAction()==KeyEvent.ACTION_DOWN){
			switch(keyCode){
				case KeyEvent.KEYCODE_BACK:
					if(mWebView.canGoBack()){
						mWebView.goBack();
					}
					else{
						finish();
					}
					return true;
			}
		}
		return super.onKeyDown(keyCode, event);
	}
		}
		
