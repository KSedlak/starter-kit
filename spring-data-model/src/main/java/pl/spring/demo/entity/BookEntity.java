package pl.spring.demo.entity;


import java.util.ArrayList;

import pl.spring.demo.to.*;

public class BookEntity implements IdAware {
		private String title;
	    private Long id;
	    ArrayList<AuthorTo> authors;
	    
	    public BookEntity() {
			
		}
	    

	    public BookEntity(Long id, String title, ArrayList<AuthorTo> authors) {
	        this.id = id;
	        this.title = title;
	        this.authors = authors;
	    }

	    @Override
	    public Long getId() {
	        return id;
	    }

	    public void setId(Long id) {
	        this.id = id;
	    }

	    public String getTitle() {
	        return title;
	    }

	    public void setTitle(String title) {
	        this.title = title;
	    }


		public ArrayList<AuthorTo> getAuthors() {
			return authors;
		}


		public void setAuthors(ArrayList<AuthorTo> authors) {
			this.authors = authors;
		}



}
