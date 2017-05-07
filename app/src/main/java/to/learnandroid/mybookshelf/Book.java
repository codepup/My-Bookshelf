package to.learnandroid.mybookshelf;

/**
 * Created by Matt on 2017-05-06.
 */

public final class Book {
    private final String id;
    private final String title;
    private final String[] authors;
    private final String genre;
    private final String description;
    private final String publisher;
    private final String publishedDate;
    private final double rating;
    private final int ratingCount;
    private final String imageUrl;

    private Book(Builder builder) {
        this.id = builder.id;
        this.title = builder.title;
        this.authors = builder.authors;
        this.genre = builder.genre;
        this.description = builder.description;
        this.publisher = builder.publisher;
        this.publishedDate = builder.publishedDate;
        this.rating = builder.rating;
        this.ratingCount = builder.ratingCount;
        this.imageUrl = builder.imageUrl;
    }

    public String getTitle() {
        return title;
    }

    public String[] getAuthors() {
        return authors;
    }

    public String getGenre() {
        return genre;
    }

    public String getDescription() {
        return description;
    }

    public String getPublisher() {
        return publisher;
    }

    public String getPublishedDate() {
        return publishedDate;
    }

    public double getRating() {
        return rating;
    }

    public int getRatingCount() {
        return ratingCount;
    }

    public String getImageUrl() {
        return imageUrl;
    }

    public static class Builder {
        private String id;
        private String title;
        private String[] authors;
        private String genre;
        private String description;
        private String publisher;
        private String publishedDate;
        private double rating;
        private int ratingCount;
        private String imageUrl;

        public Builder id(String id) {
            this.id = id;
            return this;
        }

        public Builder title(String title) {
            this.title = title;
            return this;
        }

        public Builder authors(String[] authors) {
            this.authors = authors;
            return this;
        }

        public Builder genre(String genre) {
            this.genre = genre;
            return this;
        }

        public Builder description(String description) {
            this.description = description;
            return this;
        }

        public Builder publisher(String publisher) {
            this.publisher = publisher;
            return this;
        }

        public Builder publishedDate(String publishedDate) {
            this.publishedDate = publishedDate;
            return this;
        }

        public Builder rating(double rating) {
            this.rating = rating;
            return this;
        }

        public Builder ratingCount(int ratingCount) {
            this.ratingCount = ratingCount;
            return this;
        }

        public Builder imageUrl(String imageUrl) {
            this.imageUrl = imageUrl;
            return this;
        }

        public Book build() {
            return new Book(this);
        }
    }

}
