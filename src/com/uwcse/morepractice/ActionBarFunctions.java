package com.uwcse.morepractice;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;

public class ActionBarFunctions {
    public void downloadsActivity(Context c) {
        Intent intent = new Intent(c, DownloadActivity.class);
        c.startActivity(intent);
    }
    
    public void refresh(Context c) {
    	Intent intent = new Intent(c, DownloadActivity.class);
    	((Activity) c).finish();
    	c.startActivity(intent);
    }

	public void customActivity(Context c) {
		Intent intent = new Intent(c, CustomTags.class);
    	c.startActivity(intent);
	}
	
	public void smsActivity(Context c, String packagePath) {
		Intent intent = new Intent(c, SMSActivity.class);
		intent.putExtra(TrainingPackageTopics.INTENT_KEY_NAME, packagePath);
		c.startActivity(intent);
	}
}
