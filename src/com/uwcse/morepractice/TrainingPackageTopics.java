package com.uwcse.morepractice;

import java.io.File;
import java.util.ArrayList;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class TrainingPackageTopics extends Activity {

	private static final String TAG = TrainingPackageActivity.class.getSimpleName();
	public static final String INTENT_KEY_NAME = "packageName";
	public static final String TOPIC_NAME = "topicName";
	public static final String POSITION = "0";
	private static File[] FILES;
	private String packageName;
	private boolean hasSMSFolder;
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_training_topics);
		//gestureDetector = new GestureDetector(this, new MyGestureDetector(this)); no swiping for now - make sure to uncomment dispatchTouchEvent
		
		packageName = getIntent().getExtras().getString(INTENT_KEY_NAME);
		
		this.setTitle(getNameFromPath(packageName));
		
		// check to see if sms app folder is present
		hasSMSFolder = false;
		File packageFile = new File(packageName);
        for (File file : packageFile.listFiles()) {
            if (file.isDirectory() && file.getName().equals("SMS Application")) {
                hasSMSFolder = true;
            }
        }
		
		Button learning = (Button) this.findViewById(R.id.button_learning);
		
		learning.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		 Intent intent = new Intent(TrainingPackageTopics.this, TrainingPackageActivity.class);
	             intent.putExtra(TrainingPackageActivity.INTENT_KEY_NAME, packageName);
	             intent.putExtra(TrainingPackageActivity.POSITION, 0);
	             startActivity(intent);
	        }
	    });
		
		
		File currentDir = new File(packageName);
        FILES = currentDir.listFiles();
		
        
        final ArrayList<String> quizzes = new ArrayList<String>();
        final ArrayList<String> videos = new ArrayList<String>();
        final ArrayList<String> refs = new ArrayList<String>();
        
        for(File f: FILES) {
        	String extension = getExtension(f.getName());
        	if (extension.equals("mp4")) {
    			videos.add(f.getName());
    		} else if (extension.equals("csv")) {
    			quizzes.add(f.getName());
    		} else if (!f.isDirectory()) {
    			refs.add(f.getName());
    		}
        }

		Button quiz = (Button) this.findViewById(R.id.button_quizzes);
		
		quiz.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		 Intent intent = new Intent(TrainingPackageTopics.this, Topic.class);
	             intent.putStringArrayListExtra("files", quizzes);
	    		 intent.putExtra(TrainingPackageActivity.INTENT_KEY_NAME, packageName);
	    		 intent.putExtra(TOPIC_NAME, getString(R.string.quizzes));
	             startActivity(intent);
	        }
	    });
		
		Button video = (Button) this.findViewById(R.id.button_videos);
		
		video.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		 Intent intent = new Intent(TrainingPackageTopics.this, Topic.class);
	             intent.putStringArrayListExtra("files", videos);
	    		 intent.putExtra(TrainingPackageActivity.INTENT_KEY_NAME, packageName);
	    		 intent.putExtra(TOPIC_NAME, getString(R.string.videos));
	             startActivity(intent);
	        }
	    });
		
		Button references = (Button) this.findViewById(R.id.button_ref);
		
		references.setOnClickListener(new View.OnClickListener() {
	    	public void onClick(View v) {
	    		 Intent intent = new Intent(TrainingPackageTopics.this, Topic.class);
	             intent.putStringArrayListExtra("files", refs);
	    		 intent.putExtra(TrainingPackageActivity.INTENT_KEY_NAME, packageName);
	    		 intent.putExtra(TOPIC_NAME, getString(R.string.references));
	             startActivity(intent);
	        }
	    });
	}
	

	/**
	 * Returns the extension of the given filename or null if there is no extension
	 * @param filename the file name to parse
	 * @return the extension of the given filename, or null if there is no extension
	 */
	private String getExtension(String filename) {
		String[] parts = filename.split("\\.");
		return parts[parts.length - 1].toLowerCase();
	}
	
	private String getNameFromPath(String path) {
		String[] parts = path.split("/");
		return parts[parts.length - 1].split("\\.")[0];
	}
	
	@Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
	    // show sms menu icon only when there is a SMS folder
	    if (hasSMSFolder)
	        getMenuInflater().inflate(R.menu.package_topics, menu);
        return super.onCreateOptionsMenu(menu);
    }
	
	@Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        
        if (id == R.id.action_sms) {
            //new ActionBarFunctions().smsActivity(this, packageName);
            Intent intent = new Intent(this, SMSActivity.class);
            intent.putExtra(INTENT_KEY_NAME, packageName);
            startActivity(intent);
        }
        return super.onOptionsItemSelected(item);
    }
}
