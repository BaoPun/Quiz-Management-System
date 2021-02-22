import lorem
import random

class DB:
    output=[]
    names={}
    def getName(self):
        word=lorem.sentence().split()[0]
        if word in self.names:
            self.names[word] += 1
            word = word + '-' + str(self.names[word])
        else:
            self.names[word] = 1
        return word

    globalID = 10000
    def getID(self):
        self.globalID += 1
        return self.globalID-1

    admins={4:'alice'}
    teachers={}
    students={}
    questions={}
    answers={}

    def makeTeacher(self):
        name=self.getName()
        id=self.getID()
        passwordHash='/N4rLtula/QIYB+3If6bXDONEO5CnqBPrlURto+/j7k='
        newline="INSERT INTO USERS (USERID,USERNAME,PASSWORDHASH,TEACHER,ROLE) VALUES ({},'{}','{}',NULL,'TEACHER');".format(id,name,passwordHash)
        self.teachers[id]=name
        self.output.append(newline)
        return id
    
    def getTeacher(self):
        return random.choice(list(self.teachers.keys()))

    def getStudent(self):
        return random.choice(list(self.students.keys()))

    def makeStudent(self):
        name=self.getName()
        id=self.getID()
        teacher=self.getTeacher()
        passwordHash='/N4rLtula/QIYB+3If6bXDONEO5CnqBPrlURto+/j7k='
        newline="INSERT INTO USERS (USERID,USERNAME,PASSWORDHASH,TEACHER,ROLE) VALUES ({},'{}','{}',{},'STUDENT');".format(id,name,passwordHash,teacher)
        self.students[id]=name
        self.output.append(newline)
        return id
    
    completedQuizzes=set()
    def makeQuiz(self):
        quizid=self.getID()
        userid=self.getTeacher()
        name=self.getName()
        newline="INSERT INTO QUIZZES (QUIZID,USERID,NAME) VALUES ({},{},'{}');".format(quizid,userid,name)
        self.questions[quizid]=[]
        self.output.append(newline)
        self.makeQuestions(quizid)
        self.completedQuizzes=set()
        return quizid

    def makeQuestions(self,quizid):
        for i in range(random.randint(1,5)):
            self.makeQuestion(quizid,i)

    def makeQuestion(self,quizid,ordering):
        questionid=self.getID()
        self.questions[quizid].append(questionid)
        description=lorem.sentence()
        newline="INSERT INTO QUESTIONS (QUESTIONID,QUIZID,QUESTIONTYPE,DESCRIPTION,ORDERING) VALUES ({},{},'MULTIPLE_CHOICE','{}',{});".format(questionid,quizid,description,ordering)
        self.answers[questionid]=[]
        self.output.append(newline)
        self.makeAnswers(questionid)
    
    def makeAnswers(self,questionid):
        numAnswers=random.randint(2,4)
        correctAnswer=random.randint(0,numAnswers-1)
        for i in range(numAnswers):
            if i==correctAnswer:
                iscorrect=1
            else:
                iscorrect=0
            self.makeAnswer(questionid,iscorrect,i)
    
    def makeAnswer(self,questionid,iscorrect,ordering):
        answerid=self.getID()
        self.answers[questionid].append(answerid)
        answertext=lorem.sentence()
        newline="INSERT INTO ANSWERS (ANSWERID,ANSWERTEXT,QUESTIONID,ISCORRECT,ORDERING) VALUES ({},'{}',{},{},{});".format(answerid,answertext,questionid,iscorrect,ordering)
        self.answers[answerid]=iscorrect
        self.output.append(newline)

    def completeQuiz(self,quizid):
        userid=self.getStudent()
        if (userid in self.completedQuizzes):
            return
        self.completedQuizzes.add(userid)
        for questionid in self.questions[quizid]:
            if random.random()>0.5:
                print([i for (i,x) in enumerate(self.answers[questionid]) if x])
                answerix=[i for (i,x) in enumerate(self.answers[questionid]) if x][0]
            else:
                print(len(self.answers[questionid]))
                answerix=random.randint(0,len(self.answers[questionid])-1)
            print(answerix)
            print(self.answers[questionid])
            answerid=self.answers[questionid][answerix]
            progressid=self.getID()
            newline="INSERT INTO PROGRESS (PROGRESSID,USERID,ANSWERID) VALUES ({},{},{});".format(progressid,userid,answerid)
            self.output.append(newline)

# Insert into ADMIN.PROGRESS (PROGRESSID,USERID,ANSWERID) values (1000,9,1);
# Insert into ADMIN.ANSWERS (ANSWERID,ANSWERTEXT,QUESTIONID,ISCORRECT,ORDERING) values (1,'19',21,1,1);
#Insert into ADMIN.QUESTIONS (QUESTIONID,QUIZID,QUESTIONTYPE,DESCRIPTION,ORDERING) values (21,4,'Multiple Choice','What is 9 + 10?',1);
x=DB()

for i in range(3):
    x.makeTeacher()
for i in range(5):
    x.makeStudent()
for i in range(10):
    quizid=x.makeQuiz()
    x.completeQuiz(quizid)
    x.completeQuiz(quizid)
    x.completeQuiz(quizid)
print("\n".join(x.output))

with open('inserts.sql','w') as f:
    f.write("\n".join(x.output))

# Insert into ADMIN.ANSWERS (ANSWERID,ANSWERTEXT,QUESTIONID,ISCORRECT,ORDERING) values (1,'19',21,1,1);
# Insert into ADMIN.PROGRESS (PROGRESSID,USERID,ANSWERID) values (1000,9,1);
# Insert into ADMIN.QUIZZES (QUIZID,USERID,NAME) values (4,4,'Testing Quiz');
# Insert into ADMIN.PERMISSION (QUIZID,USERID-,NAME) values (4,4,'Testing Quiz');
# Insert into ADMIN.USERS (USERID,USERNAME,PASSWORDHASH,TEACHER,ROLE) values (9,'Bob','/N4rLtula/QIYB+3If6bXDONEO5CnqBPrlURto+/j7k=',8,'STUDENT');
# Insert into ADMIN.USERS (USERID,USERNAME,PASSWORDHASH,TEACHER,ROLE) values (8,'Test','/N4rLtula/QIYB+3If6bXDONEO5CnqBPrlURto+/j7k=',null,'TEACHER');

