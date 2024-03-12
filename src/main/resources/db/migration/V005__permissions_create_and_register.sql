-- CREATE PERMISSIONS TABLE
CREATE TABLE permissions (
	id BIGINT(20) PRIMARY KEY AUTO_INCREMENT,
	description VARCHAR(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- INSERT PERMISSIONS RULE FOR CATEGORIES
INSERT INTO permissions (description) 
	values ('ROLE_CREATE_CATEGORY');

INSERT INTO permissions (description) 
	values ('ROLE_UPDATE_CATEGORY');

INSERT INTO permissions (description) 
	values ('ROLE_DELETE_CATEGORY');

INSERT INTO permissions (description) 
	values ('ROLE_SEARCH_CATEGORY');


-- INSERT PERMISSIONS RULE FOR PEOPLES
INSERT INTO permissions (description) 
	values ('ROLE_CREATE_PERSON');

INSERT INTO permissions (description) 
	values ('ROLE_UPDATE_PERSON');

INSERT INTO permissions (description) 
	values ('ROLE_DELETE_PERSON');

INSERT INTO permissions (description) 
	values ('ROLE_SEARCH_PERSON');


-- INSERT PERMISSIONS RULE FOR RELEASES
INSERT INTO permissions (description) 
	values ('ROLE_CREATE_RELEASE');

INSERT INTO permissions (description) 
	values ( 'ROLE_UPDATE_RELEASE');

INSERT INTO permissions (description) 
	values ( 'ROLE_DELETE_RELEASE');

INSERT INTO permissions (description) 
	values ( 'ROLE_SEARCH_RELEASE');


-- INSERT PERMISSIONS RULE FOR USERS
INSERT INTO permissions (description) 
	values ('ROLE_CREATE_USER');

INSERT INTO permissions (description) 
	values ('ROLE_UPDATE_USER');

INSERT INTO permissions (description) 
	values ('ROLE_DELETE_USER');

INSERT INTO permissions (description) 
	values ('ROLE_SEARCH_USER');