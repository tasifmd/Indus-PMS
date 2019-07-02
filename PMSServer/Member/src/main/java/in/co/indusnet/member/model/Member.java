package in.co.indusnet.member.model;

import java.time.LocalDate;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
public class Member {
	private String memberName;
	private String memberEmail;
	private String memberMobile;
	private String memberDesignation;
	private String memberAddress;
	private LocalDate createdTimeStamp;
	private LocalDate modidifiedTimeStamp;
	
}