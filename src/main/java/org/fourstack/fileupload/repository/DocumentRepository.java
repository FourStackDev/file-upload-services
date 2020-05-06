package org.fourstack.fileupload.repository;

import java.util.Optional;

import org.fourstack.fileupload.entity.Document;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DocumentRepository extends CrudRepository<Document, Long>{
	
	public Optional<Document> findByFileName(String fileName);

}
