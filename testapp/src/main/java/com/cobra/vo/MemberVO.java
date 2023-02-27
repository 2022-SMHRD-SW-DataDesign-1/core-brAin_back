package com.cobra.vo;

import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.NonNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class MemberVO {

	@NonNull
	private String userId;
	@NonNull
	private String userPass;
	private String userName;
	private String userPhon;
	private String userAddr1;
	private String userAddr2;
	private String userAddr3;
	private Date regiDate;
	private int verify; //  -- 회원가입시 메일 인증할 인증여부 컬럼 default 0 : 인증 안된 상태, default 9 : 관리자
	
	
}
