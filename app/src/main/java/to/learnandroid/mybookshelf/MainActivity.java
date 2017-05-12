package to.learnandroid.mybookshelf;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.RecyclerView;

import java.util.List;

import to.learnandroid.mybookshelf.data.model.Book;
import to.learnandroid.mybookshelf.data.model.BookAdapter;
import to.learnandroid.mybookshelf.utils.QueryUtils;

public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG = MainActivity.class.getSimpleName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView bookList = (RecyclerView) findViewById(R.id.book_list);
    }
}
