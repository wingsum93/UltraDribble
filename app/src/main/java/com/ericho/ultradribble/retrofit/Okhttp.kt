package com.ericho.ultradribble.retrofit

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor

/**
 * Created by steve_000 on 12/9/2017.
 * for project UltraDribble
 * package name com.ericho.ultradribble.retrofit
 */
class Okhttp {

    companion object {

        private var client:OkHttpClient? = null
        @Synchronized
        fun getDefaultClient():OkHttpClient{
            if (client==null){
                synchronized(Okhttp){
                    if(client==null){
                        client = buildOkhttpClient();
                    }
                }
            }
            return client!!
        }

        fun buildOkhttpClient():OkHttpClient{

            return OkHttpClient.Builder()
                    .addNetworkInterceptor(HttpLoggingInterceptor())
                    .build()
        }
    }
}