DROP DATABASE IF EXISTS db_dictionary;
CREATE DATABASE db_dictionary;

-- table admin
DROP TABLE IF EXISTS db_dictionary.admin;
CREATE TABLE db_dictionary.admin (
  id       INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  username VARCHAR(255),
  password VARCHAR(255)
)
  COMMENT '管理员表';

-- table word
DROP TABLE IF EXISTS db_dictionary.word;
CREATE TABLE db_dictionary.word (
  id             INT(11) UNSIGNED AUTO_INCREMENT PRIMARY KEY,
  english        VARCHAR(255) COMMENT '英文',
  chinese        VARCHAR(2048) COMMENT '中文',
  phonetic       VARCHAR(255) COMMENT '音标',
  part_of_speech VARCHAR(255) COMMENT '词性'
)
  COMMENT '单词表';

USE db_dictionary;

INSERT INTO db_dictionary.admin VALUES (NULL, 'admin', '123');

SELECT *
FROM db_dictionary.word;
