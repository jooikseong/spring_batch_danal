package kr.co.danal.spring_batch_danal.model;

import lombok.Getter;
import lombok.Setter;
import org.hibernate.annotations.Comment;
import org.springframework.data.geo.Point;

import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Date;

@Entity
@Getter@Setter
public class Store {
    @Id
    private String id;
    @Comment("상호명")
    private String tradeName;
    @Comment("지점명")
    private String branchName;
    @Comment("상권업종대분류코드")
    private String businessLargeCode;
    @Comment("상권업종대분류명")
    private String businessLargeName;
    @Comment("상권업종중분류코드")
    private String businessMiddleCode;
    @Comment("상권업종중분류명")
    private String businessMiddleName;
    @Comment("상권업종소분류코드")
    private String businessSmallCode;
    @Comment("상권업종소분류명")
    private String businessSmallName;
    @Comment("표준산업분류코드")
    private String standardCode;
    @Comment("표준산업분류명")
    private String standardName;
    @Comment("시도코드")
    private String cityCode;
    @Comment("시도명")
    private String cityName;
    @Comment("시군구코드")
    private String addressCode;
    @Comment("시군구명")
    private String addressName;
    @Comment("행정동코드")
    private String administrativeDongCode;
    @Comment("행정동명")
    private String administrativeDongName;
    @Comment("법정동코드")
    private String legalOperationCode;
    @Comment("법정동명")
    private String legalOperationName;
    @Comment("지번코드")
    private String numberCode;
    @Comment("대지구분코드")
    private String landCode;
    @Comment("대지구분명")
    private String landName;
    @Comment("지번본번지")
    private String localMainNumber;
    @Comment("지번부번지")
    private String localSubNumber;
    @Comment("지번주소")
    private String localAddress;
    @Comment("도로명코드")
    private String roadNameCode;
    @Comment("도로명")
    private String roadName;
    @Comment("건물본번지")
    private String buildMainNumber;
    @Comment("건물부번지")
    private String buildSubNumber;
    @Comment("건물관리번호")
    private String buildMgmtNumber;
    @Comment("건물명")
    private String buildMame;
    @Comment("도로명주소")
    private String roadNameAddress;
    @Comment("구우편번호")
    private String oldPostalCode;
    @Comment("신우편번호")
    private String newPostalCode;
    @Comment("동정보")
    private String dong;
    @Comment("층정보")
    private String floor;
    @Comment("호정보")
    private String ho;
    @Comment("경도")
    private String longitude;
    @Comment("위도")
    private String latitude;
}
