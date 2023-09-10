insert into programmer (name,  yearsExperience, salary)
values ('John',  1, 25500.99),
 ('Tod', 2, 35000.99),
       ('Dema', 4, 55000.99),
       ('Tanjiro', 7, 75000.99),
       ('Son Goku', 10, 255000.99)
;

insert into SEC_User (userName, encryptedPassword, ENABLED)
values ('Jon', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 1),
       ('Tod', '$2a$10$PrI5Gk9L.tSZiW9FXhTS8O8Mz9E97k2FZbFvGFFaSsiTUIl.TCrFu', 2);







insert into sec_role (roleName)
values ('ROLE_BOSS');

insert into sec_role (roleName)
values ('ROLE_GUEST');



insert into user_role (userId, roleId)
values (1, 1),
(2, 2);

