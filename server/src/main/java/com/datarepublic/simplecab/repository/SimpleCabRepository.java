package com.datarepublic.simplecab.repository;

import com.datarepublic.simplecab.model.CabTripData;

import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Repository;



import java.util.Date;
import java.util.List;

@Repository
public interface SimpleCabRepository extends JpaRepository<CabTripData, Long> {

    @Query(nativeQuery = true, value= "SELECT t.medallion FROM cab_trip_data t WHERE t.medallion = ?1")
    public List<CabTripData> findByMedallion(String medallion);


    @Query( nativeQuery = true, value = "SELECT COUNT(*) FROM cab_trip_data u where u.medallion = ?1 AND Date(u.pickup_datetime) = ?2")
    public int findByMedaliumAndPickupDate(String medallion, @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) Date pickupDateTime);

    @Cacheable(value="medallion", key="#p0")
    @Query(value = "SELECT COUNT(*) FROM cab_trip_data u WHERE u.medallion = ?1 AND Date(u.pickup_datetime) = ?2", nativeQuery = true)
    public int selectCabCount(String medallion, String pickupDateTime);


    @CacheEvict(value = "medallion",key="#p0", allEntries = true)
    @Query(value = "SELECT COUNT(*) FROM cab_trip_data u WHERE u.medallion = ?1 AND Date(u.pickup_datetime) = ?2", nativeQuery = true)
    public int resetCabCount(String medallion, String pickupDateTime);

    @Cacheable(value="medallion", key="#p0")
    @Query(value = "SELECT u.medallion,date(u.pickup_datetime),count(*) FROM cab_trip_data u " +
            " where date(u.pickup_datetime) = ?1  group by u.medallion,date(u.pickup_datetime)",  nativeQuery = true)
    public List<Object[]>   selectCabCountByDate(String pickupDateTime);

}
