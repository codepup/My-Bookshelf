package to.learnandroid.mybookshelf;

import android.text.TextUtils;
import android.util.Log;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Matt on 2017-05-06.
 */

public final class QueryUtils {
    private static final String LOG_TAG = QueryUtils.class.getSimpleName();

    private QueryUtils() {
    }

    public static List<Book> fetchBookData(String requestUrl) {
        URL url = createUrl(requestUrl);
        String jsonResponse = null;
        try {
            jsonResponse = makeHttpRequest(url);
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem making the HTTP request.", e);
        }
        List<Book> books = extractVolumeFromJson(jsonResponse);
        return books;
    }

    private static URL createUrl(String stringUrl) {
        URL url = null;
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException e) {
            Log.e(LOG_TAG, "Problem building the url", e);
        }
        return url;
    }

    private static String makeHttpRequest(URL url) throws IOException {
        String jsonResponse = "";

        if (url == null) {
            return jsonResponse;
        }

        HttpURLConnection urlConnection = null;
        InputStream inputStream = null;
        try {
            urlConnection = (HttpURLConnection) url.openConnection();
            urlConnection.setReadTimeout(10000);
            urlConnection.setConnectTimeout(15000);
            urlConnection.setRequestMethod("GET");
            urlConnection.connect();

            if (urlConnection.getResponseCode() == HttpURLConnection.HTTP_OK) {
                inputStream = urlConnection.getInputStream();
                jsonResponse = readFromStream(inputStream);
            } else {
                Log.e(LOG_TAG, "Error response code: " + urlConnection.getResponseCode());
            }
        } catch (IOException e) {
            Log.e(LOG_TAG, "Problem retrieving the book JSON response", e);
        } finally {
            if (urlConnection != null) {
                urlConnection.disconnect();
            }
            if (inputStream != null) {
                inputStream.close();
            }
        }
        return jsonResponse;
    }

    private static String readFromStream(InputStream inputStream) throws IOException {
        StringBuilder output = new StringBuilder();
        if (inputStream != null) {
            InputStreamReader inputStreamReader = new InputStreamReader(inputStream, Charset.defaultCharset());
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line = bufferedReader.readLine();
            while (line != null) {
                output.append(line);
                line = bufferedReader.readLine();
            }
        }
        return output.toString();
    }

    private static List<Book> extractVolumeFromJson(String bookJSON) {
        if (TextUtils.isEmpty(bookJSON)) {
            return null;
        }
        List<Book> books = new ArrayList<>();
        try {
            JSONObject baseJsonResponse = new JSONObject(bookJSON);
            JSONArray bookArray = baseJsonResponse.getJSONArray("items");
            for (int i = 0; i < bookArray.length(); i++) {
                JSONObject currentBook = bookArray.getJSONObject(i);

                JSONObject volumeInfo = currentBook.getJSONObject("volumeInfo");
                String title = volumeInfo.getString("title");
                JSONArray authorArray = volumeInfo.getJSONArray("authors");
                String[] authors = new String[authorArray.length()];
                for (int j = 0; j < authorArray.length(); j++) {
                    authors[j] = authorArray.getString(j);
                }

                String id = currentBook.getString("id");
                String description = currentBook.getString("description");
                String publisher = currentBook.getString("publisher");
                String publishedDate = currentBook.getString("publishedDate");
                String genre = currentBook.getString("mainCategory");
                double rating = currentBook.getDouble("averageRating");
                int ratingCount = currentBook.getInt("ratingsCount");
                String imageUrl = currentBook.getJSONObject("imageLinks").getString("medium");

                Book book = new Book.Builder()
                        .id(id)
                        .title(title)
                        .authors(authors)
                        .description(description)
                        .publisher(publisher)
                        .publishedDate(publishedDate)
                        .genre(genre)
                        .rating(rating)
                        .ratingCount(ratingCount)
                        .imageUrl(imageUrl)
                        .build();

                books.add(book);
            }
        } catch (JSONException e) {
            Log.e(LOG_TAG, "Problem parsing the book JSON results.", e);
        }
        return books;
    }


}
