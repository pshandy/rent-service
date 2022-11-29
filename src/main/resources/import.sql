INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('admin', 'a', 'b', 'c', '$2a$11$PhCtZ53fprhL0ircqxvAiOp2BA0AV3wYME0z0YsL7CWFqReb.qex6', '+79540415079')
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('user', 'a', 'b', 'c', '$2a$11$muRIk4wUxP/clptMdEg5kuEdg/6pRW7FxbI74sN2yqft7ARpZjgb6', '+79540415079')
INSERT INTO user_role VALUES (1, 1)
INSERT INTO user_role VALUES (2, 1)
INSERT INTO user_role VALUES (1, 2)

INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(1,5000,250,'Фудкорд',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(2,3000,50,'Остров',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(3,15000,450,'Фудкорд',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(4,10000,400,'Склад',false,false);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(5,8000,150,'Склад',false,false);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(6,6000,100,'Остров',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(7,5000,80,'Остров',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(8,20000,400,'Склад',false,false);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(9,25000,500,'Склад',false,false);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(10,20000,400,'Фудкорд',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(11,15000,350,'Фудкорд',false,true);
INSERT INTO Premise(Id,price,area,zone,is_occupied,internet_present) Values(12,10000,200,'Фудкорд',false,true);

INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 12, true, 1, 3, 1);
INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 6, true, 2, 6, 1);
INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 7, true, 3, 11, 1);