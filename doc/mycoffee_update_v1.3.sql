ALTER TABLE tbl_driver_work_history DROP CONSTRAINT fk_driver_work;
ALTER TABLE tbl_driver_permit_history DROP CONSTRAINT fk_driver_permit;
ALTER TABLE tbl_order DROP CONSTRAINT kf_order_uid;
ALTER TABLE tbl_order DROP CONSTRAINT kf_order_did;
ALTER TABLE tbl_order_history DROP CONSTRAINT fk_order_history_oid;
ALTER TABLE tbl_order_detail DROP CONSTRAINT fk_detail_pid;
