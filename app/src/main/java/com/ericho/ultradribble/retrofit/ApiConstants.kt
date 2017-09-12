package com.ericho.ultradribble.retrofit

/**
 * Created by Eric Ho on 2017/6/24.
 *
 * There are 3 steps altogether for web application flow:
 * + 1. Redirect users to request Dribbble access.
 *   ```GET https://dribbble.com/oauth/authorize```
 *   The parameters are listed below:
 *   client_id    - [String] type. Required. The client ID you received from Dribbble when you [registered](https://dribbble.com/account/applications/new).
 *   redirect_uri - [String] type. The URL in your application where users will be sent after authorization.
 *                                 See details about [Redirect URLs](http://developer.dribbble.com/v1/oauth/#redirect-urls).
 *   scope        - [String] type. A space separated list of scopes.
 *                                 If not provided, scope defaults to the public scope for users that don’t have a valid token for the application.
 *                                 For users who do already have a valid token for the application,
 *                                 the user won’t be shown the authorization page with the list of scopes.
 *                                 Instead, this step of the flow will automatically complete with the same scopes that were user last time the user completed the flow.
 *   state        - [String] type. An unguessable random string.
 *                                 It is used to protect against cross-site request forgery attacks.
 *
 * + 2. Dribbble redirects back to your site.
 *   If the user accepts your request,
 *   Dribbble redirects back to your site with a temporary code in a code parameter as well as the state you provided in the previous step in a state parameter.
 *   If the states don’t match, the request has been created by a third party and the process should be aborted.
 *   Exchange this for an access token:
 *   ```POST https://dribbble.com/oauth/token```
 *   See [com.ericho.ultradribble.retrofit.AccessTokenService] for more details.
 *
 *   3. Use the access token to access the API.
 *   The access token allows you to make requests to the API on a behalf of a user.
 *   ```GET https://api.dribbble.com/v1/user?access_token=...```
 *
 */

class ApiConstants private constructor() {

    companion object {

        // May be reset and not commit to github if they are abused.
        val CLIENT_ID = "57ecdebad7c40b4bf665428a05a7202f5ba54d98d61f5a79036ab3a77587a190"
        val CLIENT_SECRET = "edb60dd71e47c24996f07490b1da621d8b687af01309f1419a6de6c820972336"
        val CLIENT_ACCESS_TOKEN = "c4990318c449f21479ef69ace0fb6f124b9c6d17815978de62bbbfc74497850b"

        // Constant strings of Dribbble API
        val DRIBBBLE_V1_BASE_URL = "https://api.dribbble.com"
        val DRIBBBLE_AUTHORIZE_URL = "https://dribbble.com/oauth/authorize"
        val DRIBBBLE_GET_ACCESS_TOKEN_URL = "https://dribbble.com/oauth/token"

        // Callback urls
        val DRIBBBLE_AUTHORIZE_CALLBACK_URI = "x-ultra-oauth-dribbble://callback"
        //val DRIBBBLE_AUTHORIZE_CALLBACK_URI_SCHEMA: String = "x-mango-oauth-dribbble"
        val DRIBBBLE_AUTHORIZE_CALLBACK_URI_HOST = "callback"
        val DRIBBBLE_AUTHORIZE_SCOPE = "public+write+comment+upload"

        // Amount of images per page
        val PER_PAGE = 20
    }

}