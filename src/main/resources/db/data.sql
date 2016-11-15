CREATE EXTENSION IF NOT EXISTS pgcrypto;
INSERT INTO bk_company (company_id, name, gov_id, phone_number, created_by, created_date, version) VALUES (nextval('bk_company_seq_id'), 'Banker Company', '0123-4560-7890', '3058096232', 'SQL', NOW(), 0);
INSERT INTO bk_user (user_id, first_name, last_name, username, gov_id, phone_number, password, active, company_id, created_by, created_date, version, role) VALUES (nextval('bk_user_seq_id'), 'Admin', 'Banker', 'admin', '0123-4560-7890', '3058096232', CRYPT('P@55word', GEN_SALT('bf')), true, 100, 'SQL', NOW(), 0, 'ADMIN');
