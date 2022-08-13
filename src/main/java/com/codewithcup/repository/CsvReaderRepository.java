package com.codewithcup.repository;

import com.codewithcup.entity.CsvReader;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CsvReaderRepository extends JpaRepository<CsvReader,Long> {

}
