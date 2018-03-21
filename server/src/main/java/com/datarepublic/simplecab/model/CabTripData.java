package com.datarepublic.simplecab.model;


import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "cab_trip_data")
public class CabTripData {

    @Id
    @Column(name="cabTripId")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long cabTripId;

    private String medallion;

    private String hackLicense;

    private String vendorId;

    private int rateCode;

    private String  storeAndFwdFlag;

    @Temporal(TemporalType.DATE)
    private Date pickupDatetime;

    @Temporal(TemporalType.TIMESTAMP)
    private Date dropoffDatetime;

    private int passengerCount;

    private int tripTimeInSecs;

    private Double tripDistance;

    private Double pickupLongitude;

    private Double pickupLatitude;

    private Double dropoffLongitude;

    private Double dropoffLatitude;

    public CabTripData() {
    }

    public CabTripData(CabTripData cabTripData) {
    }

    public CabTripData(Long cabTripId, String medallion,
                       String hackLicense, String vendorId,
                       int rateCode, String storeAndFwdFlag,
                       Date pickupDatetime, Date dropoffDatetime,
                       int passengerCount, int tripTimeInSecs,
                       Double tripDistance, Double pickupLongitude,
                       Double pickupLatitude, Double dropoffLongitude,
                       Double dropoffLatitude) {
        this.cabTripId = cabTripId;
        this.medallion = medallion;
        this.hackLicense = hackLicense;
        this.vendorId = vendorId;
        this.rateCode = rateCode;
        this.storeAndFwdFlag = storeAndFwdFlag;
        this.pickupDatetime = pickupDatetime;
        this.dropoffDatetime = dropoffDatetime;
        this.passengerCount = passengerCount;
        this.tripTimeInSecs = tripTimeInSecs;
        this.tripDistance = tripDistance;
        this.pickupLongitude = pickupLongitude;
        this.pickupLatitude = pickupLatitude;
        this.dropoffLongitude = dropoffLongitude;
        this.dropoffLatitude = dropoffLatitude;
    }

    public Long getCabTripId() {
        return cabTripId;
    }

    public void setCabTripId(Long cabTripId) {
        this.cabTripId = cabTripId;
    }

    public String getMedallion() {
        return medallion;
    }

    public void setMedallion(String medallion) {
        this.medallion = medallion;
    }

    public String getHackLicense() {
        return hackLicense;
    }

    public void setHackLicense(String hackLicense) {
        this.hackLicense = hackLicense;
    }

    public String getVendorId() {
        return vendorId;
    }

    public void setVendorId(String vendorId) {
        this.vendorId = vendorId;
    }

    public int getRateCode() {
        return rateCode;
    }

    public void setRateCode(int rateCode) {
        this.rateCode = rateCode;
    }

    public String getStoreAndFwdFlag() {
        return storeAndFwdFlag;
    }

    public void setStoreAndFwdFlag(String storeAndFwdFlag) {
        this.storeAndFwdFlag = storeAndFwdFlag;
    }

    public Date getPickupDatetime() {
        return pickupDatetime;
    }

    public void setPickupDatetime(Date pickupDatetime) {
        this.pickupDatetime = pickupDatetime;
    }

    public Date getDropoffDatetime() {
        return dropoffDatetime;
    }

    public void setDropoffDatetime(Date dropoffDatetime) {
        this.dropoffDatetime = dropoffDatetime;
    }

    public int getPassengerCount() {
        return passengerCount;
    }

    public void setPassengerCount(int passengerCount) {
        this.passengerCount = passengerCount;
    }

    public int getTripTimeInSecs() {
        return tripTimeInSecs;
    }

    public void setTripTimeInSecs(int tripTimeInSecs) {
        this.tripTimeInSecs = tripTimeInSecs;
    }

    public Double getTripDistance() {
        return tripDistance;
    }

    public void setTripDistance(Double tripDistance) {
        if (tripDistance != null) {
            this.tripDistance = tripDistance.doubleValue();
        } else {
            this.tripDistance = 0.0;
        }
//        this.tripDistance = tripDistance;
    }

    public Double getPickupLongitude() {
        return pickupLongitude;
    }

    public void setPickupLongitude(Double pickupLongitude) {
        if (pickupLongitude != null) {
            this.pickupLongitude = pickupLongitude.doubleValue();
        } else {
            this.pickupLongitude = 0.0;
        }
//        this.pickupLongitude = pickupLongitude;
    }

    public Double getPickupLatitude() {
        return pickupLatitude;
    }

    public void setPickupLatitude(Double pickupLatitude) {
        if (pickupLatitude != null) {
            this.pickupLatitude = pickupLatitude.doubleValue();
        } else {
            this.pickupLatitude = 0.0;
        }
//        this.pickupLatitude = pickupLatitude;
    }

    public Double getDropoffLongitude() {
        return dropoffLongitude;
    }

    public void setDropoffLongitude(Double dropoffLongitude) {
        if (dropoffLongitude != null) {
            this.dropoffLongitude = dropoffLongitude.doubleValue();
        } else {
            this.dropoffLongitude = 0.0;
        }
//        this.dropoffLongitude = dropoffLongitude;
    }

    public Double getDropoffLatitude() {
        if (dropoffLatitude != null) {
            return dropoffLatitude.doubleValue();
        } else {
            return 0.0;
        }
    }

    public void setDropoffLatitude(Double dropoffLatitude) {
        if (dropoffLatitude != null) {
            this.dropoffLatitude = dropoffLatitude.doubleValue();
        } else {
            this.dropoffLatitude = 0.0;
        }
    }

    @Override
    public String toString() {
        return "CabTripData{" +
                "cabTripId=" + cabTripId +
                ", medallion='" + medallion + '\'' +
                ", hackLicense='" + hackLicense + '\'' +
                ", vendorId='" + vendorId + '\'' +
                ", rateCode=" + rateCode +
                ", storeAndFwdFlag='" + storeAndFwdFlag + '\'' +
                ", pickupDatetime=" + pickupDatetime +
                ", dropoffDatetime=" + dropoffDatetime +
                ", passengerCount=" + passengerCount +
                ", tripTimeInSecs=" + tripTimeInSecs +
                ", tripDistance=" + tripDistance +
                ", pickupLongitude=" + pickupLongitude +
                ", pickupLatitude=" + pickupLatitude +
                ", dropoffLongitude=" + dropoffLongitude +
                ", dropoffLatitude=" + dropoffLatitude +
                '}';
    }
}




