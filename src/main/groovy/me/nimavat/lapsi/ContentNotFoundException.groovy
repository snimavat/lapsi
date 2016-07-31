package me.nimavat.lapsi

/**
 * Created by sudhir on 28/07/16.
 */
class ContentNotFoundException extends RuntimeException {

	ContentNotFoundException(String url) {
		super("No content found for uri $url")
	}
}
