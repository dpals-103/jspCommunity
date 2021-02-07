drop database if exists jspCommunity;
create database jspCommunity;
use jspCommunity;


create table `member`(
 id int(10) unsigned primary key not null auto_increment, 
    loginId char(50) not null unique, 
    loginPw varchar(200) NOT NULL, 
    `name` CHAR(50) NOT NULL,
    `nickName` CHAR(50) NOT NULL,
    cellPhone char(20) not null,
    `email` varchar(100) not null,
    regDate datetime not null,
    updateDate DATETIME NOT NULL,
    adminLevel tinyint(1) unsigned not null default 2 comment '0=관리자/1=로그인정지/2=신규회원/3=인증된회원/4=탈퇴' 
);

#회원 1 생성
insert into `member`
set loginId = "user1", 
loginPw = "user1",
`name` = "김민수",
`nickName` = "만수",
cellPhone ='01012344567',
`email` = "nature103@naver.com",
updateDate = NOW(),
regDate = now(); 

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



create table board(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    memberId INT(10) UNSIGNED NOT NULL DEFAULT '1',
    category CHAR(10) NOT NULL unique,
    `code` CHAR(10) NOT NULL UNIQUE,
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL
);


# 공지사항 게시판 생성 
insert into board
set 
category = "공지사항",
`code` = "notice",
regDate = now(),
updateDate = now();

# 방명록 게시판 생성 
insert into board
set 
category = "방명록",
`code` = "guestBook",
regDate = now(),
updateDate = now();

# 자유게시판 생성 
insert into board
set 
category = "자유",
`code` = "free",
regDate = now(),
updateDate = now();

create table article(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    boardId INT(10) UNSIGNED NOT NULL,   
    title CHAR(100) NOT NULL,
    `body` longTEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `count` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL

);


# 게시글 생성 
insert into article
set  boardId = 1,
title = '제목1',
`body` = '내용1',
memberId = 1,
regDate = now(),
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
update `member`
set loginPw = SHA2(loginPw,256)  
where id < 5;


#부가정보테이블
create table attr(
    id int(10) unsigned not null primary key auto_increment,
    relTypecode char(20) not null,  #관련타입코드(member,article...)
    relId int(10) unsigned not null,  #관련데이터번호
    typeCode char(30) not null,      
    type2Code char(30) not null, 
    `value` text not null, 
    regDate datetime not null, 
    updateDate DATETIME NOT NULL
); 


# attr 유니크 인덱스 걸기 
## 중복변수 생성금지
## 변수찾는 속도 최적화 
alter table `attr` add unique index (`relTypeCode`, `relId`, `typeCode`,`type2Code`);

## 특정 조건을 만족하는 회원 또는 게시물(기타 데이터)를 빠르게 찾기 위해서 
alter table `attr` add index (`relTypeCode`, `typeCode`,`type2Code`);

# attr에 만료날짜 추가 
alter table `attr` add column `expireDate` datetime null after `value`; 


#추천 테이블
create table `like`(
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    articleId INT(10) UNSIGNED NOT NULL,   
    memberId INT(10) UNSIGNED NOT NULL,
    relTypeCode char(20) not null,
    `point` smallint(1) unsigned not null default '0',
    regDate DATETIME NOT NULL
); 

ALTER TABLE `jspCommunity`.`like` ADD INDEX (`relTypeCode`, `memberId`); 

#댓글 테이블 
create table reply (
    id INT(10) UNSIGNED PRIMARY KEY NOT NULL AUTO_INCREMENT,
    relTypeCode char(30) not null, 
    relId int(10) unsigned not null,
    memberId int(10) unsigned not null,
    articleId INT(10) UNSIGNED NOT NULL,
    `body` text not null,
    regDate DATETIME NOT NULL,  
    updateDate datetime not null  
);
    
ALTER TABLE `jspCommunity`.`reply` ADD INDEX (`relTypeCode`, `relId`);

    