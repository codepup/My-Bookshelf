package to.learnandroid.mybookshelf.data.model;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;

import java.util.List;

import to.learnandroid.mybookshelf.R;

/**
 * Created by Matt on 2017-05-07.
 */

//TODO Create adapter.
public class BookAdapter extends RecyclerView.Adapter<BookAdapter.ViewHolder> {
    private static final String LOG_TAG = BookAdapter.class.getSimpleName();
    private List<Book> books;

    BookAdapter(List<Book> books) {
        this.books = books;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        LayoutInflater layoutInflater = LayoutInflater.from(parent.getContext());
        ViewHolder viewHolder = new ViewHolder(layoutInflater.inflate(R.layout.book_list_item, parent, false), parent.getContext());
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        Book book = this.books.get(position);

        holder.bookTitle.setText(book.getTitle());

        Glide
                .with(holder.getContext())
                .load(book.getImageUrl())
                .into(holder.bookCover);
    }

    @Override
    public int getItemCount() {
        return this.books.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        public TextView bookTitle;
        public ImageView bookCover;
        private Context context;

        public ViewHolder(View itemView, Context context) {
            super(itemView);
            bookTitle = (TextView) itemView.findViewById(R.id.book_title_textView);
            bookCover = (ImageView) itemView.findViewById(R.id.book_cover_imageView);
            this.context = context;
        }

        public Context getContext() {
            return this.context;
        }
    }
}
