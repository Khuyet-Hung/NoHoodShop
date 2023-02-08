package com.nohood.duantotnghiep.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "ACCOUNT")
public class ACCOUNT {
    @Id
    private String USERNAME;
    private String FULLNAME;
    private String PASSWORD;
    private String EMAIL;
    private String PHONE;
    private boolean GENDER;
    private String PHOTO;
   
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<ADDRESS> addresses;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<COMMENT> comments;
    @JsonIgnore
    @OneToMany(mappedBy = "account", fetch = FetchType.EAGER)
    private List<ROLEACC> roleaccs;
    @JsonIgnore
    @OneToMany(mappedBy = "account") 
    private List<FAVORITE> favorites;
    @JsonIgnore
    @OneToMany(mappedBy = "account")
    private List<CART> carts;
	public ACCOUNT(String uSERNAME, String pASSWORD) {
		super();
		USERNAME = uSERNAME;
		PASSWORD = pASSWORD;
	}
     
}
