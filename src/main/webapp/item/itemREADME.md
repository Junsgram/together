drop table items;

주문, 상품,장바구니 테이블 필요

주문 db
create table item_order(
	item_order_num 시퀀스, 유니크 
	item_num
	buyer_Id
	buyer_email
	buyer_name
	buyer_tel
	buyer_addr
	
장바구니db - 회원ID, num(123),title, price,quantity,총가격?
create table item_cart(
	

create table items (
    num number not null,
    id varchar2(30) not null, //USERID?
    title varchar2(300) not null,
    scontent varchar2(500),
    lcontent varchar2(500),
    price number not null,
    quantity number not null, // 추가됨.
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

create sequence book_com_seq
increment by 1
start with 1
minvalue 1
nomaxvalue
nocycle
nocache;

uploadProcess 물어볼것.

결제api관련
https://okky.kr/articles/854015
포트원
결제 개발 5단계
1.포트원 SDK라이브러리를 가져온다.
2.파라미터를 셋팅하고 결제창을 호출한다.
3.프론트 페이지로 결제결과를 수신합니다.
4.결제조회 api로 결제 결과를 검증합니다.
5.웹훅 수신하여 결제를 안정화 합니다.
