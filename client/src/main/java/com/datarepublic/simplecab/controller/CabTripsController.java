package com.datarepublic.simplecab.controller;

import com.datarepublic.simplecab.repository.SimpleCabRepository;
import com.datarepublic.simplecab.utils.CustomErrorType;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.math.BigInteger;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

import static jdk.nashorn.internal.objects.NativeString.trim;

@Api
@RestController
@RequestMapping(path="/cabs")
public class CabTripsController {
    private final Logger log = LoggerFactory.getLogger(getClass());

    @Autowired
    SimpleCabRepository simpleCabService;



    @RequestMapping(method = RequestMethod.GET, path = "/getMedallionsSummary")
    @ApiOperation(value = "Gets a Summary of trips performed by a Cab")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Service not available"),
            @ApiResponse(code = 500, message = "Unexpected Runtime error") })

    public ResponseEntity<String[] > getMedallionsSummary (@RequestParam(value = "medallions") String[] medallions,
                                                           @RequestParam(value = "pickupDate, Date Format: dd-mm-yyyy")@DateTimeFormat(pattern = "dd-MM-yyyy") Date pickupDate,
                                                           @RequestParam(value = "ignoreCache",required = false) boolean ignoreCache){

        log.trace("Entering getMedallionsSummary() with pickup date ", pickupDate);

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        String dateObject = sdf.format(pickupDate);
        String[] medalliumSummary= new String[medallions.length];
        if(ignoreCache) {
           int reset = simpleCabService.resetCabCount("","");
        }
        int size = medallions.length;
        for (int i = 0; i < size; i++) {
            int tripCount = simpleCabService.selectCabCount(medallions[i],dateObject);
            medalliumSummary[i] = "Number of trips : " + tripCount + " for Medallium : " + medallions[i] + " on date : " + dateObject;
        }

        return ResponseEntity.ok(medalliumSummary);
    }

    @RequestMapping(method = RequestMethod.GET, path = "/getMedallionsByDate")
    @ApiOperation(value = "Gets a Summary of trips performed by a Cab using dates")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Service not available"),
            @ApiResponse(code = 500, message = "Unexpected Runtime error") })

    public ResponseEntity<String[]>  getMedallionsByDate ( @RequestParam(value = "pickupDate, Date Format: dd-mm-yyyy")@DateTimeFormat(pattern = "dd-MM-yyyy") Date pickupDate,
                                                           @RequestParam(value = "ignoreCache",required = false) boolean ignoreCache){

        log.trace("Entering getMedallionsByDate() with pickup date ", pickupDate);
        List<Object[]> medallionResponses = new ArrayList<>();
        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        String dateObject = sdf.format(pickupDate);
        if(ignoreCache) {
            int reset = simpleCabService.resetCabCount("","");
        }
        medallionResponses = simpleCabService.selectCabCountByDate(dateObject);

        String[] medalliumSummary= new String[medallionResponses.size()];
        int count = 0;
        for (Object[] result : medallionResponses) {
//            String maxPrice = (String)result[0];
//            Date minPrice = (Date)result[1];
//            BigInteger trips = (BigInteger)result[2];

            medalliumSummary[count] = "Number of trips : " + (BigInteger)result[2] + " for Medallium : "
                    + (String)result[0] + " on date : " + (Date)result[1];
            count++;
        }

        return ResponseEntity.ok(medalliumSummary);
    }

    @RequestMapping(method = RequestMethod.POST, path = "/postMedallionsCsvFile")
    @ApiOperation(value = "Gets a Summary of trips performed by a Cab passed as a csv file")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Service not available"),
            @ApiResponse(code = 500, message = "Unexpected Runtime error") })

    public ResponseEntity<String[] > postMedallionsCsvFile (@RequestPart(value = "cabTrip", required = false) MultipartFile cabTrip,
                                                            @RequestParam(value = "ignoreCache",required = false) boolean ignoreCache){

        log.trace("Entering postMedallionsCsvFile()");

        File file = new File(cabTrip.getName());
        String csvFile = cabTrip.getOriginalFilename();
        String cvsSplitBy = ",";
        String[] medallion = {};
        List<String> medallions = new ArrayList<>();
        List<String> pickUpTime = new ArrayList<>();
        int count = 0;
        try {
            Scanner inputStream = new Scanner(cabTrip.getInputStream());
            while(inputStream.hasNext()){
                String data = inputStream.next();
                medallion = data.split(cvsSplitBy);
                medallions.add(count,medallion[0]);
                pickUpTime.add(count,trim(medallion[1]));
                count++;

            }

        } catch (IOException e) {

            log.trace("Unable to read input .csv file. ", e.getMessage());
            e.printStackTrace();
            return new ResponseEntity(new CustomErrorType("Unable to read input .csv file. " + e.getMessage())
                    , HttpStatus.NOT_FOUND);


        }

        if(ignoreCache){
            int resetCache = simpleCabService.resetCabCount("","");
        }

        DateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        sdf.setLenient(false);
        String[] medalliumSummary= new String[medallions.size()];
        int size = medallions.size();
        for (int i = 0; i < size; i++) {
            int tripCount = simpleCabService.selectCabCount(medallions.get(i),pickUpTime.get(i));
            medalliumSummary[i] = "Number of trips : " + tripCount + " for Medallium : " +medallions.get(i) + " on date : " + pickUpTime.get(i);

        }

        return ResponseEntity.ok(medalliumSummary);
    }

    @RequestMapping(method = RequestMethod.DELETE, path = "/deleteMedaliumCache")
    @ApiOperation(value = "Removes all cache from selectCabCount method")
    @ApiResponses(value = {
            @ApiResponse(code = 200, message = "Success"),
            @ApiResponse(code = 404, message = "Service not available"),
            @ApiResponse(code = 500, message = "Unexpected Runtime error") })

    public ResponseEntity<Integer> deleteMedaliumCache (){

        log.trace("Entering deleteMedaliumCache()");

         int result = simpleCabService.resetCabCount("","");

        return ResponseEntity.ok(result);
    }

}