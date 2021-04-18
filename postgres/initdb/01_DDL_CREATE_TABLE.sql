-- uuid使うための拡張機能を設定
CREATE EXTENSION IF NOT EXISTS "uuid-ossp";
CREATE SEQUENCE test_sequence;

CREATE TABLE users (
    id UUID DEFAULT uuid_generate_v4 () PRIMARY KEY,
    first_name VARCHAR(10) NOT NULL,
    family_name VARCHAR(10) NOT NULL,
    age Int NOT NULL
);

CREATE TABLE test (
    id BIGINT DEFAULT nextval('test_sequence'),
    user_id UUID,
    FOREIGN KEY (user_id)
        REFERENCES users(id)
);

CREATE TABLE surveys (
  id BIGSERIAL PRIMARY KEY,
  name VARCHAR,
  show BOOLEAN
);

CREATE TABLE parents (
      id BIGSERIAL PRIMARY KEY,
      name VARCHAR
);

CREATE TABLE children (
   id BIGSERIAL PRIMARY KEY,
   parent_id BIGINT,
   name VARCHAR,
   FOREIGN KEY (parent_id)
       REFERENCES parents(id)
);

CREATE TABLE targets (
    survey_id BIGINT,
    parent_id BIGINT,
    children_id BIGINT,
    FOREIGN KEY (survey_id)
        REFERENCES surveys(id),
    FOREIGN KEY (parent_id)
        REFERENCES parents(id),
    FOREIGN KEY (children_id)
        REFERENCES children(id),
    PRIMARY KEY (survey_id, parent_id, children_id)
);

CREATE TABLE answers (
    survey_id BIGINT,
    target_id BIGINT,
    target_children_id BIGINT,
    created_at TIMESTAMP,
    FOREIGN KEY (survey_id)
        REFERENCES surveys(id),
    FOREIGN KEY (target_id)
        REFERENCES parents(id),
    FOREIGN KEY (target_children_id)
        REFERENCES children(id),
    UNIQUE(survey_id, target_id, target_children_id, created_at)
)
