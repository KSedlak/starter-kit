package pl.spring.demo.to;


import java.util.List;


public class BookTo implements IdAware {
    private Long id;
    private String title;
    private List<AuthorTo> authors;
    
    
    public BookTo(Long id, String title, List<AuthorTo> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

    public BookTo() {
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

	public List<AuthorTo> getAuthors() {
		return authors;
	}

	public void setAuthors(List<AuthorTo> authors) {
		this.authors = authors;
	}




}
