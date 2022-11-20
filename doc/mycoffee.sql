
DROP TRIGGER trg_driver_update_work;
DROP TRIGGER trg_driver_update_permit;
DROP TRIGGER trg_order_update;
DROP TABLE tbl_order_history;
DROP TABLE tbl_order_detail;
DROP TABLE tbl_order;
DROP TABLE tbl_codes;
DROP TABLE tbl_driver_history;
DROP TABLE tbl_driver_permit_history;
DROP TABLE tbl_driver_work_history;
DROP TABLE tbl_driver;
DROP TABLE tbl_product;
DROP TABLE tbl_product_category;
DROP TABLE tbl_user;
DROP TABLE tbl_cafe;

-- 카페 정보(하드코딩하거나 여기에 추가하거나 선택)
CREATE TABLE tbl_cafe (
  cafeid NUMBER(6) PRIMARY KEY,
  cafename VARCHAR2(50) NOT NULL,
  cafeopen NUMBER(1) DEFAULT 0 NOT NULL, -- 0:opened, 1:closed
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL
);
INSERT INTO tbl_cafe (cafeid, cafename) VALUES (1, 'MyCaffee');

-- 고객
CREATE TABLE tbl_user (
  userid VARCHAR2(50) PRIMARY KEY,
  password VARCHAR2(50) NOT NULL,
  name VARCHAR2(10) NOT NULL,
  auth NUMBER(1) DEFAULT 1 NOT NULL, --0:admin, 1:user
  address VARCHAR2(200) NOT NULL,
  mobile CHAR(13) NOT NULL,
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL
);

-- 제품 정보
CREATE TABLE tbl_product_category (
  pcategory VARCHAR2(10) PRIMARY KEY,
  pname VARCHAR2(50) NOT NULL,
  ptype NUMBER(1) NOT NULL, -- 0:음료, 1:음식
  description VARCHAR2(200),
  calorie NUMBER(4) DEFAULT 0 NOT NULL,
  fat NUMBER(4) DEFAULT 0 NOT NULL,
  sugars NUMBER(4) DEFAULT 0 NOT NULL,
  sodium NUMBER(4) DEFAULT 0 NOT NULL,
  caffeine NUMBER(4) DEFAULT 0 NOT NULL,
  protein NUMBER(4) DEFAULT 0 NOT NULL,
  imagefile VARCHAR2(100),
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL
);

-- 제품 설정
CREATE TABLE tbl_product (
  pid VARCHAR2(10) UNIQUE NOT NULL,
  pcategory VARCHAR2(10) NOT NULL,
  temperature NUMBER(1) NOT NULL, --0:아이스, 1:핫
  capacity NUMBER(4) NOT NULL,
  price NUMBER(10) NOT NULL,
  onsale NUMBER(1) DEFAULT 0 NOT NULL, -- 0:준비중, 1:판매중
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT pk_product PRIMARY KEY (pcategory, temperature, capacity),
  CONSTRAINT fk_product_category FOREIGN KEY(pcategory) REFERENCES tbl_product_category(pcategory)
);

-- 배달원
CREATE TABLE tbl_driver (
  did VARCHAR2(50) PRIMARY KEY,
  password VARCHAR2(50) NOT NULL,
  name VARCHAR2(10) NOT NULL,
  auth NUMBER(1) DEFAULT 0 NOT NULL, --0:배달관리자, 1:배달원
  mobile CHAR(13) NOT NULL,
  onwork NUMBER(1) DEFAULT 0 NOT NULL, --0:offline, 1:online
  permitted NUMBER(1) DEFAULT 0 NOT NULL, --0:미승인, 1:승인, 2:승인거부
  reason VARCHAR2(200),
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL
);

-- 배달원 대기 이력
CREATE TABLE tbl_driver_work_history (
  did VARCHAR2(50) NOT NULL,
  onwork NUMBER(1) NOT NULL,
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT pk_driver_work_history PRIMARY KEY (did, registdate),
  CONSTRAINT fk_driver_work FOREIGN KEY(did) REFERENCES tbl_driver(did)
);

-- 배달원 승인 이력
CREATE TABLE tbl_driver_permit_history (
  did VARCHAR2(50) NOT NULL,
  permitted NUMBER(1) NOT NULL,
  reason VARCHAR2(200) NOT NULL,
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT pk_driver_permit_history PRIMARY KEY (did, registdate),
  CONSTRAINT fk_driver_permit FOREIGN KEY(did) REFERENCES tbl_driver(did)
);

-- 주문 상태 마스터
CREATE TABLE tbl_codes (
  code NUMBER(4) NOT NULL,
  type VARCHAR2(10) NOT NULL,
  disp VARCHAR2(50) NOT NULL,
  CONSTRAINT pk_codes PRIMARY KEY (code, type),
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL
);
INSERT INTO tbl_codes (code, type, disp) VALUES (0, 'ORDER', '주문 작성');
INSERT INTO tbl_codes (code, type, disp) VALUES (1, 'ORDER', '주문 완료');
INSERT INTO tbl_codes (code, type, disp) VALUES (2, 'ORDER', '주문 접수');
INSERT INTO tbl_codes (code, type, disp) VALUES (3, 'ORDER', '배달 의뢰');
INSERT INTO tbl_codes (code, type, disp) VALUES (4, 'ORDER', '배달 접수');
INSERT INTO tbl_codes (code, type, disp) VALUES (5, 'ORDER', '배달 완료');

-- 주문
CREATE TABLE tbl_order (
  oid VARCHAR2(17) PRIMARY KEY, -- yyyyMMddHHmmssSSS(java)
  userid VARCHAR2(50) NOT NULL,
  did VARCHAR2(50),
  totalprice NUMBER(10) NOT NULL,
  status NUMBER(1) DEFAULT 0 NOT NULL, 
  orderdate DATE, -- 주문 완료 시간
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT kf_order_uid FOREIGN KEY(userid) REFERENCES tbl_user(userid),
  CONSTRAINT kf_order_did FOREIGN KEY(did) REFERENCES tbl_driver(did)
);

-- 주문 상태 이력
CREATE TABLE tbl_order_history (
  oid VARCHAR2(17) NOT NULL,
  status NUMBER(1) NOT NULL, 
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT pk_order_history PRIMARY KEY (oid, registdate),
  CONSTRAINT fk_order_history_oid FOREIGN KEY(oid) REFERENCES tbl_order(oid)
);

-- 주문 상세
CREATE TABLE tbl_order_detail (
  oid VARCHAR2(17) NOT NULL,
  pid VARCHAR2(10) NOT NULL,
  pieces NUMBER(6) NOT NULL,
  price NUMBER(10) NOT NULL,
  registdate DATE DEFAULT SYSDATE NOT NULL,
  updatedate DATE DEFAULT SYSDATE NOT NULL,
  CONSTRAINT pk_order_detail PRIMARY KEY (oid, pid),
  CONSTRAINT fk_detail_oid FOREIGN KEY(oid) REFERENCES tbl_order(oid),
  CONSTRAINT fk_detail_pid FOREIGN KEY(pid) REFERENCES tbl_product(pid)
);

-- INDEX
CREATE INDEX idx_product_ptype ON tbl_product_category(ptype);
CREATE INDEX idx_product_pcategory ON tbl_product(pcategory);

/*
-- 트리거는 별도의 워크시트에서 하나씩 실시
-- SQL Developrer는 한번에 하면 오류남

-- 주문 상태 이력
create or replace TRIGGER trg_order_update
AFTER UPDATE OR INSERT OF status ON tbl_order
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
BEGIN
  INSERT INTO tbl_order_history (oid, status) VALUES (:NEW.oid, :NEW.status);
END;

-- 배달원 대기 이력
create or replace TRIGGER trg_driver_update_work
AFTER UPDATE OR INSERT OF onwork ON tbl_driver
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
BEGIN
  INSERT INTO tbl_driver_work_history (did, onwork) VALUES (:NEW.did,  :NEW.onwork);
END;

-- 배달원 승인 이력
create or replace TRIGGER trg_driver_update_permit
AFTER UPDATE OR INSERT OF permitted ON tbl_driver
REFERENCING NEW AS NEW OLD AS OLD
FOR EACH ROW
BEGIN
  INSERT INTO tbl_driver_permit_history (did, permitted, reason) VALUES (:NEW.did, :NEW.permitted, :NEW.reason);
END;


*/