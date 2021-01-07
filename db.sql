drop database if exists jspCommunity;
create database jspCommunity;
use jspCommunity;


create table `member`(
 id int(10) unsigned primary key not null auto_increment, 
    loginId char(50) not null unique, 
    loginPw varchar(200) NOT NULL, 
    `name` CHAR(50) NOT NULL,
    `nickName` CHAR(50) NOT NULL,
    `email` varchar(100) not null,
    regDate datetime not null,
    adminLevel tinyint(1) unsigned not null default 2 comment '0=관리자/1=로그인정지/2=신규회원/3=인증된회원/4=탈퇴' 
);

#회원 1 생성
insert into `member`
set loginId = "user1", 
loginPw = "user1",
`name` = "김민수",
`nickName` = "만수",
`email` = "nature103@naver.com",
regDate = now(); 

#회원 2 생성
INSERT INTO `member`
SET loginId = "user2", 
loginPw = "user2",
`name` = "박나리",
`nickName` = "나리나리",
`email` = "nature104@naver.com",
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
    boardCode CHAR(10) NOT NULL,    
    title CHAR(100) NOT NULL,
    `body` longTEXT NOT NULL,
    memberId INT(10) UNSIGNED NOT NULL,
    `count` INT(10) UNSIGNED NOT NULL DEFAULT '0',
    regDate DATETIME NOT NULL,
    updateDate DATETIME NOT NULL

);


# 공지사항 게시글 생성 
insert into article
set  boardId = 1,
boardCode= "notice",
title = '제목1',
`body` = '내용1',
memberId = 1,
regDate = now(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 1,
boardCode= "notice",
title = '제목2',
`body` = '내용2',
memberId = 1,
regDate = NOW(),
updateDate = NOW();


INSERT INTO article
SET  boardId = 1,
boardCode= "notice",
title = '제목3',
`body` = '내용3',
memberId = 2,
regDate = NOW(),
updateDate = NOW();

INSERT INTO article
SET  boardId = 1,
boardCode= "notice",
title = '제목4',
`body` = '내용4',
memberId = 2,
regDate = NOW(),
updateDate = NOW();


