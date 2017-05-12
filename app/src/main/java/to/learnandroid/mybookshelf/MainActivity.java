package to.learnandroid.mybookshelf;

import android.Manifest;
import android.net.Uri;
import android.support.v4.app.LoaderManager;
import android.support.v4.content.Loader;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.net.URI;
import java.util.List;

import to.learnandroid.mybookshelf.data.model.Book;
import to.learnandroid.mybookshelf.data.model.BookAdapter;
import to.learnandroid.mybookshelf.data.model.BookLoader;
import to.learnandroid.mybookshelf.utils.QueryUtils;

public class MainActivity extends AppCompatActivity implements LoaderManager.LoaderCallbacks<List<Book>> {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();
    private static final String API_KEY = "AIzaSyBSXPLpKWjtQsw7jCuiFKbcWcgnVqq3PkQ";
    private static final String GOOGLE_BOOKS_QUERY_URL = "https://www.googleapis.com/books/v1/volumes";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView bookList = (RecyclerView) findViewById(R.id.book_list);
    }

    @Override
    public Loader<List<Book>> onCreateLoader(int id, Bundle args) {
        Uri baseUri = Uri.parse(GOOGLE_BOOKS_QUERY_URL);
        Uri.Builder uriBuilder = baseUri.buildUpon();
        uriBuilder.appendQueryParameter("key", API_KEY);
        return new BookLoader(this, uriBuilder.toString());
    }

    @Override
    public void onLoadFinished(Loader<List<Book>> loader, List<Book> data) {

    }

    @Override
    public void onLoaderReset(Loader<List<Book>> loader) {

    }
}
