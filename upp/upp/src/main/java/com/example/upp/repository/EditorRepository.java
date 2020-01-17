package com.example.upp.repository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.upp.model.Editor;

@Repository
public interface EditorRepository extends JpaRepository<Editor, Long>{

}
