DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;


CREATE TABLE `member`(
 id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    loginId CHAR(50) NOT NULL UNIQUE, 
    loginPw VARCHAR(200) NOT NULL, 
    `name` CHAR(50) NOT NULL,
    `nickName` CHAR(50) NOT NULL,
    cellPhone CHAR(20) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=관리자/1=로그인정지/2=신규회원/3=인증된회원/4=탈퇴' 
);

#회원 1 생성
INSERT INTO `member`
SET loginId = "user1", 
loginPw = "user1",
`name` = "김민수",
`nickName` = "만수",
cellPhone ='01012344567',
`email` = "nature103@naver.com",
updateDate = NOW(),
regDate = NOW(); 

#회원 2 생성
INSERT INTO `member`
SET loginId = "user2", 
loginPw = "user2",
`name` = "박나리",
`nickName` = "나리나리",
`email` = "nature104@naver.com",
cellPhone ='01012348888',
regDate = NOW(),
updateDate = NOW();

#관리자 생성
INSERT INTO `member`
SET loginId = "adm", 
loginPw = "amd",
`name` = "관리자",
`nickName` = "관리자",
`email` = "nature105@naver.com",
cellPhone ='01012377777',
adminLevel='0',
updateDate = NOW(),
regDate = NOW();



CREATE TABLE board(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    memberId INT(10) UNSIGNED NOT NULL DEFAULT '1',
    category CHAR(10) NOT NULL UNIQUE,
    `code` CHAR(10) NOT NULL UNIQUE,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL
);


# 공지사항 게시판 생성 
INSERT INTO board
SET 
category = "공지사항",
`code` = "notice",
regDate = NOW(),
updateDate = NOW();

# 방명록 게시판 생성 
INSERT INTO board
SET 
category = "방명록",
`code` = "guestBook",
regDate = NOW(),
updateDate = NOW();

# 자유게시판 생성 
INSERT INTO board
SET 
category = "자유",
`code` = "free",
regDate = NOW(),
updateDate = NOW();

CREATE TABLE article(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    boardId INT(10) UNSIGNED NOT NULL,   
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `count` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL

);


# 게시글 생성 
INSERT INTO article
SET  boardId = 1,
title = '제목1',
`body` = '내용1',
memberId = 1,
regDate = NOW(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 1,
title = '제목2',
`body` = '내용2',
memberId = 1,
regDate = NOW(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 2,
title = '제목3',
`body` = '내용3',
memberId = 2,
regDate = NOW(),
updateDate = NOW();

INSERT INTO article
SET  boardId = 3,
title = '제목4',
`body` = '내용4',
memberId = 2,
regDate = NOW(),
updateDate = NOW();

#비밀번호 암호화 
UPDATE `member`
SET loginPw = SHA2(loginPw,256)  
WHERE id < 5;


#부가정보테이블
CREATE TABLE attr(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    relTypecode CHAR(20) NOT NULL,  #관련타입코드(member,article...)
    relId INT(10) UNSIGNED NOT NULL,  #관련데이터번호
    typeCode CHAR(30) NOT NULL,      
    type2Code CHAR(30) NOT NULL, 
    `value` TEXT NOT NULL, 
    regDate DATETIME NOT NULL, 
    updateDate DATETIME NOT NULL
); 


# attr 유니크 인덱스 걸기 
## 중복변수 생성금지
## 변수찾는 속도 최적화 
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`,`type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서 
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`,`type2Code`);

# attr에 만료날짜 추가 
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`; 


#추천 테이블
CREATE TABLE articleLikes(
     id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    articleId INT(10) UNSIGNED NOT NULL,   
    memberId INT(10) UNSIGNED NOT NULL,
    `point` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL
); 

DROP DATABASE IF EXISTS jspCommunity;
CREATE DATABASE jspCommunity;
USE jspCommunity;


CREATE TABLE `member`(
 id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT, 
    loginId CHAR(50) NOT NULL UNIQUE, 
    loginPw VARCHAR(200) NOT NULL, 
    `name` CHAR(50) NOT NULL,
    `nickName` CHAR(50) NOT NULL,
    cellPhone CHAR(20) NOT NULL,
    `email` VARCHAR(100) NOT NULL,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL,
    adminLevel TINYINT(1) UNSIGNED NOT NULL DEFAULT 2 COMMENT '0=관리자/1=로그인정지/2=신규회원/3=인증된회원/4=탈퇴' 
);

#회원 1 생성
INSERT INTO `member`
SET loginId = "user1", 
loginPw = "user1",
`name` = "김민수",
`nickName` = "만수",
cellPhone ='01012344567',
`email` = "nature103@naver.com",
updateDate = NOW(),
regDate = NOW(); 

#회원 2 생성
INSERT INTO `member`
SET loginId = "user2", 
loginPw = "user2",
`name` = "박나리",
`nickName` = "나리나리",
`email` = "nature104@naver.com",
cellPhone ='01012348888',
regDate = NOW(),
updateDate = NOW();

#관리자 생성
INSERT INTO `member`
SET loginId = "adm", 
loginPw = "amd",
`name` = "관리자",
`nickName` = "관리자",
`email` = "nature105@naver.com",
cellPhone ='01012377777',
adminLevel='0',
updateDate = NOW(),
regDate = NOW();



CREATE TABLE board(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    memberId INT(10) UNSIGNED NOT NULL DEFAULT '1',
    category CHAR(10) NOT NULL UNIQUE,
    `code` CHAR(10) NOT NULL UNIQUE,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL
);


# 공지사항 게시판 생성 
INSERT INTO board
SET 
category = "공지사항",
`code` = "notice",
regDate = NOW(),
updateDate = NOW();

# 방명록 게시판 생성 
INSERT INTO board
SET 
category = "방명록",
`code` = "guestBook",
regDate = NOW(),
updateDate = NOW();

# 자유게시판 생성 
INSERT INTO board
SET 
category = "자유",
`code` = "free",
regDate = NOW(),
updateDate = NOW();

CREATE TABLE article(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    boardId INT(10) UNSIGNED NOT NULL,   
    title CHAR(100) NOT NULL,
    `body` LONGTEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `count` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL

);


# 게시글 생성 
INSERT INTO article
SET  boardId = 1,
title = '제목1',
`body` = '내용1',
memberId = 1,
regDate = NOW(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 1,
title = '제목2',
`body` = '내용2',
memberId = 1,
regDate = NOW(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 2,
title = '제목3',
`body` = '내용3',
memberId = 2,
regDate = NOW(),
updateDate = NOW();

INSERT INTO article
SET  boardId = 3,
title = '제목4',
`body` = '내용4',
memberId = 2,
regDate = NOW(),
updateDate = NOW();

#비밀번호 암호화 
UPDATE `member`
SET loginPw = SHA2(loginPw,256)  
WHERE id < 5;


#부가정보테이블
CREATE TABLE attr(
    id INT(10) UNSIGNED NOT NULL PRIMARY KEY AUTO_INCREMENT,
    relTypecode CHAR(20) NOT NULL,  #관련타입코드(member,article...)
    relId INT(10) UNSIGNED NOT NULL,  #관련데이터번호
    typeCode CHAR(30) NOT NULL,      
    type2Code CHAR(30) NOT NULL, 
    `value` TEXT NOT NULL, 
    regDate DATETIME NOT NULL, 
    updateDate DATETIME NOT NULL
); 


# attr 유니크 인덱스 걸기 
## 중복변수 생성금지
## 변수찾는 속도 최적화 
ALTER TABLE `attr` ADD UNIQUE INDEX (`relTypeCode`, `relId`, `typeCode`,`type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서 
ALTER TABLE `attr` ADD INDEX (`relTypeCode`, `typeCode`,`type2Code`);

# attr에 만료날짜 추가 
ALTER TABLE `attr` ADD COLUMN `expireDate` DATETIME NULL AFTER `value`; 


#추천 테이블
CREATE TABLE articleLikes(
     id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    articleId INT(10) UNSIGNED NOT NULL,   
    memberId INT(10) UNSIGNED NOT NULL,
    `point` TINYINT(1) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL
); 

