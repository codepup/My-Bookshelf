package to.learnandroid.mybookshelf.data.model;

import android.content.AsyncTaskLoader;
import android.content.Context;

import java.util.List;

import to.learnandroid.mybookshelf.utils.QueryUtils;

/**
 * Created by Matt on 2017-05-11.
 */

public class BookLoader extends AsyncTaskLoader<List<Book>> {
    private static final String LOG_TAG = BookLoader.class.getSimpleName();
    private String url;

    public BookLoader(Context context, String url) {
        super(context);
        this.url = url;
    }

    @Override
    public List<Book> loadInBackground() {
        if (this.url == null) {
            return null;
        }
        List<Book> books = QueryUtils.fetchBookData(url);
        return books;
    }

    @Override
    protected void onStartLoading() {
        forceLoad();
    }
}
