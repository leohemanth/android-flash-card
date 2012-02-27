package com.engg.twitter;

public class Constants {

	public static final String CONSUMER_KEY = "ZycWcNdVd6skHrhJ497HIw";
	public static final String CONSUMER_SECRET = "FAXQDVAV2L3GU28Ev7JY3xFSXdSYXnymm1WcOOkY";

	public static final String REQUEST_URL = "http://api.twitter.com/oauth/request_token";
	public static final String ACCESS_URL = "http://api.twitter.com/oauth/access_token";
	public static final String AUTHORIZE_URL = "http://api.twitter.com/oauth/authorize";

	public static final String OAUTH_CALLBACK_SCHEME = "x-oauthflow-twitter";
	public static final String OAUTH_CALLBACK_HOST = "callback";
	public static final String OAUTH_CALLBACK_URL = OAUTH_CALLBACK_SCHEME + "://"
			+ OAUTH_CALLBACK_HOST;

}
