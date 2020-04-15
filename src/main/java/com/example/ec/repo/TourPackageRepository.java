package com.example.ec.repo;/* =================================

author ankitrajprasad created on 14/04/20 
inside the package - com.example.ec.repo 

=====================================*/

import com.example.ec.domain.TourPackage;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.rest.core.annotation.RepositoryRestResource;
import org.springframework.data.rest.core.annotation.RestResource;

import java.util.Optional;

@RepositoryRestResource(collectionResourceRel = "packages", path = "packages")
public interface TourPackageRepository extends CrudRepository<TourPackage, String> {

    //Implemeting Simple Query Method Signature
    Optional<TourPackage> findByName(String name);

    /*
    List<Tour> findByTourPackageCode(String code);

    //Invalid Simple Query Method Signature
    Optional<Tour> findByRegion(Region region); throws IncorrectResultSizeDataAccessException

    //valid
    List<Tour> findByRegion(Region region);

    //But will thorw an Error if used Wrong Syntax for an Object ref. arg. for Simple Query Method
    List<Tour> findByReigon(Region region);  --> throws error PropertyReferenceException: No Propety reigon found for Type Tour
    //SO, Spring Data JPA repository method name must match your entity propertiesi.e. entity fields where For Tour class, entity field is specially
    //defined as Region


    //Now,Intermedite Example for complex query using Spring Data JPA

    @Query("select t from Tour t where t.tourPackage.code = ?1" +
    " and t.difficulty = ?2 and t.region = ?3 and t.price = ?4")
    List<Tour> lookupTour(String code, Difficulty  difficulty, Region region, Integer maxPrice);

    as Same for Complex Query :
    List<Tour> findByTourPackageCodeAndDifficultyAndRegionAndPriceLessThan(String code, Difficulty  difficulty, Region region, Integer maxPrice);
     */


    @Override
    @RestResource(exported=false)
    <S extends TourPackage> S save(S s);

    @Override
    @RestResource(exported=false)
    <S extends TourPackage> Iterable<S> saveAll(Iterable<S> iterable);

    @Override
    @RestResource(exported=false)
    void delete(TourPackage tourPackage);

    @Override
    @RestResource(exported=false)
    void deleteAll(Iterable<? extends TourPackage> iterable);

    @Override
    @RestResource(exported=false)
    void deleteAll();

    @Override
    @RestResource(exported=false)
    void deleteById(String s);
}
