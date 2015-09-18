package pl.spring.demo.entity;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "BOOK")
public class BookEntity implements Serializable {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	@Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(nullable = false, length = 50)
    private String title;
    @ManyToMany(cascade = {CascadeType.ALL})
    @JoinTable(name = "BOOK_AUTHOR",
            joinColumns = {@JoinColumn(name = "BOOK_ID", nullable = false)},
            inverseJoinColumns = {@JoinColumn(name = "AUTHOR_ID", nullable = false)}
    )
    private Set<AuthorEntity> authors = new HashSet<>();
    // for hibernate
    protected BookEntity() {
}

    public BookEntity(Long id, String title, Set<AuthorEntity> authors) {
        this.id = id;
        this.title = title;
        this.authors = authors;
    }

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

	public Set<AuthorEntity> getAuthors() {
		return authors;
	}

	public void setAuthors(Set<AuthorEntity> authors) {
		this.authors = authors;
	}

 
}
