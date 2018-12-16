insert into user (username, password, role) values ('admin', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_ADMIN');
insert into user (username, password, role) values ('user', '$2a$04$YDiv9c./ytEGZQopFfExoOgGlJL6/o0er0K.hiGb5TGKHUL8Ebn..', 'ROLE_STUDENT');



insert into course(location,time,type,lecturer_id,subject_id) values ('00-823','08:00','LECTURE',null,null );
insert into subject(category,name) values ('PROGRAMMING','Java');