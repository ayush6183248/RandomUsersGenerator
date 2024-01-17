package com.nagarro.randomuserprofilefetcher.entities;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

@Entity
@Table(name = "random_users_info")
public class RandomUsersInfo {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "user_id")
    private int userId;
    
    private String name;

    @Temporal(TemporalType.DATE)
    private Date dob;  

    @Column(nullable = false)
    private String gender;
    @Column(nullable = false)
    private int age;
    @Column(nullable = false)
    private String nationality;
    
    @Column(name = "verification_status", nullable = false)
    private String verificationStatus;

    @CreationTimestamp
    @Column(name = "date_created")
    private Timestamp dateCreated;  // To store the creation time.

    @UpdateTimestamp
    @Column(name = "date_modified")
    private Timestamp dateModified; // To store the latest update time.

    public RandomUsersInfo() {
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
		SimpleDateFormat outputFormat = new SimpleDateFormat("dd MMMM yyyy");
		return (this.dob!=null) ? outputFormat.format(this.dob) : null;

    }

    public void setDob(Date dob) {
        this.dob = dob;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getNationality() {
        return nationality;
    }

    public void setNationality(String nationality) {
        this.nationality = nationality;
    }

    public String getVerificationStatus() {
        return verificationStatus;
    }

    public void setVerificationStatus(String verificationStatus) {
        this.verificationStatus = verificationStatus;
    }

//    public Timestamp getDateCreated() {
//        return dateCreated;
//    }
//
//    public Timestamp getDateModified() {
//        return dateModified;
//    }
}