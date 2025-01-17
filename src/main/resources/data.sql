insert into T_MOTORCYCLES (CHASSIS_NUMBER, MAKER, MODEL, DISPLACEMENT, STYLE) values ('123456789', 'KTM', 'Duke 200', '199.5', 'Naked');
insert into T_MOTORCYCLES (CHASSIS_NUMBER, MAKER, MODEL, DISPLACEMENT, STYLE) values ('456789123', 'Royal Enfield', 'Classic 350', '350', 'Standard');
insert into T_MOTORCYCLES (CHASSIS_NUMBER, MAKER, MODEL, DISPLACEMENT, STYLE) values ('789456123', 'KTM', 'Duke 390', '399', 'Naked');


insert into T_ROLES (NAME) values ('USER');
insert into T_ROLES (NAME) values ('ADMIN');
insert into T_ROLES (NAME) values ('SUPERADMIN');
-- user: USER1 PW: password123
insert into T_USERS (USERNAME, PASSWORD) values ('USER1', '{bcrypt}$2a$10$/EH8xDLlZzQwAaB4tBb70.ldP9kJfQIgWeyLtkqULQRYMDLfYPNjG');
-- user: ADMINUSER PW: USER321
insert into T_USERS (USERNAME, PASSWORD) values ('ADMINUSER', '{bcrypt}$2a$10$cWzX38emyxcLsi/lIQ9hs.8hY0ZhZOLiV.We8If2MQhLxZRYyb10e');
-- user: SA PW: BIGBOSS
insert into T_USERS (USERNAME, PASSWORD) values ('SA', '{bcrypt}$2a$10$aYuTiVr3M2Fq/Zs3lMAOU.ztkX7rJW4G.RdBxJVcqvxnDVIpzhmAq');


insert into T_USERS_ROLES (USER_ID, ROLE_ID) values (0, 0);
insert into T_USERS_ROLES (USER_ID, ROLE_ID) values (1, 0);
insert into T_USERS_ROLES (USER_ID, ROLE_ID) values (1, 1);
insert into T_USERS_ROLES (USER_ID, ROLE_ID) values (2, 2);






