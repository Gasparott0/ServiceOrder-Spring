package br.com.gasparotto.osgasparotto.domain.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import br.com.gasparotto.osgasparotto.domain.model.Comment;

@Repository
public interface CommentRepository extends JpaRepository<Comment, Long>{

}
