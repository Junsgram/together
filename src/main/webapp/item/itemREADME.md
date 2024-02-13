drop table items;

주문, 상품,장바구니 테이블 필요

주문 db
create table item_order(
	order_num num not null, 
	item_num num not null,
	buyer_Id varchar2(30) not null,
	buyer_email varchar2(50) not null,
	buyer_name //우리 이름을 안받네?
	buyer_tel number not null, //call1+call2+call3
	buyer_addr varchar2(100) not null
	);
	
장바구니db - 회원ID, num(123),title, price,quantity,총가격?
num값은 시퀀스로 하지않고 VALUES 
((SELECT MAX(cart_sequence) FROM cart_items) + 1, ...)"
으로 대체합니다. 
id는 로그인한 유저 값을 가져와야함.
create table item_cart(
	num number not null,
	id varchar2(30) not null,
	title varchar2(300) not null,
	price number not null,
	ofile varchar2(30) not null,
	order_quantity number not null
	);
	
	

create table items (
    num number not null,
    id varchar2(30) not null, 
    title varchar2(300) not null,
    scontent varchar2(500),
    lcontent varchar2(500),
    price number not null,
    quantity number not null,
    likes number  default 0,
    views number default 0,
    stars number default 0,
    ofile varchar2(200),
    regidate date default sysdate,
    primary key (num)
);

insert into items(num, id, title, scontent,lcontent, price, likes,views, stars,
ofile) values (0,'test', '팝니다테스트', '싸게 팔아요 ', '싸게팔아요 사주세요', 30000,
0,0,0,'사진');

commit;

select * from items;

create SEQUENCE seq_save_items
increment by 1
start with 1
minvalue 1
nomaxvalue
nocycle
nocache;

create table member (
    id varchar2(30) not null,
    pw1 varchar2(30) not null,
    pw2 varchar2(30) not null,
    email varchar2(50) not null,
    call1 number not null,
    call2 number not null,
    call3 number not null,
    address varchar2(100) not null,
    ofile varchar2(200),
    dogname varchar2(30),
    userRole varchar2(20) default 'USER',
    userPoint number default 0,
    birthday date not null,
    primary key (id)
); 
alter table member rename column address to zipcode;
alter table member add (addr1 varchar2(100));
alter table member add (addr2 varchar2(100));
ALTER TABLE member MODIFY call1 varchar2(10);
ALTER TABLE member MODIFY call2 varchar2(10);
ALTER TABLE member MODIFY call3 varchar2(10);

create table community (
    num number not null,
    title varchar2(100) not null,
    id varchar2(30) not null,
    address varchar2(100) not null,
    scontent varchar2(500),
    lcontent varchar2(500),
    likes number  default 0,
    views number default 0,
    stars number default 0,
    ofile varchar2(300),
    regidate date default sysdate,
    primary key (num)
);

&^^^^^^^^^^^^^^^^^^^^^^^^^^^^^ 
로그아웃시 session에 userId삭제해도 되는지?
//현재 itemlist에 해당 스크립트가 있어 itemlist페이지 집입시 작동함
	//향후 헤더나 index 페이지로 이동 필요함.

결제api관련
https://okky.kr/articles/854015
포트원
결제 개발 5단계
1.포트원 SDK라이브러리를 가져온다.
2.파라미터를 셋팅하고 결제창을 호출한다.
3.프론트 페이지로 결제결과를 수신합니다.
4.결제조회 api로 결제 결과를 검증합니다.
5.웹훅 수신하여 결제를 안정화 합니다.
