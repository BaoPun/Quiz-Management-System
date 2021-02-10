CREATE TABLE users(     id NUMBER PRIMARY KEY,
                        username VARCHAR2(255),
                        passwordhash VARCHAR2(255),
                        role VARCHAR2(255));

CREATE TABLE quizzes(   id NUMBER PRIMARY KEY,
                        userid NUMBER,
                        name VARCHAR2(255));
                        
CREATE TABLE questions( id NUMBER PRIMARY KEY,
                        quizid NUMBER,
                        questiontype VARCHAR2(255),
                        description VARCHAR2(255));
                        
CREATE TABLE answers(   id NUMBER PRIMARY KEY,
                        answertext VARCHAR2(255),
                        questionid NUMBER,
                        iscorrect NUMBER);
                        
CREATE TABLE progress(  id NUMBER PRIMARY KEY,
                        userid NUMBER,
                        answerid NUMBER);
                        
CREATE TABLE permissions(   id NUMBER PRIMARY KEY,
                            userid NUMBER,
                            quizid NUMBER,
                            relationship VARCHAR2(255));

ALTER TABLE quizzes ADD FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE questions ADD FOREIGN KEY (quizid) REFERENCES quizzes(id);
ALTER TABLE answers ADD FOREIGN KEY (questionid) REFERENCES questions(id);
ALTER TABLE progress ADD FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE progress ADD FOREIGN KEY (answerid) REFERENCES answers(id);

ALTER TABLE permissions ADD FOREIGN KEY (userid) REFERENCES users(id);
ALTER TABLE permissions ADD FOREIGN KEY (quizid) REFERENCES quizzes(id);