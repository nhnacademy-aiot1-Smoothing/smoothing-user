insert into users(user_id, user_password,user_name,user_email,user_state,user_point,last_access) values ('haha', '1234', 'test', 'test@test.com', 'APPROVED', 100, null);
insert into roles(role_id, role_info) values (1, 'ROLE_ADMIN');
insert into user_roles(user_role_id,user_id, role_id) values (1,'haha', 1);

insert into users(user_id, user_password,user_name,user_email,user_state,user_point,last_access) values ('haha2', '1234', 'test', 'test2@test.com', 'APPROVED', 100, null);
insert into roles(role_id, role_info) values (2, 'ROLE_USER');
insert into user_roles(user_role_id,user_id, role_id) values (2,'haha2', 2);


insert into users(user_id, user_password,user_name,user_email,user_state,user_point,last_access) values ('haha3', '1234', 'test', 'test3@test.com', 'NOT_APPROVED', 100, null);