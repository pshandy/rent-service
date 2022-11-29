INSERT INTO role (name) VALUES ('ROLE_USER');
INSERT INTO role (name) VALUES ('ROLE_ADMIN');
INSERT INTO role (name) VALUES ('ROLE_MODER');
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('admin', 'a', 'b', 'c', '$2a$11$PhCtZ53fprhL0ircqxvAiOp2BA0AV3wYME0z0YsL7CWFqReb.qex6', '+79540415079');
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('user', 'a', 'b', 'c', '$2a$11$muRIk4wUxP/clptMdEg5kuEdg/6pRW7FxbI74sN2yqft7ARpZjgb6', '+79548415059');
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('AA', 'AA', 'BB', 'CC', '$2a$11$muRIk4wUxP/clptMdEg5kuEdg/6pRW7FxbI74sN2yqft7ARpZjgb6', '+79580855079');
INSERT INTO users (email, first_name, last_name, middle_name, password, phone_number) VALUES ('BB', 'CC', 'DD', 'FF', '$2a$11$muRIk4wUxP/clptMdEg5kuEdg/6pRW7FxbI74sN2yqft7ARpZjgb6', '+79588585970');
INSERT INTO user_role VALUES (1, 1);
INSERT INTO user_role VALUES (2, 1);
INSERT INTO user_role VALUES (1, 2);

INSERT INTO Premise(price,area,zone,is_occupied,internet_present,description) Values(5000,250,'Фудкорд',false,true,'Просторное помещение подойдёт под фастфуд');
INSERT INTO Premise(price,area,zone,is_occupied,internet_present,description) Values(3000,50,'Остров',false,true,'доступное помещение рядом с эскалатором');
INSERT INTO Premise(price,area,zone,is_occupied,internet_present,description) Values(15000,450,'Фудкорд',false,true,'Просторное помещение с большим залом');
INSERT INTO Premise(price,area,zone,is_occupied,internet_present,description) Values(10000,400,'Склад',false,false,'Большой Склад');
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(8000,150,'Склад',false,false);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(6000,100,'Остров',false,true);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(5000,80,'Остров',false,true);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(20000,400,'Склад',false,false);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(25000,500,'Склад',false,false);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(20000,400,'Фудкорд',false,true);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(15000,350,'Фудкорд',false,true);
INSERT INTO Premise(price,area,zone,is_occupied,internet_present) Values(10000,200,'Фудкорд',false,true);

INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (1,'Прилавок',1,1,false,true,false,15);
INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (1,'Склад',2,1,true,true,true,10);
INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (1,'Зал',3,1,false,false,false,5);

INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (2,'Прилавок',4,2,false,true,false,15);

INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (3,'Прилавок',5,1,false,true,false,20);
INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (3,'Склад',6,1,true,true,true,15);
INSERT INTO Room(Premise_Id,room_Type,number,floor_Number,unloading_Present,service_Exit_Present,service_Room_Present,number_Of_Outlets) VALUES (3,'Зал',7,1,false,false,false,10);

INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 12, true, 9000, 12000, 3);
INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 12, true, 9000, 12000, 4);
INSERT INTO wish (description, desired_period, is_additional_services_required, lower_budget_limit, upper_budget_limit, user_id) VALUES ('desc', 6, true, 2, 6, 1);

INSERT INTO Contract (start_Date,end_Date,is_Active,Premise_Id,user_id) VALUES ('2020-02-20','2021-02-20',false,4,3);
INSERT INTO Contract (start_Date,end_Date,is_Active,Premise_Id,user_id) VALUES ('2021-02-21','2021-06-28',false,4,3);
INSERT INTO Contract (start_Date,end_Date,is_Active,Premise_Id,user_id) VALUES ('2021-06-29','2022-06-28',false,4,4);

INSERT INTO Additional_Contract(cleaning_Required,sys_Admin_Required,electrician_Required,loader_Required,user_id) VALUES(true,false,true,true,3);

INSERT INTO Additional_Contract(cleaning_Required,sys_Admin_Required,electrician_Required,loader_Required,user_id) VALUES(true,false,true,true,4);