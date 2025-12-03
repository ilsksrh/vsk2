-- admin / admin1234
INSERT INTO t_user (email, password)
VALUES ('admin@library.kz', '12$mVkHZ6gV1eS6AbY/pMo0VeSiA5tZ8yfuJDu5O4MhIJ3H/Tm4AiBba');

INSERT INTO t_user_permissions (user_id, permission_id)
SELECT 1, id FROM t_permission WHERE name = 'ROLE_ADMIN';

INSERT INTO t_user_permissions (user_id, permission_id)
SELECT 1, id FROM t_permission WHERE name = 'ROLE_USER';
