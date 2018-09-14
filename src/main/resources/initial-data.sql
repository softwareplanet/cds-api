set global sql_mode='STRICT_TRANS_TABLES,NO_ZERO_IN_DATE,NO_ZERO_DATE,ERROR_FOR_DIVISION_BY_ZERO,NO_AUTO_CREATE_USER,NO_ENGINE_SUBSTITUTION';
INSERT INTO cds.companies (id, name, website) VALUES (1, 'Interlink', 'www.interlink.com');

INSERT INTO cds.statuses (id, description, title, company_id) VALUES (1, '1', 'In the post office', 1);
INSERT INTO cds.statuses (id, description, title, company_id) VALUES (2, '2', 'Taken by courier', 1);
INSERT INTO cds.statuses (id, description, title, company_id) VALUES (3, '3', 'In delivery process', 1);
INSERT INTO cds.statuses (id, description, title, company_id) VALUES (4, '4', 'Delivered', 1);
INSERT INTO cds.statuses (id, description, title, company_id) VALUES (5, '2', 'Not Delivered', 1);

UPDATE cds.companies set default_status = 1 where companies.id=1;


INSERT INTO cds.users (id, eMail, enabled, firstName, lastName, password, role, username, company_id) VALUES (1, 'maksymto@gmail.com', 1, 'Maxim', 'Tokar', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, 'admin', 1);
INSERT INTO cds.users (id, eMail, enabled, firstName, lastName, password, role, username, company_id) VALUES (2, 'dimadima@gmail.com', 1, 'Dima', 'Zaharchenko', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, 'dimaza', 1);
INSERT INTO cds.users (id, eMail, enabled, firstName, lastName, password, role, username, company_id) VALUES (3, 'maxmax@gmail.com', 1, 'Maxim', 'Berezovsky', '$2a$08$lDnHPz7eUkSi6ao14Twuau08mzhWrL4kyZGGU5xfiGALO/Vxd5DOi', null, 'maxim', 1);

INSERT INTO cds.authority (id, name) VALUES (1, 'ROLE_USER');
INSERT INTO cds.authority (id, name) VALUES (2, 'ROLE_ADMIN');

INSERT INTO cds.user_authority (user_id, authority_id) VALUES (1, 1);
INSERT INTO cds.user_authority (user_id, authority_id) VALUES (1, 2);
INSERT INTO cds.user_authority (user_id, authority_id) VALUES (2, 1);
INSERT INTO cds.user_authority (user_id, authority_id) VALUES (3, 1);


INSERT INTO cds.status_transition (id, action, company_id, status_from, status_to) VALUES (5, 'Take from office', 1, 1, 2);
INSERT INTO cds.status_transition (id, action, company_id, status_from, status_to) VALUES (6, 'Mark as in process of delivering ', 1, 2, 3);
INSERT INTO cds.status_transition (id, action, company_id, status_from, status_to) VALUES (7, 'Mark as delivered', 1, 3, 4);
INSERT INTO cds.status_transition (id, action, company_id, status_from, status_to) VALUES (9, 'Return to post office', 1, 5, 1);


INSERT INTO cds.phones (id, number, type, company_id) VALUES (1, '+38 099 999 99', '', null);

INSERT INTO cds.jobs (id, createdDate, customerName, description, dueDate, eMail, location, title, assignee_id, company_id, status_id) VALUES (1, '2018-05-18 09:57:58', 'Jack Daniels', 'Jask asked us to deliver his wine from the post office', '2018-05-31 00:00:00', 'jack@mail.com', '14 Homenka St.', 'Deliver old wine to Jack', 2, 1, 4);
INSERT INTO cds.jobs (id, createdDate, customerName, description, dueDate, eMail, location, title, assignee_id, company_id, status_id) VALUES (2, '2018-05-18 12:52:53', 'Maxim Berezovskiy', 'Deliver Pizza for InterLink Interns', '2018-05-26 00:00:00', 'maksymbe@interlink-ua.com', 'Grushevskogo St., 19/3', 'Pizza ', 2, 1, 1);
INSERT INTO cds.jobs (id, createdDate, customerName, description, dueDate, eMail, location, title, assignee_id, company_id, status_id) VALUES (3, '2018-05-24 15:53:57', 'Maksym Tokar', 'Hard drives for students', '2018-05-29 00:00:00', 'maksymto@interlink-ua.com', 'Sedova St., 23', 'Hard drives for students', 2, 1, 1);
INSERT INTO cds.jobs (id, createdDate, customerName, description, dueDate, eMail, location, title, assignee_id, company_id, status_id) VALUES (4, '2018-05-24 15:55:34', 'Dmytro Zaharchenko', 'PC with Core i7', '2018-05-25 00:00:00', 'dmytroza@gmail.com', 'Krupskai St., 50', 'PC with Core i7', 2, 1, 1);

INSERT INTO cds.jobs_phones (job_id, phone_id) VALUES (1, 1);

INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (10, '2018-05-18 12:03:03', 49.4496, 32.0566, 1, 2, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (13, '2018-05-18 12:07:40', 49.4496, 32.0551, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (14, '2018-05-18 12:07:50', 49.4487, 32.054, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (15, '2018-05-18 12:07:53', 49.448, 32.053, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (16, '2018-05-18 12:07:56', 49.4472, 32.0542, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (17, '2018-05-18 12:07:58', 49.4467, 32.0552, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (18, '2018-05-18 12:07:59', 49.4461, 32.0562, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (19, '2018-05-18 12:08:01', 49.4446, 32.0583, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (20, '2018-05-18 12:08:03', 49.443, 32.0618, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (21, '2018-05-18 12:08:05', 49.442, 32.0636, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (22, '2018-05-18 12:08:06', 49.4414, 32.0646, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (23, '2018-05-18 12:08:07', 49.4419, 32.0653, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (24, '2018-05-18 12:08:09', 49.4423, 32.0659, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (25, '2018-05-18 12:08:10', 49.4416, 32.0665, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (26, '2018-05-18 12:08:18', 49.4416, 32.0665, 1, 3, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (27, '2018-05-18 12:08:57', 49.4389, 32.0691, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (28, '2018-05-18 12:09:19', 49.4383, 32.0703, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (29, '2018-05-18 12:09:22', 49.4375, 32.0701, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (30, '2018-05-18 12:09:23', 49.437, 32.0691, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (31, '2018-05-18 12:09:24', 49.4367, 32.0687, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (32, '2018-05-18 12:09:25', 49.4363, 32.0682, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (33, '2018-05-18 12:09:28', 49.4358, 32.0683, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (34, '2018-05-18 12:09:31', 49.4341, 32.0701, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (35, '2018-05-18 12:09:32', 49.4335, 32.0692, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (36, '2018-05-18 12:09:34', 49.4325, 32.068, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (37, '2018-05-18 12:09:35', 49.4321, 32.0674, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (38, '2018-05-18 12:09:37', 49.4328, 32.0659, null, null, 2);
INSERT INTO cds.location_point (id, date, latitude, longitude, job_id, status_id, user_id) VALUES (39, '2018-05-18 12:09:41', 49.4328, 32.0659, 1, 4, 2);

