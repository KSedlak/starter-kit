package pl.spring.demo.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pl.spring.demo.entity.AuthorEntity;

import pl.spring.demo.mapper.AuthorMapper;

import pl.spring.demo.repository.AuthorRepository;
import pl.spring.demo.service.AuthorService;
import pl.spring.demo.to.AuthorTo;



@Service
@Transactional(readOnly = true)
public class AuthorServiceImpl implements AuthorService {

    @Autowired
    private AuthorRepository authorRepository;

    @Override
    @Transactional(readOnly = false)
    public AuthorTo saveAuthor(AuthorTo author) {
        AuthorEntity entity = AuthorMapper.map(author);
        entity = authorRepository.save(entity);
        return AuthorMapper.map(entity);
    }

	@Override
	public List<AuthorTo> findAll() {
		
		return AuthorMapper.map2ToList(authorRepository.findAll());
	}



}
