package com.example.ec.repo;/* =================================

author ankitrajprasad created on 14/04/20 
inside the package - com.example.ec.repo 

=====================================*/

import com.example.ec.domain.Tour;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import java.util.List;

/*public interface TourRepository extends CrudRepository<Tour, Integer> {*/
public interface TourRepository extends PagingAndSortingRepository<Tour, Integer> {
    /*List<Tour> findByTourPackageCode(String code);*/

    Page<Tour> findByTourPackageCode(String code, Pageable pageable);

}
