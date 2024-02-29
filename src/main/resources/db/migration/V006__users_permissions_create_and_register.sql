-- CREATE USER_PERMISSIONS TABLE
CREATE TABLE user_permissions (
	user_id BIGINT(20) NOT NULL,
	permission_id BIGINT(20) NOT NULL,
	PRIMARY KEY (user_id, permission_id),
	FOREIGN KEY (user_id) REFERENCES users(id),
	FOREIGN KEY (permission_id) REFERENCES permissions(id)
) ENGINE=InnoDB DEFAULT CHARSET=utf8;


-- VINCULANDO PERMISSÕES
-- admin
INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 1);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 2);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 3);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 4);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 5);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 6);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 7);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 8);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 9);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 10);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 11);

INSERT INTO user_permissions (user_id, permission_id) 
	values (1, 12);

-- maria
INSERT INTO user_permissions (user_id, permission_id) 
	values (2, 4);

INSERT INTO user_permissions (user_id, permission_id) 
	values (2, 8);

INSERT INTO user_permissions (user_id, permission_id) 
	values (2, 12);
