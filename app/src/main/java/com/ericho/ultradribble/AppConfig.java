package com.ericho.ultradribble;

import android.content.Context;
import android.content.SharedPreferences;
import android.text.TextUtils;


public class AppConfig {

    private String mToken;


    private boolean mShowGIF;

    private boolean mIsUseWIFI;

    private SharedPreferences mPreferences;

    public AppConfig(Context context) {
//        mPreferences = context.getSharedPreferences(SPUtils.FILE_NAME, Context.MODE_PRIVATE);


//        mShowGIF = (boolean) SPUtils.get(AppConstants.SP_SHOW_GIF, true, mPreferences);
    }

    public boolean isLogin() {
        return !TextUtils.isEmpty(mToken);
    }

    public String getToken() {
        return mToken;
    }

    public void setToken(String token) {
        mToken = token;
//        SPUtils.put(AppConstants.SP_ACCESS_TOKEN, token, mPreferences);
    }

    public boolean getShowGIFValue() {
        return mShowGIF;
    }

    public void setShowGIF(boolean showGIF) {
        mShowGIF = showGIF;
//        SPUtils.put(AppConstants.SP_SHOW_GIF, showGIF, mPreferences);
    }

    public boolean isShowGIF() {
        return mShowGIF && !mIsUseWIFI;
    }


    public void setUseWIFI(boolean useWIFI) {
        mIsUseWIFI = useWIFI;
    }
}