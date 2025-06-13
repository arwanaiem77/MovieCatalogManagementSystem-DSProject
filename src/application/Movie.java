package application;

public class Movie {
	private String title;
	private String description;
	private int releaseYear;
	private double rating;
	
	static MovieCatalog movieList = new MovieCatalog(4);
	
	public Movie() {
	}
	public Movie(String title, String description, int releaseYear,double rating) {
		super();
		this.title = title;
		this.description = description;
		this.releaseYear = releaseYear;
		setRating(rating);
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getReleaseYear() {
		return releaseYear;
	}
	public void setReleaseYear(int releaseYear) {
		this.releaseYear = releaseYear;
	}
	public double getRating() {
		return rating;
	}
	public void setRating(double rating) {
		if(rating >= 0 && rating <=10)
			this.rating = rating;
	}
	@Override
	public String toString() {
		return title +"," + description + "," + releaseYear+ ","+ rating;
	}


}
