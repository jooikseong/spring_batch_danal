## 사용 툴
- Intellij IDEA
- DBeaver

## DB 접속
- 220.126.251.96:3306/mydb
- id : root / pw : test1357

## 실행
- 서버 기동 후 http://localhost:9090/load 접속

## 추가 사항
- 서울, 경기 건은 github 100MB 초과로 등록 안했습니다.

  확인이 필요하다면 /src/main/resources 폴더에 서울, 경기 csv 파일 추가 후 [SpringBatchConfig.java](src%2Fmain%2Fjava%2Fkr%2Fco%2Fdanal%2Fspring_batch_danal%2Fconfig%2FSpringBatchConfig.java)에 getResources 메소드에서 '서울, 경기' 주석 해제 요청 드립니다.  
- JPA를 사용하여 서버 기동하면 자동으로 테이블 생성이 되어 /src/main/resources/table_create.sql  테이블 생성 SQL은 사용할 일 없어보이지만 과제 필수 제출 사항이라 제출하였으니 참고 부탁드립니다. 
