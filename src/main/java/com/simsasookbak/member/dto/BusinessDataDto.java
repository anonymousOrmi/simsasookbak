package com.simsasookbak.member.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class BusinessDataDto {
    public long b_no;
    public String b_stt; //납세자상태
    public String b_stt_cd; //상태코드 납세자상태(코드): 01: 계속사업자, 02: 휴업자, 03: 폐업자
    public String tax_type;
    public int tax_type_cd;
    public String end_dt;
    public String utcc_yn;
    public String tax_type_change_dt;
    public String invoice_apply_dt;
    public String rbf_tax_type;
    public int rbf_tax_type_cd;

    //http://www.flytodata.com/reference-business
}
