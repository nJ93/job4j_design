INSERT INTO role (role_name) VALUES ('Administrator');
INSERT INTO role (role_name) VALUES ('User');

INSERT INTO users (username, role_id) VALUES ('TestUser', 1);
INSERT INTO users (username, role_id) VALUES ('TestUser2', 2);
INSERT INTO users (username, role_id) VALUES ('TestUser3', 2);
INSERT INTO users (username, role_id) VALUES ('TestUser4', 2);

INSERT INTO rules (rule_description) VALUES ('Create');
INSERT INTO rules (rule_description) VALUES ('Update');
INSERT INTO rules (rule_description) VALUES ('Delete');

INSERT INTO role_rules (role_id, rules_id) VALUES (1, 1);
INSERT INTO role_rules (role_id, rules_id) VALUES (1, 2);
INSERT INTO role_rules (role_id, rules_id) VALUES (1, 3);
INSERT INTO role_rules (role_id, rules_id) VALUES (2, 1);

INSERT INTO state (state_name) VALUES ('In proccess');
INSERT INTO state (state_name) VALUES ('Waiting');

INSERT INTO category (category) VALUES ('First category');
INSERT INTO category (category) VALUES ('Second category');

INSERT INTO item (item_description, user_id, state_id, category_id) VALUES ('Item', 2, 1, 1);
INSERT INTO item (item_description, user_id, state_id, category_id) VALUES ('Item', 3, 1, 2);
INSERT INTO item (item_description, user_id, state_id, category_id) VALUES ('Item', 4, 2, 1);

INSERT INTO comments (item_id, comment) VALUES (1, 'Comment1');
INSERT INTO comments (item_id, comment) VALUES (2, 'Comment2');
INSERT INTO comments (item_id, comment) VALUES (3, 'Comment3');

INSERT INTO attachs (item_id, attach) VALUES (1, (decode('013d7d16d7ad4fefb61bd95b765c8ceb', 'hex')));
INSERT INTO attachs (item_id, attach) VALUES (2, (decode('013d7d16d7ad4fefb61bd95b765c8ceb', 'hex')));
INSERT INTO attachs (item_id, attach) VALUES (3, (decode('013d7d16d7ad4fefb61bd95b765c8ceb', 'hex')));