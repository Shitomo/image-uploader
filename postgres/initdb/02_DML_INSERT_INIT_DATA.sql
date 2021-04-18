INSERT INTO users (first_name, family_name, age) VALUES ('tomoaki', 'sikina', 25);
INSERT INTO users (first_name, family_name, age) VALUES ('kosuke', 'tomita', 25);

INSERT INTO surveys (name, show) VALUES
('アンケート1', false),
('アンケート2', true),
('アンケート3', false);

INSERT INTO parents (name) VALUES
('親1'),
('親2'),
('親3'),
('親4');

INSERT INTO children (parent_id, name) VALUES
(1, '子供1'),
(1, '子供2'),
(1, '子供3'),
(2, '子供4');

INSERT INTO targets (survey_id, parent_id, children_id) VALUES
(1, 1, 1),
(1, 1, 2),
(1, 1, 3);

INSERT INTO answers (survey_id, target_id, target_children_id, created_at) VALUES
(1, 1, 1, now()),
(1, 1, 2, now()),
(2, 2, 4, now()),
(1, 1, 2, now() + INTERVAL '1 hours'),
(1, 1, 2, now() + INTERVAL '2 hours');
