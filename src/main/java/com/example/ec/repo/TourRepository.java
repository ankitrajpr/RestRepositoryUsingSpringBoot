package com.example.ec.repo;/* =================================

author ankitrajprasad created on 14/04/20 
inside the package - com.example.ec.repo 

=====================================*/

import com.example.ec.domain.Tour;
import org.springframework.data.repository.CrudRepository;

public interface TourRepository extends CrudRepository<Tour, Integer> {

}
